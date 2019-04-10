import org.w3c.dom.css.RGBColor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {

    public static final String ORIGINAL = "src/pictures/chan.jpg";
    public static final String MINIMAL_SIZE = "src/pictures/minimal_size.jpg";
    public static final String TEST_BLACK_PICTURE = "src/pictures/TEST_BLACK_PICTURE.jpg";
    public static final String NEAREST = "src/pictures/nearest.jpg";
    public static final String BILINEAR = "src/pictures/bilinear.jpg";

    public static void main(String[] args) throws IOException {
        BufferedImage image = null;
        image = ImageIO.read(new File(ORIGINAL));

        int scale = 20;
        int scaledWidth = image.getWidth()/scale;
        System.out.println(scaledWidth);
        int scaledHeight = image.getHeight()/scale;

        //Уменьшаем картинку
        BufferedImage scaled = new BufferedImage(scaledWidth, scaledHeight, BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < scaled.getWidth(); i++) {
            for (int j = 0; j < scaled.getHeight(); j++) {
                if ((i == 0) && (j == 0)) {
                    scaled.setRGB(i, j, image.getRGB(0,0));
                } else if (i == 0) {
                    scaled.setRGB(i, j, image.getRGB(0, j * scale - (scale - 1)));
                } else if (j == 0) {
                    scaled.setRGB(i, j, image.getRGB(i * scale - (scale - 1), 0));
                } else {
                    scaled.setRGB(i, j, image.getRGB(i * scale - (scale - 1), j * scale - (scale - 1)));
                }
            }
        }

        ImageIO.write(scaled, "JPEG", new File(MINIMAL_SIZE));

        //просто расставил пиксели в новой картинке
        BufferedImage newImg = new BufferedImage(scaledWidth * scale - (scale - 1), scaledHeight * scale - (scale - 1), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < scaled.getWidth(); i++) {
            for (int j = 0; j < scaled.getHeight(); j++) {
                if ((i == 0) && (j == 0)) {
                    newImg.setRGB(0, 0, scaled.getRGB(i, j));
                } else if (i == 0) {
                    newImg.setRGB(0, j * scale, scaled.getRGB(i, j));
                } else if (j == 0) {
                    newImg.setRGB(i * scale, 0, scaled.getRGB(i, j));
                } else {
                    newImg.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                }
            }
        }

        ImageIO.write(newImg, "BMP", new File(TEST_BLACK_PICTURE));

        //nearest
        BufferedImage newImg2 = new BufferedImage(scaledWidth * scale - (scale - 1), scaledHeight * scale - (scale - 1), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < scaled.getWidth(); i++) {
            for (int j = 0; j < scaled.getHeight(); j++) {
                if ((i == 0) && (j == 0)) {
                    newImg2.setRGB(0, 0, scaled.getRGB(i, j));
                } else if (i == 0) {
                    newImg2.setRGB(0, j * scale, scaled.getRGB(i, j));
                } else if (j == 0) {
                    newImg2.setRGB(i * scale, 0, scaled.getRGB(i, j));
                } else {
                    newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                }
            }
        }

        //horizontal
        for (int i = 0; i < newImg2.getWidth(); i = i + scale) {
            for (int j = 0; j < newImg2.getHeight(); j = j + scale) {
                if (i != 0){
                    int rightPixels = (scale - 1) / 2;
                        //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                    //System.out.println(i);
                    int rightColor = newImg2.getRGB(i, j);
                    int leftColor = newImg2.getRGB(i - scale, j);
                    for (int k = i - 1; k > i - scale; k--) {
                        if (rightPixels != 0) {
                            newImg2.setRGB(k, j, rightColor);
                            rightPixels--;
                        } else {
                            newImg2.setRGB(k, j, leftColor);
                        }
                    }
                }
            }
        }

        //vertical
        for (int i = 0; i < newImg2.getWidth(); i++) {
            for (int j = 0; j < newImg2.getHeight(); j = j + scale) {
                if (j != 0){
                    int rightPixels = (scale - 1) / 2;
                    //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                    int rightColor = newImg2.getRGB(i, j);
                    int leftColor = newImg2.getRGB(i, j - scale);
                    for (int k = j - 1; k > j - scale; k--) {
                        if (rightPixels != 0) {
                            newImg2.setRGB(i, k, rightColor);
                            rightPixels--;
                        } else {
                            newImg2.setRGB(i, k, leftColor);
                        }
                    }
                }
            }
        }

        ImageIO.write(newImg2, "BMP", new File(NEAREST));



        //bilinear
        BufferedImage newImg3 = new BufferedImage(scaledWidth * scale - (scale - 1), scaledHeight * scale - (scale - 1), BufferedImage.TYPE_3BYTE_BGR);
        for (int i = 0; i < scaled.getWidth(); i++) {
            for (int j = 0; j < scaled.getHeight(); j++) {
                if ((i == 0) && (j == 0)) {
                    newImg3.setRGB(0, 0, scaled.getRGB(i, j));
                } else if (i == 0) {
                    newImg3.setRGB(0, j * scale, scaled.getRGB(i, j));
                } else if (j == 0) {
                    newImg3.setRGB(i * scale, 0, scaled.getRGB(i, j));
                } else {
                    newImg3.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                }
            }
        }

        //horizontal
        for (int i = 0; i < newImg3.getWidth(); i = i + scale ) {
            for (int j = 0; j < newImg3.getHeight(); j = j + scale) {
                if (i != 0){
                    //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                    //System.out.println(i);
                    Color rightColor = new Color(newImg3.getRGB(i, j));
                    Color leftColor = new Color(newImg3.getRGB(i - scale, j));
                    int part = 1;
                    for (int k = i - 1; k > i - scale; k--) {
                        int red = (rightColor.getRed() * (scale - part) + leftColor.getRed() * part)/scale;
                        int green = (rightColor.getGreen() * (scale - part) + leftColor.getGreen() * part)/scale;
                        int blue = (rightColor.getBlue() * (scale - part) + leftColor.getBlue() * part)/scale;
                        Color newColor = new Color(red, green, blue);
                        newImg3.setRGB(k, j, newColor.getRGB());
                        part++;
                    }
                }
            }
        }


        //vertical
        for (int i = 0; i < newImg3.getWidth() - 1; i++) {
            for (int j = 0; j < newImg3.getHeight() - scale; j = j + scale) {
                System.out.println(j);

                //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                Color bottomColor = new Color(newImg3.getRGB(i, j + scale));
                Color topColor = new Color(newImg3.getRGB(i, j));
                int part = 1;
                for (int k = j + 1; k < j + scale; k++) {

                    int red = (topColor.getRed() * (scale - part) + bottomColor.getRed() * part) / scale;
                    int green = (topColor.getGreen() * (scale - part) + bottomColor.getGreen() * part) / scale;
                    int blue = (topColor.getBlue() * (scale - part) + bottomColor.getBlue() * part) / scale;

                    Color newColor = new Color(red, green, blue);
                    newImg3.setRGB(i, k, newColor.getRGB());
                    part++;
                }
            }
        }

        ImageIO.write(newImg3, "BMP", new File(BILINEAR));

    }
}
