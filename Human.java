public class Human extends Team {
    int MaxHeight;
    int MaxLength;

    public int getMaxHeight(){
        return MaxHeight;
    }

    public int getMaxLength(){
        return MaxLength;
    }

    public Human(int MaxHeight, int MaxLength){
        this.MaxHeight=MaxHeight;
        this.MaxLength=MaxLength;
    }


    @Override
    public String toString(){
        return "Human";
    }
}
