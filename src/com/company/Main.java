package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в консольное приложение!");
        System.out.println("1.\tВы зададите число, которое определит длину массива целых чисел.\n" +
                "2.\tЗаполните массив значениями.\n" +
                "3.\tВыберете из массива два числа по индексу.\n" +
                "4.\tПолучите результат деления первого числа на второе.\n");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            int lengthArray = readInteger(buffer, "Введите целое число длины массива array: ");
            int[] array = new int[lengthArray];
            for (int i = 0; i < lengthArray; i++) {
                array[i] = readInteger(buffer, "Введите целое число для array[" + i + "] = ");
            }
            // Получили массив из двух значений
            int[] ar = readIndex(buffer, array, "Выберите значение массива по индексу: ");
            // Разделили первое число на второе
            int res = ar[0] / ar[1]; // ожидаем ArithmeticException - только для int
            // для получения результата double приводим делимое к double
            System.out.println("Результат: " + ar[0] + " / " + ar[1] + " = " + ((double) ar[0] / ar[1]));
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(e.getMessage());
        }
        finally {
            System.out.println("Если хотите повторить выполнение, запустите приложение еще раз.");
        }

    }

    private static int readInteger(BufferedReader buffer, String prompt) {
        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(buffer.readLine());
            } catch (NumberFormatException | IOException e) {
                if (++count == maxTries) {
                    try {
                        System.out.print("Ошибка введенного числа: ");
                        throw e;
                    } catch (IOException ioException) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static int[] readIndex(BufferedReader buffer, int[] array, String prompt) {
        int a;
        int count = 0; // счетчик попыток введения значения
        int maxTries = 3; // ограничим 3 попытками
        int j = 0;
        int[] ar = new int[2];
        while (true) {
            try {
                while (j < 2) {
                    a = readInteger(buffer, prompt); //NumberFormatException
                    ar[j] = array[a]; //ArrayIndexOutOfBoundsException
                    j++;
                }
                return ar;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                if (++count == maxTries) {
                    System.out.print("Ошибка введенного индекса массива: ");
                    throw e;
                }
            }
        }
    }
}