package practice.sorting;

public class SelectionSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        int min = 0;
        for(int i = 0; i < array.length; i++) {
            boolean check = false;
            for(int j = i; j < array.length; j++) {
                if (array[min] > array[j]) {
                    min = j;
                    check = true;
                }
            }
            if(check){
                array[min] += array[i];
                array[i] = array[min] - array[i];
                array[min] = array[min] - array[i];
            }
            min = i + 1;
            System.out.println("Шаг: " + (i+1));
            dumpArray(array);
        }
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }
}
