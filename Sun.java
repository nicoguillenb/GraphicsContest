import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Sun implements KarelArtInterface {
	
	private static final int NUM_KARELS = 80;
	private static final int BLUE = 0;
	private static final int RED = 255;
	private static final int MIN_GREEN = 150;
	private static final int MAX_GREEN = 255;
	private static final int MIN_KAREL_SIZE = 15;
	private static final int MAX_KAREL_SIZE = 40;
	private static final int RADIUS = 40;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Sun() {
		int centerX = rgen.nextInt(0, ART_WIDTH-MAX_KAREL_SIZE);
		int centerY = RADIUS;
		for (int i = 0; i < NUM_KARELS; i++) {
			int x = rgen.nextInt(centerX-RADIUS,centerX+RADIUS);
			int y = (int) rgen.nextDouble(centerY - getPossibleY(centerX, x), centerY + getPossibleY(centerX, x));
			int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
			int red = RED;
			int green = rgen.nextInt(MIN_GREEN,MAX_GREEN);
			int blue = BLUE;
			Color color = new Color(red, green, blue);
			MiniKarel karel = new MiniKarel (x, y, size, color);
			miniKarelList.add(karel);
		}
		}
	
	private double getPossibleY(int centerX, int x) {
		int a = RADIUS - Math.abs(centerX - Math.abs(x));
		int b = RADIUS*2 - a;
		double c = Math.sqrt(a*b);
		return c;
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
