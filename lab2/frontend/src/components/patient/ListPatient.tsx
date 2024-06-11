import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './Patient.css';

const ListPatient: React.FC = () => {
  const [patients, setPatients] = useState([]);
  const history = useHistory();

  useEffect(() => {
    axios.get('/patients').then(response => {
      setPatients(response.data);
    });
  }, []);

  const editPatient = (id: number) => {
    history.push(`/patients/edit/${id}`);
  };

  const deletePatient = (id: number) => {
    axios.delete(`/patients/${id}`).then(() => {
      setPatients(patients.filter((patient: any) => patient.id !== id));
    });
  };

  const createPatient = () => {
    history.push('/patients/create');
  };

  return (
    <div className="patient-list">
      <h2>Список Пацієнтів</h2>
      <ul>
        {patients.map((patient: any) => (
          <li key={patient.id}>
            {patient.name} - {patient.date_of_birth}
            <button onClick={() => editPatient(patient.id)}>Редагувати</button>
            <button onClick={() => deletePatient(patient.id)}>Видалити</button>
          </li>
        ))}
      </ul>
      <button onClick={createPatient}>Додати Пацієнта</button>
    </div>
  );
};

export default ListPatient;
