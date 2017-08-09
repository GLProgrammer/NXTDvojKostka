package Robot6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;

public class PohybSAutomPole
{
	private boolean[] vstupy;
	private int vstupRychlost;
	private int speedL;
	private int speedR;
	static DataInputStream dis;
	static DataOutputStream dos;

	public static void main(String[] args)
	{
		boolean[] vstupy; // Pole vsech vstupu. True -> aktivni, False -> neaktivni
		vstupy = new boolean[6];
		int vstupRychlost = 0; // Vstupova rychlost (jak rychle maji jet motory)

		System.out.println("Waiting...");

		NXTConnection con = RS485.waitForConnection(0, NXTConnection.PACKET);

		System.out.println("Connected...");

		dis = con.openDataInputStream();
		dos = con.openDataOutputStream();



		NXTRegulatedMotor motorL = new NXTRegulatedMotor(MotorPort.B);
		NXTRegulatedMotor motorR = new NXTRegulatedMotor(MotorPort.C);
		DifferentialPilot pilot = new DifferentialPilot(50, 100, motorL, motorL);
		Navigator nav = new Navigator(pilot);
		motorL.
		PohybSAutomPole automat = new PohybSAutomPole();
		while(true)
		{
			automat.Step();
			motorL.setSpeed(automat.speedL);
			motorR.setSpeed(automat.speedR);
		}
	}

	private void Step() {
		int prikaz = ReadOrDefault();
		int speed=0;
		if(prikaz!=DEFAULTPRIKAZ){
			speed = ReadSpeed();
		}
		boolean oponent = OpponentDetected();
		CalculateOutputs(prikaz, speed, oponent, stav);
		stav = nasledujiciStav(prikaz, oponent, stav);

	}

	public PohybSAutomPole(){

	}

	private void CtiVstupy(){
		boolean opponent = OpponentDetected();
		vstupy[5] =
	}


	private boolean OpponentDetected() throws IOException
	{


		boolean kritickaVzdalenostUSP1 = false;
		boolean kritickaVzdalenostUSP2 = false;
		boolean kritickaVzdalenostUSZ = false;

		UltrasonicSensor ultrasonicPredni1 = new UltrasonicSensor(SensorPort.S1);
		UltrasonicSensor ultrasonicPredni2 = new UltrasonicSensor(SensorPort.S2);
		UltrasonicSensor ultrasonicZadni = new UltrasonicSensor(SensorPort.S3);

		ultrasonicPredni1.ping();
		kritickaVzdalenostUSP1 = ultrasonicPredni1.getDistance() < 20; // Jednotlive mereni USP1, USP2 a USZ
		ultrasonicPredni2.ping();
		kritickaVzdalenostUSP2 = ultrasonicPredni2.getDistance() < 20;
		ultrasonicZadni.ping();
		kritickaVzdalenostUSZ = ultrasonicZadni.getDistance() < 20;

		if(kritickaVzdalenostUSP1 || kritickaVzdalenostUSP2 || kritickaVzdalenostUSZ) // Pokud alespon jeden z US nameril hodnotu mensi nez 20
		{
			return true; // Vstup Opponent = true
		}
		else
		{
			return false; // Vstup NoOpponent = true
		}
	}

}
