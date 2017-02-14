package Sensors;

import lejos.nxt.I2CPort;
import lejos.nxt.UltrasonicSensor;

/**
 * Keeps track of the Ultrasonicsensor and reports changes to the listener
 * 
 * @author Dimitry Volker
 * @version 0.1
 * @date 31/3/2015
 *
 */

public class MyUltrasonicSensor extends UltrasonicSensor implements UpdatingSensor {
	private float value;
	private SensorListener lst;
	
	/**
	 * Constructor for the MyColorSensor class
	 *
	 * @param port This is the port of the color sensor.
	 */
	public MyUltrasonicSensor(I2CPort port){
		super(port);
	}
	
	/**
	 * updateState for MyColorSensor class, required by the implement
	 */
	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		if (lst != null){  
			int curval = super.getDistance();
			if (curval != value) {  
				lst.stateChanged(this,curval);
				value = curval;
			}
		}
	}
	
	/**
	 * Set the SensorListener 
	 *
	 * @param lst This is the SensorListeren it needs to add.
	 */
	public void addListener(SensorListener lst){
		this.lst = lst;
	}
}
