package software.aoc.day1.a;

public class Dial {
    private int position;
    private int password;
    private final int DIAL_SIZE = 100;

    private Dial () {this.position = DIAL_SIZE/2;}
    public static Dial create() { return new Dial();}


    public int getPosition() {
        return position;
    }

    public void rotate(int rotation) {
        position = ((position + rotation) % DIAL_SIZE + DIAL_SIZE) % DIAL_SIZE;
        updatePassword();
    }

    private void updatePassword() {
        if (position == 0) password++;
    }

    public int getPassword() {
        return password;
    }
}
