package de.jumajumo.homework.service;

import java.time.LocalDate;

import cds.gen.homeworkservice.Activity;

public interface DueDateCalculationService {
    
    LocalDate calculate(Activity activity, LocalDate dueDate, LocalDate doneDate);

}