package application;

public class Robot {

	private Dictionary dictionary = new Dictionary();
	private String vocabulary;

	public String reply(String word) {

		if (vocabulary == null) {
			String answer = dictionary.checkAnswer(word);
			if (answer != null) {
				return answer;
			} else {
				vocabulary = word;
				return "I don't know, please teach me";
			}
		} else {
			dictionary.saveToDatabase(vocabulary, word);
			vocabulary = null;
			return "Thank You";
		}

	}

}
