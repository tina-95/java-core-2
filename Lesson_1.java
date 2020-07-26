public class Lesson_1 {

    public static void main(String[] args) {
        Team[] team = new Team[3];
        team[0] = new Human(300, 200);
        team[1] = new Cat(600,300);
        team[2] = new Robot(700,700);

        Size[] barriers = new Size[5];
        barriers[0] = new Wall(200);
        barriers[1] = new Wall(250);
        barriers[2] = new TreadMill(300);
        barriers[3] = new Wall(400);
        barriers[4] = new TreadMill(700);

        for (int i = 0; i < team.length; i++) {
            for (int j = 0; j < barriers.length; j++) {
                if (barriers[j] instanceof Wall) {
                    if (!team[i].jump(barriers[j].getSize())) {
                        break;
                    }
                }
                else{
                    if(!team[i].run(barriers[j].getSize())){
                        break;
                    }
                }
            }

        }
    }
}
