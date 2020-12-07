package practice.sorting;

public abstract class SortingMethod {

    abstract int[] sortItems(int[] array);

    void dumpArray(int[] array) {
        for(int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
