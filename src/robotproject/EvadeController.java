package robotproject;

import Sensors.MyUltrasonicSensor;
import Sensors.SensorListener;
import Sensors.UpdatingSensor;

public class EvadeController implements SensorListener{
	
	private MyUltrasonicSensor ultrasonicSensor; ///< private MyUltasonicSensor ultrasonicSensor The instance of MyUltraSonicSensor
	private int distance = 255; ///< Private int distance The distance of the object
	
	/**
	   * EvadeController: Constructor of EvadeController. Sets the MyUltraSonicSensor instance and adds the listener.
	   * @param us the instance of MyUltraSonicSensor
	   */
	public EvadeController(MyUltrasonicSensor us) {
		this.ultrasonicSensor = us;
		this.ultrasonicSensor.addListener(this);
	}

	/**
	   * Update: Updates the controllers

	   * @param mc the MoveMentcontrollerobject
	   */

	public void update(MovementController mc) {
		mc.Evade(distance);
	}

	
	
   /**
	   * StateChanged: sets the distance of the detected object
	   * @param sensor the instance of the UpdatingSensor interface 
	   * @param value the distance of the seen object
	   */

	@Override
	public void stateChanged(UpdatingSensor sensor, float value) {
		if(this.ultrasonicSensor.equals(sensor)) {
			this.distance = (int) value;
		}
	}
	
	
}
