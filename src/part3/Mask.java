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

    static Mask getNextMaskByUsingOldMask_and_StartPicture(Mask oldMask) throws EndOfPictureException {
        Point currentA = oldMask.getA();

        if (currentA.getX() == StartPicture.startPictureArr.length - 1){
            //move from left part of arr by 1 low

            currentA.setX(0);
            currentA.setY(currentA.getY()+1);

            if (currentA.getY() > StartPicture.MAX_Y) throw new EndOfPictureException();

            return getNextMaskByUsingOldMask_and_StartPicture(Mask.createMaskOnlyWithA_FromPoint(currentA));
        }else{
            //move right
            Point a = Point.createPointFromXY(currentA.getX() + 1, currentA.getY());

            Point b = currentA;

            Point c = Point.createPointFromXY(currentA.getX() + 1, currentA.getY() - 1);

            Mask nextMask = new Mask();

            if (Point.existByPointA_and_borders_maxX_and_maxY(a, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setA(a);
            }
            if (Point.existByPointA_and_borders_maxX_and_maxY(b, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setB(b);
            }
            if (Point.existByPointA_and_borders_maxX_and_maxY(c, StartPicture.MAX_X, StartPicture.MAX_Y)){
                nextMask.setC(c);
            }
            return nextMask;
        }
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
}
