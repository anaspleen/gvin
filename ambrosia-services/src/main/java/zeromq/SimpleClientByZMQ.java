/**
 * 
 */
package zeromq;

import java.util.Random;

import org.zeromq.ZContext;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.PollItem;
import org.zeromq.ZMQ.Poller;
import org.zeromq.ZMQ.Socket;
import org.zeromq.ZMsg;

/**
 * Simple client using ZeroMQ (with JNI)
 * 
 * @author tcaiati
 *
 */
public class SimpleClientByZMQ implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// ZFrame frame = new ZFrame("datas");

		SimpleClientByZMQ test = new SimpleClientByZMQ();

		test.run();

	}

	private static Random rand = new Random(System.nanoTime());

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		ZContext ctx = new ZContext();
		Socket client = ctx.createSocket(ZMQ.DEALER);

		// Set random identity to make tracing easier
		String identity = String.format("%04X-%04X", rand.nextInt(), rand.nextInt());
		client.setIdentity(identity.getBytes());
		client.connect("tcp://localhost:5555");

		PollItem[] items = new PollItem[] { new PollItem(client, Poller.POLLIN) };

		int requestNbr = 0;
		while (!Thread.currentThread().isInterrupted()) {
			// Tick once per second, pulling in arriving messages
			for (int centitick = 0; centitick < 10; centitick++) {
				ZMQ.poll(items, 10);
				if (items[0].isReadable()) {
					ZMsg msg = ZMsg.recvMsg(client);
					msg.getLast().print(identity);
					msg.destroy();
				}
			}
			String message = String.format("request #%d", ++requestNbr);
			System.out.println("message : " + message);
			client.send(message, 0);
			System.out.println("message sent");
		}
		ctx.destroy();
		ctx.close();
	}
}
