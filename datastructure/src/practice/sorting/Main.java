package practice.sorting;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Введите числа:");
        Scanner scanner = new Scanner(System.in);
        int[] array = new Main().convertToInt(scanner.nextLine());
//        SortingMethod method = new BubbleSort();
//        SortingMethod method = new SelectionSort();
//        SortingMethod method = new InsertionSort();
//        SortingMethod method = new MergeSort();
//        SortingMethod method = new ShellSort();
//        SortingMethod method = new QuickSort();
//        SortingMethod method = new CocktailSort();
        SortingMethod method = new HeapSort();
//        SortingMethod method = new RadixSort();
        method.sortItems(array);
    }

    private int[] convertToInt(String string) {
        String numbers[] = string.split("\\s");
        int[] array = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            try {
                array[i] = Integer.parseInt(numbers[i]);
            } catch (Exception e) {
                continue;
            }
        }
        return array;
    }
}