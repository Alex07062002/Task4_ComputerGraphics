package com.company;

public class NumbersList {
    private static int[] numbers;

    public static void generateList(int amount) {
        numbers = new int[amount];

        double spacing = (double) Configs.APPLICATION_HEIGHT / amount, height = spacing;
        for(int i = 0; i < amount; i++) {
            numbers[i] = (int) (height);

            height += spacing;
        }
    }

    public static void randomizeList() {
        for(int i = 0; i < NumbersList.numbers.length; i++) {
            int temp = NumbersList.numbers[i];
            int randomPlace = (int) (Math.random() * NumbersList.numbers.length);

            NumbersList.numbers[i] = NumbersList.numbers[randomPlace];
            NumbersList.numbers[randomPlace] = temp;
        }
    }

    public static int[] getList() {
        return NumbersList.numbers;
    }
}

