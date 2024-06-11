import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './Doctor.css';

const ListDoctor: React.FC = () => {
  const [doctors, setDoctors] = useState([]);
  const history = useHistory();

  useEffect(() => {
    axios.get('/doctors').then(response => {
      setDoctors(response.data);
    });
  }, []);

  const editDoctor = (id: number) => {
    history.push(`/doctors/edit/${id}`);
  };

  const deleteDoctor = (id: number) => {
    axios.delete(`/doctors/${id}`).then(() => {
      setDoctors(doctors.filter((doctor: any) => doctor.id !== id));
    });
  };

  const createDoctor = () => {
    history.push('/doctors/create');
  };

  return (
    <div className="doctor-list">
      <h2>Список Лікарів</h2>
      <ul>
        {doctors.map((doctor: any) => (
          <li key={doctor.id}>
            {doctor.name} - {doctor.specialty}
            <button onClick={() => editDoctor(doctor.id)}>Редагувати</button>
            <button onClick={() => deleteDoctor(doctor.id)}>Видалити</button>
          </li>
        ))}
      </ul>
      <button onClick={createDoctor}>Додати Лікаря</button>
    </div>
  );
};

export default ListDoctor;
