import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory, useParams } from 'react-router-dom';
import './Diagnosis.css';

const DiagnosisEditComponent: React.FC = () => {
  const [diagnosis, setDiagnosis] = useState({ patient_id: '', doctor_id: '', diagnosis: '', final_diagnosis: false });
  const history = useHistory();
  const { id } = useParams<{ id: string }>();

  useEffect(() => {
    axios.get(`/diagnoses/${id}`).then(response => {
      setDiagnosis(response.data);
    });
  }, [id]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDiagnosis({ ...diagnosis, [e.target.name]: e.target.value });
  };

  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDiagnosis({ ...diagnosis, final_diagnosis: e.target.checked });
  };

  const updateDiagnosis = (e: React.FormEvent) => {
    e.preventDefault();
    axios.put(`/diagnoses/${id}`, diagnosis).then(() => {
      history.push('/diagnoses');
    });
  };

  return (
    <div className="diagnosis-form">
      <h2>Редагувати Діагноз</h2>
      <form onSubmit={updateDiagnosis}>
        <div>
          <label htmlFor="patient_id">ID Пацієнта</label>
          <input type="text" name="patient_id" value={diagnosis.patient_id} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="doctor_id">ID Лікаря</label>
          <input type="text" name="doctor_id" value={diagnosis.doctor_id} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="diagnosis">Діагноз</label>
          <input type="text" name="diagnosis" value={diagnosis.diagnosis} onChange={handleChange} required />
        </div>
        <div>
          <label htmlFor="final_diagnosis">Остаточний діагноз</label>
          <input type="checkbox" name="final_diagnosis" checked={diagnosis.final_diagnosis} onChange={handleCheckboxChange} />
        </div>
        <button type="submit">Зберегти</button>
      </form>
    </div>
  );
};

export default DiagnosisEditComponent;

