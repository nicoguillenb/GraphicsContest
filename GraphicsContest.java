/*
 * File: GraphicsContest.java
 * --------------------------
 * This program creates an image of karelword. This program is called
 * "A day in Karelword"  with the Beatles as a background music.
 * Please watch until the end of the song.
 * 
 */

import acm.program.*;
import acm.util.MediaTools;
import acm.util.RandomGenerator;
import acm.program.*;

import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

import acm.graphics.*;

public class GraphicsContest extends GraphicsProgram implements KarelArtInterface{

	private HashMap <String, GraphicLayer> layers = new HashMap<String, GraphicLayer>();
	private ArrayList<GraphicLayer> layerArray = new ArrayList<GraphicLayer>();
	private ArrayList<ArrayList> MiniKarelArrays = new ArrayList<ArrayList>();
	private ArrayList<GraphicLayer> cloudLayers = new ArrayList<GraphicLayer>();
	private ArrayList<GraphicLayer> carLayers = new ArrayList<GraphicLayer>();
	
	private double start;
	private GLabel label;
	public int karelCounter;
	private int sunsetCounter = 0;
	AudioClip song;
	
	
	
	public void mouseClicked(MouseEvent e) {
		int i = e.getButton();
			if (i >= 0)	println("" + sunsetCounter + " " + (System.currentTimeMillis() - start));
	}
	
	

	public void run() {
		addMouseListeners();
		setUpKarelArt();
		setUpMobileKarelParts();
		song.play();
		runAnimation();
		removeAll();	
		song.stop();
		setUpLabel("I hope you liked it!", ART_HEIGHT/2);
		pause(5000);
		remove(label);
	}

	private void runAnimation() {
		start = System.currentTimeMillis();
		double freq = 0.6;
		int animationBeat = 0;
		while(true) {
			cloudAnimation(animationBeat);
			carAnimation(animationBeat);
			sunAnimation();
			if (animationBeat % 12 == 0) sunsetAnimation(freq);
			if (animationBeat % 10 == 0) layers.get("buildings").getLayer().setImage(layers.get("buildings").turnOnTheLights(DARK_YELLOW, 10, freq).getImage());
			//ends animation
			if (System.currentTimeMillis() - start > 275100) break;
			pause(50);
			animationBeat++;
		}
	}




	private void carAnimation(int animationBeat) {
		if (animationBeat % 50 == 0) {
			Cars car = new Cars();
			GraphicLayer layer = new GraphicLayer(0);
			layer.addKarelsToLayer(car.getKarelList());
			layer.setCarLocation();
			add(layer.getLayer());
			carLayers.add(layer);
			for (GraphicLayer nextCar: carLayers) {
				if (nextCar.getX() > ART_WIDTH) remove(nextCar.getLayer());
			}
		}
		for (GraphicLayer nextCar: carLayers) {
			nextCar.move(2,0);
		}
	}



	private void sunsetAnimation(double freq) {
		if (sunsetCounter < 30) {
			layers.get("sky").getLayer().setImage(layers.get("sky").turnRed().getImage());
			sunsetCounter++;
		} else if (sunsetCounter < 60) {
			layers.get("sky").getLayer().setImage(layers.get("sky").turnDark().getImage());
			sunsetCounter++;
		}
		else if (System.currentTimeMillis() - start < 133000) layers.get("sky").getLayer().setImage(layers.get("sky").turnOnTheLights(Color.YELLOW, 10, freq).getImage());
		else if (System.currentTimeMillis() - start < 134000) layers.get("sky").getLayer().setImage(layers.get("sky").turnOffTheLights(Color.YELLOW).getImage());
		else if (sunsetCounter < 90) {
			layers.get("sky").getLayer().setImage(layers.get("sky").turnLight().getImage());
			sunsetCounter++;
		} else if (sunsetCounter < 120) {
			layers.get("sky").getLayer().setImage(layers.get("sky").turnBlue().getImage());
			sunsetCounter++;
		}
	}



