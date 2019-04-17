package part1;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

class SmallPictureTest {

    private static final String SMALL_IMAGE_PATH = "src/main/resources/test/small.jpg";
    private static final String ORIGINAL_IMAGE_PATH = "src/main/resources/test/origin.jpg";

    @Test
    void createSmallPictureWithScaling() throws IOException {

        File file = new File(SMALL_IMAGE_PATH);
        if (file.exists()) {
            file.delete();
        }else{
            //file.createNewFile();
        }

        BufferedImage input = ImageIO.read(new File(ORIGINAL_IMAGE_PATH));

        //ImageIO.write(input, "jpg", file);

        int scaling = 1;
        BufferedImage smallImage = SmallPicture.getSmallPictureWithScaling(input, scaling);
        ImageIO.write(smallImage, "jpg", file);

    }
}