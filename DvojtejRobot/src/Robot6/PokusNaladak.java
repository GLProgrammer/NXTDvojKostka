package Robot6;

import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class PokusNaladak
{
	public static void main(String[] args) throws InterruptedException
	{

	NXTRegulatedMotor motorL = new NXTRegulatedMotor(MotorPort.B); //Doufam ze je L na B
	NXTRegulatedMotor motorR = new NXTRegulatedMotor(MotorPort.C); //Doufam ze je R na C

	int rychlost = 0;
	
		while(true)
		{
			Thread.sleep(3000);
			rychlost = rychlost + 5;

			System.out.println(rychlost);

			motorR.setSpeed(rychlost);
			motorL.setSpeed(rychlost);

			motorL.forward();
			motorR.forward();
		}
	}
}