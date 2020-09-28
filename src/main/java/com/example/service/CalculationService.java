package com.example.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Stack;

@Service
public class CalculationService {
    private Stack<Double> numericElements;

    public double getResult(List<String> stringElements) throws ArithmeticException {

        double firstOperand;
        double secondOperand;

        numericElements = new Stack<>();
        for (String stringElement : stringElements) {
            if (NumberUtils.isParsable(stringElement)) {
                this.numericElements.add(Double.parseDouble(stringElement));
            } else {
                firstOperand = this.numericElements.pop();
                secondOperand = this.numericElements.pop();
                switch (stringElement) {
                    case "+":
                        this.numericElements.add(firstOperand + secondOperand);
                        break;
                    case "-":
                        this.numericElements.add(firstOperand - secondOperand);
                        break;
                    case "*":
                        this.numericElements.add(firstOperand * secondOperand);
                        break;
                    case "/":
                        this.numericElements.add(firstOperand / secondOperand);
                        break;
                    case "^":
                        this.numericElements.add(Math.pow(secondOperand, firstOperand));
                        break;
                }
            }
        }
        return this.numericElements.pop();
    }
}
