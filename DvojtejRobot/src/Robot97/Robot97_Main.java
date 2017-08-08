package Robot97;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;

public class Robot97_Main {

	private static NXTConnection con;
	private static DataInputStream dis;
	private static DataOutputStream dos;

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
			while (true){
					/* TODO: Add switch instead IFs	*/
			if (Button.ENTER.isDown()) {
				Posli(0, 100);
			}
			if (Button.LEFT.isDown()) {
				Posli(1, 90);
			}
			if (Button.RIGHT.isDown()) {
				Posli(1, -90);
			}
			if (Button.ESCAPE.isDown()) {
				Posli(2);
			}

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
	}


	}
}