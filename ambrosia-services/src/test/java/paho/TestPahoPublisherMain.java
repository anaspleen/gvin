/**
 * 
 */
package paho;

/**
 * 
 * @author anaspleen
 *
 */
public class TestPahoPublisherMain {

	public static void main(String[] args) {
		// MQTT TCA VM
		// MQTTUtils.publishMessagesInThreads("TCA/ex",
		// "tcp://192.168.1.83:1883", "JavaSample", 10, 10000);
		// MQTTUtils.publishMessagesInThreads("TCA/ex",
		// "tcp://192.168.1.83:1883", "JavaSample", 5, 1000);

		// MQTT Marco
		MQTTUtils.publishMessagesInThreads("marco", "tcp://192.168.1.49:1883", "JavaSample", 1000, 1000);
	}
}
