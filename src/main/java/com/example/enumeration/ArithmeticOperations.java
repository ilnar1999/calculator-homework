package com.example.enumeration;

public enum ArithmeticOperations {
    EXPONENTIATION("^", 4),
    MULTIPLICATION("*", 3),
    DIVISION("/", 3),
    ADDITION("+", 2),
    SUBTRACTION("-", 2),
    OPENING_PARENTHESIS("(", 1),
    CLOSING_PARENTHESIS(")", 1);


    private final String symbol;
    private final int value;

    ArithmeticOperations(String symbol, int value) {
        this.symbol = symbol;
        this.value = value;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getValue() {
        return value;
    }

    public static boolean isArithmeticOperation(String symbol) {
        for (ArithmeticOperations arithmeticOperations : ArithmeticOperations.values()) {
            if (arithmeticOperations.getSymbol().equals(symbol)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isParenthesis(String symbol) {
        return symbol.equals("(") || symbol.equals(")");
    }

    public static int getValueBySymbol(String symbol) {
        for (ArithmeticOperations arithmeticOperations : ArithmeticOperations.values()) {
            if (arithmeticOperations.symbol.equals(symbol)) {
                return arithmeticOperations.getValue();
            }
        }
        return 0;
    }
}
