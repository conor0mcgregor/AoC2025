package software.aoc.day1.a;

public class Dial {
    private int position;

    private Dial () {this.position = 50;}
    public static Dial create() { return new Dial();}


    public int getPosition() {
        return position;
    }

    public void rotate(int rotation) {
        position = (position + rotation) % 100;
        ajustPosition();
    }

    private void ajustPosition() {
        if (position < 0) position += 100;
    }
}
