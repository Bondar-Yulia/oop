import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './Doctor.css';

const CreateDoctor: React.FC = () => {
  const [doctor, setDoctor] = useState({ name: '', specialty: '' });
  const history = useHistory();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDoctor({ ...doctor, [e.target.name]: e.target.value });
  };

  const createDoctor = (e: React.FormEvent) => {
    e.preventDefault();
    axios.post('/doctors', doctor).then(() => {
      history.push('/doctors');
    });
  };

  return (
    <div className="doctor-form">
      <h2>Створити Лікаря</h2>
      <form onSubmit={createDoctor}>
        <div>
          <label htmlFor="name">Ім'я</label>
          <input type="text" name="name" value={doctor.name} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="specialty">Спеціалізація</label>
          <input type="text" name="specialty" value={doctor.specialty} onChange={handleChange} required />
        </div>
        <button type="submit">Створити</button>
      </form>
    </div>
  );
};

export default CreateDoctor;

