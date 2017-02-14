package Sensors;

import java.util.ArrayList;
 

/**
 * The Sensor handler is responsible for updating the sensors.
 * 
 * @author Jasper van Hulst
 * @version 0.1
 * @date 31/3/2015
 *
 */
public class SensorHandler extends Thread {
	
	private int interval;
	private ArrayList<UpdatingSensor> sensors;
	
	/**
	 * Constructor for the SensorHandler class
	 *
	 * @param interval The time to wait in msec after updating (needed to give other thread the time to get there turn)
	 */
	public SensorHandler(int interval){
		this.interval = interval;
		this.sensors = new ArrayList<UpdatingSensor>();
		start();
	}
	
	/**
	 * the run of the thread (should not be used)
	 *
	 */
	public void run() {
		Thread.currentThread().setPriority (Thread.MAX_PRIORITY);
		for ( ; ; ) {	
			try {
				synchronized (this) {
					for (UpdatingSensor s : sensors)
            			s.updateState();
            	}
         		Thread.sleep (interval);
         	}
         	catch (InterruptedException _) { }
		}
	}
	
	
	/**
	 * Constructor for the SensorHandler class
	 *
	 * @param s a instance of UpdatingSensor, to add to the list of things to update
	 */
	public void addSensor(UpdatingSensor s) {
		sensors.add(s);
	}
	
}
