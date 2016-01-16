package sample.Model;

public class Coordinates {
    int x;
    int y;

    public Coordinates checkingMove(int x, int y){
        return new Coordinates(this.x+x, this.y+y);
    }

    public Coordinates move (int x, int y){
        this.x+=x;
        this.y+=y;
        return this;
    }

    public Coordinates(Coordinates other){
        this.x=other.x;
        this.y=other.y;
    }

    public Coordinates(int x, int y){
        this.x=x;
        this.y=y;
    }


    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 10000 * result + y;
        return result;
    }
}
