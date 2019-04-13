package part3;


public class Lab1_part3 {

    private static Objects objects = Objects.createObjectsArrAndInfo();

    static final boolean LOGGING = true;

    public static void main(String[] args) {

        //set first Mask
        Mask mask = Mask.createMaskOnlyWithA_FromPoint(Point.createPointFromXY(0, 0));

        movingABC_onPicture_AndDoLogic(mask);

        objects.printObjectsAsArray();
    }

    private static void movingABC_onPicture_AndDoLogic(Mask mask) {
        int n = 0;

        action:
        for(int i = 0; i <= StartPicture.MAX_Y; i++){
            for (int j = 0; j <= StartPicture.MAX_X; j++){
                //logic
                objects.doLogic(mask);
                //move of mask
                try {
                    mask = Mask.getNextMaskByUsingOldMask_and_StartPicture(mask);
                } catch (EndOfPictureException e) {
                    //end action
                    break action;
                }
                //current A
                log(++n+" : "+mask.getA());
            }
        }
    }

    static void log(String str){
        if (LOGGING)
        System.out.println(str);
    }

}
