/**
 * 
 */
package zeromq.pubSub;

import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

/**
 * http://zguide.zeromq.org/java:psenvsub
 * 
 * @author tcaiati
 *
 */
public class PsEnvSub {
	public static void main(String[] args) {

		// Prepare our context and subscriber
		Context context = ZMQ.context(1);
		Socket subscriber = context.socket(ZMQ.SUB);

		subscriber.connect("tcp://localhost:5552");
		subscriber.subscribe("B".getBytes());
		while (!Thread.currentThread().isInterrupted()) {
			// Read envelope with address
			String address = subscriber.recvStr();
			// Read message contents
			String contents = subscriber.recvStr();
			System.out.println(address + " : " + contents);
		}
		subscriber.close();
		context.term();
	}
}
