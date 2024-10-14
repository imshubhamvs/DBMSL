
2 of 11
Yawale Amrutulya
Inbox

Raj Sharma
Attachments
Wed, 25 Sept, 15:40
to Yash, me


One attachment
  • Scanned by Gmail
package mongodbconnect;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoCredential;

public class Main {
	public static void main(String args[]) {
		try {
		MongoClient mclient = new MongoClient(new MongoClientURI("mongodb://te31368:te31368@10.10.13.97:27017/te31368_db"));
		MongoDatabase db = mclient.getDatabase("te31368_db");
		MongoCollection<Document> col = db.getCollection("students");
		System.out.println("1. Insert\n 2. Delete\n 3. Update");
		int choice;
		Scanner sc = new Scanner(System.in);
		int roll;
		while(true) {
			System.out.println("Enter choice: ");
			choice = sc.nextInt();
			switch(choice) {
			case 1:
				System.out.println("Name. :");
				sc.nextLine();
				String name = sc.nextLine();
				System.out.println("Roll no. :");
				roll = sc.nextInt();
				Document doc = new Document();
				doc.append("roll", roll);
				doc.append("name", name);
				col.insertOne(doc);
				break;
			case 2:
				System.out.println("Roll no. :");
				roll = sc.nextInt();
				col.deleteOne(Filters.eq("roll", roll));
				break;
			case 3:
				System.out.println("Roll no: ");
				roll = sc.nextInt();
				System.out.println("Enter updated name: ");
				sc.nextLine();
				name = sc.nextLine();
				doc = new Document();
				doc.append("roll", roll);
				doc.append("name", name);
				col.replaceOne(Filters.eq("roll", roll), doc);
				break;
			}
		}
		}
		catch(Exception e) {
			System.out.println(e);
		}
	}
}