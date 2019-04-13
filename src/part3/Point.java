package part3;

class Point {
    private int x;
    private int y;

    private Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Point createPointFromXY(int x, int y){
        return new Point(x, y);
    }

    static boolean existByPointA_and_borders_maxX_and_maxY(Point point, int MAX_X, int MAX_Y){
        if (point.getX()>=0 && point.getX()<=MAX_X){
            if (point.getY()>=0 && point.getY()<=MAX_Y){
                return true;
            }
        }
        return false;
    }

    int getX() {
        return x;
    }

    void setX(int x) {
        this.x = x;
    }

    int getY() {
        return y;
    }

    void setY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "part3.Point{" +
               "y=" + y +
               ", x=" + x +
               '}';
    }
}
