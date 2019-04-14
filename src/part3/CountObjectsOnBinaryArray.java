package part3;


public class CountObjectsOnBinaryArray {

    private static Objects objects = Objects.createObjectsArrAndInfo();

    static final boolean LOGGING = false;

    public static void main(String[] args) {
        //set first Mask
        Mask mask = Mask.createMaskOnlyWithA_FromPoint(Point.createPointFromXY(0, 0));
        log(mask.toString());

        movingABC_onPicture_AndDoLogic(mask);

        PictureArrays.printArray(objects.getArrOfObjects());
        objects.printASSO();

        System.out.println("number of objects : "+objects.getNumberOfObjects());
    }

    private static void movingABC_onPicture_AndDoLogic(Mask mask) {
        int n = 0;

        for(int i = 0; i <= StartPicture.MAX_Y; i++){
            for (int j = 0; j <= StartPicture.MAX_X; j++){
                //logic
                objects.doLogic(mask);
                //move of mask
                if (notLastElementOfArray(i, j))
                mask = Mask.getNextMask(mask);
            }
        }
    }

    private static boolean notLastElementOfArray(int i, int j) {
        return !(i == StartPicture.MAX_Y && j == StartPicture.MAX_X);
    }

    static void log(String str){
        if (LOGGING)
        System.out.println(str);
    }

}
