package de.jumajumo.homework.enumeration;

public enum EnumInterval {
    WEEKLY("1", 7, EnumIntervalDimension.DAY), BIWEEKLY("2", 2, EnumIntervalDimension.WEEK),
    MONTHLY("3", 1, EnumIntervalDimension.MONTH), YEARLY("4", 1, EnumIntervalDimension.YEAR);

    private final String code;
    private final Integer value;
    private final EnumIntervalDimension dimension;

    private EnumInterval(final String code, final Integer value, final EnumIntervalDimension dimension) {
        this.code = code;
        this.value = value;
        this.dimension = dimension;
    }

    public String getCode() {
        return this.code;
    }

    public Integer getValue() {
        return this.value;
    }

    public EnumIntervalDimension getDimension() {
        return this.dimension;
    }

    public static EnumInterval getByCode(final String code) {
        for (final EnumInterval interval : EnumInterval.values()) {
            if (code.equals(interval.code)) {
                return interval;
            }
        }

        throw new IllegalArgumentException("interval with code <" + code + "> not found.");
    }
}