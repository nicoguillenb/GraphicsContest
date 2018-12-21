import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Cars implements KarelArtInterface {
	
	private static final int NUM_WHEELS = 2;
	private static final int MIN_CIRCLES = 4;
	private static final int MAX_CIRCLES = 8;
	private static final int MIN_BLUE = 0;
	private static final int MAX_BLUE = 20;
	private static final int MIN_RED = 0;
	private static final int MAX_RED = 20;
	private static final int MIN_GREEN = 0;
	private static final int MAX_GREEN = 20;
	private static final double MIN_KAREL_Y = ART_HEIGHT*0.80;
	private static final double MAX_KAREL_Y = ART_HEIGHT*0.86;
	private static final int KAREL_SIZE = 40;
	private static final int WHEEL_SIZE = 15;
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Cars() {
		double x = KAREL_SIZE;
		double y = rgen.nextDouble(MIN_KAREL_Y,MAX_KAREL_Y);
		Color randomColor = rgen.nextColor();
		MiniKarel karel = new MiniKarel (x, y, KAREL_SIZE, randomColor);
		karel.rotateKarel();	
		karel.flipKarel();	
		miniKarelList.add(karel);
		for (int i = 0; i < NUM_WHEELS; i++) {
			double wheelX = karel.getX()+(KAREL_SIZE-WHEEL_SIZE)*i;
			double wheelY = y+KAREL_SIZE-WHEEL_SIZE;
			int red =  rgen.nextInt(MIN_RED,MAX_RED);
			int green = rgen.nextInt(MIN_GREEN,MAX_GREEN);
			int blue =  rgen.nextInt(MIN_BLUE,MAX_BLUE);
			Color color = new Color(red, green, blue);
			MiniKarel karelWheels = new MiniKarel (wheelX, wheelY, WHEEL_SIZE, color);
			miniKarelList.add(karelWheels);
		}
	}

	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	public void move(int x, int y) {
		for (MiniKarel nextKarel : miniKarelList) {
			nextKarel.moveKarel(x, y);
		}
	}
	
	public double getX() {
		double x = ART_WIDTH;
		for (MiniKarel nextKarel : miniKarelList) {
			if (nextKarel.getX()< x) x = nextKarel.getX();
		}
		return x;
	}
	
	public double getY() {
		double y = ART_WIDTH;
		for (MiniKarel nextKarel : miniKarelList) {
			if (nextKarel.getY()< y) y = nextKarel.getY();
		}
		return y;
	}
	
	public void setCloudTuNull() {
		for (MiniKarel nextKarel : miniKarelList) {
			nextKarel.setKarelToNull();
			nextKarel = null;
		}
	}
}
