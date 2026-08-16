package com.example.hospitalManagementSystem.service;

import com.example.hospitalManagementSystem.entity.Doctor;
import com.example.hospitalManagementSystem.repository.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctor by id
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow();
    }

    // Save doctor
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Update doctor
    public Doctor updateDoctor(Long id, Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findById(id).orElseThrow();

        existingDoctor.setName(doctor.getName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setSpecialization(doctor.getSpecialization());

        return doctorRepository.save(existingDoctor);
    }

    // Delete doctor
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}