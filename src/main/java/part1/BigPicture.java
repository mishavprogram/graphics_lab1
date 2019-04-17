package part1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class BigPicture {

    /*
        color - origin. position - how will be in new image
     */
    private Map<Integer, Integer> originPoints = new HashMap<>();

    private int scaling = 1;

    private BufferedImage originImage;
    /*
        this is image that have size as new big image, but all points are black,
        only size/scaling has colour, because its points from original picture
     */
    private BufferedImage bigBlackImage;

    public BigPicture(BufferedImage originImage, int scaling) {
        this.originImage = originImage;
        this.scaling = scaling;
        fillOriginPointsMap();
        createBlackBigImage();
    }

    private void createBlackBigImage() {
        ///////////////////////////get picture with black points///////////////////////
        bigBlackImage = new BufferedImage(originImage.getWidth() * scaling,
                                     originImage.getHeight() * scaling,
                                     BufferedImage.TYPE_3BYTE_BGR);
        ///////////////////////////add color points/////////////////////////////////////
        int originHeight = 0;
        int originWidth = 0;

        for (int height = 0; height < originImage.getHeight() * scaling; height = height + scaling) {
            originWidth = 0;
            for (int width = 0; width < originImage.getWidth() * scaling; width = width + scaling) {

                Point point = new Point(width, height);
                int color = originImage.getRGB(originWidth, originHeight);
                bigBlackImage.setRGB(width, height, color);
                originWidth++;
            }
            originHeight++;
        }
    }

    private void fillOriginPointsMap() {
        int originHeight = 0;
        int originWidth = 0;

        for (int height = 0; height < originImage.getHeight() * scaling; height = height + scaling) {
            originWidth = 0;
            for (int width = 0; width < originImage.getWidth() * scaling; width = width + scaling) {

                Point point = new Point(width, height);
                Integer color = originImage.getRGB(originWidth, originHeight);
                insertPoint(point, color);

                originWidth++;
            }
            originHeight++;
        }
    }

    private void insertPoint(Point point, Integer color) {
        int x = point.x;
        int y = point.y;

        originPoints.put(hash(x, y), color);
    }

    public BufferedImage getBigBlackImage() {
        return bigBlackImage;
    }

    public Map<Integer, Integer> getOriginPoints() {
        return originPoints;
    }

    public int getScaling() {
        return scaling;
    }

    public static int hash(int x, int y) {
        return x * 100_000_000 + y;
    }

}
