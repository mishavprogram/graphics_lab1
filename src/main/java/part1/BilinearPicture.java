package part1;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BilinearPicture extends BigPicture {

    public BilinearPicture(BufferedImage originImage, int scaling) {
        super(originImage, scaling);
    }

    public BufferedImage getFinalImage(){
        BufferedImage result = super.getBigBlackImage();
        int scaling = getScaling();

        //horizontal
        for (int width = 0; width < result.getWidth(); width = width + scaling ) {
            for (int height = 0; height < result.getHeight(); height = height + scaling) {
                if (width != 0){
                    //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                    //System.out.println(i);
                    Color rightColor = new Color(result.getRGB(width, height));
                    Color leftColor = new Color(result.getRGB(width - scaling, height));
                    int part = 1;//part is incrising
                    for (int k = width - 1; k > width - scaling; k--) {

                        int red = (rightColor.getRed() * (scaling - part) + leftColor.getRed() * part)/scaling;
                        int green = (rightColor.getGreen() * (scaling - part) + leftColor.getGreen() * part)/scaling;
                        int blue = (rightColor.getBlue() * (scaling - part) + leftColor.getBlue() * part)/scaling;

                        Color newColor = new Color(red, green, blue);
                        result.setRGB(k, height, newColor.getRGB());
                        part++;
                    }
                }
            }
        }

        //vertical
        for (int width = 0; width < result.getWidth() - 1; width++) {
            for (int height = 0; height < result.getHeight() - scaling; height = height + scaling) {
                //System.out.println(j);

                //newImg2.setRGB(i * scale, j * scale, scaled.getRGB(i, j));
                Color bottomColor = new Color(result.getRGB(width, height + scaling));
                Color topColor = new Color(result.getRGB(width, height));
                int part = 1;
                for (int k = height + 1; k < height + scaling; k++) {

                    int red = (topColor.getRed() * (scaling - part) + bottomColor.getRed() * part) / scaling;
                    int green = (topColor.getGreen() * (scaling - part) + bottomColor.getGreen() * part) / scaling;
                    int blue = (topColor.getBlue() * (scaling - part) + bottomColor.getBlue() * part) / scaling;

                    Color newColor = new Color(red, green, blue);
                    result.setRGB(width, k, newColor.getRGB());
                    part++;
                }
            }
        }
        return result;
    }
}
