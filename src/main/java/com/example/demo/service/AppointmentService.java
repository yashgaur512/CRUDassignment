package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;


    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment createAppointment(Appointment appointment) {
    	Appointment createdAppointment = new Appointment();
    	createdAppointment.setId(appointment.getId());
    	createdAppointment.setDescription(appointment.getDescription());
    	createdAppointment.setEndTime(appointment.getEndTime());
    	createdAppointment.setStartTime(appointment.getStartTime());
    	createdAppointment.setTitle(appointment.getTitle());
        return appointmentRepository.saveAndFlush(createdAppointment);
    }
    
    public Appointment getAppointmentById(Long id) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        return appointmentOptional.orElse(null);
    }
    
    public Appointment updateAppointment(Long id, Appointment updatedAppointment) {
        Optional<Appointment> appointmentOptional = appointmentRepository.findById(id);
        if (appointmentOptional.isPresent()) {
            Appointment existingAppointment = appointmentOptional.get();
            existingAppointment.setTitle(updatedAppointment.getTitle());
            existingAppointment.setDescription(updatedAppointment.getDescription());
            existingAppointment.setStartTime(updatedAppointment.getStartTime());
            existingAppointment.setEndTime(updatedAppointment.getEndTime());

            
            return appointmentRepository.save(existingAppointment);
        } else {
            return null;
        }
    }
    
    public boolean deleteAppointment(Long id) {
        if (appointmentRepository.existsById(id)) {
            appointmentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    
}
