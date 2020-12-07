package practice.sorting;

public class RadixSort extends SortingMethod {
    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        array = radixSort(array);
        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }

    //TODO states output
    private int[] radixSort(int[] array) {
        int max = getMax(array);

        for(int exp = 1; max/exp > 0; exp *= 10) {
            int bucket[] = new int[array.length];
            int count[] = new int[10];

            for (int i : array) {
                count[(i / exp) % 10]++;
            }
            for(int i = 1; i < 10; i++) {
                count[i] += count[i - 1];
            }
            for(int i = array.length - 1; i >= 0; i--) {
                bucket[count[(array[i] / exp) % 10] - 1] = array[i];
                count[(array[i] / exp) % 10]--;
            }

            System.arraycopy(bucket, 0, array, 0, array.length);
        }

        return array;
    }

    private int getMax(int array[]) {
        int max = array[0];
        for (int i : array) {
            if (i > max) {
                max = i;
            }
        }
        return max;
    }

//    private int[] radixSort(int[] array) {
//        int m = array[0], exp = 1, n = array.length;
//        int[] b = new int[10];
//        for(int i = 1; i < n; i++) {
//            if (array[i] > m) {
//                m = array[i];
//            }
//        }
//        while (m / exp > 0) {
//            int[] bucket = new int[10];
//            for(int i = 0; i < n; i++) {
//                bucket[(array[i] / exp) % 10]++;
//            }
//            for(int i = 1; i < 10; i++) {
//                bucket[i] += bucket[i - 1];
//            }
//            for(int i = n - 1; i >= 0; i--) {
//                b[--bucket[(array[i] / exp) % 10]] = array[i];
//            }
//            for(int i = 0; i < n; i++) {
//                array[i] = b[i];
//            }
//            exp *= 10;
//        }
//        return array;
//    }

}


//1 23 2 11 3 5 4 6 55 7 84 59 87 66 54 99 0
