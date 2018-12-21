import acm.graphics.GImage;
import acm.graphics.GMath;
import acm.util.RandomGenerator;
import java.applet.*;
import java.awt.Color;


public interface KarelArtInterface {
	
	public static final int ART_WIDTH = 700;
	public static final int ART_HEIGHT = 500;
	public static final int NUM_KARELS_SKY = 1600;
	public static final int NUM_KARELS_OCEAN = 1000;
	public static final int NUM_KARELS_BUILDING = 22;
	public static final double PROPORTION_SKI_GRASS = 0.60;
	public static final int NUM_KARELS_STREET = 200;
	public static final String SONG_NAME = "adayinthelife.wav";
	public static final String SCREEN_FONT = "SansSerif-BOLD-36";
	public static final Color DARK_YELLOW = new Color (254, 229, 0);
	public RandomGenerator rgen = RandomGenerator.getInstance();
	

	public GImage karel = new GImage("karel.png");
	public int [][] karelArray = karel.getPixelArray();
	
	public default int [][] getKarelArray() {
		return karelArray.clone();
	}
	
	public default GImage getKarelImage() {
		return karel;
	}
		
	
	public default double getPossibleY(double radius, double centerX, double x) {
		double a = radius - Math.abs(centerX - Math.abs(x));
		double b = radius*2 - a;
		double c = Math.sqrt(a*b);
		return c;
	}
}
