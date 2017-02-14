package robotproject;

import robotproject.Robot.STATE;
import lejos.nxt.Button;
import lejos.nxt.ButtonListener;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;

public class RobotController implements ButtonListener{
	
	private Logger dataLogger;
	private DetectObj detectObj; 
	private MyLightSensor lightSensor;
	private MyColorSensor colorSensor;
	private NXTRegulatedMotor m1,m2;
	
	private boolean running = true;

	public RobotController() {
		Button.ESCAPE.addButtonListener(this);
		this.dataLogger = new Logger("log");
		dataLogger.Stop(); /* just stopping it now no need to log anyting */
		
		this.detectObj = new DetectObj();
		this.lightSensor = new MyLightSensor();
		this.colorSensor =  new MyColorSensor();
		
		m1 = new NXTRegulatedMotor(Config.MOTOR1PORT);
		m2 = new NXTRegulatedMotor(Config.MOTOR2PORT);
		
		m1.setSpeed(400);
		m2.setSpeed(400);
		m1.forward();
		m2.forward();

		
		update();
	}
	
	
	private void update() {
		while(running) {
			
		//	LCD.clear();
			LCD.drawString("color sensor : " + colorSensor.isDetect(), 0, 0);
			LCD.drawString("ligth sensor : " + lightSensor.isDetect(), 0, 1);
			
			if(lightSensor.isDetect() || colorSensor.isDetect()) {
					if(lightSensor.isDetect() && colorSensor.isDetect() && Robot.state != Robot.STATE.EVADE) {
						Robot.state = Robot.STATE.FORWARD;
					} else {
						if(lightSensor.isDetect()) {
							Robot.state = Robot.STATE.LEFT;
						} 
						if(colorSensor.isDetect()) {
							Robot.state = Robot.STATE.RIGHT;
						}
					}
			} else {
				if(Robot.state == STATE.LEFT) {
					
				}
			//	Robot.state = Robot.STATE.FORWARD;
			}
			
			if(Robot.state == STATE.FORWARD) {
				m1.setSpeed(500);
				m2.setSpeed(500);
			}
			if(Robot.state == STATE.LEFT) {
				m2.setSpeed(200);
				m1.setSpeed(500);
			}
			if(Robot.state == STATE.RIGHT) {
				m1.setSpeed(200);
				m2.setSpeed(500);
			}
			
			/*
			if(lightSensor.getLevel() < 45 || colorSensor.getcolor().equals("Black")) {
				if(lightSensor.getLevel() < 45) {
					m2.setSpeed(200);
					m1.setSpeed(500);
				} 
				if(colorSensor.getcolor().equals("Black")) {
					m1.setSpeed(200);
					m2.setSpeed(500);
				} 
			} else {
				m1.setSpeed(500);
				m2.setSpeed(500);
			}
			*/
		}    
	}


	@Override
	public void buttonPressed(Button b) {
		if(b.equals(Button.ESCAPE)) {
			this.running = false;
		}
	}

	//unused
	@Override
	public void buttonReleased(Button b) {
		
	}
}
