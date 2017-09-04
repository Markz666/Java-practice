package zmx.cs.project;

import java.util.LinkedList;

class Trie {
	private Node root;	
	// construction
	public Trie() {
    	root = new Node(' ');
	}
	// insert the word with frequency
	public void insert(String word, int freq, String filename) {
		Node current = root;
		for(int i = 0; i < word.length(); i++) {
			Node child = current.subNode(word.charAt(i));
			if(child != null) {
				current = child;
			}
			else {
				current.childList.add(new Node(word.charAt(i)));
				current = current.subNode(word.charAt(i));
			}
			current.count++;
		}
		current.isEnd = true;		
		// set the last node
		if (filename == "1.txt") {
			current.has1.hasWord = true;
			current.has1.freq = freq;
		}
		if (filename == "2.txt") {
			current.has2.hasWord = true;
			current.has2.freq = freq;
		}
		if (filename == "3.txt") {
			current.has3.hasWord = true;
			current.has3.freq = freq;
		}
		if (filename == "4.txt") {
			current.has4.hasWord = true;
			current.has4.freq = freq;
		}
		if (filename == "5.txt") {
			current.has5.hasWord = true;
			current.has5.freq = freq;
		}
	}
   
	// search the word
	public boolean search(String word) {
		Node current = root;
		for(int i = 0; i < word.length(); i++) {

			if(current.subNode(word.charAt(i)) == null) {
				return false;
			}
			else {
				current = current.subNode(word.charAt(i));
			}
		}

		if (current.isEnd == true) {
			return true;
		}
		else {
			return false;
		}
	}	

	// check the word

	public boolean checkWord(String word, int[][] result, int index) {
		Node current = root;
		for(int i = 0; i < word.length(); i++) {
			if(current.subNode(word.charAt(i)) == null) {
				return false;
			}
			else {
				current = current.subNode(word.charAt(i));
			}
		}
		if (current.isEnd == true) {
			// get the frequency of the word in each file
			result[0][index + 2] = current.has1.freq;
			result[1][index + 2] = current.has2.freq;
			result[2][index + 2] = current.has3.freq;
			result[3][index + 2] = current.has4.freq;
			result[4][index + 2] = current.has5.freq;
			return true;
		}
		else {
			return false;
		}
	}
}

class Freq {
	String word;
	int freq;	

	// construction
	public Freq(String word, int freq) {
		this.word = word;
		this.freq = freq;
	}

	public String getWord() {
		return word;
	}	

	public int getFreq() {
		return freq;
	}
}

class LastNode {
	boolean hasWord;
	int freq;
	// construction
	public LastNode(boolean hasWord, int freq) {
		this.hasWord = hasWord;
		this.freq = freq;
	}	

	public boolean getHasWord() {
		return hasWord;
	}
	
	public int getFreq() {
		return freq;
	}
}
class Node {
	char content;                               // single character
	boolean isEnd;                              // if it is the last node
	int count;
	LinkedList<Node> childList;
	LastNode has1 = new LastNode(false, 0);     // last node to store if the word exists in file1 and the frequency
	LastNode has2 = new LastNode(false, 0);     // last node to store if the word exists in file2 and the frequency
	LastNode has3 = new LastNode(false, 0);     // last node to store if the word exists in file3 and the frequency
	LastNode has4 = new LastNode(false, 0);     // last node to store if the word exists in file4 and the frequency
	LastNode has5 = new LastNode(false, 0);     // last node to store if the word exists in file5 and the frequency	
	// construction
	public Node(char c) {
		childList = new LinkedList<Node>();
		isEnd = false;
		content = c;
		count = 0;
	}	
	// child node
	public Node subNode(char c) {
		if(childList != null) {
			for(Node eachChild : childList) {
				if(eachChild.content == c) {
					return eachChild;
				}
			}
		}
		return null;
	}
}
