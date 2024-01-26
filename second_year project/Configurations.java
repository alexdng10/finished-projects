//My name is Que Hung Dang and this is my code for Configurations.java 251205417

//This class configurations implements all the methods needed by algorithm computerPlay to determine the best move
public class Configurations {
	
//create 4 private variables to store the input given from configurations
	private char[][] board;
	private int board_size;
	private int lengthToWin;
	private int max_levels;
	
	public Configurations (int board_size, int lengthToWin, int max_levels) {
		
		//initialize all the private variables to the input by using this method
		this.board_size = board_size;
		this.lengthToWin = lengthToWin;
		this.max_levels = max_levels;
		
		//create a new square board with the given size after that construct a double for loop for row and column
		//to set every tile to an empty character this board only store one character per tile since this is a 2d array
		board = new char[board_size][board_size];
		for (int i = 0; i < board_size; ++i) {
			for (int j = 0; j < board_size; ++j) {
				board[i][j] = ' ';
				
		}
	}
}
	//this create a hash dictionary with a prime number to construct our hash table to store our pattern and scores with chaining index implemented. 
	public HashDictionary createDictionary() {
		//return the new hash dictionary with the 8971 as the size which is also a prime number size.
		return new HashDictionary(8971);
	}
	
	//now turn every tile of the board into a string to check with the hashtable to see if theres is a duplication
	// in the hashtable if there is then return the score of it else then just return -1.
	public int repeatedConfiguration(HashDictionary hashTable) {
		
		//create empty string result to add each character into the result by using a double for loop to iterate through the board.
		String result = "";
		for(int i = 0; i < board_size; ++i) {
			for (int j = 0; j < board_size; ++j) {
				result = result + board[i][j];
			}
		}
		//after that using the get method we created from hash dictionary to compare it.
		if (hashTable.get(result) == -1) {
			return -1;
		}
		
		//if there is then return the score as indicated from our get method() from hash dictionary.
		else {
			int score = hashTable.get(result);
			
			//return the score.
			return score;
		}
	}
	
	//This method first represents the content of board as a String as described above using the same mechanic
	public void addConfiguration(HashDictionary hashDictionary, int score) {
		String result = "";
		for(int i = 0; i < board_size; ++i) {
			for (int j = 0; j < board_size; ++j) {
				result = result + board[i][j];
			}
		}
		//then add the pattern of the board into the hash dictionary after creating an
		//data input with the new result string and score.
		Data input = new Data(result,score);
		
		//it will automatically hash the string to put it into the hash dictionary since we already implemented them.
		hashDictionary.put(input);
	}
	
	//set the current tile with the input symbol method with the exact row and column from the given input
	public void savePlay (int row, int col, char symbol) {
		board[row][col] = symbol;
	}
	
