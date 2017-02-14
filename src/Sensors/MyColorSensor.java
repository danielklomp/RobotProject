package Sensors;

import lejos.nxt.ColorSensor;
import lejos.nxt.SensorPort;



/**
 * Keeps track of the colorsensor and reports changes to the listener
 * 
 * @author Aydin Biber
 * @version 0.1
 * @date 31/3/2015
 *
 */

public class MyColorSensor extends ColorSensor implements UpdatingSensor {
	private SensorListener lst;
	private float value; //latest value from the color sensor;
	private int _zero = 1023;
	private int _hundred = 0;
	
	/**
	 * Constructor for the MyColorSensor class
	 *
	 * @param port This is the port of the color sensor.
	 */
	public MyColorSensor(SensorPort port) {
		super(port);
	}
		
	/**
	 * updateState for MyColorSensor class, required by the implement
	 *
	 */
	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		if(lst != null) {
			if(_getLightValue() != value) {
				lst.stateChanged(this, _getLightValue());
				//System.out.println("here");
				value = _getLightValue();
			}
		}
	}

	/**
	 * Constructor for the SensorHandler class
	 * @return value Int value between 0 and 100.
	 */
	
	public int _getLightValue() {
		if(_hundred == _zero) return 0;
		
		int value = super.getRawLightValue();
		value = 100 * (value - _zero) / (_hundred - _zero);
		

		return value;
	}
	
	/**
	 * Constructor for the SensorHandler class
	 *
	 * @param lst The listener we need to set for the ColorSensor
	 */
	
	public void addListener(SensorListener lst){
		this.lst = lst;
	}
	
	/**
	 * Sets the new low value
	 * @param low The new low integer.
	 */
	
	public void _setLow (int low) {
		_zero = 1023 - low;
	}
	
	/**
	 * Sets the new high value
	 * @param high The new high integer.
	 */

	public void _setHigh (int high) {
		_hundred = 1023 - high;
	}

}
