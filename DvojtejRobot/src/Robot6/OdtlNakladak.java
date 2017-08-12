package Robot6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;
import lejos.robotics.localization.OdometryPoseProvider;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Navigator;

public class OdtlNakladak
{
	public static NXTRegulatedMotor motorL;
	public static NXTRegulatedMotor motorR;
	public static NXTRegulatedMotor motorUS;

	public static float polomerKruzniceOtaceni = 4.3f;
	public static float pi = 3.1415926f; //deset na -7
	public static float polomerKola = 5.6f;

	public static void main(String[] args) throws IOException, InterruptedException
	{
		System.out.println("Waiting...");

		NXTConnection con = RS485.waitForConnection(0, NXTConnection.PACKET);

		System.out.println("Connected...");

		DataInputStream dis = con.openDataInputStream();
		DataOutputStream dos = con.openDataOutputStream();

		//Zde musim vytvorit objekt lejos.navigator (zatim nevim jak na to) TAK PREJ NE

		motorL = new NXTRegulatedMotor(MotorPort.B); //Doufam ze je L na B
		motorR = new NXTRegulatedMotor(MotorPort.C); //Doufam ze je R na C
		motorUS = new NXTRegulatedMotor(MotorPort.A);

		motorUS.setSpeed(60); //Myslim si ze rozumna rychlost
		int uhelOtaceniMajak = 120;

		DifferentialPilot pilot = new DifferentialPilot(5.6, 18.6, motorL, motorR); //Snad lze zapsat motory i takto

		UltrasonicSensor ultrasonicPredni = new UltrasonicSensor(SensorPort.S1); // Vytvareni objektu
		UltrasonicSensor ultrasonicZadni = new UltrasonicSensor(SensorPort.S3);

		boolean nepritelUSPredni = false;
		boolean nepritelUSZadni = false;

//		motorUS.rotate(-60); //Otoci na zacatku majak do maximalni (prave nebo leve) pozice

		while(true)
		{

//			if(!motorUS.isMoving()) //Pokud majak prestal rotovat do jedne polohy
//			{
//				motorUS.rotate(uhelOtaceniMajak, true);
//				uhelOtaceniMajak = uhelOtaceniMajak * (-1);
//			}

			if(dis.available() > 0)
			{
				switch(dis.readInt())
				{
					case 0: //Prisel prikaz JED
						System.out.println("Prikaz 0");

						int rychlostL = dis.readInt(); //Precte prvni cislo jako rychlost leveho motoru
						int rychlostR = dis.readInt(); //Precte druhe cislo jako rychlost praveho motoru

						motorL.setSpeed(rychlostL); //Nastaveni rychlosti obou motoru
						motorR.setSpeed(rychlostR);

						MotorLJed();
						MotorRJed();

					break;
					case 1: //Prisel prikaz ZASTAV
						System.out.println("Prikaz 1");

						pilot.stop();

					break;
					case 2: //Prisel prikaz OTOC SE O (x stupnu)
						System.out.println("Prikaz 2");

						int uhel = dis.readInt();

						motorUS.stop();

						System.out.println("Zastavil jsem majak");

						motorUS.rotate((-1) * motorUS.getTachoCount()); //Otoces do uhlu 0

						motorUS.setSpeed(60);
						motorUS.rotate((-1) * uhel); //Rychle se otoci do smeru kam se otaci

						System.out.println("Otocil jsem majak");

						motorUS.setSpeed(30);
						motorUS.rotate(uhel, true); //Pomalu zacne otacet s majakem nazpatek

						while(motorUS.isMoving()) //Dokud se plne nedotocil
						{
							ultrasonicPredni.ping(); //Hledani nepritele
							nepritelUSPredni = ultrasonicPredni.getDistance() < 15;

							ultrasonicZadni.ping();
							nepritelUSZadni = ultrasonicZadni.getDistance() < 15;

							if(nepritelUSPredni || nepritelUSZadni) //Pokud uvidel nepritele pisknou a zkoncit program
							{
								Sound.beep();
								Thread.sleep(1000);
								System.exit(1);
							}
						}
						System.out.println("Nepratel hledan");

						pilot.setRotateSpeed(40);

						pilot.rotate((double) uhel);

						System.out.println("Otaceni podvozku");

					break;
					default:

						System.out.print("Precten jiny prikaz"); //Pokud prijde neco jineho tak nahlasit chybu
						System.out.println(dis.read());

					break;
				}
			}

			//ultrasonicPredni.continuous();
			//nepritelUSPredni = ultrasonicPredni.getDistance() < 15;

			//ultrasonicZadni.continuous();
			//nepritelUSZadni = ultrasonicZadni.getDistance() < 15;

			//if(ultrasonicZadni.getDistance() < 15) //Pokud nektery z US detekuje nepritele; nepritelUSPredni || nepritelUSZadni; || ultrasonicPredni.getDistance() < 15
			//{
			//	pilot.stop();
			//}
		}


	}

	public static void MotorLJed() //fce pro jizdu motoru jak dopredu tak dozadu jako jeden prikaz
	{
		if(motorL.getSpeed() < 0)
			motorL.backward();
		if(motorL.getSpeed() == 0)
			motorL.flt(); // Zkusim float nejsem si jistej vysledkem
		if(motorL.getSpeed() > 0)
			motorL.forward();
	}

	public static void MotorRJed() //fce pro jizdu motoru jak dopredu tak dozadu jako jeden prikaz
	{
		if(motorR.getSpeed() < 0)
			motorR.backward();
		if(motorR.getSpeed() == 0)
			motorR.flt(); // Zkusim float nejsem si jistej vysledkem
		if(motorR.getSpeed() > 0)
			motorR.forward();
	}
}