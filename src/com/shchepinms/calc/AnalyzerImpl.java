package com.shchepinms.calc;

public class AnalyzerImpl implements Analyzer {

    private static final String SPLIT_MARKER = "cut_here";

    private static AnalyzerImpl instance;
    private String data;

    private AnalyzerImpl() {}

    public static AnalyzerImpl getInstance() {
        return instance != null ? instance : new AnalyzerImpl();
    }

    @Override
    public CalcInput getCorrectData(String data) throws Exception {
        this.data = data;
        if (isEmpty()) throw new Exception("Empty input");
        Operand operand = getOperand();
        String first = getFirstValue(operand);
        String second = getSecondValue(operand);
        validateValue(first);
        validateValue(second);
        if (isValuesDifferentType(first, second)) throw new Exception("Values different type");
        int firstValue = getArabicValue(first);
        int secondValue = getArabicValue(second);
        return new CalcInput(firstValue, secondValue, operand, isArabicValue(first));
    }

    private int getArabicValue(String value) {
        return isArabicValue(value) ? Integer.parseInt(value) : toArabicValue(value);
    }

    private int toArabicValue(String value) {
        for(RomeNumbers number : RomeNumbers.values()) {
            if (number.getSymbol().equals(value))
                return number.getValue();
        }
        throw new RuntimeException("Wow! So unexpectedly, such Wow!");
    }

    private boolean isValuesDifferentType(String value1, String value2) {
        return isArabicValue(value1) != isArabicValue(value2);
    }

    private boolean isArabicValue(String value) {
        for (int i = 1; i < 11; i++) {
            if (value.equals(String.valueOf(i)))
                return true;
        }
        return false;
    }

    private void validateValue(String value) throws Exception {
        for(RomeNumbers number: RomeNumbers.values()) {
            if (value.equals(String.valueOf(number.getValue())) || value.equals(number.getSymbol()))
                return;
        }
        throw new Exception("invalid value");
    }

    private boolean isEmpty() {
        return data == null || (data.length() < 3);
    }

    private String getFirstValue(Operand operand) {
        return getDataToSplit(operand).split(SPLIT_MARKER)[0].strip();
    }

    private String getDataToSplit(Operand operand) {
        return data.replace(operand.getSymbol(), SPLIT_MARKER);
    }

    private String getSecondValue(Operand operand) {
        return getDataToSplit(operand).split(SPLIT_MARKER)[1].strip();
    }

    private Operand getOperand() throws Exception {
        for (Operand operand : Operand.values()) {
            if (data.contains(operand.getSymbol()))
                return operand;
        }
        throw new Exception("Operand not found");
    }


}
