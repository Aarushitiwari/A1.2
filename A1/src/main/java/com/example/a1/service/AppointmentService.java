package com.example.a1.service;

import com.example.a1.dao.AppointmentDAO;
import com.example.a1.model.Appointment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {
    private final AppointmentDAO appointmentDAO;

    @Autowired
    public AppointmentService(@Qualifier("invalidDao") AppointmentDAO appointmentDAO) {
        this.appointmentDAO = appointmentDAO;
    }

    public int addAppointment(Appointment appointment) {
        return appointmentDAO.insertAppointment(appointment);
    }

    public List<Appointment> getAllAppointments() {
        return appointmentDAO.selectAllAppointments();
    }

    public Optional<Appointment> getAppointmentById(UUID id) {
        return appointmentDAO.selectAppointmentById(id);
    }

    public List<Appointment> getAppointmentByDate(String date) {
        System.out.println("inside service" + date);
        return appointmentDAO.selectAppointmentByDate(date);
    }

    public int deleteAppointment(UUID id) {
        return appointmentDAO.deleteAppointmentById(id);
    }

    public int updateAppointment(UUID id, Appointment newAppointment) {
        return appointmentDAO.updateAppointmentById(id, newAppointment);
    }
}
