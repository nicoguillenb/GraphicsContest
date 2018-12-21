import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Mountain implements KarelArtInterface {
	
	private static final int NUM_KARELS = 500;
	private static final int NUM_MOUNTAINS = 5;
	private static final int BLUE = 0;
	private static final int MIN_RED = 70;
	private static final int MAX_RED = 130;
	private static final int MIN_GREEN = 50;
	private static final int MAX_GREEN = 90;
	private static final int MIN_KAREL_SIZE = 15;
	private static final int MAX_KAREL_SIZE = 40;
	private static final int MIN_HEIGHT = 120;
	private static final int MAX_HEIGHT = 200;
	private static final double BASE_Y = ART_HEIGHT*PROPORTION_SKI_GRASS;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Mountain() {
		for(int mountain = 0; mountain< NUM_MOUNTAINS; mountain++) {
			int centerX = mountain*ART_WIDTH/4;
			int height = rgen.nextInt(MIN_HEIGHT, MAX_HEIGHT);
			for (int i = 0; i < NUM_KARELS; i++) {
				int x = rgen.nextInt(centerX-(height),centerX+(height));
				int y = (int) rgen.nextDouble(BASE_Y - getPossibleY(height, centerX, x), BASE_Y );
				int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
				int red = rgen.nextInt(MIN_RED,MAX_RED);
				int green = rgen.nextInt(MIN_GREEN,MAX_GREEN);
				int blue = BLUE;
				Color color = new Color(red, green, blue);
				MiniKarel karel = new MiniKarel (x, y, size, color);
				miniKarelList.add(karel);
			}
		}
		}
	
	private double getPossibleY(int height, int centerX, int x) {
		int a = height - Math.abs(centerX - x);
		return a;
	}

	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	public void move(int x, int y) {
		for (MiniKarel nextKarel : miniKarelList) {
			nextKarel.moveKarel(x, y);
		}
	}
	
}
