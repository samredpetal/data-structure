package practice.sorting;


public class HeapSort extends SortingMethod {

    @Override
    int[] sortItems(int[] array) {
        System.out.println("Начальное состояние:");
        dumpArray(array);
        int n = array.length;

        // Куча из массива
        for(int i = n/2 - 1; i >= 0; i--){
            heapify(array, n, i);
            System.out.println("Текущее состояние: ");
            dumpArray(array);
        }

        // Извлечение чисел по одному из кучи
        for(int i = n - 1; i >= 0; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;

            heapify(array, i, 0);
        }

        System.out.println("Конечное состояние");
        dumpArray(array);
        return array;
    }

    /**
     *
     * @param array
     * @param n - указывает на размер массива
     * @param i - указывает на самый большой элемент
     */
    private void heapify(int array[], int n, int i) {
        int biggest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;

        if (l < n && array[l] > array[biggest]) {
            biggest = l;
        }

        if (r < n && array[r] > array[biggest]) {
            biggest = r;
        }

        // Если корень не самый большой элемент
        if (biggest != i) {
            int temp = array[i];
            array[i] = array[biggest];
            array[biggest] = temp;

            heapify(array, n, biggest);
        }
    }

}
