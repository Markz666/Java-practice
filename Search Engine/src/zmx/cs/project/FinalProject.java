package zmx.cs.project;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage; 

public class FinalProject extends Application {
	// load the file in workspace
	private String text = "";
	public static File file1 = new File("1.txt");
	public static File file2 = new File("2.txt");
	public static File file3 = new File("3.txt");
	public static File file4 = new File("4.txt");
	public static File file5 = new File("5.txt");	
	private Text word = new Text("Plz enter the word(s): ");
	private TextField tfWord = new TextField();
	private Button searchButton = new Button("Search");
	private TextArea textArea = new TextArea();

	public static void main(String[] args) {
		System.out.println("Launch search engine!");
		Application.launch(args);
	}	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// read file and store the word to a string array
		String[] wordArr1 = read(file1);
		String[] wordArr2 = read(file2);
		String[] wordArr3 = read(file3);
		String[] wordArr4 = read(file4);
		String[] wordArr5 = read(file5);

		// array list to store the word and its frequency
		ArrayList <Freq> wordFreq1 = new ArrayList<Freq>();
		ArrayList <Freq> wordFreq2 = new ArrayList<Freq>();
		ArrayList <Freq> wordFreq3 = new ArrayList<Freq>();
		ArrayList <Freq> wordFreq4 = new ArrayList<Freq>();
		ArrayList <Freq> wordFreq5 = new ArrayList<Freq>();

		// compute the frequency
		int n1 = freq(wordArr1, wordFreq1);
		int n2 = freq(wordArr2, wordFreq2);
		int n3 = freq(wordArr3, wordFreq3);
		int n4 = freq(wordArr4, wordFreq4);
		int n5 = freq(wordArr5, wordFreq5);

		// create a trie to store the word
		Trie trie = new Trie();
		// insert the word to the trie
		wordInsert(trie, wordFreq1, n1, file1);
		wordInsert(trie, wordFreq2, n2, file2);
		wordInsert(trie, wordFreq3, n3, file3);
		wordInsert(trie, wordFreq4, n4, file4);
		wordInsert(trie, wordFreq5, n5, file5);		

		// create a grid pane
		GridPane pane = new GridPane();
		pane.setPadding(new Insets(20, 20, 20, 20));
		searchButton.setPrefSize(60, 25);
		textArea.setPrefSize(200, 500);
//		pane.add(word, 0, 0);
		pane.add(tfWord, 1, 0);
		pane.add(searchButton, 2, 0);
		pane.add(textArea, 1, 2);

