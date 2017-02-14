package robotproject;

import Sensors.MyColorSensor;
import Sensors.MyLightSensor;
import Sensors.MyUltrasonicSensor;
import Sensors.SensorHandler;
import lejos.geom.Line;


/**
 * Main class
 * 
 * @author {Jasper van Hulst}
 * @version 0.1
 * @date 2/4/3/2015
 * @param args is not used
 * @exeption InterruptedException
 */


public class Main {
	public static void main(String[] args) throws InterruptedException {
		SensorHandler sh1 = new SensorHandler(10);
		SensorHandler sh2 = new SensorHandler(10);
		
		MyColorSensor colorSensor = new MyColorSensor(Config.COLORSENSORPORT);
		MyLightSensor ligthSenor = new MyLightSensor(Config.LIGHTSENSORPORT);
		MyUltrasonicSensor ultrasonicSensor = new MyUltrasonicSensor(Config.ULTRASONICSENSORPORT);
		sh1.addSensor(colorSensor);
		sh1.addSensor(ligthSenor);
		sh2.addSensor(ultrasonicSensor);
		
		LineController lineController =  new LineController(ligthSenor,colorSensor);
		EvadeController evadeController = new EvadeController(ultrasonicSensor);
		MovementController movementController = new MovementController(lineController, evadeController);
		movementController.Update();
	}
}
