package com.shchepinms.calc;

public class Main {

    public static void main(String[] args) throws Exception {
        Input input = InputImpl.getInstance();
        String consoleData = input.getInput();
        System.out.println(calc(consoleData));
    }

    public static String calc(String input) throws Exception {
        Analyzer analyzer = AnalyzerImpl.getInstance();
        Calculator calc = CalculatorImpl.getInstance();
        Output output = OutputImpl.getInstance();

        CalcInput calcInput = analyzer.getCorrectData(input);
        CalcOutput calcOutput = calc.calculate(calcInput);
        return output.getOutput(calcOutput);
    }
}
