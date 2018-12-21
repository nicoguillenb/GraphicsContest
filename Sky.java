import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Sky implements KarelArtInterface {
	

	private static final int BLUE = 255;
	private static final int MIN_RED = 100;
	private static final int MAX_RED = 180;
	private static final int MIN_GREEN = 180;
	private static final int MAX_GREEN = 240;
	private static final int MIN_KAREL_SIZE = 30;
	private static final int MAX_KAREL_SIZE = 40;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public Sky() {
		for (int i = 0; i < NUM_KARELS_SKY; i++) {
			int x = rgen.nextInt(-MIN_KAREL_SIZE, ART_WIDTH);
			double y = rgen.nextDouble(-MIN_KAREL_SIZE, ART_HEIGHT*PROPORTION_SKI_GRASS);
			int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
			int red = rgen.nextInt(MIN_RED,MAX_RED);
			int green = rgen.nextInt(MIN_GREEN,MAX_GREEN);
			int blue = BLUE;
			Color color = new Color(red, green, blue);
			MiniKarel karel = new MiniKarel (x, y, size, color);
			miniKarelList.add(karel);
		}
		}
	
	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	
}
