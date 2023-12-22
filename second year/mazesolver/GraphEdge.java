//this class represents an edge inside the graph 
//Que Hung Dang
// 251205417

public class GraphEdge {
	
	//have 4 instance variable to store the 2 nodes first and second and the type of the edge
	// and also the name of the edge
	private GraphNode first;
	private GraphNode second;
	private int type;
	private String label;
	
	//construct the graph edge with the given information and assigning the correct variables into
	// the corresponding variables
	GraphEdge(GraphNode u, GraphNode v, int type, String label){
		first = u;
		second = v;
		this.type = type;
		this.label = label;
	}
	
	
	//all simple function to help with the graph edge to return first or second node
	// or return the type of the edge or get the name of the edge.
	GraphNode firstEndpoint() {
		return first;
	}
	
	GraphNode secondEndpoint() {
		return second;
	}
	
	int getType() {
		return type;
	}
	
	//set the type of edge and also set the label of the edge with the given information.
	void setType(int newType){
		type = newType;
	}
	
	String getLabel() {
		return label;
	}
	
	void setLabel(String newLabel){
		label = newLabel;
	}
	
}
