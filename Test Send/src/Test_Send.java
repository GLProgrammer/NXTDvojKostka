import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.nxt.*;
import lejos.nxt.comm.*;
import lejos.util.Delay;
import lejos.util.TextMenu;


public class Test_Send{
	public static void main(String[] args) throws InterruptedException {
		String name = "BOT 6";
		//String[] connectionStrings = {"Bluetooth", "RS485"};
		//TextMenu connectionMenu = new TextMenu(connectionStrings, 0, "Connection");
			//String[] modeStrings = {"Packet", "Raw"};
		//TextMenu modeMenu = new TextMenu(modeStrings, 0, "Mode");
			//NXTCommConnector[] connectors = {Bluetooth.getConnector(),RS485.getConnector()};
			//int[] modes = {NXTConnection.PACKET, NXTConnection.RAW};

		//int connectionType = connectionMenu.select();
		//LCD.clear();
		//int mode = modeMenu.select();

		/*LCD.clear();
		LCD.drawString("Name: " + name, 0, 0);
		LCD.drawString("Type: " + connectionStrings[connectionType], 0, 1);
		LCD.drawString("Mode: " + modeStrings[mode], 0, 2);
		LCD.drawString("Connecting...", 0, 3);*/
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
				/*dos.writeChars("Test");		//POSILANI
				dos.flush();
				dos.writeChars("Super");
				dos.flush();
				dos.writeChars("Awesome");
				dos.flush();*/
				dos.writeInt(1);

				dos.flush();
				Thread.sleep(1000);
				dos.writeInt(2);

				dos.flush();
				Thread.sleep(1000);
				dos.writeInt(3);

				dos.flush();
				Thread.sleep(1000);
				/*dos.writeChars("1");
				dos.flush();
				Thread.sleep(5000);*/
				/*dos.writeChars("2");
				dos.flush();
				Thread.sleep(5000);*/
				/*dos.writeChars("3");
				dos.flush();
				Thread.sleep(5000);*/
				/*dos.writeChars("4");
				dos.flush();
				Thread.sleep(5000);*/
			} catch (IOException ioe) {
				System.out.println("Write Exception");
			}

			/*try {
				LCD.drawString("Read: ", 0, 7);
				LCD.drawInt(dis.readInt(), 8, 6, 7);
			} catch (IOException ioe) {
				LCD.drawString("Read Exception", 0, 5);
			}*/

		try {
			System.out.println("Closing...");
			dis.close();
			dos.close();
			con.close();
		} catch (Exception e) {
			System.out.println("Close Exception");
		}
		System.out.println("Finished!!!");
		Thread.sleep(20000);
	}

}
