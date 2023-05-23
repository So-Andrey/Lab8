package allForDragons;

public class Coordinates {
    private long x; //Максимальное значение поля: 610
    private float y;
    public long getX() {
        return x;
    }
    public float getY() {
        return y;
    }

    public void setX(long x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Coordinates(long x, float y) {
        this.x = x;
        this.y = y;
    }
}