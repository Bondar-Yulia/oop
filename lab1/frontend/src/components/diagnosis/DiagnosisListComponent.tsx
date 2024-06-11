import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useHistory } from 'react-router-dom';
import './Diagnosis.css';

const DiagnosisListComponent: React.FC = () => {
  const [diagnoses, setDiagnoses] = useState([]);
  const history = useHistory();

  useEffect(() => {
    axios.get('/diagnoses').then(response => {
      setDiagnoses(response.data);
    });
  }, []);

  const editDiagnosis = (id: number) => {
    history.push(`/diagnoses/edit/${id}`);
  };

  const deleteDiagnosis = (id: number) => {
    axios.delete(`/diagnoses/${id}`).then(() => {
      setDiagnoses(diagnoses.filter((diagnosis: any) => diagnosis.id !== id));
    });
  };

  const createDiagnosis = () => {
    history.push('/diagnoses/create');
  };

  return (
    <div className="diagnosis-list">
      <h2>Список Діагнозів</h2>
      <ul>
        {diagnoses.map((diagnosis: any) => (
          <li key={diagnosis.id}>
            Пацієнт ID: {diagnosis.patient_id}, Лікар ID: {diagnosis.doctor_id}, Діагноз: {diagnosis.diagnosis}, Остаточний: {diagnosis.final_diagnosis ? 'Так' : 'Ні'}
            <button onClick={() => editDiagnosis(diagnosis.id)}>Редагувати</button>
            <button onClick={() => deleteDiagnosis(diagnosis.id)}>Видалити</button>
          </li>
        ))}
      </ul>
      <button onClick={createDiagnosis}>Додати Діагноз</button>
    </div>
  );
};

export default DiagnosisListComponent;
