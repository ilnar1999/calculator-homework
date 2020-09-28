package com.example.service;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DisplayService {

    public void printExceptions(List<Exception> exceptions) {
        System.out.println("Выражение содержит следующие ошибки:");
        for (Exception exception : exceptions) {
            System.out.println(exception.getMessage());
        }
    }

    public void printException(Exception exception) {
        System.out.println("Выражение содержит следующие ошибки:");
        System.out.println(exception.getMessage());
    }

    public void printResult(double result) {
        if (result % 1 == 0) {
            System.out.println((int) result);
        } else {
            System.out.println(result);
        }
    }
}

