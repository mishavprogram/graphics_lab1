package part3;

class Mask {
    private Point A;
    private Point B;
    private Point C;

    public Mask(){}

    private Mask(Point a) {
        A = a;
    }

    static Mask createMaskOnlyWithA_FromPoint(Point point){
        return new Mask(point);
    }

    static Mask getNextMask(Mask oldMask) {
        Point currentA = oldMask.getA();

        if (currentA.getX() == StartPicture.MAX_X){
            //move from left part of arr by 1 low

            currentA.setX(0);
            currentA.setY(currentA.getY()+1);
        }else {
            currentA = Point.createPointFromXY(currentA.getX()+1, currentA.getY());
        }
            //move right
            Point a = currentA;

            Point b = Point.createPointFromXY(currentA.getX()-1, currentA.getY());

            Point c = Point.createPointFromXY(currentA.getX(), currentA.getY() - 1);

            Mask nextMask = new Mask();//сюди потраплять лише існуючі точки

            if (Point.exist(a, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setA(a);
            }
            if (Point.exist(b, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setB(b);
            }
            if (Point.exist(c, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setC(c);
            }
            CountObjectsOnBinaryArray.log(nextMask.toString());
            return nextMask;
    }

    public Point getA() {
        return A;
    }

    public void setA(Point a) {
        A = a;
    }

    public Point getB() {
        return B;
    }

    public void setB(Point b) {
        B = b;
    }

    public Point getC() {
        return C;
    }

    public void setC(Point c) {
        C = c;
    }

    @Override
    public String toString() {
        return "Mask{" +
               "A=" + A +
               ", B=" + B +
               ", C=" + C +
               '}';
    }
}
