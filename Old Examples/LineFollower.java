package robotproject;

import lejos.nxt.ColorSensor;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

public class LineFollower {
	
	private LightSensor light;
	private final int blackWhiteLimit = 49; // het keer punt tussen wit en zwart
	private boolean isLimit = false;
	
	public LightSensor getLight() {
		return light;
	}

	public boolean isLimit() {
		return isLimit;
	}
	
	public int getLevel(){
		return light.readValue();
	}

	public LineFollower() {
		light = new LightSensor(SensorPort.S1);
		
	}
	
	public void Update() {
		//System.out.println("light: " + light.readValue());
		if (light.readValue() > blackWhiteLimit)
		{
			isLimit = true;	 //wit
		} else {
			isLimit = false; // zwart
		}
	}
}
