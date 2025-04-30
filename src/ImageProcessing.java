import static imageProcessingMethods.utilityMethods.*;

public class ImageProcessing {
    public static void main(String[] args) {
        // The provide images are apple.jpg, flower.jpg, kitten.jpg
        int[][] imageData = imgToTwoD("./src/images/apple.jpg");

        //viewImageData(imageData);

        int[][] trimmed = trimBorders(imageData, 60);
        twoDToImage(trimmed, "./trimmed_apple.jpg");

    }

        // Image Processing Methods
        public static int[][] trimBorders(int[][] imageTwoD, int pixelCount) {
            //Example method
            if (imageTwoD.length > pixelCount * 2 && imageTwoD[0].length > pixelCount * 2) {
                int[][] trimmedImg = new int[imageTwoD.length - pixelCount * 2][imageTwoD[0].length - pixelCount * 2];
                for (int i = 0; i < trimmedImg.length; i++) {
                    for (int j = 0; j < trimmedImg[0].length; j++) {
                        trimmedImg[i][j] = imageTwoD[i + pixelCount][j + pixelCount];
                    }
                }
                return trimmedImg;
            } else {
                System.out.println("Cannot trim that many pixels from the given image.");
                return imageTwoD;
            }
        }

    }
