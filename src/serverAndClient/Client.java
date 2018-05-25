package serverAndClient;

import java.io.IOException;

import com.lloseng.ocsf.client.AbstractClient;
import game.GameWindow;

public class Client extends AbstractClient {
	
	private String messageFromServer;
	private GameWindow game;
	public static int rankPb;
//	private int rank;

	public Client(String host, int port) {
		super(host, port);
		this.messageFromServer = "";
		this.game = new GameWindow();
//		this.rank = 2;
	}

	@Override
	protected void handleMessageFromServer(Object msg) {
		this.messageFromServer = msg.toString();
		if(messageFromServer.equals("ready")) {
			this.game.startGame();
			try {
				while(!this.game.isEnd()) {
					System.out.println("not end");
					if(game.isEnd()) {
						System.out.println("end: " + game.isEnd());
						sendToServer(String.format("end"));
						System.out.println("sent end");
						break;
					}
				}
				System.out.println("done");
			} catch (IOException e) {}
		}
		if(messageFromServer.equals("4")) {
			rankPb = 4;
			this.game.setRank();
			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("3")) {
			rankPb = 3;
			this.game.setRank();
			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("2")) {
			rankPb = 2;
			this.game.setRank();
			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("1")) {
			rankPb = 1;
			this.game.setRank();
			try { this.closeConnection(); } catch (IOException e) {}
		}
		if(messageFromServer.equals("again")) {
			this.game.reset();
		}
	}

	@Override
	protected void connectionEstablished() {
		super.connectionEstablished();
	}
	
	public GameWindow getGame() {
		return this.game;
	}
	
//	public void tellServerToUpdateRank() {
//		try {
//			while(this.game.isActive()) {
//				System.out.println("boolean end: "+game.isEnd());
//				if(game.isEnd() == true) break;
//			}
//			System.out.println("end: "+game.isEnd());
//			if(game.isEnd()) {
//				sendToServer(String.format("end"));
//				System.out.println("sent end");
//				this.connectionClosed();
//			}
//			System.out.println("done");
//		} catch (IOException e) {}
//	}
}
