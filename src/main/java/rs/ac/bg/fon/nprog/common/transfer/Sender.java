package rs.ac.bg.fon.nprog.common.transfer;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public class Sender implements Serializable {

	private Socket socket;

	public Sender(Socket socket) {
		this.socket = socket;
	}

	public void send(Object object) throws Exception {

		try {
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			out.writeObject(object);
			out.flush();
		} catch (Exception ex) {
			throw new Exception("Greska pri slanju objekta!\n" + ex.getMessage());
		}
	}
}
