package com.example.a1.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@RequestMapping("/api/v1/current")
@RestController
public class DateController {
    Calendar c = Calendar.getInstance();

    @GetMapping
    public int getDate(@RequestParam(name = "type") String type) {
        if(type.equalsIgnoreCase("date")) {
            System.out.println(c.get(Calendar.DATE));                   // checks the type and returns current date
            return c.get(Calendar.DATE);
        } else if(type.equalsIgnoreCase("day")) {         // checks the type and returns current day
            return c.get(Calendar.DAY_OF_WEEK);
        } else if(type.equalsIgnoreCase("month")) {       // checks the type and returns current month
            return c.get(Calendar.MONTH);
        } else if(type.equalsIgnoreCase("year")) {        // checks the type and returns current year
            return c.get(Calendar.YEAR);
        }

        return -1;
    }
 }
