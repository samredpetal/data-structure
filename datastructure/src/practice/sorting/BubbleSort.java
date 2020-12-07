package practice.sorting;

public class BubbleSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            boolean check = false;
            for(int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    array[j] += array[j + 1];
                    array[j + 1]  = array[j] - array[j + 1];
                    array[j] = array[j] - array[j + 1];
                    check = true;
                }
                counter++;
                System.out.println("Шаг: " + counter);
                dumpArray(array);
            }
            if(!check) break;
        }
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }
}
