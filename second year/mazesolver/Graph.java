//Hello my name is Que Hung Dang and this is 
// code for 2210 assignment 5  this is class graph to construct a proper graph for the maze class
//251205417

//import java utilities so we can create a linked list to store all the incident edges
import java.util.*;
public class Graph implements GraphADT{
	
	// create a private linkedlist to store the adjacency_list of the edges
	private LinkedList<GraphEdge>[] adjacency_list;
	
	//create an array that store all the graph node inside the graph
	private GraphNode[] node;
	
	//public constructor graph to create a graph with n is the desired size for the graph
	public Graph(int n){
		
		//initialize the adjacency_list size with the given n size.
		adjacency_list = new LinkedList[n];
		
		//initialize the size of the array for graph node with the given size.
		node = new GraphNode[n];
		
		//now construct a for loop to create a new graph node for each index of the array list in array node
		// and also to create a new linked list in the adjacency_list that store the graph edge variables
		for (int i = 0; i < n; ++i) {
			adjacency_list[i] = new LinkedList<GraphEdge>();
			node[i] = new GraphNode(i);
		}
	}
	
	//function to insert an edge between 2 nodes with the given edge type and label the edge
	public void insertEdge(GraphNode u, GraphNode v, int edgeType, String label) throws GraphException{
		
		
		//check if the node is bigger than the adjancency_list or not, since we initialize the node from 0 to size - 1 so now we have to add 1 to see if its 
		// larger than the length of the adjacency_list.
		if (u.getName()+1 > adjacency_list.length) {
			throw new GraphException("Node not exist");
		}
		else if (v.getName()+1 > adjacency_list.length) {
			throw new GraphException("Node not exist");
		}
		
		//if it pass the length now we have to create a edge with the given variables
		
		// then we have to check if the graph edge already in the graph or not if it is then we have 
		// to throw a graph exception that alert the user that the edge already exist. 
		GraphEdge edge = new GraphEdge(u,v,edgeType,label);
		
		//get the name of both nodes so we can insert the edge in both nodes in the adjacency_list.
		int u_name = u.getName();
		int v_name = v.getName();
		
		//now create a for loop that goes through one of the node incident edges, it can be either and 
		// get the graph edge 
		for (int i = 0; i < adjacency_list[u_name].size(); ++i) {
			GraphEdge exist = adjacency_list[u_name].get(i);
			if (edge == exist) {
				throw new GraphException("Edge already exist");
			}
		}
		
		//if we iterate through the linked list and we can't find the edge then we can safely add the edges into both nodes in the 
		// adjacency_list.
		adjacency_list[u_name].add(edge);
		adjacency_list[v_name].add(edge);
		
	}
	
	
	
	//this function is to return the graph node when we want to find the node in the graph
	 public GraphNode getNode(int name) throws GraphException {
		 
		 //if the integer is larger than the length of the list or integer is a negative number
		 // then we have to throw the graph exception.
		 if (name > adjacency_list.length || name < 0) {
			 throw new GraphException("Node not exist");
		 }
		 
		 // otherwise if not then we can safely return the correct graph node with the given index to find the node in the array/
		 return node[name];
	 }
	 
	 // this public function is to return an iterator of an incident eges of the given node if we can find it.
	 public Iterator incidentEdges(GraphNode u) throws GraphException {
		 
		 //now we get the name of the graph node and check if its larger than the node length
		 // if it is larger then we have to throw a graph exception.
		int name = u.getName();
		if (name > node.length) {
			throw new GraphException("Node not exist");
		}
		
		//otherwise if the given index is null then we return null
		if (adjacency_list[name] == null) {
			return null;
		}
		
		// if the incident edges in the given node is not null then we return the iterator of the given index from the 
		// adjacency_list
		return adjacency_list[name].iterator();
		 
	 }
	 
	 
	 // this function is to return the graph edge between 2 given nodes
	 public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
		 
		 // now we have check if both the node is inside the length of the array node if its not then we 
		 // have to throw the new graph exception.
		 if (u.getName() > node.length || v.getName() > node.length) {
			 throw new GraphException("Node not exist");
		 }
		 
		 //now initialize an integer that represent the name of a node
		 int name = u.getName();
		 
		 //then use the for loop to traverse through the adjacency list to see if we can find the given edge, if we find it then
		 // we can return the graph edge at the correct index
		 for (int i = 0; i < adjacency_list[name].size(); ++i) {
			 if (adjacency_list[name].get(i).firstEndpoint() == u && adjacency_list[name].get(i).secondEndpoint() == v) {
				 return adjacency_list[name].get(i);
			 }
		 }
		 //if we can't find it then we can throw the graph exception
		 throw new GraphException("edge does not exist");
	 }
	 
	 //final method to check if 2 nodes are adjacent to each other
	 public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
		 
		 // again check if both node are inside the array node or not
		 if (u.getName() > node.length || v.getName() > node.length) {
			 throw new GraphException("Node not exist");
		 }
		 
		 //then we call the get name function to find the integer name and use the for loop to check both ways to see if they are next to each other
		 // if can be in any other, if either one of it is correct then we will return true.
		 int name = u.getName();
		 for (int i = 0; i < adjacency_list[name].size(); ++i) {
			 if ((adjacency_list[name].get(i).firstEndpoint() == u && adjacency_list[name].get(i).secondEndpoint() == v) ||
				 (adjacency_list[name].get(i).firstEndpoint() == v && adjacency_list[name].get(i).secondEndpoint() == u)
				 ) {
				 return true;
			 }
		 }
		 
		 //other wise we will return false after going through the entire thing and we can't find them being
		 // adjacent.
		 return false;
		
	 }
	 
	
	
	

}
