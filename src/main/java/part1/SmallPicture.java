package part1;

import java.awt.image.BufferedImage;

public class SmallPicture {

    public static BufferedImage getSmallPictureWithScaling(BufferedImage input, int scaling) {

        BufferedImage output = new BufferedImage(input.getWidth() / scaling,
                                                 input.getHeight() / scaling,
                                                 BufferedImage.TYPE_3BYTE_BGR);

        int heightOutput = 0;
        int widthOutput = 0;

        for (int height = 0; height < input.getHeight() - scaling; height = height + scaling) {
            widthOutput = 0;
            for (int width = 0; width < input.getWidth() - scaling; width = width + scaling) {
                int color = input.getRGB(width, height);
                output.setRGB(widthOutput, heightOutput, color);
                widthOutput++;
            }
            heightOutput++;
        }
        return output;
    }

}
