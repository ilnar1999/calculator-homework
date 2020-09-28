package com.example.service;

import com.example.exception.ParenthesisException;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import com.example.enumeration.ArithmeticOperations;

@Service
public class ParseService {

    private Stack<String> operators = new Stack<>();
    private List<String> elementsInReversPolishNotation = new ArrayList<>();

    public List<String> parseExpressionInReversePolishNotation(List<String> elementsOfExpression) throws ParenthesisException {

        for (String elementOfExpression : elementsOfExpression) {
            if (NumberUtils.isParsable(elementOfExpression)) {
                this.elementsInReversPolishNotation.add(elementOfExpression);
            } else if (elementOfExpression.equals(ArithmeticOperations.CLOSING_PARENTHESIS.getSymbol())) {
                this.pullOperatorsBetweenParenthesis();
            } else if (ArithmeticOperations.isArithmeticOperation(elementOfExpression)) {
                this.putOperatorInOperators(elementOfExpression);
            }
        }
        while (!this.operators.isEmpty()) {
            this.elementsInReversPolishNotation.add(this.operators.pop());
        }
        return this.elementsInReversPolishNotation;
    }

    private void pullOperatorsBetweenParenthesis() throws ParenthesisException {

        String currentOperator;

        if (operators.isEmpty()) {
            throw new ParenthesisException(" - скобки не согласованны");
        }
        currentOperator = this.operators.pop();
        while (!currentOperator.equals(ArithmeticOperations.OPENING_PARENTHESIS.getSymbol())) {
            this.elementsInReversPolishNotation.add(currentOperator);
            if (operators.isEmpty()) {
                throw new ParenthesisException(" - скобки не согласованны");
            }
            currentOperator = this.operators.pop();
        }
    }

    private void putOperatorInOperators(String elementOfExpression) {
        int valueOfLastOperator;
        int valueOfCurrentElement;
        if (!this.operators.isEmpty()) {
            valueOfLastOperator = ArithmeticOperations.getValueBySymbol(operators.peek());
            valueOfCurrentElement = ArithmeticOperations.getValueBySymbol(elementOfExpression);
            if (valueOfCurrentElement < valueOfLastOperator) {
                this.elementsInReversPolishNotation.add(this.operators.pop());
            }
        }
        this.operators.add(elementOfExpression);
    }
}
