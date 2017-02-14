package Sensors;

import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;

/**
 * Keeps track of the LightSensor and reports changes to the listener
 * 
 * @author Dimitry Volker
 * @version 0.1
 * @date 31/3/2015
 *
 */

public class MyLightSensor extends LightSensor implements UpdatingSensor{
	private float value;
	private SensorListener lst;
	
	/**
	 * Constructor for the MyLightSensor class
	 *
	 * @param port This is the port of the light sensor.
	 */
	public MyLightSensor(SensorPort port){
		super(port);
	}
	
	/**
	 * updateState for MyColorSensor class, required by the implement
	 * @todo check if getLightValue() is the right function here.
	 */
	public void updateState() {
		if (lst != null){  	
			int curval = super.getLightValue();
			if (curval != value) {  
				lst.stateChanged(this,curval);
				value = curval;
			}
		}
	}
	
	/**
	 * Constructor for the SensorHandler class
	 *
	 * @param lst The listener we need to set for the Lightsensor
	 */
	public void addListener(SensorListener lst){
		this.lst = lst;
	}
}
