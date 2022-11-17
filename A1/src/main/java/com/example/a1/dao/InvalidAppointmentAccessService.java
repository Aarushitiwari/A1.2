package com.example.a1.dao;

import com.example.a1.model.Appointment;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository("invalidDao")
public class InvalidAppointmentAccessService implements AppointmentDAO {
    private static List<Appointment> DB =  new ArrayList<>();

    @Override
    public int insertAppointment(UUID id, Appointment appointment) {
        DB.add(new Appointment(id,appointment.getName()));
        return 1;
    }

    @Override
    public List<Appointment> selectAllAppointments() {
        return DB;
    }

    @Override
    public Optional<Appointment> selectAppointmentById(UUID id) {
        return DB.stream().filter(appointment -> appointment.getID().equals(id)).findFirst();
    }

    @Override
    public List<Appointment> selectAppointmentByDate(String date) {
        System.out.println("inside service" + date);
        return DB.stream().filter(appointment -> appointment.getDate().equals(date)).collect(Collectors.toList());
    }

    @Override
    public int deleteAppointmentById(UUID id) {
        Optional<Appointment> appointmentMaybe = selectAppointmentById(id);
        if(appointmentMaybe.isEmpty()) {
            return 0;
        }
        DB.remove(appointmentMaybe.get());
        return 1;
    }

    @Override
    public int updateAppointmentById(UUID id, Appointment update) {
        return selectAppointmentById(id).map( appointment ->
        {
            int indexOfAppointmentToUpdate = DB.indexOf(appointment);
            if(indexOfAppointmentToUpdate >= 0) {
                DB.set(indexOfAppointmentToUpdate, new Appointment(id, update.getName()) );
                return 1;
            }
            return 0;
        }).orElse(0);
    }
}
