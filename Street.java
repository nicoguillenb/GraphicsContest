import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Street implements KarelArtInterface {
	

	private static final int MIN_KAREL_SIZE = 30;
	private static final int MAX_KAREL_SIZE = 40;
	private static final double MIN_Y = ART_HEIGHT*0.85;
	private static final double MAX_Y = ART_HEIGHT*0.90;
	private static final int NUM_KARELS_STREET = 200;

	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Street() {
		for (int i = 0; i < NUM_KARELS_STREET; i++) {
			int x = rgen.nextInt(-MIN_KAREL_SIZE, ART_WIDTH);
			double y = rgen.nextDouble(MIN_Y, MAX_Y);
			int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
			int grey = getRandomGrey();
			Color color = new Color(grey, grey, grey);
			MiniKarel karel = new MiniKarel (x, y, size, color);
			miniKarelList.add(karel);
		}
		}
	
	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	public void move(int x, int y) {
		for (MiniKarel nextKarel : miniKarelList) {
			nextKarel.getKarel().move(x, y);
		}
	}
	
	private int getRandomGrey() {
		int index = rgen.nextInt(4);
		if(index == 0) return 220;
		if(index == 1) return 211;
		if(index == 2) return 192;
		if(index == 3) return 169;
		else return 128;
	}
	
}
