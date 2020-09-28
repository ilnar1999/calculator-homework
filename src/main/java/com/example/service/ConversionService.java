package com.example.service;

import com.example.enumeration.ArithmeticOperations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConversionService {

    public List<String> convertStringToList(String expression) {

        List<String> elements = new ArrayList<>();
        StringBuilder currentElement = new StringBuilder();
        String currentSymbol;

        for (int i = 0; i < expression.length(); i++) {
            currentSymbol = Character.toString(expression.charAt(i));
            if (ArithmeticOperations.isArithmeticOperation(currentSymbol) || currentSymbol.equals(" ")) {
                if (currentElement.length() != 0) {
                    elements.add(currentElement.toString().trim());
                    currentElement.delete(0, currentElement.length());
                }
                if (ArithmeticOperations.isArithmeticOperation(currentSymbol)) {
                    elements.add(currentSymbol);
                }
            } else {
                currentElement.append(currentSymbol);
            }
        }
        if (currentElement.length() != 0) {
            elements.add(currentElement.toString().trim());
        }
        return elements;
    }
}