	//check if the specific row and column of that tile is empty if its not then return false else return true
	public boolean squareIsEmpty (int row, int col) {
		if (board[row][col] == ' '){
			return true;
		}
		
			return false;
		
}
	//this wins method check for a x or a + pattern inside the table with the given symbol character
	public boolean wins (char symbol) {
		
		//how to work is it create a double for loop to travel to every single tile of the board whenever its called
		for(int row = 0; row < board_size; ++row) {
			for (int col = 0; col < board_size; ++ col) {
				
				//then if that specific tile has the same symbol it will begin its checking
				if(board[row][col] == symbol) {
					
					//before checking the surrounding tile of that specific tile that has the character we need to make sure that it is not going to be out of bound.
					// so in order for it to not be out of bound we have 4 conditions to be met here is the following:
					//1. if row - 1 is a negative then it is a tile at the border of the table so it will go out of bound 
					// on the left side of the board 
					// example of row: 0 1 2 3
					//if 0 - 1 then it will be -1 which does not exist in our table, so it has to be equal or greater than 0 for the condition to be met
					//
					//2. if row + 1 is equal or greater than board size it will also be out of bound on the right side
					// when u initialize a table size it will be from 0 to n-1 where n is the size of the table so if its 8 then its 0 - 7 not 0 - 8
					// so the row can not be equal to board size so it will travel out of bound of the table
					// hence row + 1 must be less than board_size
					// so both of the row next tile from left and right will be in bound to check if the cross or plus shape has the same character too
					
					// 3. same for the row now we will do for column if column - 1 be negative it will be out of bound for the top size
					//example: 
					// 0
					// 1
					// 2
					// 3
					// now we have the column of size of 4 if it -1 then the tile checking is out of bound since 0 is the lowest u can go to check the top if they have the 
					// top same tile characters. basically these conditions double check that we have a border for our checking shape
					// the center piece can not be on the border of the 4 size since it is impossible to form an x or plus shape
					//
					// 4. last one where column has to be less than board size, if its equal or greater than board size then it will be out of bound for the bottom.
					// using the same example in 3 we can see that 0 - 3 is the index we have if we initialize the size of 2d array of 4. so the column must be less than board_size
					// when adding 1 to match the conditions and make sure that it is in bound of the table.
					//after putting these if methods it will skip all the border tile as its center pieces so the check will be smoother.
					
					//after checking all the conditions met lets check for the surrounding tile if they form an X shape or a plus shape.
					if (row - 1 >= 0 && row + 1 < board_size  && col - 1 >= 0 && col + 1 < board_size) {
						
						//so I create a check integer and if 4 side of the corresponding shape has the symbol then add one to it it will equal to 4
						//once it equal to 4 we can check the symbol perside to see if it met the length to win the game by using 2 private functions
							int check = 0;
							//check bottom right side of x
							if (board[row+1][col+1] == symbol) {
								check++;
							}
							
							//check top left side of x
							if (board[row-1][col-1] == symbol) {
								check ++;
							}
							//check top right side of x
							if (board[row+1][col-1] == symbol) {
								check ++;
							}
							//check bottom left side of x
							if (board[row-1][col+1] == symbol) {
								check ++;
							}
							//if all 4 side has the same symbol then check will equal to 4 then proceed to check per side to keep adding it into the equal to length
							//to win
							if(check == 4) {
								
								//see the private function description to see how it works
								boolean result = diagCheck(symbol, row, col);
								//its a boolean variable so if result is true then return true else do nothing
								
								if (result == true) {
										return true;
									}			
							}
					}
					
					//same as the x shape work but now we change a little bit for plus shape, we also separate this and create the same border for the board
					//as we did for our x shape.
					if (row - 1 >= 0 && row + 1 < board_size  && col - 1 >= 0 && col + 1 < board_size) {
						
						//create the same check mechanic
							int check = 0;
							
							//now since it a plus shape we will either add 1 or minus 1 to either column or row but not both
							//so this is top to check + shape
							if (board[row][col - 1] == symbol) {
					 			   check++;
					 		   }
							//bottom of + shape
							if (board[row][col + 1] == symbol) {
					 			  check++;
					 		  }
							//left of + shape
							if (board[row - 1][col] == symbol) {
					 			   check++;
					 		   }
							//right of + shape
							if (board[row + 1][col] == symbol) {
					 			   check++;
					 		   }
							//if they all have the same symbol we checking for check will equal to 4 so
							//we use the private function to check it but now its rowCheck 
							//instead of diagChow
							if (check == 4) {
								boolean result = rowCheck(symbol, row, col);
								//also if the private function return true then return true for the wins method.
								if (result == true) {
									return true;
									}
							}	
					}
					
				}
			}			
	
		}
				
				
		//after checking all the tiles with the correct border and nothing comes out then we just return false.
		return false;
	}
	//this function check if the board is draw or not
	//so first create a doubke for loop to travel through 
	//every tile in the board and check if its empty or not if
	// its equal to an empty space then return false;
	public boolean isDraw() {
		for (int i = 0; i < board_size; ++ i) {
			for (int j = 0; j < board_size; ++ j) {
				if(board[i][j] == ' ') {
					return false;
				}
			}
		}
		//then use the wins function we just created to check if X wins or not 
		// if it wins then return false same for O too 
		if (wins('X') == true) {
			return false;
		}
		else if (wins('O') == true) {
			return false;
		}
		//if all the conditions did not met then return true so the board is draw.
		else {	
		return true;
		}
	}
	//this function is to eval board to see who won
	// we use is draw and wins functions
	public int evalBoard() {
		
		//if wins is from computer then return 3
		if (wins('O') == true) {
			return 3;
		}
		//human true then return 0
		if (wins('X') == true){
			return 0;
		}
		//if its draw then return 2
		if (isDraw() == true) {
			return 2;
		}
		//if its not the 3 conditions then return 1.
		return 1;
	}
	
