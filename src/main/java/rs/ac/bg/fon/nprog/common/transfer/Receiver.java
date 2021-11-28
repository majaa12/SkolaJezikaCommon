package rs.ac.bg.fon.nprog.common.transfer;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.net.Socket;

public class Receiver implements Serializable {

	private Socket socket;

	public Receiver(Socket socket) {
		this.socket = socket;
	}

	public Object receive() throws Exception {
		try {
			ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
			return in.readObject();
		} catch (Exception ex) {
			throw new Exception("Greska pri prihvatanju objekta!\n" + ex.getMessage());
		}
	}
}
