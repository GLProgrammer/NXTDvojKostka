package Robot97;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;

public class Robot97_Main {

	private static NXTConnection con;
	private static DataInputStream dis;
	private static DataOutputStream dos;
	private static LightSensor light;
	private static int Black;
	private static int White;
	private static int CompareValue;

	public static void Posli(int Stav, int Parametr, int Parametr2) throws InterruptedException{
		try{		//POSILANI
			dos.writeInt(Stav);
			dos.flush();
			//Thread.sleep(100);
			dos.writeInt(Parametr);
			dos.flush();
			//Thread.sleep(100);
			dos.writeInt(Parametr2);
			dos.flush();
			//Thread.sleep(100);
		}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void Posli(int Stav, int Parametr) throws InterruptedException{
		try{		//POSILANI
			dos.writeInt(Stav);
			dos.flush();
			Thread.sleep(100);
			dos.writeInt(Parametr);
			dos.flush();
			Thread.sleep(100);
		}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void Posli(int Stav) throws InterruptedException{

		try{		//POSILANI
			dos.writeInt(Stav);
			dos.flush();
			Thread.sleep(100);
			dos.writeInt(0);
			dos.flush();
			Thread.sleep(100);
		}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		light = new LightSensor(SensorPort.S2);

		String name = "BOT 6";
		TouchSensor stop = new TouchSensor(SensorPort.S3);
		System.out.println("Connecting...");
		NXTConnection con = RS485.connect("BOT 6", NXTConnection.PACKET);
		if (con == null) {
			System.out.println("Connect fail");
			Sound.twoBeeps();
			Thread.sleep(5000);
			System.exit(1);
		}
			System.out.println("Connected!!");
			dis = con.openDataInputStream();
			dos = con.openDataOutputStream();

			/*Thread.sleep(1000);
			Sound.twoBeeps();
			Button.ENTER.waitForPressAndRelease();
			Black = light.getLightValue();
			Sound.beep();
			Button.ENTER.waitForPressAndRelease();
			White = light.getLightValue();
			Sound.beep();
			CompareValue = (Black + White)/2;
			Button.ENTER.waitForPressAndRelease();*/
			while (true) {
				/*if (light.getLightValue() < CompareValue) {
					Posli(0, 100, 90);
				}else {
					Posli(0, 90, 100);
				}*/
				Posli(0,100,100);

			/*switch (Button.readButtons()) {
			case Button.ID_ENTER:
				Posli(0,100);
				break;

			case Button.ID_LEFT:
				Posli(1,90);
				break;

			case Button.ID_RIGHT:
				Posli(1,-90);
				break;

			case Button.ID_ESCAPE:
				Posli(2);
				break;
			default:
				break;
			}*/

			}
			//LineFollower.INIT();
			/*try{		//POSILANI
				dos.writeInt(0);

				dos.flush();
				Thread.sleep(500);
				dos.writeInt(100);

				dos.flush();
			}
			catch (IOException ioe) {
				System.out.println("Write Exception");
			}*/
	//}


	}
}