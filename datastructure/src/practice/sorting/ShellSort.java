package practice.sorting;

public class ShellSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        int d = array.length / 2;
        while (d > 0) {
            for(int i = 0; i < array.length - d; i++) {
                int j = i;
                while (j >= 0 && array[j] > array[j + d]) {
                    int temp = array[j];
                    array[j] = array[j + d];
                    array[j + d] = temp;
                    j--;
                }
                System.out.println("Шаг: " + (i+1));
                dumpArray(array);
            }
            d = d / 2;
        }
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }


}
