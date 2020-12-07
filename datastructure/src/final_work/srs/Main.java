package final_work.srs;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        new Main().arrays();
    //    new Main().sortingMethods();
        new Main().lists();
    }

    /*
     * Даны три одномерных массива: А, В, С размерностью N элементов.
     * Какой из массивов имеет минимальное значение первого элемента,
     * принадлежащее отрезку [p, q] (при просмотре массива с начала).
     */
    private void arrays() {
        Scanner scanner = new Scanner(System.in);
        int[] A = {1, 3, 5, 3, 2, 8};
        int[] B = {10, 14, 25, 13, 0, 8};
        int[] C = {0, 4, 15, 9, 4, 1};

        System.out.println("Введите отрезок [p;q]:");
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        /*
         * Если индекс конечного элемента отрезка меньше,
         * то поменять значения
         */
        p = p > q ? q : p;

        /*
         * Вычисление минимального первого элемента из указанного отрезка
         */
        if (A[p] <= B[p] && A[p] <= C[p]) {
            System.out.println("A");
        } else if (B[p] <= A[p] && B[p] <= C[p]) {
            System.out.println("B");
        } else if (C[p] < B[p] && C[p] < A[p]) {
            System.out.println("C");
        }
    }

    /*
     * Информация о студентах группы:
     * ФИО, пол, год рождения, средний доход на одного человека в семье, нуждается в общежитии (да / нет)
     *
     * Рекурсивный алгоритм быстрой сортировки
     *
     * Список студентов, которые могут рассчитывать на общежитие.
     */
    private void sortingMethods() {
        Student students[] = new Student[11];

        students[0] = new Student("Уланов Элмурат", 'М', "13.05.1998", 9000, true);
        students[1] = new Student("Абаканов Мурат", 'М', "17.05.1999", 8900, true);
        students[1] = new Student("Мирланов Эсен", 'М', "31.03.1999", 5900, false);
        students[2] = new Student("Адурахимова Эльнура", 'Ж', "01.12.1998", 5800, true);
        students[3] = new Student("Аманова Айпери", 'Ж', "07.07.1998", 15200, false);
        students[4] = new Student("Кутман уулу Эшим", 'М', "19.08.1997", 7900, true);
        students[5] = new Student("Эрмеков Уулбек", 'М', "12.09.1998", 12000, false);
        students[6] = new Student("Ажымудинов Эрсултан", 'М', "25.03.1998", 14350, true);
        students[7] = new Student("Эржанов Бакыт", 'М', "11.02.1998", 7200, true);
        students[8] = new Student("Максатов Алинур", 'М', "01.06.1998", 10800, true);
        students[9] = new Student("Муратова Меерим", 'Ж', "18.04.1998", 11000, true);
        students[10] = new Student("Абирова Алтынай", 'Ж', "19.07.1998", 2000, false);

        /*
         * Присвоение отсортированного массива
         */
        students = quickSort(students, 0, students.length - 1);

        /*
         * Сдвинуть в конец студентов, которые не нуждаются в общежитии
         */
        for (int i = 0; i < students.length; i++) {
            if (!students[i].isNeedHostel()) {
                for(int j = i; j < students.length - 1; j++) {
                    Student temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }

        /*
         * Студенты, которые не нуждаются в общежитии, но имеют малый средний доход будут в списке первее
         */
        for (int i = 0; i < students.length - 1; i++) {
            if(students[i].isNeedHostel()) continue;
            if (students[i].getAverageIncome() > students[i + 1].getAverageIncome()) {
                Student temp = students[i];
                students[i] = students[i + 1];
                students[i + 1] = temp;
            }
        }

        /*
         * Вывод отсортированного списка
         */
        for (Student student : students) {
            System.out.println(student);
        }
    }


    /*
     * Рекурсивный алгоритм быстрой сортировка
     * Сортировка по среденему доходу студентов
     */
    private Student[] quickSort(Student[] array, int left, int right) {
        if (left >= right) {
            return array;
        }
        double pivot = array[(left + right) / 2].getAverageIncome();
        int index = partition(array, left, right, pivot);
        array = quickSort(array, left, index - 1);
        array = quickSort(array, index, right);
        return array;
    }

    private int partition(Student[] array, int left, int right, double pivot) {
        while (left <= right) {
            while (array[left].getAverageIncome() < pivot) {
                left++;
            }

            while (array[right].getAverageIncome() > pivot) {
                right--;
            }

            if (left <= right) {
                Student temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }

    /*
     * Написать процедуру(ы), которая находит среднее арифметическое всех элементов
     * непустого списка L (тип элементов - real).
     */
    private void lists() {
        double[] list = {10, 3.8, 98, 23.1, 54.9, 34};
        System.out.println("Среднее арифметическое равно:");
        System.out.println(arithmeticMean(list));
    }

    /*
     * Метод для вычисления среднего значения элементов списка
     */
    private double arithmeticMean(double[] L) {
        /*
         * Для хранения суммы
         */
        double sum = 0;

        /*
         * Суммирвание всех элементов листа
         */
        for (double i : L) {
            sum += i;
        }

        /*
         * Деление суммы на количество элементов в листе
         */
        sum /= L.length;
        return sum;
    }
}
