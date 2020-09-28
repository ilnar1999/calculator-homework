package com.example.service;

import com.example.exception.ParenthesisException;
import com.example.validation.ExpressionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;

@Service
public class ListenService {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private DisplayService displayService;
    @Autowired
    private ParseService parseService;
    @Autowired
    private CalculationService calculationService;
    @Autowired
    private ExpressionValidator expressionValidator;


    public void run() {

        Scanner scanner;
        String expression;
        List<String> elementsOfExpression;
        List<Exception> exceptions;

        while (true) {
            scanner = new Scanner(System.in);
            expression = scanner.nextLine();
            if (expression.equalsIgnoreCase("exit")) {
                break;
            }
            elementsOfExpression = this.conversionService.convertStringToList(expression);
            exceptions = this.expressionValidator.getExceptionsInExpression(elementsOfExpression);
            if (!exceptions.isEmpty()) {
                this.displayService.printExceptions(exceptions);
            } else {
                try {
                    elementsOfExpression = this.parseService.parseExpressionInReversePolishNotation(elementsOfExpression);
                    this.displayService.printResult(this.calculationService.getResult(elementsOfExpression));
                } catch (ParenthesisException | ArithmeticException e) {
                    this.displayService.printException(e);
                }
            }
        }
    }
}