	private void sunAnimation() {
		if (layers.get("sun").getY() >= 0) {
			if (sunsetCounter < 60) layers.get("sun").move(0, 0.7);
			else if (sunsetCounter > 60) layers.get("sun").move(0, -0.7);
		}
	}



	private void cloudAnimation(int animationBeat) {
		if (animationBeat % 100 == 0) {
			Cloud cloud = new Cloud();
			GraphicLayer layer = new GraphicLayer(0);
			layer.addKarelsToLayer(cloud.getKarelList());
			layer.setCloudLocation();
			add(layer.getLayer());
			cloudLayers.add(layer);
			for (GraphicLayer nextCloud : cloudLayers) {
				if (nextCloud.getX() > ART_WIDTH) remove(nextCloud.getLayer());
			}
		}
		for (GraphicLayer nextCloud : cloudLayers) {
			nextCloud.move(2,0);
		}
	}



	private void setUpMobileKarelParts() {
		setUpSmallBuildings();
//		setUpCars();
	}

	private void setUpSmallBuildings() {
		SmallBuildings smallBuildings = new SmallBuildings();
		ArrayList<MiniKarel> miniKarelArray = smallBuildings.getKarelList();
		for (MiniKarel nextKarel : miniKarelArray) {
			nextKarel.turnOnTheLights(DARK_YELLOW, 4, 0.5);
			add(nextKarel.getKarel());
		}
		MiniKarelArrays.add(miniKarelArray);
	}
	
	private void setUpKarelArt() {
		setCanvasSize(ART_WIDTH, ART_HEIGHT);
		song = MediaTools.loadAudioClip(SONG_NAME);
		setBackground(Color.BLACK);
		setUpLabel("Please be patient", ART_HEIGHT/2);
		pause(5000);
		remove(label);
		setUpLabel("This might take a while! :)", ART_HEIGHT/2);
		setUpSky();
		setUpSun();
		setUpMountains();
		setUpGrass();
		setUpBuildings();
		setUpStreet();
		for (GraphicLayer nextLayer : layerArray) {
			add(nextLayer.getLayer());
		}
		remove(label);
	}
	
	private void setUpLabel(String string, int y) {
		label = new GLabel(string);
		label.setFont(SCREEN_FONT);
		label.setColor(Color.WHITE);
		label.setLocation((getWidth()-label.getWidth())/2, y);
		add(label);
	}



	private void setUpSun() {
		Sun sun = new Sun();
		GraphicLayer layer = new GraphicLayer(0);
		layer.addKarelsToLayer(sun.getKarelList());
		layers.put("sun",layer);
		layerArray.add(layer);
		sun = null;
	}

	private void setUpStreet() {
		Street street = new Street();
		GraphicLayer layer = new GraphicLayer(0);
		layer.addKarelsToLayer(street.getKarelList());
		layers.put("street",layer);
		layerArray.add(layer);
		street = null;
	}

	private void setUpBuildings() {
		Buildings buildings = new Buildings();
		GraphicLayer layer = new GraphicLayer(0);
		layer.addKarelsToLayer(buildings.getKarelList());
		layers.put("buildings",layer);
		layerArray.add(layer);
		buildings = null;
		
	}

	private void setUpMountains() {
		Mountain mountains = new Mountain();
		GraphicLayer layer = new GraphicLayer(0);
		layer.addKarelsToLayer(mountains.getKarelList());
		layers.put("mountain",layer);
		layerArray.add(layer);
		mountains = null;
	}

	private void setUpSky() {
		Sky sky = new Sky();
		GraphicLayer layer = new GraphicLayer(1);
		layer.addKarelsToLayer(sky.getKarelList());
		layers.put("sky",layer);
		layerArray.add(layer);
		sky = null;
	}
	
	private void setUpGrass() {
		Grass grass = new Grass();
		GraphicLayer layer = new GraphicLayer(0);
		layer.addKarelsToLayer(grass.getKarelList());
		layers.put("grass",layer);
		layerArray.add(layer);
		grass = null;
	}
}
