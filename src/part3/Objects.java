package part3;

import static part3.CountObjectsOnBinaryArray.log;

class Objects {
    private int[][] arrOfObjects;

    private int number = 0;

    private int[] assoArr = new int[100];

    public int[] getAssoArr() {
        return assoArr;
    }

    public int getNumber() {
        return number;
    }

    public void printASSO(){
        for(int i=1; i<=getNumber(); i++){
            System.out.println(i+" - "+getAssoArr()[i]);
        }
    }

    public int getNumberOfObjects(){
        int max = 0;
        for (int i=1; i<=getNumber(); i++){
            if (getAssoArr()[i]>max)
                max = getAssoArr()[i];
        }
        return max;
    }

    private Objects() {
        this.setArrOfObjects(StartPicture.startPictureArr.clone());
        for(int i=0; i<arrOfObjects.length; i++){
            arrOfObjects[i] = StartPicture.startPictureArr[i].clone();
        }
        this.clearArr(getArrOfObjects());
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

    void doLogic(Mask mask) {
        if (!labeledA(mask)){
            log("1. A NOT labeled");
        }else if (labeledA(mask) && !labeledB(mask) && !labeledC(mask)){
            number++;
            assoArr[number] = number;
            arrOfObjects[mask.getA().getY()][mask.getA().getX()] = number;
            log("2. A labeled");
        }else if (labeledB(mask) && labeledC(mask)){
            if (valueFromArrOfObjects(mask.getB())==valueFromArrOfObjects(mask.getC())){
                //якщо значення однакові
                setPointValueToOtherPointValue(mask.getA(), mask.getB());
                log("3. ==");
            }else{
                //якщо значення різні, то треба все-рівно якесь присвоїти для A, а потім
                //знайти макс, і поміняти в макс значення на те що в min
                setPointValueToOtherPointValue(mask.getA(), mask.getB());
                if (valueFromArrOfObjects(mask.getB())>valueFromArrOfObjects(mask.getC())){
                    //тоді в асоц масиві для B поставити значення C
                    assoArr[valueFromArrOfObjects(mask.getB())] = valueFromArrOfObjects(mask.getC());
                    log("3. B > C");
                }else{
                    assoArr[valueFromArrOfObjects(mask.getC())] = valueFromArrOfObjects(mask.getB());
                    log("3. C > B");
                }
            }
        }else if (labeledB(mask) && !labeledC(mask)){
            setPointValueToOtherPointValue(mask.getA(), mask.getB());
            log("4. B labeled");
        }else if (labeledC(mask) && !labeledB(mask)){
            setPointValueToOtherPointValue(mask.getA(), mask.getC());
            log("5. C labeled");
        }else{
            log("not detected!");
        }
    }

    private void setPointValueToOtherPointValue(Point getter, Point source){
        arrOfObjects[getter.getY()][getter.getX()] = arrOfObjects[source.getY()][source.getX()];
    }

    private int valueFromArrOfObjects(Point point){
        return arrOfObjects[point.getY()][point.getX()];
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
}
