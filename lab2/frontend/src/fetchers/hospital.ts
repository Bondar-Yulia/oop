import { fetchApi } from '#fetchers/generic';
import { Doctor, Patient, Diagnosis } from '#types/models';

// Doctors
export async function createDoctor(doctor: Omit<Doctor, 'id'>) {
    return fetchApi<number>('/doctors', { method: 'POST', body: doctor });
}

export async function updateDoctor(doctor: Doctor) {
    return fetchApi<number>('/doctors', { method: 'PUT', body: doctor });
}

export async function deleteDoctor(id: number) {
    return fetchApi<number>(`/doctors/${id}`, { method: 'DELETE' });
}

export async function getDoctors() {
    return fetchApi<Doctor[]>('/doctors', { method: 'GET' });
}

export async function getDoctor(id: number) {
    return fetchApi<Doctor>(`/doctors/${id}`, { method: 'GET' });
}

// Patients
export async function createPatient(patient: Omit<Patient, 'id'>) {
    return fetchApi<number>('/patients', { method: 'POST', body: patient });
}

export async function updatePatient(patient: Patient) {
    return fetchApi<number>('/patients', { method: 'PUT', body: patient });
}

export async function deletePatient(id: number) {
    return fetchApi<number>(`/patients/${id}`, { method: 'DELETE' });
}

export async function getPatients() {
    return fetchApi<Patient[]>('/patients', { method: 'GET' });
}

export async function getPatient(id: number) {
    return fetchApi<Patient>(`/patients/${id}`, { method: 'GET' });
}

// Diagnoses
export async function createDiagnosis(diagnosis: Omit<Diagnosis, 'id'>) {
    return fetchApi<number>('/diagnoses', { method: 'POST', body: diagnosis });
}

export async function updateDiagnosis(diagnosis: Diagnosis) {
    return fetchApi<number>('/diagnoses', { method: 'PUT', body: diagnosis });
}

export async function deleteDiagnosis(id: number) {
    return fetchApi<number>(`/diagnoses/${id}`, { method: 'DELETE' });
}

export async function getDiagnoses() {
    return fetchApi<Diagnosis[]>('/diagnoses', { method: 'GET' });
}

export async function getDiagnosis(id: number) {
    return fetchApi<Diagnosis>(`/diagnoses/${id}`, { method: 'GET' });
}
