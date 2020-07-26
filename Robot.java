public class Robot extends Team {
     int MaxHeight;
     int MaxLength;

     public Robot (int height, int length){
         this.MaxHeight=height;
         this.MaxLength=length;
     }
    public int getMaxHeight(){
         return MaxHeight;
    }
    public int getMaxLength(){
         return MaxLength;
    }

    @Override
    public String toString(){
        return "Robot";
    }

}
