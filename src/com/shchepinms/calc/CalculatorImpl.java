package com.shchepinms.calc;

public class CalculatorImpl implements Calculator {

    private CalcInput data;

    private static Calculator instance;

    private CalculatorImpl() {}

    public static Calculator getInstance() {
        return instance != null ? instance : new CalculatorImpl();
    }

    @Override
    public CalcOutput calculate(CalcInput data) throws Exception {
        int result = data.firstNumber();
        switch (data.operand()) {
            case ADD -> result += data.secondNumber();
            case SUBTRACT -> result -= data.secondNumber();
            case MULTIPLY -> result *= data.secondNumber();
            case DIVIDE -> result /= data.secondNumber();
        }
        if (!data.isArabic() && result < 1) throw new Exception("Rome value < 1");
        return new CalcOutput(result, data.isArabic());
    }

}
