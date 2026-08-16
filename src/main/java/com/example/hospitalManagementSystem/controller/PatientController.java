package com.example.hospitalManagementSystem.controller;

import com.example.hospitalManagementSystem.entity.Patient;
import com.example.hospitalManagementSystem.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // Create Patient
    @PostMapping
    public ResponseEntity<Patient> savePatient(@RequestBody Patient patient){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(patientService.savePatient(patient));
    }

    // Get All Patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients(){
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    // Get Patient By Id
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // Update Patient
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                 @RequestBody Patient patient){
        return ResponseEntity.ok(patientService.updatePatient(id, patient));
    }

    // Delete Patient
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id){
        patientService.deletePatient(id);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}