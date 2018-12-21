import java.awt.*;
import java.util.ArrayList;

import acm.graphics.*;
import acm.util.RandomGenerator;

public class PsychodelicKarel implements KarelArtInterface {
	


	private ArrayList<MiniKarel >miniKarelList = new ArrayList<MiniKarel>();
	
	
	public PsychodelicKarel(int karelSize) {
		int columns = ART_WIDTH/(karelSize);
		int rows = ART_HEIGHT/(karelSize);
		for (int r = 1; r < rows; r+=2) {
			for (int c = 1; c < columns; c+=2) {
				Color color = rgen.nextColor();
				MiniKarel karel = new MiniKarel (c*karelSize, r*karelSize, karelSize, color);
				miniKarelList.add(karel);
			}
		}
		}

	
	public ArrayList<MiniKarel> getKarelList() {
		return miniKarelList;
	}
	
	
}
