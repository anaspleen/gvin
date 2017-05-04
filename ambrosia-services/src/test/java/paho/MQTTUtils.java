/**
 * 
 */
package paho;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utils for MQTT
 * 
 * @author anaspleen
 *
 */
public class MQTTUtils {

	/** logger */
	private static final Logger LOG = LoggerFactory.getLogger(MQTTUtils.class);

	/**
	 * Default const
	 */
	private MQTTUtils() {
		// NTD
	}

	/**
	 * Send a message
	 * 
	 * @param topic
	 *            the topic
	 * @param content
	 *            content string
	 * @param qos
	 *            quality
	 * @param sampleClient
	 *            client
	 * @throws MqttException
	 *             exeption
	 * @throws MqttPersistenceException
	 *             exeption
	 */
	public static void publishMessage(String topic, String content, int qos, MqttClient client)
			throws MqttException, MqttPersistenceException {

		// if (LOG.isDebugEnabled()) {
		// LOG.debug("Publishing message: " + content);
		// }

		MqttMessage message = new MqttMessage(content.getBytes());
		message.setQos(qos);
		client.publish(topic, message);
	}

	public static void publishMessagesInThreads(String topic, String broker, String clientId, int nbThreads,
			int nbMessagePerThread) {

		ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

		for (int nT = 1; nT <= nbThreads; nT++) {

			System.out.println("----------- THREAD : " + nT);

			Runnable worker = new PublishMQTT(topic, broker, clientId + nT, nbMessagePerThread);
			executor.execute(worker);

			System.out.println("----------- THREAD : " + nT + " FINISHED");
		}

		executor.shutdown();
	}
}
