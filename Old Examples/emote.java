package robotproject;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import lejos.nxt.LCD;

public class Emote {

	public Emote() {
		
	}
	
	public static void sad() {
		LCD.clear();
		LCD.drawString( "   00        00",0,0);
		LCD.drawString( "   00        00",0,1);
		LCD.drawString( "   00        00",0,2);
		LCD.drawString( "   00        00",0,3);
		LCD.drawString( "   ",0,4);
		LCD.drawString( "    0000000000 ",0,5);
		LCD.drawString( "   0          0",0,6);
		LCD.drawString( "  0            0",0,7);
	}
	
	
}
