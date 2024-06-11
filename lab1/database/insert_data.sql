-- Insert sample data into Patients table
INSERT INTO Patients (name, date_of_birth, address, phone_number) VALUES
('John Doe', '1980-01-01', '123 Main St', '555-1234'),
('Jane Smith', '1990-02-02', '456 Elm St', '555-5678');

-- Insert sample data into Doctors table
INSERT INTO Doctors (name, specialty) VALUES
('Dr. Alice Johnson', 'Cardiology'),
('Dr. Bob Brown', 'Neurology');

-- Insert sample data into Nurses table
INSERT INTO Nurses (name) VALUES
('Nurse Anna White'),
('Nurse Emily Green');

-- Insert sample data into Appointments table
INSERT INTO Appointments (patient_id, doctor_id, nurse_id, type, description, status, appointment_date) VALUES
(1, 1, NULL, 'Procedure', 'Blood test', 'Scheduled', '2024-06-15 09:00:00'),
(2, 2, 1, 'Medication', 'Pain relief', 'Scheduled', '2024-06-16 10:00:00');

-- Insert sample data into Diagnoses table
INSERT INTO Diagnoses (patient_id, doctor_id, diagnosis, final_diagnosis) VALUES
(1, 1, 'Hypertension', FALSE),
(2, 2, 'Migraine', FALSE);
