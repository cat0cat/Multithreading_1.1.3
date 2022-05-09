import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;

public class Main {

    public static final int SIZE = 9_999_999 ;

    public static void main(String[] args) {
        Integer[] array = createArray();
        System.out.println("Массив случайных чисел: " + Arrays.toString(array));
        System.out.println("Размерность массива: " + SIZE);

        //однопоточное решение
        long start = System.currentTimeMillis();
        int sum = sumArray(array);
        int average = sum / array.length;
        long finish = System.currentTimeMillis();
        System.out.println("Однопоточное решение:" + System.lineSeparator() +
                "Сумма элементов массива " + sum + System.lineSeparator() +
                "Среднее арифметическое элементов массива " + average + System.lineSeparator() +
                "Время выполнения " + (finish - start) + " мс" + System.lineSeparator());

        //многопоточное решение
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        start = System.currentTimeMillis();
        sum = forkJoinPool.invoke(new ArraySumTask(0, array.length, array));
        average = sum / array.length;
        finish = System.currentTimeMillis();
        System.out.println("Многопоточное решение:" + System.lineSeparator() +
                "Сумма элементов массива " + sum + System.lineSeparator() +
                "Среднее арифметическое элементов массива " + average + System.lineSeparator() +
                "Время выполнения " + (finish - start) + " мс");
    }


    public static Integer[] createArray() {
        //метод генерации целочисленного массива
        Integer[] arr = new Integer[SIZE];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(SIZE);
        }
        return arr;
    }
    public static Integer sumArray(Integer[] arr){
        //метод подсчета суммы значений элементов целочисленного массива
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        return sum;
    }

}