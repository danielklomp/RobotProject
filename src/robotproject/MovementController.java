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
	
	private NXTRegulatedMotor m1 = new NXTRegulatedMotor(Config.MOTOR1PORT);
	private NXTRegulatedMotor m2 = new NXTRegulatedMotor(Config.MOTOR2PORT);
	private STATE state = STATE.Stop;
	private LineController lineController;
	private EvadeController evadeContoller;
	private int cV,lV,dv = 0; /// sensor values
	
	/**
	   * MovementController: The constructor of class MovementController

	   * @param lc the instance of LineController
	   * @param ec the instance of EvadeController
	   */

	public MovementController(LineController lc, EvadeController ec) {
		this.lineController = lc;
		this.evadeContoller = ec;
	}
	
	/**
	   * Folow: Sets values of cV and lV
	   * @param cV value of the colorSensor
	   * @param lV value of the lightSensor
	   */

	public void Follow(int cV, int lV) {
		this.cV = cV;
		this.lV = lV;
	}
	
	/**
	   * Evade: Sets value of dv (Distance value) of detected object
	   * @param d the distance of the detected object
	   */

	public void Evade(int d) {
		this.dv = d;
	}
	
	/**
	   * Update: Update function of MovementController class
	   */

	public void Update() {
		while(Button.ESCAPE.isUp()) {
			
			if(Button.LEFT.isDown()) {
				state = STATE.Calibrate;
			}
			if(Button.RIGHT.isDown()) {
				boolean test = true;
				while(test) {
					evadeContoller.update(this);
					m1.setSpeed(300); m2.setSpeed(300);
					m1.forward(); m2.forward();
					if(dv < Config.MINDISTANCE) {
						test = false;
						Sound.setVolume(100);
						Sound.beep();
					}
				}
				state = STATE.Evade;
			}
			
			if(state == STATE.Calibrate){

				m1.resetTachoCount();
				m1.setSpeed(150);
				m2.setSpeed(150);
				
				m1.forward();
				m2.backward();
				
				while (m1.getTachoCount() < 600) {
					lineController.Calibration(false);
				}
				lineController.Calibration(true);
				state = STATE.Stop;
			}
			
			if(state == STATE.Search){
				lineController.Update(this);
				m1.setSpeed(200); m2.setSpeed(200);
				m1.forward(); m2.forward();
				System.out.println("search " +lV  + " " + cV);
				if(lV < 50 || cV < 50) { 
					state = STATE.Follow;
				}
				
			}
			
			if(state == STATE.Follow) {
				lineController.Update(this);
				evadeContoller.update(this);
				
				if(dv < Config.MINDISTANCE) {
					state = STATE.Evade;
				}
				
				//m1.setSpeed(300); m2.setSpeed(300);
				m1.forward(); m2.forward();
				
				System.out.println("follow " + lV  + " " + cV);

					float speed = 5 * lV /2 ;
					m2.setSpeed(speed);

					float speed2 = 5 * cV / 2 ;
					m1.setSpeed(speed2);
		
			}
			
			if(state == STATE.Evade) {
				m1.resetTachoCount();
				m1.setSpeed(300); m2.setSpeed(300);
				m1.backward(); m2.forward();
				int tmptaco = 0;
				
				while(dv < Config.MINDISTANCE) {
					evadeContoller.update(this);
					//distanceTurned++;
				}
				Delay.msDelay(50);
				m1.resetTachoCount();
				m1.forward();
				
				while(m1.getTachoCount() < 700) {}
				
				m2.backward();
				
				while(dv > Config.MINDISTANCE) {
					evadeContoller.update(this);
				}
				
				m2.forward();
				m1.backward();
				Delay.msDelay(400);
				m1.forward();
				Delay.msDelay(500);
				state = STATE.Stop;
				
			}
			
			if(state == STATE.Stop) {
				m1.stop();
				m2.stop();
			}
		}
	}
}
