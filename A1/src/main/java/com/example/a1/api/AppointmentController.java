package com.example.a1.api;

import com.example.a1.model.Appointment;
import com.example.a1.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@RequestMapping("/api/v1/appointments")
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // post method to add a new appointment
    @PostMapping
    public void addAppointment(@Valid @NotNull @RequestBody Appointment appointment) {
        appointmentService.addAppointment(appointment);
    }

    // get method to fetch an appointment by date
    @GetMapping(path = "{date}")
    public List<Appointment> getAppointmentByDate(@PathVariable("date") String date) {
        return appointmentService.getAppointmentByDate(date);
    }

    // delete method to delete an appointment by ID
    @DeleteMapping(path = "{id}")
    public void deleteAppointmentById(@PathVariable("id") UUID id) {
        appointmentService.deleteAppointment(id);
    }

    // put method to update an existing appointment by id
    @PutMapping(path = "{id}")
    public void updateAppointment(@PathVariable("id") UUID id, @Valid @NotNull @RequestBody Appointment appointmentToUpdate) {
        appointmentService.updateAppointment(id,appointmentToUpdate);
    }
}

