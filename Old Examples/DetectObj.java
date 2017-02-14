package robotproject;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.objectdetection.Feature;
import lejos.robotics.objectdetection.FeatureDetector;
import lejos.robotics.objectdetection.RangeFeatureDetector;

public class DetectObj {
		
	private int MAX_DISTANCE = 30; // Afstand tot object in centimeters
	private int PERIOD = 500; // In milliseconds
	private UltrasonicSensor us;
	private FeatureDetector fd;
	private boolean objDetected = false;
	
	
	public boolean getObjDetected() {
		return objDetected;
	}
	
	public DetectObj() {
		
		us = new UltrasonicSensor(Config.ULTRASONICSENSORPORT);
	    fd = new RangeFeatureDetector(us, MAX_DISTANCE, PERIOD);
	}
	
	public void Update() {
		
		Feature result = fd.scan();
		if(result != null) {
			//System.out.println("Range: " + result.getRangeReading().getRange());
			objDetected = true;
		} else {
			objDetected = false;
		}
	}
	
}
