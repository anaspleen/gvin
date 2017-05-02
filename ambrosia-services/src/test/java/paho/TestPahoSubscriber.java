/**
 * 
 */
package paho;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.apache.commons.io.FileUtils;
import org.bson.Document;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import fr.greeniot.ambrosia.utils.ConstantesAMBROSIA;

/**
 * http://www.jitendrazaa.com/blog/java/snmp/creating-snmp-agent-server-in-java-using-snmp4j/
 * http://www.jitendrazaa.com/blog/java/snmp/create-snmp-client-in-java-using-snmp4j/
 * 
 * @author anaspleen
 *
 */
public class TestPahoSubscriber {

	public static void main(String[] args) throws IOException {
		String topic = "TCA/ex";
		String broker = "tcp://192.168.1.83:1883";
		String clientId = "JavaSample";
		MemoryPersistence persistence = new MemoryPersistence();

		// pour publier en ligne de commande :
		// mosquitto_pub -h localhost -t TCA/ex -m "here à moi"

		// pour passer du JSON en ligne de commande :
		// mosquitto_pub -h localhost -t TCA/ex -m "{\"cle\":\"valeur\"}"
		// ou
		// mosquitto_pub -h localhost -t TCA/ex -m "{'cle':'valeur'}"

		// pour s'abonner en ligne de commande :
		// mosquitto_sub -h localhost -t "TCA/ex"

		// logs de mosquitto :
		// tail -1000f /var/log/mosquitto/mosquitto.log

		try {
			MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
			MqttConnectOptions connOpts = new MqttConnectOptions();
			connOpts.setCleanSession(true);
			System.out.println("Connecting to broker: " + broker);
			sampleClient.connect(connOpts);
			sampleClient.subscribe(topic);

			System.out.println("Connected to broker: " + broker);

			// listen to
			sampleClient.setCallback(new MqttCallback() {

				public void messageArrived(String topic, MqttMessage message) throws Exception {
					String time = new Timestamp(System.currentTimeMillis()).toString();
					String response = new String(message.getPayload());
					System.out.println("\nReceived a Message!" + "\n\tTime:    " + time + "\n\tTopic:   " + topic
							+ "\n\tMessage: " + response + "\n\tQoS:     " + message.getQos() + "\n\tID:     "
							+ message.getId() + "\n");
					// latch.countDown(); // unblock main thread

					try {
						Document doc = Document.parse(response);

						System.out.println(doc);
						System.out.println(doc.get("cle"));

						// persist it in Mongo
						persist(doc);

					} catch (Exception e) {
						System.err.println(e);

						// OK to send a binary file (png here) :
						// mosquitto_pub -h localhost -t TCA/ex -f
						// pathToImage/test.png
						FileUtils.writeByteArrayToFile(new File("c:/Temp/test.png"), message.getPayload());
					}
				}

				public void connectionLost(Throwable cause) {
					System.out.println("Connection to Solace broker lost!" + cause.getMessage());
					// latch.countDown();
				}

				public void deliveryComplete(IMqttDeliveryToken token) {
				}
			});

		} catch (MqttException me) {
			System.out.println("reason " + me.getReasonCode());
			System.out.println("msg " + me.getMessage());
			System.out.println("loc " + me.getLocalizedMessage());
			System.out.println("cause " + me.getCause());
			System.out.println("excep " + me);
			me.printStackTrace();
		}
	}

	private static void persist(Document doc) {
		MongoClient client = new MongoClient(new MongoClientURI("mongodb://192.168.1.83:27017"));
		MongoDatabase database = client.getDatabase("test");

		MongoCollection<Document> bouteille = database.getCollection("frommqtt");
		bouteille.insertOne(doc);

		client.close();

		System.out.println("Doc : " + doc.get(ConstantesAMBROSIA.TAG_ID).toString() + " persisted");

		// tester : si BD se plante, les messages sont quand même réenvoyés
		// automatiquement par ce listener
	}

	// script sh : publishMessages.sh
	// i=0
	// max=100
	// while [ $i -lt $max ]
	// do
	// echo $i
	// mosquitto_pub -h localhost -t TCA/ex -m "{'cle':'valeur$i'}"
	// true $(( i++ ))
	// done
}
