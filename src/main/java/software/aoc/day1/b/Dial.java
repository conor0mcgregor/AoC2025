package software.aoc.day1.b;

public class Dial {
    private final int DIAL_SIZE = 100;
    private int position;
    private int password;

    private Dial () {
        this.position = DIAL_SIZE/2;
        this.password = 0;
    }
    public static Dial create() { return new Dial();}


    public int getPosition() {
        return position;
    }

    public void rotate(int rotation) {
        updatePassword(rotation);
        position = ((position + rotation) % DIAL_SIZE + DIAL_SIZE) % DIAL_SIZE;
    }

    private void updatePassword(int rotation) {
        password += countClicksInZero(rotation);

    }

    private int countClicksInZero(int rotation) {
        return rotation < 0 ? countLeftCliks(-rotation) : countRightCliks(rotation);
    }

    private int countRightCliks(int steps) {
        return (position + steps) / 100;
    }

    private int countLeftCliks(int steps) {
        int fullCicles = steps/100;
        int rest = steps % 100;
        return (rest >= position && position != 0)? fullCicles+1 : fullCicles;
    }


    public int getPassword() {
        return password;
    }
}
