package de.jumajumo.homework.enumeration;

public enum EnumCalculationStrategy {

    BasedOnDueDate("0"), BasedOnDoneDate("1");

    private String code;

    private EnumCalculationStrategy(final String code) {
        this.code = code;
    }

    public static EnumCalculationStrategy getByCode(final String code) {
        for (final EnumCalculationStrategy strategy : EnumCalculationStrategy.values()) {
            if (code .equals(strategy.code))
                return strategy;
        }

        throw new IllegalArgumentException("strategy with key <" + code + "> is not known.");
    }
}