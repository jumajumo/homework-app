package de.jumajumo.homework.service;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

import de.jumajumo.homework.enumeration.EnumInterval;

@Service
public class IntervalCalculationServiceImpl implements IntervalCalculationService {

    @Override
    public LocalDate addInterval(final LocalDate baseDate, final String intervalCode) {

        final EnumInterval interval = EnumInterval.getByCode(intervalCode);
        
        switch (interval.getDimension())
        {
            case DAY: return baseDate.plusDays(interval.getValue());
            case WEEK: return baseDate.plusWeeks(interval.getValue());
            case MONTH: return baseDate.plusMonths(interval.getValue());
            case YEAR: return baseDate.plusYears(interval.getValue());
        }

        throw new IllegalArgumentException("Interval code <" + intervalCode + "> cannot be calculated.");
    }
    
}