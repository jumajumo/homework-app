package de.jumajumo.homework.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cds.gen.homeworkservice.Activity;
import de.jumajumo.homework.enumeration.EnumCalculationStrategy;


@Service
public class DueDateCalculationServiceImpl implements DueDateCalculationService {
    
    @Autowired
    private IntervalCalculationService intervalService;

    @Override
    public LocalDate calculate(final Activity activity, final LocalDate dueDate, final LocalDate doneDate) {

        final String calculationStrategyCode = activity.getCalculationStrategyCode();
        final EnumCalculationStrategy calculationStrategy = EnumCalculationStrategy.getByCode(calculationStrategyCode);

        final LocalDate baseDate;
        switch (calculationStrategy) {
            case BasedOnDueDate:
                baseDate = dueDate;
                break;
            case BasedOnDoneDate:
                baseDate = doneDate;
                break;
            default:
                baseDate = LocalDate.now();
        }

        final String intervalCode = activity.getIntervalCode();
        return intervalService.addInterval(baseDate, intervalCode);
    }

}