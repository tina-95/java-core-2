public class Wall implements Size {
    int height;

    public Wall(int height){
        this.height=height;
    }

    @Override
    public int getSize() {
        return height;
    }
}
