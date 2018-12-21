import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class Buildings implements KarelArtInterface {
	

	private static final int MIN_KAREL_SIZE = 80;
	private static final int MAX_KAREL_SIZE = 90;
	private static final double BASE_Y = ART_HEIGHT*0.80;
	private static final int RANGE_Y = 8;
	private static final int MIN_STRETCH = 300;
	private static final int MAX_STRETCH = 400;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	public Buildings() {
		for (int i = 0; i < NUM_KARELS_BUILDING; i++) {
			int size = rgen.nextInt(MIN_KAREL_SIZE,MAX_KAREL_SIZE);
			int stretch = rgen.nextInt(MIN_STRETCH,MAX_STRETCH);
			int x = rgen.nextInt(-MIN_KAREL_SIZE, ART_WIDTH);
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
