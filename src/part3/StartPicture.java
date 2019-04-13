package part3;

class StartPicture {
    static final int[][] startPictureArr =
        {
            {0,1,1,0,0,0,0},
            {1,0,1,0,0,0,0},
            {0,0,0,1,0,0,0},
            {0,0,1,0,0,0,0},
            {0,0,0,0,0,0,0}
            };

    static void printObjectsAsArray(){
        System.out.println("height = "+startPictureArr.length);
        System.out.println("width = "+startPictureArr[0].length);

        for(int height=0; height<startPictureArr.length; height++){
            for (int width=0; width<startPictureArr[height].length; width++){
                //спочатку пишеться ВИСОТА, потім - ШИРИНА
                System.out.print(startPictureArr[height][width]+" ");
            }
            System.out.println();
        }
    }

    static final int MAX_Y = startPictureArr.length - 1;
    static final int MAX_X = startPictureArr[0].length - 1;
}
