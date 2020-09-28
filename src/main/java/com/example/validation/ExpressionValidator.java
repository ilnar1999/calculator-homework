package com.example.validation;

import com.example.enumeration.ArithmeticOperations;
import com.example.exception.EmptyRowException;
import com.example.exception.ExpressionIsNotRecognizedException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExpressionValidator {

    private List<String> elementsOfExpression;

    public List<Exception> getExceptionsInExpression(List<String> elementsOfExpression) {
        List<Exception> exceptions = new ArrayList<>();
        this.elementsOfExpression = elementsOfExpression;

        if (this.elementsOfExpression.isEmpty()) {
            exceptions.add(new EmptyRowException(" - введена пустая строка"));
            return exceptions;
        }
        if (this.isNotRecognized()) {
            exceptions.add(new ExpressionIsNotRecognizedException(" - не распознано"));
        }
        if (this.beginsWithOperator()) {
            exceptions.add(new ExpressionIsNotRecognizedException(" - начинается с оператора"));
        }
        if (this.endsWithOperator()) {
            exceptions.add(new ExpressionIsNotRecognizedException(" - заканчивается оператором"));
        }
        if (this.hasOperatorsOnEachOther()) {
            exceptions.add(new ExpressionIsNotRecognizedException(" - несколько операторов идут подряд"));
        }
        if (this.hasOperandsOnEachOther()) {
            exceptions.add(new ExpressionIsNotRecognizedException(" - несколько чисел идут подряд"));
        }

        return exceptions;
    }

    private boolean isNotRecognized() {
        for (String element : this.elementsOfExpression) {
            if (ArithmeticOperations.isArithmeticOperation(element)) {
                continue;
            }
            if (!element.matches("\\d+")) {
                return true;
            }
        }
        return false;
    }

    private boolean beginsWithOperator() {
        return ArithmeticOperations.isArithmeticOperation(this.elementsOfExpression.get(0))
                && !ArithmeticOperations.isParenthesis(this.elementsOfExpression.get(0));
    }

    private boolean endsWithOperator() {
        int lastElementPosition = this.elementsOfExpression.size() - 1;
        return ArithmeticOperations.isArithmeticOperation(this.elementsOfExpression.get(lastElementPosition))
                && !ArithmeticOperations.isParenthesis(this.elementsOfExpression.get(lastElementPosition));
    }

    private boolean hasOperatorsOnEachOther() {
        boolean previousElementIsOperator = ArithmeticOperations.isArithmeticOperation(this.elementsOfExpression.get(0))
                && !ArithmeticOperations.isParenthesis(this.elementsOfExpression.get(0));
        boolean currentElementIsOperator;
        for (int i = 1; i < this.elementsOfExpression.size(); i++) {
            currentElementIsOperator = ArithmeticOperations.isArithmeticOperation(this.elementsOfExpression.get(i))
            && !ArithmeticOperations.isParenthesis(this.elementsOfExpression.get(i));
            if (currentElementIsOperator && previousElementIsOperator) {
                return true;
            }
            previousElementIsOperator = currentElementIsOperator;
        }
        return false;
    }

    private boolean hasOperandsOnEachOther() {
        boolean previousElementIsOperand = NumberUtils.isParsable(this.elementsOfExpression.get(0));
        boolean currentElementIsOperand;
        for (int i = 1; i < this.elementsOfExpression.size(); i++) {
            currentElementIsOperand = NumberUtils.isParsable(this.elementsOfExpression.get(i));
            if (currentElementIsOperand && previousElementIsOperand) {
                return true;
            }
            previousElementIsOperand = currentElementIsOperand;
        }
        return false;
    }
}
