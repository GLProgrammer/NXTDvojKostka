package Robot97;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.TachoMotorPort;
public class NaberKostky {
	private static NXTRegulatedMotor navijenipasu;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		navijenipasu = new NXTRegulatedMotor(MotorPort.B);
		navijenipasu.setSpeed(10000);
			navijenipasu.forward();

			while (true){

			}
	}

}
