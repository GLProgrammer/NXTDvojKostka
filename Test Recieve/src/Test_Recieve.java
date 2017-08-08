import lejos.util.TextMenu;
import lejos.nxt.LCD;
import lejos.nxt.Motor;
import lejos.nxt.comm.*;
import java.io.*;

public class Test_Recieve {

	public static void main(String[] args) throws InterruptedException, IOException {
		// System.out.println(RS485.isAddress("00165318BD7A"));	//00165318D9FD - BOT 6	//00165318BD7A - BOT 97
		//String[] connectionStrings = new String[]{"Bluetooth", "USB", "RS485"};
		//TextMenu connectionMenu = new TextMenu(connectionStrings, 0, "Connection");
		//String[] modeStrings = new String[] {"Packet", "Raw"};
		//TextMenu modeMenu = new TextMenu(modeStrings, 0, "Mode");
		//NXTCommConnector[] connectors = {Bluetooth.getConnector(), USB.getConnector(), RS485.getConnector()};
		//int[] modes = {NXTConnection.PACKET, NXTConnection.RAW};

		//int connectionType = connectionMenu.select();
		//LCD.clear();
		//int mode = modeMenu.select();
		while (true){
			/*LCD.clear();
			LCD.drawString("Type:       " + connectionStrings[connectionType], 0, 0);
			LCD.drawString("Mode: " + modeStrings[mode], 0, 1);*/
			System.out.println("Waiting...");

			NXTConnection con = RS485.waitForConnection(0, NXTConnection.PACKET);

			System.out.println("Connected...");

			DataInputStream dis = con.openDataInputStream();
			DataOutputStream dos = con.openDataOutputStream();

			while (true){
				int function;
				int parameter;
				try {
					function = dis.readInt();
					Thread.sleep(100);
					parameter = dis.readInt();

				} catch (EOFException e) {
					break;
				}

				//if (n != null) {
					System.out.println("Function: " + function + " with parameter: " + parameter);
					/*switch (n.charAt(1)) {
					case '0':	//FWD

							Motor.C.backward();
							Motor.B.backward();
							Thread.sleep(1000);
							Motor.C.stop();
							Motor.B.stop();
						break;

					case '1':	//BCKWD

						Motor.C.forward();
						Motor.B.forward();
						Thread.sleep(1000);
						Motor.C.stop();
						Motor.B.stop();
						break;

					case '2':	//Left

						Motor.C.backward();
						Motor.B.forward();
						Thread.sleep(1000);
						Motor.C.stop();
						Motor.B.stop();
					break;

					case '3':	//Right

						Motor.C.forward();
						Motor.B.backward();
						Thread.sleep(1000);
						Motor.C.stop();
						Motor.B.stop();
					break;

					case '4':	//UP

						Motor.A.forward();
						Thread.sleep(250);
						Motor.A.stop();
					break;

					default:	//Read ERROR

						System.out.println("READ ERROR");
						break;
					}*/
				}


			//}
			System.out.println("Closing...");
			dis.close();
			dos.close();
			con.close();
		}
	}

}
