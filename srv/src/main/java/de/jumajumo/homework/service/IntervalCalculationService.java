package de.jumajumo.homework.service;

import java.time.LocalDate;

public interface IntervalCalculationService {

    LocalDate addInterval(final LocalDate baseDate, final String intervalCode);

}
