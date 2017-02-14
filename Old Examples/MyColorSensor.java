package robotproject;

import lejos.nxt.Button;
import lejos.nxt.ColorSensor;
import lejos.nxt.ColorSensor.Color;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.util.TextMenu;

public class MyColorSensor {
	
	private String colorNames[] = {"None", "Red", "Green", "Blue", "Yellow",
            "Megenta", "Orange", "White", "Black", "Pink",
            "Grey", "Light Grey", "Dark Grey", "Cyan"};
	
	private ColorSensor cs;
	private boolean detect = false;
	
	public boolean isDetect() {
		Update();
		return detect;
	}
	
	public MyColorSensor(){

       cs = new ColorSensor(Config.COLORSENSORPORT);
       cs.setFloodlight(Color.WHITE);
	}
	
	private String getcolor() {
		ColorSensor.Color vals = cs.getColor();
		return colorNames[vals.getColor() + 1];
	}
	
	public void Update() {
		if(getcolor().equals("Black")) {
			this.detect = true;
		} else {
			this.detect = false;
		}
	}
}
