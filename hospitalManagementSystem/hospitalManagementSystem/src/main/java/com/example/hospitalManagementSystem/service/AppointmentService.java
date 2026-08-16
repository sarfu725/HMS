package com.example.hospitalManagementSystem.service;



import com.example.hospitalManagementSystem.entity.Appointment;
import com.example.hospitalManagementSystem.entity.Doctor;
import com.example.hospitalManagementSystem.entity.Patient;
import com.example.hospitalManagementSystem.repository.AppointmentRepository;
import com.example.hospitalManagementSystem.repository.DoctorRepository;
import com.example.hospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentService {

   private final AppointmentRepository appointmentRepository;
   private final DoctorRepository doctorRepository;
   private final PatientRepository patientRepository;

   @Transactional
   public Appointment createNewAppointment(Appointment appointment, Long doctorId, Long patientId){
       Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();
       Patient patient = patientRepository.findById(patientId).orElseThrow();

       if (appointment.getId() != null) throw new IllegalArgumentException("Appiontment should not have an id");

       appointment.setDoctor(doctor);
       appointment.setPatient(patient);

       patient.getAppointments().add(appointment); //to maintain consistency

       return appointmentRepository.save(appointment);
   }
   @Transactional
    public Appointment reAssignAppointmentToAnotherDoctor(Long appointmentId, Long doctorId) {
       Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor); //this will automatically call tha update because it is dirty now

        doctor.getAppointments().add(appointment); // just for bidirectional consistency
        return appointment;
    }
}
