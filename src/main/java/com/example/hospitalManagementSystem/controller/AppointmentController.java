package com.example.hospitalManagementSystem.controller;

import com.example.hospitalManagementSystem.entity.Appointment;
import com.example.hospitalManagementSystem.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody Appointment appointment,
            @RequestParam Long doctorId,
            @RequestParam Long patientId) {

        return ResponseEntity.ok(
                appointmentService.createNewAppointment(appointment, doctorId, patientId)
        );
    }

    @PutMapping("/{appointmentId}/doctor/{doctorId}")
    public ResponseEntity<Appointment> changeDoctor(
            @PathVariable Long appointmentId,
            @PathVariable Long doctorId) {

        return ResponseEntity.ok(
                appointmentService.reAssignAppointmentToAnotherDoctor(appointmentId, doctorId)
        );
    }
}