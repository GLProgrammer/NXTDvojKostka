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
	public static DataInputStream dis;
	public static DataOutputStream dos;
	public static LightSensor light;
	private static LineFollower line;
	private static int Black;
	private static int White;
	public static int CompareValue;

	public static void Posli(int Stav, int Parametr, int Parametr2) throws InterruptedException{
		try{
			dos.writeInt(Stav);
			dos.flush();
			dos.writeInt(Parametr);
			dos.flush();
			dos.writeInt(Parametr2);
			dos.flush();
			}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void Posli(int Stav, int Parametr, int Parametr2, int Parametr3, int Parametr4) throws InterruptedException{
		try{
			dos.writeInt(Stav);
			dos.flush();
			dos.writeInt(Parametr);
			dos.flush();
			dos.writeInt(Parametr2);
			dos.flush();
			dos.writeInt(Parametr3);
			dos.flush();
			dos.writeInt(Parametr4);
			dos.flush();
			}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void Posli(int Stav, int Parametr) throws InterruptedException{
		try{
			dos.writeInt(Stav);
			dos.flush();
			dos.writeInt(Parametr);
			dos.flush();
		}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void Posli(int Stav) throws InterruptedException{
		try{
			dos.writeInt(Stav);
			dos.flush();
		}
		catch (IOException ioe) {
			System.out.println("Write Exception");
		}
	}

	public static void main(String[] args) throws InterruptedException, IOException {
		//NaberKostky.INITKostky();
		light = new LightSensor(SensorPort.S2);
		line = new LineFollower();
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
			LineFollower.INIT();
			Sound.beepSequenceUp();
			NaberKostky.INITKostky();
	}
}
