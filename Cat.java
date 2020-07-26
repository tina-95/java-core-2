public class Cat extends Team{
    int MaxHeight;
    int MaxLength;

    public Cat (int maxHeight,int maxLength){
        this.MaxHeight=maxHeight;
        this.MaxLength=maxLength;
    }


    public int getMaxHeight(){
        return MaxHeight;
    }
    public int getMaxLength(){
        return MaxLength;
    }

    @Override
    public String toString(){
        return "Cat";
    }
}
