package part1;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NearestPicture extends BigPicture {

    private BufferedImage finalImage;

    public NearestPicture(BufferedImage originImage, int scaling) {
        super(originImage, scaling);
        finalImage = new BufferedImage(originImage.getWidth(), originImage.getHeight(), BufferedImage.TYPE_3BYTE_BGR);
        fillNearestPicture();
    }

    private void fillNearestPicture() {
        //проходить по всім точкам, і закрашує в колір найближчої оригінальної точки

    }

    /*
        в межах SCALING знаходить оригінальні точки, беручи її з мапи
        //TODO убрати BufferedImage. Навіщо він тут?
     */
    public List<Point> nearestOriginalPoints(Point point, BufferedImage image) {

        List<Point> points = new ArrayList<>();
        Map<Integer, Integer> map = super.getOriginPoints();
        int scaling = super.getScaling();

        //проход по квадрату навколо точки
        for (int height = point.y - scaling; height <= point.y + scaling; height++) {
            for (int width = point.x - scaling; width <= point.x + scaling; width++) {

                Point toCompare = new Point(width, height);
                
                if (exist(toCompare, image)) {

                    int hash = BigPicture.hash(toCompare.x, toCompare.y);
                    Integer color = map.get(hash);
                    if (color != null) {
                        points.add(toCompare);
                    }
                }
            }
        }
        return points;
    }

    public Point nearestPoint(Point goal, List<Point> points) {
        Point nearestPoint = points.get(0);
        double lenght = lenghtBetweenPoints(goal, nearestPoint);

        for (Point p :
            points) {
            if (lenghtBetweenPoints(p, goal)<lenght){
                nearestPoint = p;
                lenght = lenghtBetweenPoints(p, goal);
            }
        }
        return nearestPoint;
    }

    private double lenghtBetweenPoints(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public boolean exist(Point point, BufferedImage image) {
        return point.x >= 0 && point.y >= 0 && point.x <= image.getWidth() - 1 && point.y <= image.getHeight() - 1;
    }
}
