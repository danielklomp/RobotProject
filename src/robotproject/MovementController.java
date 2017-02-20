package robotproject;

import robotproject.Config.STATE;
import lejos.nxt.Button;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.util.Delay;

/**
 * The MovementController class , will control the motors based on sensor output
 * 
 * @author {Jasper van Hulst}
 * @version 0.1
 * @date 17/3/2015 
 */


public class MovementController {
	// Refactor: Changed names to enhance readability
	//
	private NXTRegulatedMotor regulatedMotorRight = new NXTRegulatedMotor(Config.MOTOR1PORT);
	private NXTRegulatedMotor regulatedMotorLeft = new NXTRegulatedMotor(Config.MOTOR2PORT);
	private STATE robotMovementState = STATE.Stop;
	private LineController lineController;
	private EvadeController evadeContoller;
	private int colorSensorValue,lightSensorValue, distanceSensorValue = 0; /// sensor values
	
	/**
	   * MovementController: The constructor of class MovementController

	   * @param lineController the instance of LineController
	   * @param evadeController the instance of EvadeController
	   */

	public MovementController(LineController lineController, EvadeController evadeController) {
		this.lineController = lineController;
		this.evadeContoller = evadeController;
	}
	
	/**
	   * Folow: Sets values of cV and lV
	   * @param colorSensorValue value of the colorSensor
	   * @param lightSensorValue value of the lightSensor
	   */

	public void FollowLine(int colorSensorValue, int lightSensorValue) {
		this.colorSensorValue = colorSensorValue;
		this.lightSensorValue = lightSensorValue;
	}
	
	/**
	   * setDistanceSensorValue: Sets value of dv (Distance value) of detected object
	   * @param d the distance of the detected object
	   */

	public void setDistanceSensorValue(int value) {
		this.distanceSensorValue = value;
	}
	
	/**
	   * UpdateControllerAndRobotState: UpdateControllerAndRobotState function of MovementController class
	   */
	//Refactor: Does 2 things now in stead of 12, but still too much
	public void UpdateControllerAndRobotState() {
		while(Button.ESCAPE.isUp()) {

			updateControllerState();
			checkRobotState();
		}
	}

	// Refactor : This function does too many things! It should be split into multiple methods
	private void checkRobotState() {
		if(robotMovementState == STATE.Calibrate){
			turnTangoCount();
		}
		
		if(robotMovementState == STATE.Search){
			changeSpeedsToSearhObject();
		}
		
		if(robotMovementState == STATE.Follow) {
			lineController.Update(this);
			evadeContoller.update(this);
			
			if(distanceSensorValue < Config.MINDISTANCE) {
				robotMovementState = STATE.Evade;
			}
			followLine();
		}
		
		if(robotMovementState == STATE.Evade) {
			startEvadion();
		}
		
		if(robotMovementState == STATE.Stop) {
			regulatedMotorRight.stop();
			regulatedMotorLeft.stop();
		}
	}

	private void updateControllerState() {
		if(Button.LEFT.isDown()) {
			robotMovementState = STATE.Calibrate;
		}
		if(Button.RIGHT.isDown()) {
			testDistanceToObject();
		}
	}

	private void changeSpeedsToSearhObject() {
		lineController.Update(this);
		regulatedMotorRight.setSpeed(200); regulatedMotorLeft.setSpeed(200);
		regulatedMotorRight.forward(); regulatedMotorLeft.forward();
		System.out.println("search " +lightSensorValue  + " " + colorSensorValue);
		if(lightSensorValue < 50 || colorSensorValue < 50) { 
			robotMovementState = STATE.Follow;
		}
	}

	private void startEvadion() {
		regulatedMotorRight.resetTachoCount();
		regulatedMotorRight.setSpeed(300); regulatedMotorLeft.setSpeed(300);
		regulatedMotorRight.backward(); regulatedMotorLeft.forward();
		int tmptaco = 0;
		
		while(distanceSensorValue < Config.MINDISTANCE) {
			evadeContoller.update(this);
		}
		Delay.msDelay(50);
		regulatedMotorRight.resetTachoCount();
		regulatedMotorRight.forward();
		
		while(regulatedMotorRight.getTachoCount() < 700) {}
		
		regulatedMotorLeft.backward();
		
		while(distanceSensorValue > Config.MINDISTANCE) {
			evadeContoller.update(this);
		}
		
		regulatedMotorLeft.forward();
		regulatedMotorRight.backward();
		Delay.msDelay(400);
		regulatedMotorRight.forward();
		Delay.msDelay(500);
		robotMovementState = STATE.Stop;
	}

	private void followLine() {
		//m1.setSpeed(300); m2.setSpeed(300);
		regulatedMotorRight.forward(); regulatedMotorLeft.forward();
		
		System.out.println("follow " + lightSensorValue  + " " + colorSensorValue);

			float speed = 5 * lightSensorValue /2 ;
			regulatedMotorLeft.setSpeed(speed);

			float speed2 = 5 * colorSensorValue / 2 ;
			regulatedMotorRight.setSpeed(speed2);
	}

	private void turnTangoCount() {
		regulatedMotorRight.resetTachoCount();
		regulatedMotorRight.setSpeed(150);
		regulatedMotorLeft.setSpeed(150);
		
		regulatedMotorRight.forward();
		regulatedMotorLeft.backward();
		
		while (regulatedMotorRight.getTachoCount() < 600) {
			lineController.Calibration(false);
		}
		lineController.Calibration(true);
		robotMovementState = STATE.Stop;
	}

	private void testDistanceToObject() {
		boolean testDistance = true;
		while(testDistance) {
			evadeContoller.update(this);
			regulatedMotorRight.setSpeed(300); regulatedMotorLeft.setSpeed(300);
			regulatedMotorRight.forward(); regulatedMotorLeft.forward();
			if(distanceSensorValue < Config.MINDISTANCE) {
				testDistance = false;
				Sound.setVolume(100);
				Sound.beep();
			}
		}
		robotMovementState = STATE.Evade;
	}
}
