package Robot97;

import java.io.IOException;

import lejos.nxt.*;
import lejos.util.Delay;
import lejos.util.Timer;
import lejos.util.TimerListener;

public class LineFollower {
	private static Timer timer;
	public static LightSensor light;
	private static Robot97_Main main;
	private static int Black;
	private static int White;
	public static int CompareValue;
	private static int Toceni;
	private static TouchSensor Touch;
	private static boolean NakladakLoaded;
	private static boolean Follow;
	private static int pomerPohonu;
	private static int X;
	private static boolean StopPushing;

	public static void Reguluj() throws InterruptedException{
		//int minSkala = 0;
		//int maxSkala = 100;
		while (true){
		int Aktualni = light.readValue();
		int AktualniSkala = 100 / (White - Black) * (Aktualni - Black);
		pomerPohonu = X*(AktualniSkala - 50);
			main.Posli(0, 200 + pomerPohonu, 200 - pomerPohonu);
		}
	}

	public static void Follow() throws InterruptedException, IOException{
		//main.Posli(2, 90);
		//Thread.sleep(5000);
		Button.ENTER.waitForPressAndRelease();
		Sound.beepSequenceUp();
		while (light.getLightValue()>CompareValue) {			main.Posli(0,150,150);	}		//Rychlost zacatek
		main.Posli(1);
		Thread.sleep(500);
		Sound.beep();
		main.Posli(2, 80);
		while (Robot97_Main.dis.readInt() != 1) {
		}
		//Thread.sleep(6000);
		Sound.twoBeeps();
		main.Posli(1);

	while (Follow) {
		if (light.getLightValue() < CompareValue && !NakladakLoaded) {
			main.Posli(0, 150, 200);
		}
		if(light.getLightValue() > CompareValue && !NakladakLoaded)  {
			main.Posli(0, 200, 150);
		}
		if (Touch.isPressed()&& !NakladakLoaded) {
			NakladakLoaded = true;
			Follow = false;
			Sound.twoBeeps();
		}
		/*if (!Touch.isPressed()	&& NakladakLoaded) {
			NakladakLoaded = false;
			main.Posli(1);
			Follow = false;
			Sound.beepSequenceUp();
		}*/
	}
	int StartovaciCas = (int)System.currentTimeMillis();
	int PozadovanyDelay = 15000;
	int CasNyni;
	while (Touch.isPressed()) {
		CasNyni = (int) System.currentTimeMillis();
		if (CasNyni - StartovaciCas >= PozadovanyDelay ) {
			break;
		}
		main.Posli(0,150,150);
	}
	main.Posli(1);
	}
	public static void INIT() throws InterruptedException, IOException {
		StopPushing = false;
		Follow = true;
		NakladakLoaded = false;
		X = 10;
		Touch = new TouchSensor(SensorPort.S3);
		light = new LightSensor(SensorPort.S2);
		Thread.sleep(1000);
		Sound.twoBeeps();
		Button.ENTER.waitForPressAndRelease();
		Black = light.readValue();
		Sound.beep();
		Button.ENTER.waitForPressAndRelease();
		White = light.readValue();
		Sound.beep();
		CompareValue = (Black + White)/2;
		Follow();
	}
}
