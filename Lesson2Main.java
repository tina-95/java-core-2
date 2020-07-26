public class Lesson2Main {
    private static Lesson2 lesson2 = new Lesson2();
    private static String[][] testArr1 = {{"1", "1", "1", "1"},{"1", "1", "1", "1"},{"1", "1", "1", "1"},{"1", "1", "1", "1"}};
    private static String[][] testArr2 = {{"1", "1", "1", "1"},{"1", "1", "1", "1"},{"1", "1", "1", "some text"},{"1", "1", "1", "1"}};

    public static void main(String[] args) {
        try {
            System.out.println("Sum=" + lesson2.method(testArr1));
        } catch (MyArrayDataException | MyArraySizeException e) {
            System.out.println(e.getMessage());
        }
    }

}
