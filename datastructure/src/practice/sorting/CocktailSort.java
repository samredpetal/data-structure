package practice.sorting;

public class CocktailSort extends SortingMethod {
    //TODO not practice.sorting correctly
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        array = cocktailSort(array);
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }

    private int[] cocktailSort(int[] array) {
        int left = 0, right = array.length;
        boolean check = true;
        int counter=0;
        while (check) {
            check = false;
            for(int i = left; i < right - 1; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i+1] = temp;
                    check = true;
                }
                left++;
                counter++;
                System.out.println("Шаг: " + counter);
                dumpArray(array);
            }

            if(!check) break;

            check = false;

            right--;

            for(int i = right - 1; i >= left; i--) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i+1] = temp;
                    check = true;
                }
            }
        }
        return array;
    }
}
