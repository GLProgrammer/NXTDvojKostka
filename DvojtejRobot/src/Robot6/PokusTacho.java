package Robot6;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;

public class PokusTacho
{
	public static void main(String[] args)
	{
		NXTRegulatedMotor motor = new NXTRegulatedMotor(MotorPort.A);
		motor.flt();
		while(true)
		{
			System.out.println(motor.getTachoCount());
		}
	}
}
