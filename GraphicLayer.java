import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class GraphicLayer implements KarelArtInterface {
	
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	private int[][] backgroundArray;
	private GImage background;
	
	
	public GraphicLayer(int bgProperty) {
		backgroundArray = new int[ART_HEIGHT][ART_WIDTH];
		if (bgProperty == 1) {
			makeArrayBlack(this.backgroundArray);
		} else {
			makeArrayTransparent(this.backgroundArray);
		}
	}	

	public void addKarelsToLayer(ArrayList<MiniKarel> MiniKarelArray){
		for (MiniKarel nextKarel : MiniKarelArray) {
			int x = (int) nextKarel.getX();
			int y = (int) nextKarel.getY();
			int [][] karelArray = nextKarel.getKarelArrayReduced();
			for (int r = y; r < (karelArray.length + y); r++) {
				for (int c = x; c < (karelArray[0].length + x); c++) {
					if (pixelInBounds(r, c, this.backgroundArray)) {
						if(GImage.getAlpha(karelArray [r-y][c-x]) != 0) {
							this.backgroundArray[r][c] = karelArray [r-y][c-x];
						}
					}
				}
			}
			nextKarel.setKarelToNull();
			nextKarel = null;
		}
		this.background = new GImage(this.backgroundArray);
	}
	
	public GImage turnOnTheLights(Color color, int size, double frequency) {
		int rows = this.backgroundArray.length;
		int cols = this.backgroundArray[0].length;
		for (int r = 0; r < rows; r += size) {
			for (int c = 0; c < cols; c += size) {
				if (rgen.nextBoolean(frequency)) {
					for (int r2 = 0; r2 < size; r2++) {
						for (int c2 = 0; c2 < size; c2++) {
							
								if (this.backgroundArray[r+r2][c+c2] == Color.WHITE.getRGB()) {
									this.backgroundArray[r+r2][c+c2] = color.getRGB();
								} else if (this.backgroundArray[r+r2][c+c2] == color.getRGB()) {
									this.backgroundArray[r+r2][c+c2] = Color.WHITE.getRGB();
								
							}
						}
					}
				}	
			}
		}	
		return new GImage (backgroundArray);
	}
	
	public GImage turnOffTheLights(Color color) {
		int rows = this.backgroundArray.length;
		int cols = this.backgroundArray[0].length;
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				if (this.backgroundArray[r][c] == color.getRGB()) {
					this.backgroundArray[r][c] = Color.WHITE.getRGB();
				}	
			}
		}	
		return new GImage (backgroundArray);
	}
	
	private void makeArrayBlack(int[][] matrix) {
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = Color.BLACK.getRGB();
			}
		}
	}
	
	private void makeArrayTransparent(int[][] matrix) {
		Color transparent = new Color (0,0,0,0);
		for (int r = 0; r < matrix.length; r++) {
			for (int c = 0; c < matrix[0].length; c++) {
				matrix[r][c] = transparent.getRGB();
				
			}
		}	
	}

	/**
	 * This method returns the negative image of the source image.
	 */
	public GImage turnRed() {
		int sourceR = backgroundArray.length;
		int sourceC = backgroundArray[0].length;
		for (int r = 0; r < sourceR; r++) {
			for (int c = 0; c < sourceC; c++) {
				int pixel = backgroundArray[r][c];
				int red = GImage.getRed(pixel);
			    int green = GImage.getGreen(pixel)-5;
			    int blue = GImage.getBlue(pixel);
			    if (backgroundArray[r][c] != Color.BLACK.getRGB() && backgroundArray[r][c] != Color.WHITE.getRGB() && GImage.getAlpha(backgroundArray[r][c]) != 0) {
			    	backgroundArray[r][c] = GImage.createRGBPixel(red, green, blue);
			    } 
			}
		}
		return new GImage (backgroundArray);
	}
	
	/**
	 * This method returns the negative image of the source image.
	 */
	public GImage turnBlue() {
		int sourceR = backgroundArray.length;
		int sourceC = backgroundArray[0].length;
		for (int r = 0; r < sourceR; r++) {
			for (int c = 0; c < sourceC; c++) {
				int pixel = backgroundArray[r][c];
				int red = GImage.getRed(pixel);
			    int green = GImage.getGreen(pixel)+5;
			    int blue = GImage.getBlue(pixel);
			    if (backgroundArray[r][c] != Color.BLACK.getRGB() && backgroundArray[r][c] != Color.WHITE.getRGB() && GImage.getAlpha(backgroundArray[r][c]) != 0) {
			    	backgroundArray[r][c] = GImage.createRGBPixel(red, green, blue);
			    } 
			}
		}
		return new GImage (backgroundArray);
	}
	
	/**
	 * This method returns the negative image of the source image.
	 */
	public GImage turnDark() {
		int sourceR = backgroundArray.length;
		int sourceC = backgroundArray[0].length;
		for (int r = 0; r < sourceR; r++) {
			for (int c = 0; c < sourceC; c++) {
				int pixel = backgroundArray[r][c];
				int red = GImage.getRed(pixel)-2;
			    int green = GImage.getGreen(pixel);
			    int blue = GImage.getBlue(pixel)-3;
			    if (backgroundArray[r][c] != Color.BLACK.getRGB() && backgroundArray[r][c] != Color.WHITE.getRGB() && GImage.getAlpha(backgroundArray[r][c]) != 0) {
			    	backgroundArray[r][c] = GImage.createRGBPixel(red, green, blue);
			    } 
			}
		}
		return new GImage (backgroundArray);
	}
	
	/**
	 * This method returns the negative image of the source image.
	 */
	public GImage turnLight() {
		int sourceR = backgroundArray.length;
		int sourceC = backgroundArray[0].length;
		for (int r = 0; r < sourceR; r++) {
			for (int c = 0; c < sourceC; c++) {
				int pixel = backgroundArray[r][c];
				int red = GImage.getRed(pixel)+2;
			    int green = GImage.getGreen(pixel);
			    int blue = GImage.getBlue(pixel)+3;
			    if (backgroundArray[r][c] != Color.BLACK.getRGB() && backgroundArray[r][c] != Color.WHITE.getRGB() && GImage.getAlpha(backgroundArray[r][c]) != 0) {
			    	backgroundArray[r][c] = GImage.createRGBPixel(red, green, blue);
			    } 
			}
		}
		return new GImage (backgroundArray);
	}
	
	public GImage getLayer() {
		return this.background;
	}
	
	public double getX() {
		return this.background.getX();
	}
	
	public double getY() {
		return this.background.getY();
	}
	
	public void move(double x, double d) {
		this.background.move(x, d);
	}

	public void setCloudLocation() {
		int randomY = rgen.nextInt(ART_HEIGHT/5*2);	
		double x = -150;
		this.background.setLocation(x, randomY);
	}	

	public void setCarLocation() {
		double x = rgen.nextDouble(-100, -50);
		this.background.setLocation(x, 0);
	}
	
	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	/**
	 * This method indicates whether an x-y coordiante is within the bounds of matrix of pixels.
	 */
	private boolean pixelInBounds(int r, int c, int[][] matrix) {
		return r >= 0 && c >= 0 && c < ART_WIDTH && r < ART_HEIGHT;
	}
	
	
}
