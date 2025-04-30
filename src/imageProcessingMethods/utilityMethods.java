package imageProcessingMethods;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.Random;
import javax.imageio.ImageIO;

public class utilityMethods {

    /* Accepts a String wich can be a file path or image URL.
    It returns a 2D array of integers that contains every pixel from the image stored as
   int hexadecimal values containing the RGBA values for the pixel
     */
    public static int[][] imgToTwoD(String inputFileOrLink){
        try {
            BufferedImage image = null;
            if (inputFileOrLink.substring(0, 4).toLowerCase().equals("http")) {
                URL imageUrl = new URL(inputFileOrLink);
                image = ImageIO.read(imageUrl);
                if (image == null) {
                    System.out.println("Failed to get image from provided URL.");
                }
            } else {
                image = ImageIO.read(new File(inputFileOrLink));
            }
            int imgRows = image.getHeight();
            int imgCols = image.getWidth();
            int[][] pixelData = new int[imgRows][imgCols];
            for (int row = 0; row < imgRows; row++) {
                for (int col = 0; col < imgCols; col++) {
                    pixelData[row][col] = image.getRGB(col, row);
                }
            }
            return pixelData;
        } catch (Exception e) {
            System.out.println("Failed to load image: " + e.getLocalizedMessage());
            return null;
        }
    }
    /* It accepts a 2D array of integers and a String for the file name
    It converts the 2D array pixel data into a img and saves it
    We will use this method to create images after we modify the 2D array of int
     */
    public static void twoDToImage(int[][] imgData, String fileName) {
        try {
            int imgRows = imgData.length;
            int imgCols = imgData[0].length;
            BufferedImage result = new BufferedImage(imgCols, imgRows, BufferedImage.TYPE_INT_RGB);
            for (int row = 0; row < imgRows; row++) {
                for (int col = 0; col < imgCols; col++) {
                    result.setRGB(col, row, imgData[row][col]);
                }
            }
            File output = new File(fileName);
            ImageIO.write(result, "jpg", output);
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e.getLocalizedMessage());
        }
    }
    /* This method accepts an int value representing the pixel
    hexadecimal value and returns a 4 element int array consisting of R, G, B and A values
     */
    public static int[] getRGBAFromPixel(int pixelColorValue) {
        Color pixelColor = new Color(pixelColorValue);
        return new int[] { pixelColor.getRed(), pixelColor.getGreen(), pixelColor.getBlue(), pixelColor.getAlpha() };
    }

/* This method accepts an array of integers that represent the RGBA values
and convert it into a single int value representing the pixel hexadecimal value
 */
    public static int getColorIntValFromRGBA(int[] colorData) {
        if (colorData.length == 4) {
            Color color = new Color(colorData[0], colorData[1], colorData[2], colorData[3]);
            return color.getRGB();
        } else {
            System.out.println("Incorrect number of colors in RGBA array");
            return -1;
        }
    }

    /* This is a method used for extracting a 3x3 section from the top left of the image
    called "viewImageData". This method is used to view the structure of the image data in both
    the war pixel from and the extracted RGBA form.
 */
    public static void viewImageData(int[][] imageTwoD) {
        if (imageTwoD.length > 3 && imageTwoD[0].length > 3) {
            int[][] rawPixels = new int[3][3];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    rawPixels[row][col] = imageTwoD[row][col];
                }
            }
            System.out.println("Raw pixel data from the top left corner.");
            System.out.println(Arrays.toString(rawPixels).replace("],", "],\n" + "\n"));
            int[][][] rgbPixels = new int[3][3][4];
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    rgbPixels[row][col] = getRGBAFromPixel(imageTwoD[row][col]);
                }
            }
            System.out.println("raw pixel data from the top left corner.");
            for (int[][] row : rgbPixels) {
                System.out.println(Arrays.toString(row) + System.lineSeparator());
            }
        } else {
            System.out.println("The image is not large enough to extract 9 pixels from the top left corner");
        }
    }
}
