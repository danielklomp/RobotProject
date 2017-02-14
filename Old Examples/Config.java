import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;

public class Config {
	
	public static final int WEELBASE = 11;
	public static final int WEELDIA = 56;
	
	public static final MotorPort MOTOR1PORT = MotorPort.A;
	public static final MotorPort MOTOR2PORT = MotorPort.C;
	
	public static final int BLACKWHITELIMIT = 45; // het keer punt tussen wit en zwart
	
	public static final SensorPort LIGHTSENSORPORT = SensorPort.S1;
	public static final SensorPort COLORSENSORPORT = SensorPort.S4;
	public static final SensorPort ULTRASONICSENSORPORT = SensorPort.S2;
	
	
}
