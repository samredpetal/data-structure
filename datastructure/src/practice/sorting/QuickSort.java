package practice.sorting;

public class QuickSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        array = quickSort(array, 0, array.length - 1);
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }

    private int[] quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return array;
        }
        int pivot = array[(left + right) / 2];
        int index = partition(array, left, right, pivot);
        array = quickSort(array, left, index - 1);
        array = quickSort(array, index, right);
        return array;
    }

    private int partition(int[] array, int left, int right, int pivot) {
        while (left <= right) {
            while (array[left] < pivot) {
                left++;
            }

            while (array[right] > pivot) {
                right--;
            }

            if (left <= right) {
                int temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }


}
