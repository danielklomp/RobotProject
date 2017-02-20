package robotproject;

import lejos.robotics.Color;
import Sensors.SensorListener;
import Sensors.MyColorSensor;
import Sensors.MyLightSensor;
import Sensors.UpdatingSensor;

/**
 * Calibrate the sensors and give instructions to MovementController
 * 
 * @author {Dimitry Volker}
 * @version 0.1
 * @date 31/3/2015
 *
 */

public class LineController implements SensorListener {
	
	private MyLightSensor lightS; ///< private MyLightSensor lightS The instance of MyLightSensor
	private MyColorSensor colorS; ///< private MyColorSensor colorS The instance of MyColorSensor
	private int ligthsensorHI = 0; ///< private int lighsensorHI The high value for the LightSensor
	private int ligthsensorLOW = 1023; ///< private int lighsensorLOW The low value for the LightSensor
	private int colorsensorHI = 0; ///< private int colorsensorHI The high value for the ColorSensor
	private int colorsensorLOW = 1023; ///< private int colorsensorLOW The low value for the ColorSensor
	private int cV = 0; ///< private int colorsensorHI The high value for the ColorSensor
	private int lV = 0; ///< private int colorsensorHI The high value for the ColorSensor
	
	/**
	   * Constructor of LineController, Sets both Sensors and adds their listeners
	   * @param ligtS the instance of MyLightSensor
	   * @param colorS the instance of MyColorSensor
	   * @return the requested DoxyTest object
	   * @exception throws an exception if id not in range
	   */

	public LineController(MyLightSensor lightS, MyColorSensor colorS){
		
		this.colorS = colorS;
		this.lightS = lightS;
		this.colorS.addListener(this);
		this.lightS.addListener(this);

		colorS.setFloodlight(Color.WHITE);
		lightS.setFloodlight(true);
	}
	
	/**
	   * Function Calibration: Calibrate the colors around the robot for optimal line detection
	   * @param boolean done the bool that indicates wether the process of Calibrating is done. When done it will set the sensors high and lows
	   
	   */

	public void Calibration(boolean done) {
		//ligthsensor
		if(lightS.getNormalizedLightValue() < ligthsensorLOW) {
			ligthsensorLOW = lightS.getNormalizedLightValue();
		}
		if(lightS.getNormalizedLightValue() > ligthsensorHI) {
			ligthsensorHI = lightS.getNormalizedLightValue();
		}
		//colorsensor
		if(colorS.getRawLightValue() < colorsensorLOW) {
			colorsensorLOW = colorS.getRawLightValue();
		}
		if(colorS.getRawLightValue() > colorsensorHI) {
			colorsensorHI = colorS.getRawLightValue();
		}
		if(done){
			lightS.setLow(ligthsensorLOW);
			lightS.setHigh(ligthsensorHI);
			colorS._setLow(colorsensorHI);
			colorS._setHigh(colorsensorLOW);
			System.out.println(ligthsensorLOW + " " + ligthsensorHI);
			System.out.println(colorsensorLOW + " " + colorsensorHI);
			System.out.println(lightS.getLightValue());
			System.out.println(colorS._getLightValue());
		} else {
			System.out.println(ligthsensorLOW + " " + ligthsensorHI);
			System.out.println(colorsensorLOW + " " + colorsensorHI);
		}
	}
	
	/**
	   * Function UpdateControllerAndRobotState: UpdateControllerAndRobotState function of class LineController, sets the values of sensor to Movementcontrollers follow function

	   * @param mc the instance of MovementController
	   */

	public void Update(MovementController mc){
		mc.FollowLine(cV, lV);
		//System.out.println("update " + cV + " " + lV);
	}

	/**
	   * stateChanged: stateChanged gets called when values of Sensors change
	   * @param sensor the instance of UpdatingSensor
	   * @param value the value of the sensor
	   */
	@Override
	public void stateChanged(UpdatingSensor sensor, float value) {
		// TODO Auto-generated method stub
		if(sensor.equals(lightS)){
			if(value > 100 )
				this.lV = 100;
			if(value < 0) 
				this.lV = 0;
			
			this.lV = (int)value;
		}
		
		if(sensor.equals(colorS)){
			if(value > 100 )
				this.cV = 100;
			if(value < 0) 
				this.cV = 0;
			
			this.cV = (int)value;
		}
		
	}
}
