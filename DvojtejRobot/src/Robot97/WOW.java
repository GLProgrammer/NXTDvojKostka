package Robot97;

import java.io.DataInputStream;
import java.io.DataOutputStream;

import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.TouchSensor;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;

public class WOW {

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
			DataInputStream dis = con.openDataInputStream();
			DataOutputStream dos = con.openDataOutputStream();
			try {
				dos.writeInt(3);
				dos.flush();
				dos.writeInt(50);
				dos.flush();
				dos.writeInt(50);
				dos.flush();
				dos.writeInt(200);
				dos.flush();
				dos.writeInt(200);
				dos.flush();
			} catch (Exception e) {

			}
	}

}
