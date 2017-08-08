package Robot6;
import lejos.util.TextMenu;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.comm.*;
import java.io.*;

public class PohybSAutom {

	public static void main(String[] args) throws InterruptedException, IOException {

			System.out.println("Waiting...");

			NXTConnection con = RS485.waitForConnection(0, NXTConnection.PACKET);

			System.out.println("Connected...");

			DataInputStream dis = con.openDataInputStream();
			DataOutputStream dos = con.openDataOutputStream();

			NXTRegulatedMotor motorL = new NXTRegulatedMotor(MotorPort.B);
			NXTRegulatedMotor motorR = new NXTRegulatedMotor(MotorPort.C);

			int aktualniStav = 0; // Stavy: 0 -> IDLE, 1 -> MOTION; prvotni stav je idle
			int vstupKostka = 0; // Vstupy: 0 -> JED, 1 -> ZATOC, 2 -> STUJ; prvotne na jednom z vstupu, nemelo by delat problem
			int vstupHodnota = 0; // Jak moc ma jet / zatacet rychle

			while (true)
			{
				while(dis.available() == 0){} // Pocka dokud neco nelze precist

				vstupKostka = dis.readInt();
				Thread.sleep(100);
				vstupHodnota = dis.readInt();

				switch(vstupKostka) // Zmena stavu
				{
					case 0: // Prikaz JED
					{
						switch (aktualniStav)
						{
							case 0: // Stav IDLE
							{
								aktualniStav = 1;
							}
							break;
							case 1: // Stav MOTION
							{
								aktualniStav = 1;
							}
							break;

							default:
							{
								System.out.println("Error");
							}
							break;
						}
					}

					case 1: // Prikaz ZATOC
					{
						switch (aktualniStav)
						{
							case 0: // Stav IDLE
							{
								aktualniStav = 1;
							}
							break;
							case 1: // Stav MOTION
							{
								aktualniStav = 1;
							}
							break;

							default:
							{
								System.out.println("Error");
							}
							break;
						}
					}
					break;

					case 2: // Prikaz STOP
					{
						switch (aktualniStav)
						{
							case 0: // Stav IDLE
							{
								aktualniStav = 0;
							}
							break;
							case 1: // Stav MOTION
							{
								aktualniStav = 0;
							}
							break;

							default:
							{
								System.out.println("Error");
							}
							break;
						}
					}
					break;

					default:
					{
						System.out.println("Error");
					}

					switch(vstupKostka) // Vystup (motory L a R)
					{
						case 0: // Prikaz JED
						{
							switch (aktualniStav)
							{
								case 0: // Stav IDLE
								{
									System.out.println("IDLE -> nejede");
								}
								break;
								case 1: // // Stav MOTION
								{
									motorL.setSpeed(vstupHodnota);
									motorR.setSpeed(vstupHodnota);
									motorL.forward();
									motorR.forward();
								}
								break;

								default:
								{
									System.out.println("Error");
								}
								break;
							}
						}
						break;
						case 1: // Prikaz ZATOC
						{
							switch (aktualniStav)
							{
								case 0: // Stav IDLE
								{
									System.out.println("IDLE -> nejede");
								}
								break;
								case 1: // // Stav MOTION
								{
									motorL.setSpeed(vstupHodnota);
									motorR.setSpeed(-vstupHodnota);
									motorL.forward();
									motorR.forward();
								}
								break;

								default:
								{
									System.out.println("Error");
								}
								break;
							}
						}
						break;
						case 2: // Prikaz STOP
						{
							switch (aktualniStav)
							{
								case 0: // Stav IDLE
								{
									motorL.stop();
									motorR.stop();
								}
								break;
								case 1: // // Stav MOTION
								{
									System.out.println("MOTION -> nejde");
								}
								break;

								default:
								{
									System.out.println("Error");
								}
								break;
							}
						}
						break;
						default:
						{
							System.out.println("Error");
						}
						break;
					}
				}
			}

/*
			System.out.println("Closing...");
			dis.close();
			dos.close();
			con.close(); */
		}
	}

