import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory, useParams } from 'react-router-dom';
import './Patient.css';

const EditPatient: React.FC = () => {
  const [patient, setPatient] = useState({ name: '', date_of_birth: '', address: '', phone_number: '' });
  const history = useHistory();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    axios.get(`/patients/${id}`).then(response => {
      setPatient(response.data);
    });
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setPatient({ ...patient, [e.target.name]: e.target.value });
  };

  const updatePatient = (e: React.FormEvent) => {
    e.preventDefault();
    axios.put(`/patients/${id}`, patient).then(() => {
      history.push('/patients');
    });
  };

  return (
    <div className="patient-form">
      <h2>Редагувати Пацієнта</h2>
      <form onSubmit={updatePatient}>
        <div>
          <label htmlFor="name">Ім'я</label>
          <input type="text" name="name" value={patient.name} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="date_of_birth">Дата народження</label>
          <input type="date" name="date_of_birth" value={patient.date_of_birth} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="address">Адреса</label>
          <input type="text" name="address" value={patient.address} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="phone_number">Телефон</label>
          <input type="text" name="phone_number" value={patient.phone_number} onChange={handleChange} required />
        </div>
        <button type="submit">Зберегти</button>
      </form>
    </div>
  );
};

export default EditPatient;
