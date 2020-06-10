package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

public class Dictionary {

	private Map<String, String> database;
	private static final String FILE = "data.obj";

	@SuppressWarnings("unchecked")
	public Dictionary() {
		database = new HashMap<>();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
			database = (Map<String, String>) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String checkAnswer(String word) {
		return database.get(word);
	}

	public void saveToDatabase(String vocabulary, String word) {
		database.put(vocabulary, word);
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
			out.writeObject(database);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
