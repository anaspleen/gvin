/**
 * 
 */
package fr.greeniot.ambrosia.listener;

import org.bson.Document;

import com.mongodb.CursorType;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

/**
 * @author tcaiati
 *
 */
public class Listener {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// http://stackoverflow.com/questions/9691316/how-to-listen-for-changes-to-a-mongodb-collection

		MongoClient client = new MongoClient(new MongoClientURI("mongodb://192.168.1.90:27017"));
		MongoCollection<Document> coll = client.getDatabase("ambrosia_test").getCollection("bouteille2");
		// do this in database
		// db.createCollection("bouteille2", { capped: true, size: 100000 })
		Document projection = new Document();
		Document sort = new Document();
		sort.put("$natural", 1);

		// pas top pour mon cas car ressort toute la collection à chaque fois
		MongoCursor<Document> cur = coll.find().sort(sort).projection(projection).cursorType(CursorType.TailableAwait)
				.iterator();

		System.out.println("== open cursor ==");

		Runnable task = () -> {
			System.out.println("\tWaiting for events");

			while (cur.hasNext()) {
				Document obj = cur.next();
				System.out.println(obj);

			}
		};
		new Thread(task).start();
	}
}
