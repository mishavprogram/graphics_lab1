package part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;

class NearestPictureTest {

    private static final String ORIGINAL_IMAGE_PATH = "src/main/resources/test/origin.jpg";
    private static final String BLACK_IMAGE_PATH = "src/main/resources/test/black.jpg";
    private static final String NEAREST_IMAGE_PATH = "src/main/resources/test/nearest.jpg";
    private BufferedImage originImage;

    @BeforeEach
    void setUp() throws IOException {
        originImage = ImageIO.read(new File(ORIGINAL_IMAGE_PATH));
    }

    @Test
    void checkCountOfNearestOriginalPoints(){
        int scaling = 2;
        NearestPicture nearestPicture = new NearestPicture(originImage, scaling);

        Point point = new Point(0,0);
        int expected = 4;
        int actual = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage()).size();
        assertEquals(expected, actual);

        point = new Point(1,0);
        expected = 4;
        actual = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage()).size();
        assertEquals(expected, actual);

        point = new Point(2,0);
        expected = 6;
        actual = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage()).size();
        assertEquals(expected, actual);

        point = new Point(1,1);
        expected = 4;
        actual = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage()).size();
        assertEquals(expected, actual);
    }

    @Test
    void checkNearestPointWithScaling2(){
        int scaling = 2;
        NearestPicture nearestPicture = new NearestPicture(originImage, scaling);

        Point point = new Point(0,0);
        List<Point> nearestPoints = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage());
        Point nearest = nearestPicture.nearestPoint(point, nearestPoints);
        assertEquals(point, nearest);

        point = new Point(1,0);
        nearestPoints = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage());
        nearest = nearestPicture.nearestPoint(point, nearestPoints);
        assertEquals(nearest, new Point(0,0));
    }

    @Test
    void checkNearestPointWithScaling3(){
        int scaling = 3;
        NearestPicture nearestPicture = new NearestPicture(originImage, scaling);

        Point point = new Point(0,0);
        List<Point> nearestPoints = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage());
        Point nearest = nearestPicture.nearestPoint(point, nearestPoints);
        assertEquals(nearest, new Point(0,0));

        point = new Point(1,1);
        nearestPoints = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage());
        nearest = nearestPicture.nearestPoint(point, nearestPoints);
        assertEquals(nearest, new Point(0,0));

        point = new Point(2,2);
        nearestPoints = nearestPicture.nearestOriginalPoints(point, nearestPicture.getBigBlackImage());
        nearest = nearestPicture.nearestPoint(point, nearestPoints);
        assertEquals(nearest, new Point(3,3));
    }

    @Test
    void createNearestFile() throws IOException {
        File file = new File(NEAREST_IMAGE_PATH);
        if (file.exists()) {
            file.delete();
        }else{
            //file.createNewFile();
        }

        int scaling = 3;
        NearestPicture bigPicture = new NearestPicture(originImage, scaling);
        BufferedImage nearestImage = bigPicture.getFinalImage();
        ImageIO.write(nearestImage, "jpg", file);
    }
}