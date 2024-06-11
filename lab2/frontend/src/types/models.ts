export interface Doctor {
    id: number;
    name: string;
    specialty: string;
  }
  
  export interface Patient {
    id: number;
    name: string;
    date_of_birth: string;
    address: string;
    phone_number: string;
  }
  
  export interface Diagnosis {
    id: number;
    patient_id: number;
    doctor_id: number;
    diagnosis: string;
    final_diagnosis: boolean;
  }
  