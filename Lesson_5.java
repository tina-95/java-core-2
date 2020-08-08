import java.util.Arrays;


public class Lesson_5 {
    /*1. Необходимо написать два метода, которые делают следующее:
            1) Создают одномерный длинный массив, например:
    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];
2) Заполняют этот массив единицами;
3) Засекают время выполнения: long a = System.currentTimeMillis();
4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
    arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
5) Проверяется время окончания метода System.currentTimeMillis();
6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
    Отличие первого метода от второго:
    Первый просто бежит по массиву и вычисляет значения.
    Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.

    Пример деления одного массива на два:
            System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h);

    Пример обратной склейки:
            System.arraycopy(a1, 0, arr, 0, h);
System.arraycopy(a2, 0, arr, h, h);

    Примечание:
            System.arraycopy() копирует данные из одного массива в другой:
            System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
    По замерам времени:
    Для первого метода надо считать время только на цикл расчета:
            for (int i = 0; i < size; i++) {
        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
    }
    Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.*/

    public static void main(String[] args) {

        Method1();
        Method2();
    }

    public static void Method1(){

        final int size = 10000000;
        final int h = size / 2;
        float[] arr1 = new float[size];
        arrCompletion(arr1);

        long a = System.currentTimeMillis();


        newValue(arr1);
        System.out.println("Time Method1 : " + (System.currentTimeMillis()-a));
    }

    public static void Method2(){

        final int size = 10000000;
        final int h = size / 2;
        float[] arr2 = new float[size];
        arrCompletion(arr2);

        long b = System.currentTimeMillis();

        Thread myThread1 = new Thread(new Runnable()
        {
            public void run()
            {
                float[] arrPart1 = Arrays.copyOfRange(arr2, 0, arr2.length/2);
                newValue(arrPart1);
                System.arraycopy(arrPart1,0,arr2,0,arr2.length/2);
            }
        });

        Thread myThread2 = new Thread(new Runnable()
        {
            public void run()
            {
                float[] arrPart2 = Arrays.copyOfRange(arr2, arr2.length/2, arr2.length);
                newValue(arrPart2);
                System.arraycopy(arrPart2,0,arr2,arr2.length/2,arr2.length/2);
            }
        });
        myThread1.start();
        myThread2.start();
       try {
           myThread1.join();
           myThread2.join();
       }
       catch (Exception e){

       }


        newValue(arr2);
        System.out.println("Time Method2 : " + (System.currentTimeMillis()-b));



    }

    public static void arrCompletion(float [] arr){
        for (int i =0;i<arr.length;i++){
            arr[i]=1;

        }

    }

    public static void newValue(float [] arr){
        for (int i =0; i<arr.length;i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
