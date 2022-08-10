package com.shchepinms.calc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputImpl implements Input {

    private static Input instance;

    private InputImpl() {}

    public static Input getInstance() {
        return instance != null ? instance : new InputImpl();
    }

    @Override
    public String getInput() {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            return reader.readLine();
        } catch (IOException ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
