package part3;

import java.util.HashMap;
import java.util.Map;

public class Lab1_part3 {

    private static int[][] arrOfObjects;

    private int number = 1;

    private Map<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {

        arrOfObjects = StartPicture.startPictureArr.clone();

        clearArr(arrOfObjects);

        //set first Mask
        Mask mask = Mask.createMaskOnlyWithA_FromPoint(Point.createPointFromXY(0, 0));

        int n = 0;

        action:
        for(int i = 0; i <= StartPicture.MAX_Y; i++){
            for (int j = 0; j <= StartPicture.MAX_X; j++){
                //logic

                //move of mask
                try {
                    mask = Mask.getNextMaskByUsingOldMask_and_StartPicture(mask);
                } catch (EndOfPictureException e) {
                    //end action
                    break action;
                }
                //current A
                System.out.println(++n + " : "+ mask.getA());
            }
        }
    }

    private static void clearArr(int[][] arr) {
        for(int i = 0; i <= StartPicture.MAX_Y; i++){
            for (int j = 0; j <= StartPicture.MAX_X; j++){
                arr[i][j] = 0;
            }
        }
    }
}
