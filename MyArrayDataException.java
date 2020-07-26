public class MyArrayDataException extends RuntimeException {
    private int i, j;
    private String wrongValue;

    public MyArrayDataException(int i, int j, String wrongValue) {
        super("Wrong element [" + i + "," + j + "]=" + wrongValue);
        this.i = i;
        this.j = j;
        this.wrongValue = wrongValue;
    }
}
