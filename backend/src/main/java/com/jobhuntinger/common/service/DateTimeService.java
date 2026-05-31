package com.jobhuntinger.common.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class DateTimeService {
    public String createDateDisplay(LocalDateTime localDateTime) {
        LocalDate date = localDateTime.toLocalDate();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateFormatter.format(date);
    }

    public String createTimeDisplay(LocalDateTime localDateTime) {
        LocalTime time = localDateTime.toLocalTime();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        return timeFormatter.format(time);
    }
}
