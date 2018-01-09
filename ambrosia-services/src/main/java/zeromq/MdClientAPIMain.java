/**
 * 
 */
package zeromq;

import org.zeromq.ZMsg;

/**
 * @author tcaiati
 *
 */
public class MdClientAPIMain {

	/**
	 * Thomas
	 * 
	 * @param args
	 */
	// public static void main(String[] args) {
	//
	// MdClientAPI client = new MdClientAPI("tcp://localhost:5555", true);
	// client.setTimeout(3000);
	// ZMsg message = new ZMsg();
	// ZFrame frame1 = new ZFrame("test1");
	// ZFrame frame2 = new ZFrame("test2");
	// ZFrame frame3 = new ZFrame("test3");
	//
	// message.add(frame1);
	// message.add(frame2);
	// message.add(frame3);
	//
	// ZMsg response = client.send("service", message);
	//
	// System.exit(0);
	// }

	/**
	 * http://zguide.zeromq.org/java:mdclient
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// boolean verbose = (args.length > 0 && "-v".equals(args[0]));
		boolean verbose = true;
		MdClientAPI clientSession = new MdClientAPI("tcp://localhost:5555", verbose);

		int count;
		for (count = 0; count < 100000; count++) {
			ZMsg request = new ZMsg();
			request.addString("Hello world");
//			ZMsg reply = clientSession.send("echo", request);
			ZMsg reply = clientSession.send("mmi.echo", request);
			System.out.println("message sent");
			if (reply != null) {
				reply.destroy();
				System.out.println("reply destroyed");
			} else {
				System.out.println("reply is null");
				break; // Interrupt or failure
			}
		}

		System.out.printf("%d requests/replies processed\n", count);
		clientSession.destroy();
	}
}
