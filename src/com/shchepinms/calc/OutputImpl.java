package com.shchepinms.calc;

public class OutputImpl implements Output {

    private static OutputImpl instance;

    private OutputImpl() {}

    public static OutputImpl getInstance() {
        return instance != null ? instance : new OutputImpl();
    }

    @Override
    public String getOutput(CalcOutput output) {
        return output.isArabic() ? String.valueOf(output.value()) : arabicToRoman(output.value());
    }

    private String arabicToRoman(int value) {
        int[] values = {1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String[] romeLiterals = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuilder romeValue = new StringBuilder();
        for(int i = 0; i < values.length; i++) {
            while(value >= values[i]) {
                value -= values[i];
                romeValue.append(romeLiterals[i]);
            }
        }
        return romeValue.toString();
    }
}
