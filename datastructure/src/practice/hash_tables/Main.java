package practice.hash_tables;

public class Main {
    public static void main(String[] args) {
        HashTable table = new HashTable(7);
        table.addByQuadraticProbing(8);
        table.addByQuadraticProbing(33);
        table.addByQuadraticProbing(15);
        table.addByQuadraticProbing(26);
        table.addByQuadraticProbing(22);
        System.out.println(table);
        System.out.println(table.loadFactory());
        table.remove(8);
        System.out.println(table);

    }
}
