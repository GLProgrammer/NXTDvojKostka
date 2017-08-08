package Robot97;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.Sound;
import lejos.nxt.comm.NXTConnection;
import lejos.nxt.comm.RS485;

public class Robot97_Main {

	public static void main(String[] args) throws InterruptedException {
		String name = "BOT 6";
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
			while (true){
			try{		//POSILANI
				dos.writeInt(0);

				dos.flush();
				Thread.sleep(500);
				dos.writeInt(100);

				dos.flush();
			}
			catch (IOException ioe) {
				System.out.println("Write Exception");
			}
	}


	}
}