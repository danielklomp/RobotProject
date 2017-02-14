package robotproject;

import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;

/**
 * Collection of variables that hold the basic basic inforamtion 
 * 
 * @author {Jasper van Hulst}
 * @version 0.1
 * @date 2/4/2015
 *
 */

public class Config {
	public static int WEELBASE = 11; ///< public Static int WEELBASE the distance between the wheels in centimeters 
	public static int WEELDIAMETER = 56; ///< public Static in WEELDIAMETER the diameter of a wheen in millimeters
	
	public static MotorPort MOTOR1PORT = MotorPort.A; ///< public static MotorPort MOTOR1PORT The port of motor 1
	public static MotorPort MOTOR2PORT = MotorPort.C; ///< public static MotorPort MOTOR2PORT The port of motor 2
	
	public static final int MINDISTANCE  = 15; ///< public static final int MINDISTANCE The min distance at what the sensor detects objects
	
	public static SensorPort LIGHTSENSORPORT = SensorPort.S1; ///< public static SensorPort LIGHTSENSORPORT The port of Lightsensor
	public static SensorPort COLORSENSORPORT = SensorPort.S4; ///< public static SensorPort COLORSENSORPORT The port of SensorPort
	public static SensorPort ULTRASONICSENSORPORT = SensorPort.S2; ///< public static SensorPort ULTRASONICSENSORPORT The port of UltrasonicSensorPort 
	
	public static enum STATE{Follow,Stop,Calibrate,Search,Evade}; ///< public static enum STATE The state of the robot
	
}
