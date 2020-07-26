public abstract class Team {
    public boolean jump(int height) {
        if(getMaxHeight()>=height){
            System.out.println(this +" прыгнул");
            return true;
        } else {
            System.out.println(this +" не прыгнул");
            return false;
        }

    }


    public boolean run( int length) {
        if(getMaxLength()>=length)
        {
            System.out.println(this+" пробежал");
            return true;
        }
        else {
            System.out.println(this+" не пробежал");
            return false;
        }

    }

    public abstract int getMaxHeight();
    public abstract int getMaxLength();

}
