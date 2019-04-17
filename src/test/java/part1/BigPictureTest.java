package part1;

import static org.junit.jupiter.api.Assertions.*;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class BigPictureTest {

    private static final String ORIGINAL_IMAGE_PATH = "src/main/resources/test/origin.jpg";
    private static final String BLACK_IMAGE_PATH = "src/main/resources/test/black.jpg";
    private BufferedImage originImage;
    private BigPicture bigPicture;

    @BeforeEach
    void setUp() throws IOException {
        originImage = ImageIO.read(new File(ORIGINAL_IMAGE_PATH));
    }

    @Test
    void readOriginFile() {
        File file = new File(ORIGINAL_IMAGE_PATH);
        if (!file.exists()) {
            fail("file not exist!");
        }
    }

    @Test
    void countOfPointsInMapShouldBeEqualsAsInOriginImage() {
        int height = originImage.getHeight();
        int width = originImage.getWidth();

        int countOfOriginPoints = height * width;

        int scaling = 2;
        BigPictureImpl bigPicture = new BigPictureImpl(originImage, scaling);
        int sizeOfMap = bigPicture.getOriginPoints().size();
        assertEquals(countOfOriginPoints, sizeOfMap);

        scaling = 3;
        bigPicture = new BigPictureImpl(originImage, scaling);
        sizeOfMap = bigPicture.getOriginPoints().size();
        assertEquals(countOfOriginPoints, sizeOfMap);
    }

    @Test
    void bigBlackImageHasMinumumBlackPoints() {
        int scaling = 3;
        BigPictureImpl bigPicture = new BigPictureImpl(originImage, scaling);

        int blackColor = -16777216;

        //randomColor shoud be black
        int randomColor = bigPicture.getBigBlackImage().getRGB(1, 1);
        assertEquals(blackColor, randomColor);
        //randomColor shoud be not black
        randomColor = bigPicture.getBigBlackImage().getRGB(0, 0);
        assertNotEquals(blackColor, randomColor);

        //big black image has minimum black points
        BufferedImage blackImage = bigPicture.getBigBlackImage();
        int countOfPoint = blackImage.getHeight() * blackImage.getWidth();
        int minimumBlackPointCount = countOfPoint - countOfPoint / scaling;
        int countOfBlackPoint = 0;

        for (int height = 0; height < blackImage.getHeight(); height++) {
            for (int width = 0; width < blackImage.getWidth(); width++){
                if (blackImage.getRGB(width, height)==blackColor){
                    countOfBlackPoint++;
                }
            }
        }
        assertTrue(countOfBlackPoint>=minimumBlackPointCount);
    }

    @Test
    void createBlackImage() throws IOException {
        File file = new File(BLACK_IMAGE_PATH);
        if (file.exists()) {
            file.delete();
        }else{
            //file.createNewFile();
        }

        int scaling = 2;
        BigPictureImpl bigPicture = new BigPictureImpl(originImage, 3);
        BufferedImage blackPicture = bigPicture.getBigBlackImage();
        ImageIO.write(blackPicture, "jpg", file);
    }

    private class BigPictureImpl extends BigPicture {

        public BigPictureImpl(BufferedImage originImage, int scaling) {
            super(originImage, scaling);
        }
    }

}