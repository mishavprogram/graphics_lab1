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
        //this.clearArr(getArrOfObjects());
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
        //тут equals - labeled/not labeled
        if (a_equals_0(mask)){//a==0
            //do nothing
        }else if (a_equals_1_and_other_equals_0(mask)) {//a==1, b==0, c==0
            Point p = mask.getA();
            arrOfObjects[p.getY()][p.getX()] = ++number;
        }else if (a_c_equals_1_and_b_equals_0(mask)) {
            Point a = mask.getA();
            Point c = mask.getC();
            //maybe inc?
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[c.getY()][c.getX()];
        }else if (a_b_equals_1_and_c_equals_0(mask)) {
            Point a = mask.getA();
            Point b = mask.getB();
            //maybe inc?
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[b.getY()][b.getX()];
        }else if (abc_equals_1(mask)) {
            Point a = mask.getA();
            Point b = mask.getB();
            Point c = mask.getC();

            arrOfObjects[c.getY()][c.getX()] = arrOfObjects[b.getY()][b.getX()];
            arrOfObjects[a.getY()][a.getX()] = arrOfObjects[b.getY()][b.getX()];
        }
    }

    private boolean a_equals_0(Mask mask){
        Point a = mask.getA();

        if (StartPicture.startPictureArr[a.getY()][a.getX()] == 0){
            Lab1_part3.log("position 1");
            return true;
        }
        return false;
    }

    private boolean a_equals_1_and_other_equals_0(Mask mask){
        Point a = mask.getA();
        Point b = mask.getB();
        Point c = mask.getC();

        if (b==null || StartPicture.startPictureArr[b.getY()][b.getX()]==0){
            //b is 0
            if (c==null || StartPicture.startPictureArr[c.getY()][c.getX()]==0){
                //c is 0
                if (StartPicture.startPictureArr[a.getY()][a.getX()]>0){
                    //a has 1
                    Lab1_part3.log("position 2");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a_c_equals_1_and_b_equals_0(Mask mask){
        Point a = mask.getA();
        Point b = mask.getB();
        Point c = mask.getC();

        if (b==null || StartPicture.startPictureArr[b.getY()][b.getX()]==0){
            //b is 0
            if (c!=null && StartPicture.startPictureArr[c.getY()][c.getX()]>0){//c!=null - because can be out of border
                //c is 0
                if (StartPicture.startPictureArr[a.getY()][a.getX()]>0){
                    //a has 1
                    Lab1_part3.log("position 3");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean a_b_equals_1_and_c_equals_0(Mask mask){
        Point a = mask.getA();
        Point b = mask.getB();
        Point c = mask.getC();

        if (b!=null && StartPicture.startPictureArr[b.getY()][b.getX()]>0){
            //b is 0
            if (c==null || StartPicture.startPictureArr[c.getY()][c.getX()]==0){
                //c is 0
                if (StartPicture.startPictureArr[a.getY()][a.getX()]>0){
                    //a has 1
                    Lab1_part3.log("position 4");
                    return true;
                }
            }
        }
        return false;
    }

    private boolean abc_equals_1(Mask mask){
        Point a = mask.getA();
        Point b = mask.getB();
        Point c = mask.getC();

        if (b!=null && StartPicture.startPictureArr[b.getY()][b.getX()]>0){
            //b is 0
            if (c!=null || StartPicture.startPictureArr[c.getY()][c.getX()]>0){
                //c is 0
                if (StartPicture.startPictureArr[a.getY()][a.getX()]>0){
                    //a has 1
                    Lab1_part3.log("position 5");
                    return true;
                }
            }
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
