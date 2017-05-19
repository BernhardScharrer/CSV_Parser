import org.gnet.client.ClientEventListener;
import org.gnet.client.GNetClient;
import org.gnet.client.ServerModel;
import org.gnet.packet.Packet;

public class Client extends GNetClient {
	
	private static ServerModel model;
	
	public Client() {
		super("84.200.106.98", 2884);
		super.bind();
		super.start();
		super.addEventListener(new Listener());
	}
	
	public static void send(Packet packet) {
		if (model != null) {
			model.sendPacket(packet);
		}
	}
	
	private class Listener extends ClientEventListener {

		@Override
		protected void clientConnected(ServerModel server) {
			Client.model = server;
		}

		@Override
		protected void clientDisconnected(ServerModel server) {
			
		}

		@Override
		protected void debugMessage(String msg) {
			System.out.println(msg);
		}

		@Override
		protected void errorMessage(String msg) {
			System.out.println(msg);
		}

		@Override
		protected void packetReceived(ServerModel server, Packet packet) {
			
		}
		
	}
	
}
