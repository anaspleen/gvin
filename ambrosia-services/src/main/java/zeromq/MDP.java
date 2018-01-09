/**
 * 
 */
package zeromq;

import java.util.Arrays;

import org.zeromq.ZFrame;

/**
 * @author tcaiati
 *
 */
public enum MDP {
	C_CLIENT("MDPC01"), W_WORKER("MDPW01"), W_READY(1), W_REQUEST(2), W_REPLY(3), W_HEARTBEAT(4), W_DISCONNECT(5);

	private final byte[] data;

	MDP(String var3) {
		this.data = var3.getBytes();
	}

	MDP(int var3) {
		byte var4 = (byte) (var3 & 255);
		this.data = new byte[] { var4 };
	}

	public ZFrame newFrame() {
		return new ZFrame(this.data);
	}

	public boolean frameEquals(ZFrame var1) {

		boolean res = false;
		if (var1 != null) {
			res = Arrays.equals(this.data, var1.getData());
		} else {
			System.err.println("var1 is null");
		}

		return res;
	}
}