	//create a private function to check the diagonal of the X shape
	//now that we have a minimum X shape we can keep checking each side to see if its add up will
	//equal to length of win
	//this function takes in the row and the column and the symbol that they currently have the x shape which is the center piece.
	private boolean diagCheck(char symbol,int row, int col) {
		//now check is equal to 1 because the center piece if confirm so its 1
		 int check = 1;
		 //create 4 boolean check so if the next tile doesnt have the same symbol it break so its stop adding incorect length to win check to it
		 boolean t1 = true;
		 boolean t2 = true;
		 boolean t3 = true;
		 boolean t4 = true;
		 
		 
		 //so create a for loop but a single one that start at i = 1 and less than board size
		 //this will make sure it not going to count our center piece again and it only check the 4 side of the X shape
		 //with separate if function for each side
		 for (int i = 1; i < board_size; ++i) {
			 //so we have the top right check first and for column it has to be greater or equal to 0
			 //and for row it has to be less than board size other wise if these 2 dont met it gon be out of bound
			 //we have separate conditions for per side check but its the same principal
				   if (row + i < board_size && col - i >= 0) {
					   //now if test 1 is true and the top right in the second tile (i = 2) then it will add check = check + 1 so after
					   // first iterations it will be 1 + 4 = 5 then after that it will keep check until it reach the end
					   //of the for loop
					   if (board[row + i][col - i] == symbol && t1) {
						   check = check + 1;
					   }
					   //if it doesn't equal then turn t1 to false which gon break the check for top right and same for other side too 
					   // in case the symbol does not equal to symbol we checking so now we stop checking if its different
					   else {
						   t1 = false;
					   }
					   
				   }
				   //same principal for this bottom right side of X shape
				   if (row + i < board_size && col + i < board_size) {
					   if (board[row + i][col + i] == symbol && t2) {
						   check = check + 1;
					   }
					   else {
						   t2 = false;
					   }
				   }
				   //same for top left
				   if (row - i >= 0 && col - i >= 0) {
					   if (board[row - i][col - i] == symbol && t3) {
						   check = check + 1;
					   }
					   else {
						   t3 = false;
					   }
				   }
				   //same for bottom left
				   if (row - i >= 0 && col + i < board_size) {
					   if (board[row - i][col + i] == symbol && t4) {
						   check = check + 1;
					   }
					   else {
						   t4 = false;
					   }
				   } 
				   //so if its equal or greater than length to win then we return true.
				   //the reason is greater cuz we might place the center piece at the last tile
				   if(check >= lengthToWin) {
						return true;
					}
				   }
	    
		//if not equal or less than then just return false.
		return false;
		 
		
	}
	
	//this private function use the same mechanis as the diag check but we change
	// a little bit for row check cuz its top bottom left or right
	private boolean rowCheck(char symbol, int row, int col) {
		int check = 1;
		 boolean t1 = true;
		 boolean t2 = true;
		 boolean t3 = true;
		 boolean t4 = true;
		 for (int i = 1; i < board_size; ++i) {
				  
			 //so this is for top of plus shape
			 	   if(col - i >= 0) {
			 		   if (board[row][col - i] == symbol && t1) {
			 			   check = check + 1;
			 		   }
			 		   else {
			 			   t1 = false;
			 		   }
				   }   
			 //this is for bottom of plus shape
			 	   if(col + i < board_size) { 		   
			 		   if (board[row][col + i] == symbol && t2) {
			 			  check = check + 1;
			 		  }
			 		   else {
			 			   t2 = false;
			 		   }
				   }   
			 	//this is for left side of plus shape
			 	   if(row - i >= 0) {   
			 		   if (board[row - i][col] == symbol && t3) {
			 			   check = check + 1;
			 		   }
			 		   else {
			 			   t3 = false;
			 		   }
				   }
			 	  //this is for right side of plus shape
			 	   if(row + i < board_size) {
			 		   if (board[row + i][col] == symbol && t4) {
			 			   check = check + 1;
			 		   }
			 		   else {
			 			   t4 = false;
			 		   }
				   }
			 	   //now if its greater or equal to lengthtowin to return true, same mechanic as the previous private function
				   if (check >= lengthToWin) {
					   return true;
				   }
		 		}		 
		//after all iterations and it doesnt equal to length to win or equal we just return false;
		return false;
		
	}
	

	
	
}


