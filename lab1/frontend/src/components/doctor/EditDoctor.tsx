import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory, useParams } from 'react-router-dom';
import './Doctor.css';

const EditDoctor: React.FC = () => {
  const [doctor, setDoctor] = useState({ name: '', specialty: '' });
  const history = useHistory();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    axios.get(`/doctors/${id}`).then(response => {
      setDoctor(response.data);
    });
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDoctor({ ...doctor, [e.target.name]: e.target.value });
  };

  const updateDoctor = (e: React.FormEvent) => {
    e.preventDefault();
    axios.put(`/doctors/${id}`, doctor).then(() => {
      history.push('/doctors');
    });
  };

  return (
    <div className="doctor-form">
      <h2>Редагувати Лікаря</h2>
      <form onSubmit={updateDoctor}>
        <div>
          <label htmlFor="name">Ім'я</label>
          <input type="text" name="name" value={doctor.name} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="specialty">Спеціалізація</label>
          <input type="text" name="specialty" value={doctor.specialty} onChange={handleChange} required />
        </div>
        <button type="submit">Зберегти</button>
      </form>
    </div>
  );
};

export default EditDoctor;
