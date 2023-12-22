//This is Station java file for Que Hung Dang Student id 251205417. This function represent a single Station in the transit system.

public class Station {
//Create instance variables to store the information of station
	private static int nextID = 0;
	private int id;
	private int stnNo;
	private String name;
	private Station prev;
	private Station next;
	private int x;
	private int y;
//create a public station class to initilize all of the variables. prev and next is null.
	public Station(int stnNo, String stnName, int x, int y) {
		this.stnNo = stnNo;
		this.name = stnName;
		this.x = x;
		this.y = y;
		this.prev = null;
		this.next = null;
		id = nextID;
		++nextID;
	}
//return the id of the station
	public int getId() {
		return id;
	}
//return the station number of the station
	public int getStnNo() {
		return stnNo;
	}
//return the name of the station
	public String getname() {
		return name;
	}
//return the previous linked of the station
	public Station getPrev() {
		return prev;
		
	}
//return the next linked of the station
	public Station getNext() {
		return next;
	}
//return the coordinate on x axis.
	public int getX() {
		return x;
	}
//return the coordiate on y axis.
	public int getY() {
		return y;
	}
	
//set the previous linked of station
	public void setPrev(Station stn) {
		prev = stn;
	}
//set the next linked of the station
	public void setNext(Station stn) {
		next = stn;
	}
//return the string containing all of the variables station itself.
	public String toString() {
		return "Stn: " + stnNo + " (" + name + ")";
	}
	
}
