package practice.sorting;

public class InsertionSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        for(int i = 0; i < array.length; i++) {
            for(int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    array[j] += array[j - 1];
                    array[j - 1] = array[j] - array[j - 1];
                    array[j] = array[j] - array[j - 1];
                }
            }
            System.out.println("Шаг: " + (i+1));
            dumpArray(array);
        }
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }
}
