package Sensors;

/**
 * The Sensor Listener for the sensor classes.
 * 
 * @author Dimitry Volker
 * @version 0.1
 * @date 31/3/2015
 *
 */
public interface SensorListener {
	
	/**
	 * When a sensor changes of value, this will be called
	 *
	 * @param sensor This is the object of the other interface
	 * @param value The new value
	 */
	public void stateChanged(UpdatingSensor sensor , float value);
}
