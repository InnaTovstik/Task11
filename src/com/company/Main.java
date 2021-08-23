package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome To MyApp");
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        try {
            //1.Предложили пользователю ввести число. Это число будет длиной массива целых чисел
            int lengthArray = readInteger(buffer, "Please Enter The Number To Length Array: ");
            int[] array = new int[lengthArray];
            //2.	Предложили пользователю заполнить массив.
            for (int i = 0; i < lengthArray; i++) {
                array[i] = readInteger(buffer, "Enter The Value Of The Array Element a[" + i + "] = ");
            }
            //Предложили пользователю выбрать 2 числа из массива по индексу
            int[] ar = readIndex(buffer, array);
            // Разделили первое число на второе
            int rez = ar[0] / ar[1]; // ожидаем ArithmeticException только для int
            // для получения результата double приводим делимое к double
            System.out.println("rezult = " + ((double) ar[0] / ar[1]));
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }

    }

    private static int readInteger(BufferedReader buffer, String prompt) {
        int count = 0;
        int maxTries = 3;
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(buffer.readLine());
            } catch (NumberFormatException | IOException e) {
                if (++count == maxTries) try {
                    throw e;
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    }

    private static int[] readIndex(BufferedReader buffer, int[] array) {
        int a;
        int count = 0; // счетчик попыток введения значения
        int maxTries = 5; // ограничим 5 попытками
        int j = 0;
        int[] ar = new int[2];
        String prompt = "Select Index Of Number: ";
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
                    throw e;
                }
            }
        }
    }
}