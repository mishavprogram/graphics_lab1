package part3;

class Objects {
    private int[][] arrOfObjects;

    private int number = 0;

    private int[] assoArr = new int[100];

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

    void doLogic(Mask mask) {
        if (!labeledA(mask)){
            //do nothing
        }else if (labeledA(mask) && !labeledB(mask) && !labeledC(mask)){
            incrementValueOnArrOfObject(mask.getA());
            number++;
            assoArr[number] = number;
        }else if (labeledB(mask) && labeledC(mask)){
            if (valueFromArrOfObjects(mask.getB())==valueFromArrOfObjects(mask.getC())){
                //якщо значення однакові
                setPointValueToOtherPointValue(mask.getA(), mask.getB());
            }else{
                //якщо значення різні, то треба все-рівно якесь присвоїти для A, а потім
                //знайти макс, і поміняти в макс значення на те що в min
                setPointValueToOtherPointValue(mask.getA(), mask.getB());
                if (valueFromArrOfObjects(mask.getB())>valueFromArrOfObjects(mask.getC())){
                    //тоді в асоц масиві для B поставити значення C
                    assoArr[valueFromArrOfObjects(mask.getB())] = valueFromArrOfObjects(mask.getC());
                }else{
                    assoArr[valueFromArrOfObjects(mask.getC())] = valueFromArrOfObjects(mask.getB());
                }
            }
        }else if (labeledB(mask) && !labeledC(mask)){
            setPointValueToOtherPointValue(mask.getA(), mask.getB());
        }else if (labeledC(mask) && !labeledB(mask)){
            setPointValueToOtherPointValue(mask.getA(), mask.getC());
        }else{
            Lab1_part3.log("not detected!!!");
        }
    }

    private void incrementValueOnArrOfObject(Point point){
        arrOfObjects[point.getY()][point.getX()]++;
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
