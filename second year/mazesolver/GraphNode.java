


public class GraphNode {
	
	//create 2 instance variables  to store 
	// the name of the node and the boolean of the node
	private int name;
	private boolean mark;
	
	//the node is unmarked at first so its false at the beginning.
	GraphNode(int name){
		this.name = name;
		mark = false;
	}
	
	//mark the node with the given boolean
	void mark(boolean mark){
		this.mark = mark;
	}
	
	//return the mark boolean and get the name of the node
	boolean isMarked() {
		return mark;
	}
	
	
	int getName() {
		return name;
	}
	

}
