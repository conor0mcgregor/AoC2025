package software.aoc.day1.a;

public class Dial {
    private int position;
    private int password;

    private Dial () {this.position = 50;}
    public static Dial create() { return new Dial();}


    public int getPosition() {
        return position;
    }

    public void rotate(int rotation) {
        position = ((position + rotation) % 100 + 100) % 100;
        updatePassword();
    }

    private void updatePassword() {
        if (position == 0) password++;
    }

    public int getPassword() {
        return password;
    }
}
