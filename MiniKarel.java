
import acm.graphics.*;
import acm.program.*;
import acm.util.*; 

import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class MiniKarel implements KarelArtInterface {
	
	private static final int LUMINOSITY_TO_BE_PAINTED_WHITE = 200;
	private int width;
	private int height;
	private double x;
	private double y;
	private Color color;
	private GImage karel;
	private int [][] karelArray;
	//reminder sunset stop at 30.
	
	public MiniKarel(double x, double y, int size, Color color) {
		this.width = size;
		this.height = size;
		this.x = x;
		this.y = y;
		this.color = color;
		this.karelArray = getKarelImage().getPixelArray();
		int rows = karelArray.length;
		int cols = karelArray[0].length;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				karelArray[r][c] = paintPixel(karelArray[r][c], color);
			}
		}
		this.karel = new GImage(karelArray, this.x, this.y);
		this.karel.setSize(this.width, this.height);		
	}

	public GImage getKarel() {
		return this.karel;
	}
	
	public int[][] getKarelArray() {
		return this.karelArray;
	}
	
	public void turnOnTheLights(Color color, int numberOfLights, double frequency) {
		int rows = this.karelArray.length;
		int cols = this.karelArray[0].length;
		int blankSpaceC= 0;
		int blankSpaceR= 0;
		int whiteR = 0;
		int whiteC = 0;
		for (int c = 0; c < cols; c++) {
			if (this.karelArray[rows/2][c] == Color.WHITE.getRGB() || this.karelArray[rows/2][c] == color.getRGB()) {
				blankSpaceC++;
			}
			if (whiteC == 0 && (this.karelArray[rows/2][c] == Color.WHITE.getRGB() || this.karelArray[rows/2][c] == color.getRGB())) whiteC = c;
		}
		for (int r = 0; r < rows; r++) {
			if (this.karelArray[r][cols/2] == Color.WHITE.getRGB() || this.karelArray[r][cols/2] == color.getRGB()) {
				blankSpaceR++;
			}
			if (whiteR == 0 && (this.karelArray[r][cols/2] == Color.WHITE.getRGB() || this.karelArray[r][cols/2] == color.getRGB())) whiteR = r;
		}
		int intervals = blankSpaceC/numberOfLights;
		for (int r2 = 0; r2 < blankSpaceR; r2 += intervals) {
			for (int c2 = 0; c2 < blankSpaceC; c2 += intervals) {
				if (rgen.nextBoolean(frequency)) {
				for (int r3 = 0; r3 < intervals; r3++) {
					for (int c3 = 0; c3 < intervals; c3++) {
							if (this.karelArray[whiteR+r2+r3][whiteC+c2+c3] == Color.WHITE.getRGB()) {
								this.karelArray[whiteR+r2+r3][whiteC+c2+c3] = color.getRGB();
							} else if (this.karelArray[whiteR+r2+r3][whiteC+c2+c3] == color.getRGB()) {
								this.karelArray[whiteR+r2+r3][whiteC+c2+c3] = Color.WHITE.getRGB();
							}	
						}
						
					}
				}	
			}
		}	
		this.karel = new GImage(karelArray, this.x, this.y);
		this.karel.setSize(this.width, this.height);
	}
	
	public int[][] getKarelArrayReduced() {
		double reduceFactorR = this.karelArray.length/this.height;
		double reduceFactorC = this.karelArray[0].length/this.width;
		int outputR = (int) (this.karelArray.length/reduceFactorR); 
		int outputC = (int) (this.karelArray[0].length/reduceFactorC); 
		
		int [][] output = new int[outputR][outputC];
		for (int r = 0; r < outputR; r++) {
			for (int c = 0; c < outputC; c++) {
				output[r][c] = this.karelArray[(int) (r*reduceFactorR)][(int) (c*reduceFactorC)];
			}
		}
		return output;
	}
	
	public void stretchKarel(int stretchFactor) {
		int outputR = this.karelArray.length + stretchFactor; 
		int outputC = this.karelArray[0].length; 
		int [][] output = new int[outputR][outputC];
		//top half ends at this integer
		int topHalf = this.karelArray.length/2;
		//bottom half start at this integer
		int bottomHalf = this.karelArray.length/2 + stretchFactor;
		for (int r = 0; r < outputR; r++) {
			for (int c = 0; c < outputC; c++) {
				if (r < topHalf) {
					output[r][c] = this.karelArray[r][c];
				} else if (r > bottomHalf) {
					output[r][c] = this.karelArray[topHalf + r - bottomHalf][c];
					
				} else {
					output[r][c] = this.karelArray[topHalf][c];	
				}		
			}
		}
		this.height = this.width * outputR / outputC;
		this.y -= (this.height - this.width);
		this.karelArray = output;
		//makes sure eclipse collect garbage at some point by setting value tu null
		output = null;
		this.karel = new GImage(karelArray, this.x, this.y);
		this.karel.setSize(this.width, this.height);
	}
	
	public void moveKarel(double x, double y) {
		this.karel.move(x, y);
	}
	
	public void setKarelLocation(double x, double y) {
		this.karel.setLocation(x, y);
	}
	
	public void setKarelToNull() {
		this.karel = null;
		this.karelArray = null;
		this.color = null;
		
	}
	
	private int paintPixel(int pixel, java.awt.Color color) {
		int red = GImage.getRed(pixel);
		int green = GImage.getGreen(pixel);
		int blue = GImage.getBlue(pixel);
		int luminosity = returnLuminosity(pixel);
		int alpha = GImage.getAlpha(pixel);
		if (alpha != 0) {
			if (green/2 > Math.max(red, blue)) {
				pixel = color.getRGB();
			} else if (luminosity > LUMINOSITY_TO_BE_PAINTED_WHITE) {
				pixel = Color.WHITE.getRGB();
			} else {
				pixel = Color.BLACK.getRGB();
			}
		} 
		return pixel;
	}
	
	/**
	 * This method calculates the luminosity of a given pixel.
	 */
	private int returnLuminosity(int pixel) {
		int red = GImage.getRed(pixel);
		int green = GImage.getGreen(pixel);
		int blue = GImage.getBlue(pixel);
		return computeLuminosity(red, green, blue);
	}
	
	private int computeLuminosity(int r, int g, int b) {
		return GMath.round(0.299 * r + 0.587 * g + 0.114 * b);
	}
	
	public double getX() {
		return this.karel.getX();
	}
	
	public double getY() {
		return this.karel.getY();
	}
	
	/**
	 * This method rotates the image to the right.
	 */
	public void rotateKarel() {
		int [][] sourceMatrix = this.karelArray;
		int sourceHeight = sourceMatrix.length;
		int sourceWidth = sourceMatrix[0].length;
		int [][] resultMatrix = new int [sourceWidth][sourceHeight];
		int resultHeight = resultMatrix.length;
		int resultWidth = resultMatrix[0].length;
		for (int r = 0; r < resultHeight; r++) {
			for (int c = 0; c < resultWidth; c++) {
				resultMatrix[r][c] = sourceMatrix[(sourceHeight-1)-c][r];
			}
		}
		this.karelArray = resultMatrix;
		this.karel = new GImage(resultMatrix, this.x, this.y);
		this.karel.setSize(this.width, this.height);
	}

	/**
	 * This method flips the image horizontally.
	 */
	public void flipKarel() {
		int [][] sourceMatrix = this.karelArray;
		int sourceR = sourceMatrix.length;
		int sourceC = sourceMatrix[0].length;
		for (int r = 0; r < sourceR/2; r++) {
			for (int c = 0; c < sourceC; c++) {
				int flippedR = (sourceR-1)-r;
				int temp = sourceMatrix[r][c];
				sourceMatrix[r][c] = sourceMatrix[flippedR][c];
				sourceMatrix[flippedR][c] = temp;
			}
		}
		this.karelArray = sourceMatrix;
		this.karel = new GImage(sourceMatrix, this.x, this.y);
		this.karel.setSize(this.width, this.height);
	}
	
}


