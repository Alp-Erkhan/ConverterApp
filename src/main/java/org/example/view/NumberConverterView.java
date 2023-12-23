package org.example.view;

import java.util.Scanner;

public class NumberConverterView {
    Scanner scanner = new Scanner(System.in);
    public void start() {
        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("--- NUMBER-CONVERTER");
            String prompt = """
                    [1] Binary to decimal
                    [2] Decimal to binary
                    [3] Hexadecimal to decimal
                    [4] Decimal to hexadecimal
                    [0] End""";
            int choice = inputIntegerWithPrompt(prompt);
            switch (choice) {
                case 1 -> binaryToDecimal();
                case 2 -> decimalToBinary();
                case 3 -> hexadecimalToDecimal();
                case 4 -> DecimalToHexadecimal();
                case 0 -> running = false;
                default -> displayErrorMessage("Invalid Input!");
            }
        }
    }

    private void hexadecimalToDecimal() {
        System.out.print("Enter a hexadecimal number: ");
        Scanner scanner = new Scanner(System.in);
        String hexadecimal = scanner.next();
        if (hexadecimal.isEmpty()) {
            throw new IllegalArgumentException("Hexadecimal string is empty");
        }

        hexadecimal = hexadecimal.toUpperCase();

        int decimal = getDecimal(hexadecimal);

        System.out.println("The decimal is: " + decimal);
    }

    private static int getDecimal(String hexadecimal) {
        int decimal = 0;

        for (int i = 0; i < hexadecimal.length(); i++) {
            char hexChar = hexadecimal.charAt(i);

            int digit;
            if (hexChar >= '0' && hexChar <= '9') {
                digit = hexChar - '0';
            } else if (hexChar >= 'A' && hexChar <= 'F') {
                digit = hexChar - 'A' + 10;
            } else {
                throw new IllegalArgumentException("Invalid hexadecimal character: " + hexChar);
            }
            decimal = decimal * 16 + digit;
        }
        return decimal;
    }

    private void DecimalToHexadecimal() {
        System.out.print("Enter a decimal number: ");
        Scanner scanner = new Scanner(System.in);
        int decimal = scanner.nextInt();
        if (decimal < 0) {
            throw new IllegalArgumentException("Input decimal must be non-negative");
        }

        if (decimal == 0) {
            System.out.println("0");
        }

        StringBuilder hexadecimalBuilder = new StringBuilder();

        while (decimal > 0) {
            int remainder = decimal % 16;
            char hexDigit = (remainder < 10) ? (char) ('0' + remainder) : (char) ('A' + remainder - 10);
            hexadecimalBuilder.insert(0, hexDigit);
            decimal /= 16;
        }

        System.out.println("The hexadecimal is: " + hexadecimalBuilder);
    }

    public void binaryToDecimal() {
        int decimal = 0;
        int base = 1;
        System.out.print("Enter a binary number: ");
        Scanner scanner = new Scanner(System.in);
        String binary = scanner.next();

        for(int i = binary.length() - 1; i >= 0; i--) {
            if(binary.charAt(i) == '1') {
                decimal += base;
            }
            base *= 2;
        }
        System.out.println("The decimal is " + decimal);
    }

    public void decimalToBinary() {
        System.out.print("Enter a decimal number: ");
        Scanner scanner = new Scanner(System.in);
        int decimal = scanner.nextInt();

        StringBuilder binary = new StringBuilder();
        while (decimal > 0) {
            binary.insert(0, decimal % 2);
            decimal /= 2;
        }

        System.out.println("The binary is " + binary);
    }

    private int inputIntegerWithPrompt(String prompt) {
        while (true) {
            displayPrompt(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                displayErrorMessage("Only whole numbers allowed.");
            }
        }
    }

    public void displayPrompt(String prompt) {
        System.out.println(prompt);
        System.out.print("> ");
    }

    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
