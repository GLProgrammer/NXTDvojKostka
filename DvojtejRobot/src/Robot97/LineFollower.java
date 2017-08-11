package Robot97;

import lejos.nxt.*;

public class LineFollower {
	private static LightSensor light;
	private static Robot97_Main main;
	private static int Black;
	private static int White;
	private static int CompareValue;
	private static int Toceni;
	private static TouchSensor Touch;
	private static boolean NakladakLoaded;
	private static boolean Follow;
	private static int pomer;
	private static int X;


	public static void Reguluj(){
		pomer = X*(CompareValue - light.getLightValue());
	}

	public static void Follow() throws InterruptedException{
		//main.Posli(2, 90);
		//Thread.sleep(5000);
		Sound.beepSequenceUp();
		main.Posli(0,100,100);
		while (light.getLightValue()>CompareValue) {		}
		main.Posli(1);
		Thread.sleep(500);
		Sound.beep();
		main.Posli(2, 80);
		Thread.sleep(5000);
		Sound.twoBeeps();
	//	main.Posli(1);

	while (Follow) {
		if (light.getLightValue() < CompareValue && !NakladakLoaded) {
			main.Posli(0, 250, 300);
		} else if(light.getLightValue() > CompareValue && !NakladakLoaded)  {
			main.Posli(0, 300, 250);
		} else if (Touch.isPressed()&& !NakladakLoaded) {
			NakladakLoaded = true;
			Sound.twoBeeps();
		}
		else if (!Touch.isPressed()&& NakladakLoaded) {
			NakladakLoaded = false;
			main.Posli(1);
			Follow = false;
		}

	}

	}
	public static void INIT() throws InterruptedException {
		Follow = true;
		NakladakLoaded = false;
		X = 10;
		Touch = new TouchSensor(SensorPort.S3);
		light = new LightSensor(SensorPort.S2);
		Thread.sleep(1000);
		Sound.twoBeeps();
		Button.ENTER.waitForPressAndRelease();
		Black = light.getLightValue();
		Sound.beep();
		Button.ENTER.waitForPressAndRelease();
		White = light.getLightValue();
		Sound.beep();
		CompareValue = (Black + White)/2;
		Reguluj();
		//Follow();
	}
}
