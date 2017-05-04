/**
 * 
 */
package paho;

import org.bson.Document;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**
 * Calss to publish MQTT, threaded
 * 
 * @author anaspleen
 *
 */
public class PublishMQTT implements Runnable {

	/** the topic : Marco */
	private String topic;

	/** broker : tcp://192.168.1.49:1883 */
	private String broker;

	/** client Id : "TestJava" */
	private String clientId;

	/** nb of message to send */
	private int nbMessagePerThread;

	/**
	 * @param topic
	 * @param broker
	 * @param clientId
	 * @param nbMessagePerThread
	 */
	public PublishMQTT(String topic, String broker, String clientId, int nbMessagePerThread) {
		this.topic = topic;
		this.broker = broker;
		this.clientId = clientId;
		this.nbMessagePerThread = nbMessagePerThread;
	}

	/**
	 * @return the topic
	 */
	public String getTopic() {
		return topic;
	}

	/**
	 * @param topic
	 *            the topic to set
	 */
	public void setTopic(String topic) {
		this.topic = topic;
	}

	/**
	 * @return the broker
	 */
	public String getBroker() {
		return broker;
	}

	/**
	 * @param broker
	 *            the broker to set
	 */
	public void setBroker(String broker) {
		this.broker = broker;
	}

	/**
	 * @return the clientId
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId
	 *            the clientId to set
	 */
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the nbMessagePerThread
	 */
	public int getNbMessagePerThread() {
		return nbMessagePerThread;
	}

	/**
	 * @param nbMessagePerThread
	 *            the nbMessagePerThread to set
	 */
	public void setNbMessagePerThread(int nbMessagePerThread) {
		this.nbMessagePerThread = nbMessagePerThread;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {

		int qos = 2;
		MemoryPersistence persistence = new MemoryPersistence();

		try {

			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			System.out.println("Connected");

			Document message = null;
			String content = "";

			for (int i = 1; i <= nbMessagePerThread; i++) {
				message = new Document();
				message.put("key", clientId + "-value" + i);
				content = message.toString();
				MQTTUtils.publishMessage(topic, content, qos, sampleClient);
			}

			System.out.println("Messages published : " + clientId);
			sampleClient.disconnect();
			System.out.println("Disconnected");
		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}
}
