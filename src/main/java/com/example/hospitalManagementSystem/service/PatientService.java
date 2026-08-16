package com.example.hospitalManagementSystem.service;

import com.example.hospitalManagementSystem.entity.Patient;
import jakarta.persistence.EntityNotFoundException;
import com.example.hospitalManagementSystem.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    // Save Patient
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    // Get All Patients
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Get Patient By Id
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + id));
    }

    // Update Patient
    public Patient updatePatient(Long id, Patient patient) {

        Patient existingPatient = patientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id : " + id));

        existingPatient.setName(patient.getName());
        existingPatient.setBirthDate(patient.getBirthDate());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setGender(patient.getGender());
        existingPatient.setBloodGroup(patient.getBloodGroup());
        existingPatient.setInsurance(patient.getInsurance());

        return patientRepository.save(existingPatient);
    }

    // Delete Patient
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}