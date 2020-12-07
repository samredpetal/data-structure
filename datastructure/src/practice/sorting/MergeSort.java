package practice.sorting;

public class MergeSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        array = mergeSort(array);
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }

    private int[] mergeSort(int[] array) {
        if (array.length > 1) {
            int size = (int)Math.ceil((double) array.length / 2.0);
            int[] array1 = new int[size];
            int[] array2 = new int[array.length - size];
            System.arraycopy(array, 0, array1, 0, array1.length);
            System.arraycopy(array, size, array2, 0, array2.length);
            array1 = mergeSort(array1);
            array2 = mergeSort(array2);
            return merge(array1, array2);
        }
        return array;
    }

    private int[] merge(int[] array1, int[] array2) {
        int[] array = new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                array[k] = array1[i];
                i++;
            } else {
                array[k] = array2[j];
                j++;
            }
            k++;
        }
        if (i == array1.length) {
            System.arraycopy(array2, j, array, k, array2.length - j);
        }else{
            System.arraycopy(array1, i, array, k, array1.length - i);
        }

        return array;
    }


}



