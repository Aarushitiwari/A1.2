package com.example.a1.dao;

import com.example.a1.model.Appointment;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

// interface acting as the database access object to simulate the DB
public interface AppointmentDAO {
    int insertAppointment(UUID id, Appointment appointment);

    default int insertAppointment(Appointment appointment) {
        UUID id = UUID.randomUUID();
        return insertAppointment(id,appointment);
    }

    List<Appointment> selectAllAppointments();

    Optional<Appointment> selectAppointmentById(UUID id);

    List<Appointment> selectAppointmentByDate(String date);

    int deleteAppointmentById(UUID id);

    int updateAppointmentById(UUID id, Appointment appointment);
}