		Label lblText = new Label("This is a search engine");
		pane.getChildren().add(lblText);		
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						if (lblText.getText().trim().length() == 0)
							text = "Enter the word(s):";
						else
							text = "";						
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								lblText.setText(text);
							}
						});
						Thread.sleep(1500);
					}
				}
				catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}			
		}).start();
		
		ColumnConstraints col1 = new ColumnConstraints(110);
		ColumnConstraints col2 = new ColumnConstraints(390);
		ColumnConstraints col3 = new ColumnConstraints(100);

		pane.getColumnConstraints().addAll(col1, col2, col3);
		pane.setVgap(15);                         //set the vertical gap distance
		pane.setHalignment(lblText, HPos.CENTER);
		pane.setHalignment(searchButton, HPos.CENTER);
		textArea.setEditable(false);	

		// search
		searchButton.setOnAction(e -> searching(trie));
		// create a scene
		Scene scene = new Scene(pane, 600, 380);
		primaryStage.setTitle("Search Engine");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void searching(Trie trie) {
		// clear the text area
		textArea.setText("");		
		// get the word(s)
		String searchingWord = tfWord.getText().trim();
		String searchingWordLowerCase = searchingWord.toLowerCase();
		String[] searchingWordArr = searchingWordLowerCase.toString().split(" ");
		int wordNum = searchingWordArr.length + 2;
		// create an array to store the frequency of each word
		int[][] result = new int[5][wordNum];
		for (int i = 0; i < 5; i++) {
			result[i][0] = i + 1;
			for (int j = 1; j < wordNum; j++) {
				result[i][j] = 0;
			}
		}					
		// check whether the word(s) is in these files
		check(trie, searchingWordArr, result);					
		// add these frequencies as the relative numbers
		for (int i = 0; i < 5; i++) {
			int sum = 0;
			for (int j = 2; j < wordNum; j++) {
				sum += result[i][j];
			}
			result[i][1] = sum;
		}					
		// sort by relative number
		bubbleSort(result, 5, wordNum);					
		// display
		for (int i = 0; i < 5; i++) {
			textArea.appendText("File: " + result[i][0] + ".txt" + '\n');
			textArea.appendText("     has " + result[i][1] + " relative words." + '\n');
			for (int j = 2; j < wordNum; j++) {
				textArea.appendText("     " + searchingWordArr[j - 2] + ": " + result[i][j]);
			}
			textArea.appendText("" + '\n');
		}
	}
	
	public static void bubbleSort(int[][] result, int fileNum, int wordNum) {
		int temp;
		for (int i = 0; i < fileNum - 1; i++) {
			for (int j = i + 1; j < fileNum; j++) {
				if (result[i][1] < result[j][1]) {
					for (int k = 0; k < wordNum; k++) {
						temp = result[i][k];
						result[i][k] = result[j][k];
						result[j][k] = temp;
					}
				}
			}
		}
	}  
	
	public static void check(Trie trie, String[] Arr, int[][] result) {
		for (int i = 0; i < Arr.length; i++) {
			trie.checkWord(Arr[i], result, i);
		}
	}
	
	public static void wordInsert(Trie trie, ArrayList <Freq> wordFreq, int n, File file) {
		for (int i = 0; i < n; i++) {
			// insert the word, its frequency and its filename
			trie.insert(wordFreq.get(i).getWord(), wordFreq.get(i).getFreq(), file.getName());
		}
	}
	
	public static String[] read(File file) throws Exception {

		Scanner input = new Scanner(file);	
		StringBuilder stringBuilder = new StringBuilder();      // create a string builder
		while (input.hasNext()) {
			String str = input.nextLine();                 // read
			String only_ab = str.replaceAll("[^a-zA-Z]", " ");  // keep the alphabet character
			String lowerCase = only_ab.toLowerCase();           // change into lower case
			stringBuilder.append(lowerCase);                    // store in the string builder
			stringBuilder.append(" ");
		}
		String wordStr = delOthers(stringBuilder.toString());   // delete some other words
		String[] wordArr = wordStr.split(" ");                  // store the word to a string array
		return wordArr;
	}

	public static int freq(String[] wordArr, ArrayList <Freq> wordFreq) {
		Map <String, Integer> map = new HashMap <String, Integer> ();     // create a hash map to store the word and frequency
		int number = 0;                                                   // count how many different words in a file
		for (int i = 0; i < wordArr.length; i++) {
			Integer count = map.get(wordArr[i]);
			if(count == null) {
				map.put(wordArr[i], 1);
				number++;
			}
			else {
				map.put(wordArr[i], ++count);
			}
		}		

		for(Map.Entry <String, Integer> entry : map.entrySet()) {
			Freq freq = new Freq(entry.getKey(), entry.getValue());       // create a "freq" to store the word and frequency
			wordFreq.add(freq);
		}		
		return number;
	}
	
	public static String delOthers(String wordStr) {
		String art = " a | an | the ";
		String prep = " in | on | with | by | for | at | about | under | as | of | into | within | throughout | inside | outside | without | of | to | from | up | out ";
		String pron = " i | you | he | she | they | we | me | him | her | them | us | my | his | your | their | this | that | these | those | myself | himself | themselves | who | what | which | some | many | both | that | whom | whose | whatever | whichever | whoever | whomever | it | how ";
		String others = " ll | b | c | d | e | f | g | h | j | k | l | m | n | o | p | q | r | s | t | u | v | w | x | y | z | ve ";

		for (int n = 3; n > 0; n--) {
			wordStr = wordStr.replaceAll(art, " ");            // delete the art
			wordStr = wordStr.replaceAll(prep, " ");           // delete the prep
			wordStr = wordStr.replaceAll(pron, " ");           // delete the pron
			wordStr = wordStr.replaceAll(others, " ");         // delete some other words such as single character
			wordStr = wordStr.replaceAll(" +", " ");           // delete unnecessary space
		}
		return wordStr;
	}
}




