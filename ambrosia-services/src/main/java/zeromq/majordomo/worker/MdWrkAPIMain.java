/**
 * 
 */
package zeromq.majordomo.worker;

import org.zeromq.ZMsg;

/**
 * Majordomo Protocol worker example. Uses the mdwrk API to hide all MDP aspects
 * 
 * http://zguide.zeromq.org/java:mdworker
 * 
 * @author tcaiati
 *
 */
public class MdWrkAPIMain {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// boolean verbose = (args.length > 0 && "-v".equals(args[0]));
		boolean verbose = true;
		MdWrkAPI workerSession = new MdWrkAPI("tcp://localhost:5555", "echo", verbose);

		ZMsg reply = null;
		while (!Thread.currentThread().isInterrupted()) {
			ZMsg request = workerSession.receive(reply);
			if (request == null)
				break; // Interrupted
			reply = request; // Echo is complex :-)
		}
		workerSession.destroy();
	}
}
