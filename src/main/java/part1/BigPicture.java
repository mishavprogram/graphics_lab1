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

    public BigPicture(BufferedImage originImage, int scaling) {
        this.originImage = originImage;
        this.scaling = scaling;
        fillOriginPointsMap();
    }

    private void fillOriginPointsMap(){
        int originHeight = 0;
        int originWidth = 0;

        for(int height=0;height<originImage.getHeight()*scaling;height=height+scaling){
            originWidth=0;
            for (int width = 0; width<originImage.getWidth()*scaling; width=width+scaling){

                Point point = new Point(width, height);
                Integer color = originImage.getRGB(originWidth, originHeight);
                insertPoint(point, color);

                originWidth++;
            }
            originHeight++;
        }
    }

    private void insertPoint(Point point, Integer color){
        int x = point.x;
        int y = point.y;

        originPoints.put(hash(x,y), color);
    }

    public Map<Integer, Integer> getOriginPoints() {
        return originPoints;
    }

    private int hash(int x, int y){
        return x*100_000_000+y;
    }

}
