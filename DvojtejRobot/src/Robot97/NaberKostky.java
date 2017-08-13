package Robot97;

import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.UltrasonicSensor;

public class NaberKostky {
	private static NXTRegulatedMotor sklapenipasu;
    private static NXTRegulatedMotor navijenipasu;
    private static UltrasonicSensor USPasy;
    private static int StartovaciCas;
    private static int PozadovanyDelay;
    private static int CasNyni;
	private static boolean KostkaLoaded = false;

    public static void INITKostky() throws InterruptedException, IOException{
    	sklapenipasu = new NXTRegulatedMotor(MotorPort.A);
    	USPasy = new UltrasonicSensor(SensorPort.S1);
    	navijenipasu = new NXTRegulatedMotor(MotorPort.B);
    	//while (LineFollower.light.readValue() > LineFollower.CompareValue) {	    	Robot97_Main.Posli(0, -100, -100);	}

		/*StartovaciCas = (int)System.currentTimeMillis();
		PozadovanyDelay = 6000;
		CasNyni = 0;

		while (true) {
	    	Robot97_Main.Posli(0,-100,-100);
			CasNyni = (int) System.currentTimeMillis();
    		if (CasNyni - StartovaciCas >= PozadovanyDelay ) {
    			break;
    		}
		}*/

    	//Robot97_Main.Posli(3,100,100,1,1);
    	/*while (Robot97_Main.dis.readInt()!=1) {

		}*/
    	Robot97_Main.Posli(3,100,100,-800,-800);
    	while (Robot97_Main.dis.readInt() != 1) {		}
    	Robot97_Main.Posli(1);
    	Thread.sleep(1000);
    	Sound.beep();
    	Robot97_Main.Posli(2, -90);
		while (Robot97_Main.dis.readInt() != 1) {
		}
    	//Button.ENTER.waitForPressAndRelease();
    	Robot97_Main.Posli(1);

    	StartovaciCas = (int)System.currentTimeMillis();
    	PozadovanyDelay = 5000;
    	CasNyni= 0;

    		while (USPasy.getDistance() > 13) {
    			System.out.println(USPasy.getDistance());
        		navijenipasu.forward();
        		Robot97_Main.Posli(0, -200, -200);
    			CasNyni = (int) System.currentTimeMillis();
    		if (CasNyni - StartovaciCas >= PozadovanyDelay ) {
    			break;
    		}}
    	Robot97_Main.Posli(1);
    	if (USPasy.getDistance() > 9 && !KostkaLoaded) {
    		navijenipasu.stop();
    		sklapenipasu.rotate(40);
    		Sound.beep();
    		KostkaLoaded = true;
    		//System.exit(1);		//Koskta sebrana
    		//JedKNakladaku();
    		Sound.beepSequenceUp();
    		//while (light.getLightValue()>Robot97_Main.CompareValue) {			Robot97_Main.Posli(0,100,100);Thread.sleep(100);	}
    		//Robot97_Main.Posli(1);
    		//Thread.sleep(500);
    		//Sound.beep();
    		JedKNakladaku();
    		//System.exit(1);
    		/*while (true) {
    			if (CasNyni - StartovaciCas >= PozadovanyDelay ) {
        			break;
        		}
			}*/
    		}
    	else if (USPasy.getDistance() <13) {
    		Robot97_Main.Posli(3,100,100,-400,-400);
        	while (Robot97_Main.dis.readInt() != 1) {		}

/*
    		while(Robot97_Main.light.readValue()> Robot97_Main.CompareValue){
        		Robot97_Main.Posli(0,100,100);
        		Thread.sleep(100);
    		}*/
    		Robot97_Main.Posli(1);
    		Thread.sleep(1000);
    		Robot97_Main.Posli(2, -90);
    		Robot97_Main.Posli(1);
    		Thread.sleep(1000);
    		INITKostky();
		}
    	//System.exit(1);
    	/*while(true){

    	}*/



    	/*while (true) {
    		System.out.println(USPasy.getDistance());
		}*/


    }
	private static void JedKNakladaku() throws InterruptedException, IOException {
		Robot97_Main.Posli(3,200,200, 3050, 3050);
		while (Robot97_Main.dis.readInt() != 1) {

		}
		Robot97_Main.Posli(2, -90);
		while (Robot97_Main.dis.readInt() != 1) {

		}
		Robot97_Main.Posli(1);
		Thread.sleep(1000);

		Robot97_Main.Posli(3,100,100,-400,-400);
		while (Robot97_Main.dis.readInt() != 1) {

		}
		Robot97_Main.Posli(1);
		sklapenipasu.rotate(-40);
		Thread.sleep(1000);
		Sound.beep();
		navijenipasu.backward();
		Thread.sleep(5000);
		Sound.beepSequenceUp();
		System.exit(1);
		/*StartovaciCas = (int)System.currentTimeMillis();
		PozadovanyDelay = 18000;
		CasNyni = 0;

		while(true){
			Robot97_Main.Posli(0,200,200);
			CasNyni = (int) System.currentTimeMillis();
			if (CasNyni - StartovaciCas >= PozadovanyDelay ) {
    			break;
    		}
		}*/
		//Robot97_Main.Posli(1);
	}
}