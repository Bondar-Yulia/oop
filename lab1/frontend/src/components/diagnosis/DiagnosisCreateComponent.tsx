import React, { useState } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './Diagnosis.css';

const DiagnosisCreateComponent: React.FC = () => {
  const [diagnosis, setDiagnosis] = useState({ patient_id: '', doctor_id: '', diagnosis: '', final_diagnosis: false });
  const history = useHistory();

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDiagnosis({ ...diagnosis, [e.target.name]: e.target.value });
  };

  const handleCheckboxChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setDiagnosis({ ...diagnosis, final_diagnosis: e.target.checked });
  };

  const createDiagnosis = (e: React.FormEvent) => {
    e.preventDefault();
    axios.post('/diagnoses', diagnosis).then(() => {
      history.push('/diagnoses');
    });
  };

  return (
    <div className="diagnosis-form">
      <h2>Створити Діагноз</h2>
      <form onSubmit={createDiagnosis}>
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
        <button type="submit">Створити</button>
      </form>
    </div>
  );
};

export default DiagnosisCreateComponent;
