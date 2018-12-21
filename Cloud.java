import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Cloud implements KarelArtInterface {
	
	private static final int NUM_KARELS = 20;
	private static final int MIN_CIRCLES = 4;
	private static final int MAX_CIRCLES = 8;
	private static final int MIN_BLUE = 240;
	private static final int MAX_BLUE = 255;
	private static final int MIN_RED = 240;
	private static final int MAX_RED = 255;
	private static final int MIN_GREEN = 240;
	private static final int MAX_GREEN = 255;
	private static final int MIN_KAREL_SIZE = 15;
	private static final int MAX_KAREL_SIZE = 30;
	private static final int MIN_RADIUS = 10;
	private static final int MAX_RADIUS = 20;
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Cloud() {
		int numberOfCircles = rgen.nextInt(MIN_CIRCLES, MAX_CIRCLES);
		for (int circle = 0; circle < numberOfCircles; circle++) {
			int radius = rgen.nextInt(MIN_RADIUS, MAX_RADIUS);
			double centerX = rgen.nextDouble(0,numberOfCircles)*MAX_RADIUS+MAX_RADIUS;
			double centerY = MAX_RADIUS;
			for (int i = 0; i < NUM_KARELS; i++) {
				double x = rgen.nextDouble(centerX-radius,centerX+radius);
				double y = rgen.nextDouble(centerY - getPossibleY(radius, centerX, x), centerY + getPossibleY(radius, centerX, x));
				int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
				int red =  rgen.nextInt(MIN_RED,MAX_RED);
				int green = rgen.nextInt(MIN_GREEN,MAX_GREEN);
				int blue =  rgen.nextInt(MIN_BLUE,MAX_BLUE);
				Color color = new Color(red, green, blue);
				MiniKarel karel = new MiniKarel (x, y, size, color);
				miniKarelList.add(karel);
			}
		}
	}
		


	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	public void setCloudLocation() {
	int randomY = rgen.nextInt(ART_HEIGHT/2-MAX_RADIUS*2);	
		for (MiniKarel nextKarel : miniKarelList) {
			double x = nextKarel.getX() - MAX_RADIUS*MAX_CIRCLES;
			double y = nextKarel.getY() + randomY;
			nextKarel.moveKarel(x, y);
		}	
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
