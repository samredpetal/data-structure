package practice.hash_tables;

import java.util.Arrays;

public class HashTable {
    private int[] hashTable;

    public HashTable(int size) {
        this.hashTable = new int[size];
    }

    public boolean addByQuadraticProbing(int number) {
        int i = 0;
        return addByQuadraticProbing(number, i);
    }

    private boolean addByQuadraticProbing(int number, int i){
        if (hashTable[(number + i*i) % hashTable.length] != 0) {
            addByQuadraticProbing(number, i+1);
        }else{
            hashTable[(number + i*i) % hashTable.length] = number;
        }
        return true;
    }

    public boolean remove(int number){
        for(int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] == number) {
                hashTable[i] = 0;
                return true;
            }
        }
        return false;
    }

    public double loadFactory() {
        return ((double) size()) / ((double) hashTable.length);
    }

    public int size() {
        int size = 0;
        for(int i = 0; i < hashTable.length; i++) {
            if (hashTable[i] != 0) {
                size++;
            }
        }
        return size;

    }

    public int get(int i) {
        return hashTable[i];
    }

    @Override
    public String toString() {
        return "HashTable{" +
                "hashTable=" + Arrays.toString(hashTable) +
                '}';
    }
}
