package final_work.control_work;


import practice.collections.linkedList.LinkedList;
import practice.collections.queue.RoundQueue;
import practice.collections.stack.Stack;
import practice.graph.Graph;
import practice.trees.Node;
import practice.trees.binaryTree.BinaryTreeNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        new Main().stacks();
//        new Main().lists();
//        new Main().queues();
//        new Main().mapsAndHashtables();
//        new Main().treesAndPyramids1();
//        new Main().treesAndPyramids2();
        new Main().graphs();

    }

    /*
     * Задача «Ханойская башня» имеется три колышка. На один из них нанизаны
     * диски, отсортированные по размеру — от самого большого до самого маленького.
     *
     * Ваша задача — переместить их с одного колышка на другой, сохранив порядок. При этом
     * нельзя брать больше одного диска за раз и класть больший диск на меньший.
     */
    private void stacks() {
        Stack<Integer> tower1 = new Stack<>();
        Stack<Integer> tower2 = new Stack<>();
        Stack<Integer> tower3 = new Stack<>();
        tower1.push(3);
        tower1.push(2);
        tower1.push(1);

        /*
         * Вывод начального состояния
         */
        for (int i : tower1) {
            System.out.println(i);
        }

        tower3.push(tower1.pop()); // 1 -> 3

        tower2.push(tower1.pop()); // 1 -> 2
        tower2.push(tower3.pop()); // 3 -> 2

        tower3.push(tower1.pop()); // 1 -> 3

        tower1.push(tower2.pop()); // 2 -> 1

        tower3.push(tower2.pop()); // 2 -> 3
        tower3.push(tower1.pop()); // 1 -> 3

        System.out.println();
        for (int i : tower3) {
            System.out.println(i);
        }
    }

    /*
     * «Задача нахождения цикла » . Есть произвольный односвязный кольцевой
     * список, в которой включены не все ячейки (т.е. в котором может быть петля) необходимо
     * предложить алгоритм который бы позволил определить, есть ли цикл.
     */
    private void lists(){
        LinkedList<Character> list = new LinkedList<>();
        list.add('A');
        list.add('B');
        list.add('C');
        list.add('D');
        list.add('E');
        list.add('F');
        list.add('G');
        list.add('H');
        list.add('I');
        /*
         * Присвоить следующий элемент элемента (I) элемент (D)
         */
//        list.getNode(8).setNextNode(list.getNode(3));
//        System.out.println(list.getNode(8).getNextNode().getData());

        /*
         * Переменная для хранения найден ли цикл
         */
        boolean isLoop = false;

        /*
         * Цикл будет сравнивать элементы между собой на равность
         * Не будут проверяться на равность только те элементы индексы которых сходяться
         */
        for(int i = 0; list.getNode(i).hasNext(); i++) {
            for(int j = 0; j < list.size(); j++) {
                /*
                 * если индексы элементов не похожи,
                 * но они равный между собой,
                 * то выйти из цикла и сделать isLoop true
                 */
                if (i != j && list.getNode(i) == list.getNode(j)) {
                    isLoop = true;
                    break;
                }
            }
            if(isLoop) break;
        }

        /*
         * Вывод информации
         */
        if(isLoop) System.out.println("Loop!");
        else System.out.println("No loop!");
    }

    /*
     * Рассмотрим круг из n человек, пронумерованных от 1 до n. Затем будем
     * исключать каждого второго из круга до тех пор, пока не уцелеет только один человек.
     *
     * Тогда процесс исключения при n=10 будет таким: 2 4 6 8 10 3 7 1 9. А номер Иосефуса
     * будет равно 5.
     *
     * Напишите программу, который принимает два аргумента командной строки m и n и
     * выводит порядок, в котором люди удаляются (и таким образом будет показывать
     * Иосефуса, где ему стать в круге).
     */
    private void queues() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите числа:");
        /*
         *
         */
        int m = scanner.nextInt();

        /*
         * Количество солдатов
         */
        int n = scanner.nextInt();

        RoundQueue<Integer> queue = new RoundQueue<>(n);

        /*
         * Заполнить очередь числами от 1 до n
         */
        for(int i = 1; i <= n; i++) {
            queue.enqueue(i);
        }


        /*
         * Число для Иосифа
         */
        int josephus = queue.front();

        /*
         * Будет удалять числа под четным индеском,
         * пока очередь не станет пустым.
         * Если в очереди останется только один элемент,
         * то присваиваем число переменной josephus
         */
        for(int i = 1; !queue.isEmpty(); i++) {
            if (queue.size() == 1) {
                josephus = queue.front();
                break;
            }
            if (i % m == 0) {
                System.out.println("Убит " + queue.dequeue());
            } else {
                queue.enqueue(queue.dequeue());
            }
        }

        /*
         * Вывод числа Иосифа
         */
        System.out.println("Номер Иосифа: " + josephus);
    }

    /*
     * Предположим, у вас есть следующая содержимое текста в файле Text.txt:
     * hello, mud, yellow, book, mud, car, bank, mud, hello, book, book, ruby, yellow, cow, toenail, bank
     *
     * Напишите программу, которая будет подсчитывать количество вхождений каждого
     * слова и представлять их в алфавитном порядке.
     */
    private void mapsAndHashtables() {
        HashMap<String, Integer> map = new HashMap<>();

        /*
         * Адрес и название файла с текстом
         */
        String fileName = "src/final_work/control_work/Text.txt";

        /*
         * Если файла нет, то выводим сообщение
         */
        try {
            /*
             * Считываем файл
             */
            Scanner scanner = new Scanner(new File(fileName));

            /*
             * Присваиваем содержимое файла в переменный text
             */
            String text = scanner.nextLine().trim();

            /*
             * Распределим кажое слово в массив
             */
            String[] words = text.split("[,]\\s");

            /*
             * Будем помещать количество каждого слова в
             * HashMap под ключом самого слова,
             * если слово уже имеется будем просто увеличивать его значение на один.
             */
            for (String word : words) {
                if (map.containsKey(word)) {
                    map.put(word, map.get(word) + 1);
                }else{
                    map.put(word, 1);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
            e.printStackTrace();
        }

        /*
         * Выводим слова и их количества.
         */
        for (Map.Entry<String, Integer> entry: map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    /*
     * Применение двоичных деревьев для представления алгебраических
     * выражений. Напишите программу, которая будет выводить алгебраическое выражение в
     * виде infix, prefix и postfix форме.
     */
    private void treesAndPyramids1() {
        /*
         * Создание бинарного дерева
         * Значения корня будет +
         */
        BinaryTreeNode tree = new BinaryTreeNode(0, "+");

        /*
         * Значение правого элемента корня -
         */
        tree.setRight(new BinaryTreeNode(1, "-"));

        /*
         * Значение левого элемента корня 50
         */
        tree.setLeft(new BinaryTreeNode(2, "50"));

        /*
         * Значение правого элемента правого потомка корня 50
         */
        tree.getRight().setRight(new BinaryTreeNode(3, "50"));

        /*
         * Значение левого элемента правого потомка корня 85
         */
        tree.getRight().setLeft(new BinaryTreeNode(4, "85"));

        /*
         * Вывод как постфикс
         */
        System.out.println("Postfix");
        for (Node node : tree.postOrder()) {
            System.out.print(node.getData() + " ");
        }

        /*
         * Вывод как инфикс
         */
        System.out.println();
        System.out.println("Infix");
        for (Node node : tree.inOrder()) {
            System.out.print(node.getData() + " ");
        }

        /*
         * Вывод как префикс
         */
        System.out.println();
        System.out.println("Prefix");
        for (Node node : tree.preOrder()) {
            System.out.print(node.getData() + " ");
        }
        System.out.println();
    }

    /*
     * Напишите программу реализации пирамидальной структуры для следующей
     * последовательности чисел: 100, 90, 80, 30, 60, 50, 70, 20, 10, 40. В результате выполнения
     * программы выведите на экран полученную пирамиду в следующем виде.
     */
    private void treesAndPyramids2() {
        int[] array = {100, 90, 80, 30, 60, 50, 70, 20, 10, 40, 130};
        int j = 1, i = 0;
        while (i < array.length) {

            /*
             * Каждый раз будем выводить
             * По j элементов из массива
             */
            for(; i + 1 < j ; i++) {
                /*
                 * Если i станет больше числа элемнтов в массиве,
                 * то выходим из цикла
                 */
                if(!(i < array.length)) break;
                System.out.print(array[i] + " ");
            }
            System.out.println();

            /*
             * Увеличим j в 2 раза
             */
            j *= 2;
        }
    }

    /*
     * Задача выбора кратчайшего пути в графе. Реализовать алгоритм Дейкстры,
     * определения кратчайшего пути между вершинами 3 и 4 для графа представленного ниже.
     */
    private void graphs() {
        /*
         * Создадим граф
         */
        Graph graph = new Graph();

        /*
         * Добавим вершины
         */
        graph.addVertex('0');
        graph.addVertex('1');
        graph.addVertex('2');
        graph.addVertex('3');
        graph.addVertex('4');
        graph.addVertex('5');
        graph.addVertex('6');
        graph.addVertex('7');
        graph.addVertex('8');

        /*
         * Зададим ребра вершинам
         */
        graph.addEdge(3, 0, 1);

        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 4, 7);
        graph.addEdge(0, 6, 2);

        graph.addEdge(1, 7, 5);
        graph.addEdge(1, 4, 3);

        graph.addEdge(4, 7, 4);
        graph.addEdge(4, 6, 4);

        graph.addEdge(6, 3, 3);

        graph.addEdge(5, 2, 1);

        graph.addEdge(2, 8, 2);

        graph.addEdge(8, 5, 2);

//        graph.displayMat();

        System.out.println("Самое короткое расстояние от вершины 3 к вершине 4 равна:");

        /*
         * Выведем самое короткое расстояние от вершины 3 до вершины 4 в графе.
         */
        System.out.println(graph.dijkstraAlgorithm(3, 4));
    }
}
