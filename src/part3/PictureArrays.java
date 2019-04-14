package part3;

public class PictureArrays {
    static void printArray(int[][] arr){
        for(int height=0; height<arr.length; height++){
            for (int width=0; width<arr[height].length; width++){
                //спочатку пишеться ВИСОТА, потім - ШИРИНА
                System.out.print(arr[height][width]+" ");
            }
            System.out.println();
        }
    }
}
