
// This class represents a 2D grid that is created as an array of singly linked list.
public class LinkedGrid<T> {
// private variables for this class to create the grid.
private int width;
private int height;
private LinearNode<T>[] grid;


// the constructor that takes in the width and height in order to create the grid. The width will contain
// the front nodes of each of the linked list. 
	public LinkedGrid(int width, int height) {
//assign their value to their instance variables
		this.width = width;
		this.height = height;
		
// create the length of grid with the width.
		grid = new LinearNode[width];
//create a for a double for loop to create a node for each of the front and connect them as a singly linked list
		for (int i = 0; i < width; ++i) {
			LinearNode<T> node = new LinearNode<T>();
			grid[i] = node;
			for (int j = 0; j < height; ++j) {
//create a node then assign the front to that node and get next the node and repeat. 
				LinearNode<T> nextnode = new LinearNode<T>();
				node.setNext(nextnode);
				node = node.getNext();
			}
		}
	}

	
// take in 3 parameters col row and data to set the cell of the grid with the element of that node with 
// given data. 
	public void setElement(int col, int row, T data) {
// if col or row is invalid and out of bound then throw a linkedlistexception.
		if(col >= width || row >= height || col < 0 || row < 0) {
			throw new LinkedListException("Error");
		}
// else then create a set lineat node and to find the front of which column to assign data to
		LinearNode<T> set = new LinearNode();
		set = grid[col];
		for (int i=0;i<row;++i) {
				set = set.getNext();
			}
// after that set the data into the set node with set element function.
			set.setElement(data);
	}
	
	
// create a constructor that takes in 2 parameter which are col and row then check if they are out of bound
// if they are then throw linkedlistexception
	public T getElement(int col, int row) {
		if(col >= width || row >= height || col < 0 || row < 0) {
			
// if they are out of bound then execute exception.
			throw new LinkedListException("Error");
		}
// if there is nothing from the grid column then return null
		else if(grid[col] == null) {
			return null;
		}
// else create a node and set the node into grid column as the front node
		else {
			 LinearNode<T> node = new LinearNode();
			 node = grid[col];
// now while row not 0 then create a while loop and get next node until it reaches the end.
			 while (row != 0) {
					node = node.getNext();
					row--;
				}
			 
// if node is null then return null
			 if (node == null) {
				 return null;
			 }
			 
// if not return the element of that node.
			 else {
				 return node.getElement();
			 }
		}
	}

// a constructor to return width
	public int getWidth() {
		return width;
	}
// a constructor to return height.
	public int getHeight() {
		return height;
	}
// a constructor to create a tostring method to print out the complete grid.
	public String toString() {
// create an empty string and a linear node array
		String result = "";
		LinearNode<T>[] node;
// set that node into grid.
		node = grid;
// if grid is empty then return empty
		if (grid == null) {
			return "Empty";
		}
		else {
			
// else if not create a double for loop to print out all of the result of the grid by using get element
// and to stringf method. if get element not null only, if its null then add null into there
			for (int i = 0; i < height; ++i) {
				for (int j = 0; j < width; ++j) {
					if (node[j].getElement() != null) {
						result = result + node[j].getElement().toString() + "  ";
						node[j] = node[j].getNext();
						}
					else {
						result = result + "null  ";
					}
				}
				
// after they finish one singly linked list then create a new line
				result = result + "\n";
			}
		}
// return the string result at the end. 
		return result;
	}

}
