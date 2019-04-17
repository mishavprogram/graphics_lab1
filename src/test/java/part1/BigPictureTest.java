package part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class BigPictureTest {

    private class BigPictureImpl extends BigPicture{
        public BigPictureImpl(BufferedImage originImage, int scaling) {
            super(originImage, scaling);
        }
    }

    private BufferedImage originImage;
    private static final String ORIGINAL_IMAGE_PATH = "src/main/resources/test/origin.jpg";

    private BigPicture bigPicture;

    @BeforeEach
    void setUp() throws IOException {
        originImage = ImageIO.read(new File(ORIGINAL_IMAGE_PATH));
    }

    @Test
    void readOriginFile(){
        File file = new File(ORIGINAL_IMAGE_PATH);
        if (!file.exists()) fail("file not exist!");
    }

    @Test
    void countOfPointsInMapShouldBeEqualsAsInOriginImage(){
        int height = originImage.getHeight();
        int width = originImage.getWidth();

        int countOfOriginPoints = height*width;

        int scaling = 2;
        BigPictureImpl bigPicture = new BigPictureImpl(originImage, scaling);
        int sizeOfMap = bigPicture.getOriginPoints().size();
        assertEquals(countOfOriginPoints, sizeOfMap);

        scaling = 3;
        bigPicture = new BigPictureImpl(originImage, scaling);
        sizeOfMap = bigPicture.getOriginPoints().size();
        assertEquals(countOfOriginPoints, sizeOfMap);
    }

}