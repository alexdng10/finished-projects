import java.awt.Color;
//Hello my name is Que Hung Dang, this is my Assignment 4 submission. Student id 251205417.
// This class contains the Minesweeper-related code. It is used to initialize the game board,
// determine the numbers for each cell (which indicate how many bombs are in neighbouring
// cells), the recursive "region clearing", etc.
public class Game {
	
//create the private varibales but width and height will be static
	private LinkedGrid<Character> board;
	private LinkedGrid<GUICell> cells;
	public static int width;
	public static int height;
	private boolean isPlaying;
	private GUI gui;
	
// the game constructor to create a game takes in width and height, boolean fixedrandom, and int seed.
// 4 parameters.
	public Game(int width, int height, boolean fixedRandom, int seed) {
		this.width = width;
		this.height = height;
// create a new board with linkedgrid method by width and height.
		board = new LinkedGrid<Character>(width,height);
// create a double for loop to set each cell with _
		for (int i = 0; i < width; ++i) {
			for (int j = 0; j < height; ++j) {
				board.setElement(i, j, '_');
			}
		}
// create a cells for guicell with the linked grid width and height
		cells = new LinkedGrid<GUICell>(width,height);
// create a double for loop for it to set all the data with guicell by using set element method.
		for (int i = 0; i < width; ++i) {
			for(int j = 0; j < height; ++j) {
				GUICell data = new GUICell(i,j);
				cells.setElement(i, j, data);
			}
		}
// turn is playing true
		isPlaying = true;
// use bomb randomizer to place bombs across the board and using seed
		BombRandomizer.placeBombs(board, fixedRandom, seed);
// use the method determine numbers to know  the bomb around each cells
		determineNumbers();
// set the gui object
		gui = new GUI(this, cells);
	}

	
// alomost the same as the first constructor but this time take in a ready made board.
	public Game(LinkedGrid<Character> board) {
		this.board = board;
// use get width and height to initilize the variables.
		width = board.getWidth();
		height = board.getHeight();
// create a cells linked grid to put the data from guicell into cells
		cells = new LinkedGrid<GUICell>(width,height);
		for (int i =0; i < width; ++i) {
			for (int j =0; j<height;++j) {
				GUICell data = new GUICell(i,j);
				cells.setElement(i, j, data);
			}
		}
		isPlaying = true;
		determineNumbers();
		gui = new GUI(this, cells);
	}
	
// return width
	public int getWidth() {
		return width;
	}
// return height
	public int getHeight() {
		return height;
	}
// return cells
	public LinkedGrid<GUICell> getCells(){
		return cells;
	}
// go through every single node in the board and calculate how many
//	bombs are in surrounding cells
	public void determineNumbers() {
// create a double for loop and if board get element got X then set the cells number to -1.
		for (int col = 0; col < width; ++col) {
			for (int row = 0; row < height; ++row) {
				if (board.getElement(col, row) == 'x') {
					cells.getElement(col, row).setNumber(-1);
				} else {
					
// create a result to store each bomb number in that cells
					int result = 0;
					
// create another double for loop inside the loop in order to find the bomb around that cells
					for (int a = -1; a < 2; a++) {
						for (int j = -1; j < 2; j++) {
							
// if column the combined numbers is negative or greater that width or height then skip.
							if ( (col+a) < 0 || (col+a) >= width || (row+j) < 0 || (row+j) >= height) {
								continue;
							} 
							
// if a and j is both 0 then skip because if not they are checking itself for bomb.
							else if(a==0 && j==0) {
								continue;
							} else {
								
// finally if the board get element with the specific column and row has x then result will add one.
// the double for loop of a and j is designed to go around a specific cell.
								if (board.getElement((col+a), (row+j)) == 'x') {
									result = result + 1;
								}
							}
						}
					}
// after the double for loop of a and j set the numbers inside that specific cells to determine the bomb around it. 
				cells.getElement(col, row).setNumber(result);
				}
			}
		}
		}
	


	
// this method precesses a cell being clicked or a simulated click and return an int value
// representing how many cells are being revealed. 
	public int processClick(int col, int row) {
// if isplaying is false then return -10.
		if (isPlaying == false) {
			return -10;
		}
// if the given cell contains -1 then set background to red and reveal the cell. also return -1
		if (cells.getElement(col, row).getNumber() == -1 ) {
			cells.getElement(col, row).setBackground(Color.red);
			cells.getElement(col, row).reveal();
			isPlaying = false;
			return -1;
			
		}
// if the cell contain 0 then begin recursive region clearing by using the method.
		if (cells.getElement(col, row).getNumber() == 0) {
			return recClear(col,row);
		}
// if the cells is greater than 0 and equal 8 or less than 8 then if its revealed then return 0.
		if (cells.getElement(col, row).getNumber() > 0 && cells.getElement(col, row).getNumber() <= 8) {
			if(cells.getElement(col, row).isRevealed()) {
				return 0;
			}
			else {
// else reveal the cell and set back ground to white and return 1
				cells.getElement(col, row).reveal();
				cells.getElement(col, row).setBackground(Color.white);
				return 1;
			}
		}
// if not then just return 0.
		return 0;
	}

// take it two integers for column and row and returns an int representing the numbers of cells
// being reveal from this method call. 
	private int recClear(int col, int row) {
		
// if column and row out of bound or negative then return 0
		if (col >= width || row >= height || col < 0 || row < 0) {
			return 0;
		}
// if cells is revealed then also return 0
		else if (cells.getElement(col, row).isRevealed()) {
			return 0;
		}
// if cells if a bomb then return 0.
		else if (cells.getElement(col, row).getNumber() == -1) {
			return 0;
		}
// if cells is greater that 0 in that number then reveal that specific cells. and return 1.
		else if (cells.getElement(col, row).getNumber() > 0) {
			cells.getElement(col, row).reveal();
// if gui is empty then set background of cell to white
			if (gui != null) {
				cells.getElement(col, row).setBackground(Color.white);
			}
			return 1;
			
// else reveal that cells and add all the 8 recursive calls with integer equal 1 at the beginning
// check each recclear before adding into result. 
// at the end return result. 
			
		} else {
			cells.getElement(col, row).reveal();
// if gui is not null then set back ground to cells to white. 
			if (gui != null) {
				cells.getElement(col, row).setBackground(Color.white);
			}
			int result = 1;
			result = result + recClear(col-1, row-1);
			result = result + recClear(col-1, row);
			result = result + recClear(col-1, row+1);
			result = result + recClear(col, row-1);
			result = result + recClear(col, row+1);
			result = result + recClear(col+1, row-1);
			result = result + recClear(col+1, row);
			result = result + recClear(col+1, row+1);
			return result;
		}
	}
	

}
