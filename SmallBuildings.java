import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class SmallBuildings implements KarelArtInterface {
	
	private static final int NUM_KARELS_SMALL_BUILDING = 30;
	private static final int MIN_KAREL_SIZE = 80;
	private static final int MAX_KAREL_SIZE = 85;
	private static final double BASE_Y = ART_HEIGHT*0.85;
	private static final int RANGE_Y = 20;
	private static final int MIN_STRETCH = 5;
	private static final int MAX_STRETCH = 250;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public SmallBuildings() {
		for (int i = 0; i < NUM_KARELS_SMALL_BUILDING; i++) {
			int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
			int stretch = rgen.nextInt(MIN_STRETCH,MAX_STRETCH);
			int x = ART_WIDTH/(NUM_KARELS_SMALL_BUILDING-1)*i + rgen.nextInt(-RANGE_Y, RANGE_Y) - size/2;
			double y = BASE_Y - size;
			Color color = rgen.nextColor();
			MiniKarel karel = new MiniKarel (x, y, size, color);
			karel.stretchKarel(stretch);
			miniKarelList.add(karel);
		}
		}
	
	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
}
