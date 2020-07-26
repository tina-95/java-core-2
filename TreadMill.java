public class TreadMill implements Size {
int length;

public TreadMill(int length){
    this.length=length;
}

@Override
    public int getSize(){
    return length;
}
}
