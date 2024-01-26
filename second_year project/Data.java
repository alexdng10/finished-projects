

//create a class data to store all the string and score of per movement of each table possibilities
public class Data {
	
	//create 2 private variables of string to store pattern and score to store the outcome
	//of the game
private String config;
private int score;

//create a constructor public data that takes in input config as string and score as int.
	public Data(String config, int score) {
		
		//initlize these variables to the private variables 
		//with this command for both config and score to store them
		this.config = config;
		this.score = score;
	}

//now will return string config when call upon
	public String getConfiguration() {
		return config;
	}
	
//return the score of the board when call upon
	public int getScore() {
		return score;
	}

}
