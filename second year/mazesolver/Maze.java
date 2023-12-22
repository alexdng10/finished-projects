//this is the maze class that construct the maze from an input file 
// and then use the maze that constructed to create a solve method if there exist a way to show the path from the
// start to exit node. 
//name Que Hung Dang 251205417


// import all the java file read and functions to create a stack and read input files.
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

public class Maze {
	
	//create private variables to store all the information needed to construct a maze
	//also create 2 stack one for the path from start to exit
	// the other is to store the edges that is being visited and will be pop to add back the coin in case it is not the correct path way.
    private int coin;
    private int width;
    private int length;
    private Graph maze;
    private GraphNode start;
    private GraphNode exit;
    private Stack<GraphNode> path;
    private Stack<GraphEdge> execution;
    
    //maze function that given a name of an input file.
    Maze (String inputFile) throws GraphException,MazeException{
    	
    	//create construct string variable to instruct us what to do and follow the command.
        String construct;
        
        //initialize the stack of path and execution to store graph edges.
        path = new Stack<GraphNode>();
        
        execution = new Stack<GraphEdge>();
        
        //now use the try catch bracket to read files
        try {
        	
        	//use function file read to create a new file reader to find the given name input file
            FileReader file = new FileReader(inputFile);
            
            //now use the scanner function to scan line per line to know what to do from the input file
            Scanner line = new Scanner(file);
            //skip the first line we dont need to use it
            construct = line.nextLine();
            
            //move on to the next line it will be the width of the maze so parse int it to make , the next line
            // will also be for length of the maze.
            construct = line.nextLine();
            width = Integer.parseInt(construct);
            construct = line.nextLine();
            length = Integer.parseInt(construct);
            
            //finally last line before moving to the maze is the coin line to show how many coin is available.
            construct = line.nextLine();
            coin = Integer.parseInt(construct);
            
            
            //now multiply the width and length to find the size of the maze
            int size = width * length;
            
            //create a string instruct to instruct what to do to create the maze 
            String instruct;
            
            
            //set up 2 counter for width and depth
            int counter_w = 0;
            int counter_d = 0;
            
            //set up a boolean horizonto to true so we can go between horizontal and verticle
            boolean horizontal = true;
            
            //create the maze with the size we multipled
            maze = new Graph(size);
            
          
            //create a while loop that stop when line is empty or when width is larger than the size of the maze.
            while (line.hasNextLine() != false && counter_w < size) {
            	
            	//now read the next of line of input file and assign it into the instruct var
            	instruct = line.nextLine();
            	
            	//if horizontal is true then go to that bracket
            	if(horizontal) {
            		
            		//now create a for loop to find the start, exit, as well as room in the maze between themselves
            		//is instruction between the 2 nodes so we have to add 2 every time
	            	for (int i = 0; i < instruct.length(); i = i + 2) {
	            		
	            		//get a character at the given index
	            		char val = instruct.charAt(i);
	            		
	            		//now check if the index is a start node or an exit node if it is then assign the 
	            		// current from the counter to start or exit
	            		if(val == 's') {
	            			start = maze.getNode(counter_w);
	            		}
	            		else if (val == 'x') {
	            			exit = maze.getNode(counter_w);
	            		}
	            		
	            		//now connect each node together with the given instruction if it reaches end of the line it will stop and exit the loop
	            		if ((val == 'o' || val =='s' || val == 'x') && i+1 < instruct.length()) {
	            			
	            			
	            			// now get the sign that is next to the nodes so we will add one
	            			char sign = instruct.charAt(i+1);
	            			
	            			//now if the sign is a c then it is a corridor then insert an edge between the 2 nodes
	            			// with 0 int and label it as corridor
	            			if(sign == 'c') {
	            				maze.insertEdge(maze.getNode(counter_w),maze.getNode(counter_w+1), 0, "corridor");
	            			}
	            			
	            			//otherwise its going to be a digit which means a door so get the integer from the character
	            			// to make sure we get the correct int and insert it into the edge between the 2 nodes and label it as door
	            			// if its wall then we dont insert any edges.
	            			else if(Character.isDigit(sign) == true) {
	            				int it2 = Character.getNumericValue(sign);
	            				
	            				maze.insertEdge(maze.getNode(counter_w), maze.getNode(counter_w+1), it2, "door");				
	            			}
	            			
	            			//move the pointer to the next node
	            			counter_w++;
	            			
	            		}
	            		
	            	}
	            	// at end of each line outside the for loop before going to verticle we want to move
	            	// to the next line for horizontal so we add 1
	            	if(counter_w<size-1) {
	            		counter_w++;
	            	}
	            	
	            	// turn horizontal to false;
	            	horizontal = false;
	            	
            	}
            	
            	//now when horizontal is false
            	else {
            		
            		//create a for loop again with the same instruct that is also i + 2
            		for (int i = 0; i < instruct.length(); i = i + 2) {
            			
            			//now check the instruction of the vertical since it will be instructions we will check
            			
            			// if its corridor or door
            			char input = instruct.charAt(i);
            			
            			
            			//then when insert we went to insert the counter_d node insert it with the counter_d + width since
            			// we inserting the edges vertically
            			if(input == 'c') {
            				maze.insertEdge(maze.getNode(counter_d), maze.getNode(counter_d+width), 0, "corridor");
            			}
            			
            			//if its digit same mechanic but add the width with depth node
            			else if(Character.isDigit(input)) {
            				int it = Character.getNumericValue(input);
            				maze.insertEdge(maze.getNode(counter_d), maze.getNode(counter_d+width), it, "door");
            			
            			}
            			
            			//then increase the point for depth as well
            			counter_d++;
            			
            		}
            		//horizontal back to true;
                	horizontal = true;
            	}
            	
            	
            }
           
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    
    
    //now this is function solve that return an iterator with the path that is need to be taken
    // to reach the exit node from start.
    public Iterator solve() throws GraphException {
    	
    	//now create a boolean check for the private helper function to help us
    	//the private function going to push the correct node inside the path so we can return the iterator later
    	boolean check = findpath(start,exit);
    	
    	//now if it doesn't return true then have to throw the no solution for the given maze.
    	if (check == false) {
    		throw new GraphException("Maze no solution");
    	}
    	
    	//finally return the iterator
    	return path.iterator();
    	
    }
    
    //this function recursively check if there is a path from start node to exit node to solve the maze.
    private boolean findpath(GraphNode start, GraphNode exit) throws GraphException {
    	 
    	
    	//firstly push the start node into the path stack
    	path.push(start);
    	
    	//now check if it equals to exit then we find our base case and return true.
    	if (start.equals(exit) == true ){
    		return true;
    	}
    	
    	//mark the node to true so it is marked now so we dont visit it twice
    	start.mark(true);
    	
    	//now create an edge_list to store all the edge from the iterator list
    	LinkedList<GraphEdge> edge_list =  new LinkedList<GraphEdge>();
    	
    	//this will give an iterator with all the incident edges of node start
    	Iterator list = maze.incidentEdges(start);
    	
    	//now add all of the edges from the iterator to the edge lest that we just created so now we can use a for loop for our functions.
    	while(list.hasNext()) {
    		GraphEdge info = (GraphEdge) list.next();
    		
    		edge_list.add((GraphEdge) info);
    	}
		
    	//this function will go through every incident edges with the given node start in the edge list that we just created
		for (GraphEdge edge : edge_list) {
			
			//now check the second node inside this list to see if its marked or not
			// if it is not marked then we will check its label
			GraphNode node = edge.secondEndpoint();
			
			if (node.isMarked() == false){
				
				//if its a corridor then we push the edge into our execution stack and then we recursively
				// call the find path again with the node from the second end point
				if(edge.getLabel() == "corridor") {
					execution.push(edge);
					if(findpath(node,exit) == true) {
						return true;
					}
			
				}
				
				//otherwise if its a door then we need check if we have enough coin to go through the door
				else if(edge.getLabel() == "door") {
					
					//if we minus the coin with the number of required coin and its equal to 0 or higher then 
					// we can go through the door and we recursively call it again but also
					// push the edge into the execution stack
					if (coin  - edge.getType()   >= 0) {
						execution.push(edge);
						
						//now minus the coin with the get type edge
						coin = coin - edge.getType();
						
						
						//and recursively find a pathway to exit
						if (findpath(node,exit) == true) {
							return true;
						}
						
					}
					
				}
			}	
			
			
			//same mechanic but now for first node of the edges
			GraphNode node2 = edge.firstEndpoint();
			if (node2.isMarked() == false){
				if(edge.getLabel() == "corridor") {
					execution.push(edge);
					if(findpath(node2,exit) == true) {
						return true;
					}
			
				}
				else if(edge.getLabel() == "door") {
					
					if (coin - edge.getType()  >= 0) {
						execution.push(edge);
					
						coin = coin - edge.getType();	
						
						if (findpath(node2,exit) == true) {
							return true;
						}
						
					}
					
				}
			}
			
		}
		
		//now if none of the recursion return true then we have to add the  coin again since we
		// can not find a path way so now we have to add the coin back
		//using the execution stack we will pop the first edge then 
		// add the coin back to the user after popping that specific edge
		if(!execution.isEmpty()) {
			
			//temporary edge to add the coin back
			GraphEdge temp = execution.pop();
			coin = coin + temp.getType();
		}
		
		//unmarked the node and also pop the node into the path way since it is not the correct way to go to the exit node.
		start.mark(false);
		path.pop();
		return false;
		
    }
    
    
    //this function return the maze if its not null but if its null then throw the graph exception.
    Graph getGraph() throws GraphException {
    	if (maze == null) {
    		throw new GraphException("Maze is null");
    			
    		
    	}
    	return maze;
    }

}