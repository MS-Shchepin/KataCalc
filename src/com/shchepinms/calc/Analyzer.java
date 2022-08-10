package com.shchepinms.calc;

import com.shchepinms.calc.CalcInput;

interface Analyzer {
    CalcInput getCorrectData(String data) throws Exception;
}
