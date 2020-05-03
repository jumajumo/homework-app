package de.jumajumo.homework.service;

import java.time.LocalDate;

public interface TaskService {
    void markAsDone(final String taskId, final LocalDate doneDate);
    void reopen(final String doneTaskId);
}