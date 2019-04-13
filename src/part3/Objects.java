package part3;

import java.util.HashMap;
import java.util.Map;

class Objects {
    private int[][] arrOfObjects;

    private int number = 0;

    private Map<Integer,Integer> map = new HashMap<>();

    private Objects() {
        this.setArrOfObjects(StartPicture.startPictureArr.clone());
        for(int i=0; i<arrOfObjects.length; i++){
            arrOfObjects[i] = StartPicture.startPictureArr[i].clone();
        }
        this.clearArr(getArrOfObjects());
        printObjectsAsArray();
    }

    static Objects createObjectsArrAndInfo(){
        return new Objects();
    }

    private void clearArr(int[][] arr) {
        for(int height = 0; height <= StartPicture.MAX_Y; height++){
            for (int width = 0; width <= StartPicture.MAX_X; width++){
                arr[height][width] = 0;
            }
        }
    }

    public int[][] getArrOfObjects() {
        return arrOfObjects;
    }

    void setArrOfObjects(int[][] arrOfObjects) {
        this.arrOfObjects = arrOfObjects;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Map<Integer, Integer> getMap() {
        return map;
    }

    public void setMap(Map<Integer, Integer> map) {
        this.map = map;
    }

    void doLogic(Mask mask) {
        if (!labeledA(mask)){
            //do nothing
            Lab1_part3.log("1");
        }else if (labeledA(mask) && !labeledB(mask) && !labeledC(mask)) {//a==1, b==0, c==0
            Lab1_part3.log("2");
            Point p = mask.getA();
            arrOfObjects[p.getY()][p.getX()] = ++number;
        }else if (labeledA(mask) && !labeledB(mask) && labeledC(mask)) {
            Lab1_part3.log("3");
            Point a = mask.getA();
            Point c = mask.getC();
            //maybe inc?
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[c.getY()][c.getX()];
        }else if (labeledA(mask) && labeledB(mask) && !labeledC(mask)) {
            Lab1_part3.log("4");
            Point a = mask.getA();
            Point b = mask.getB();
            //maybe inc?
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[b.getY()][b.getX()];
        }else if (labeledB(mask) && labeledC(mask)) {
            Lab1_part3.log("5");
            Point a = mask.getA();
            Point b = mask.getB();
            Point c = mask.getC();

            //arrOfObjects[c.getY()][c.getX()] = arrOfObjects[b.getY()][b.getX()];
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[b.getY()][b.getX()];
        }else{
            Lab1_part3.log("not detected !");
        }
    }

    private int valueFromPicture(Point point){
        return StartPicture.startPictureArr[point.getY()][point.getX()];
    }

    private boolean labeledA(Mask mask){
        return  (mask.getA()!=null && valueFromPicture(mask.getA())>0);
    }

    private boolean labeledB(Mask mask){
        return (mask.getB()!=null && valueFromPicture(mask.getB())>0);
    }

    private boolean labeledC(Mask mask){
        return (mask.getC()!=null && valueFromPicture(mask.getC())>0);
    }

    private boolean a_equals_0(Mask mask){
        Point a = mask.getA();

        if (valueFromPicture(a) == 0){
            Lab1_part3.log("position 1");
            return true;
        }
        return false;
    }

    void printObjectsAsArray(){
        System.out.println("height = "+arrOfObjects.length);
        System.out.println("width = "+arrOfObjects[0].length);

        for(int height=0; height<arrOfObjects.length; height++){
            for (int width=0; width<arrOfObjects[height].length; width++){
                //спочатку пишеться ВИСОТА, потім - ШИРИНА
                System.out.print(arrOfObjects[height][width]+" ");
            }
            System.out.println();
        }
    }
}
