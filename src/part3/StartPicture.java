package part3;

class StartPicture {
    public static final int[][] startPictureArr =
        {
            {0,0,1,0,0,0,0,0,0},
            {0,1,1,1,0,0,1,0,0},
            {0,0,1,1,0,0,1,0,0},
            {0,0,0,1,0,0,0,0,0},
            {0,0,0,0,0,1,1,0,0},
            {0,0,0,0,0,0,0,0,0},
            };

    public static final int MAX_Y = startPictureArr.length - 1;
    public static final int MAX_X = startPictureArr[0].length - 1;
}
