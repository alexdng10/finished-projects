//This is TransitLine java file for Que Hung Dang Student id 251205417. 
//This class is used to represent a line (route) in the transit system.
//Each line contains some number of Stations (including some InterchangeStations) 
//that are connected in both directions. 

public class TransitLine {
//create instance variable for line letter and first station
	private char lineLetter;
	private Station firstStn;
//initialize the variables.
	public TransitLine(char letter, Station first) {
		lineLetter = letter;
		firstStn = first;
	}
// return the line letter
	public char getLineLetter() {
		return lineLetter;
	}
//return the first station
	public Station getFirstStn() {
		return firstStn;
	}
// check if the current station has a next station linked or not, if its interchange station
// then cast it in to interchange and use get next with the line letter
// if there is something then return true else will be false.
	public boolean hasNext(Station stn) {
		if(stn instanceof InterchangeStation) { 
			if (((InterchangeStation) stn).getNext(getLineLetter()) == null) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			if(stn.getNext()== null) {
				return false;
			}
			else {
				return true;
			}
		}		
	}
//add station adding the station on to the transit line with checking different type of station before adding
	
	public void addStation(Station stn) {
// begin with create a current station by using get first station method
		
		Station curr = getFirstStn();
//create a while loop for has next current if its true then keep going through the transit line.
//break when there is nothing next which return false
		while (hasNext(curr)) {
//checking if either current is interchangestation or station in order to go to the next current station.
			if (curr instanceof InterchangeStation == false) {
				
				curr = curr.getNext();
			}
			else {
				
				curr = ((InterchangeStation) curr).getNext(getLineLetter());
			}
		}
// then check the input of station if its interchangestation or not
//if station is not interchange then proceed with normal set next to null and set prev to current station
		if (stn instanceof InterchangeStation == false) {
			stn.setNext(null);
			stn.setPrev(curr);
// connect the current into the station but need to check if its interchange or not
// if its not then proceed with set next normal station else set next with the transit line letter
			if (curr instanceof InterchangeStation == false) {
				curr.setNext(stn);
			}
			else {
				((InterchangeStation)curr).setNext(stn,getLineLetter());
			}
		}
// if station is interchange then use the interchangestation station to set prev to curr with transit line
// and set next to null with transit line

		else {
			if (curr instanceof InterchangeStation == false) {
				curr.setNext(stn);
			}
			else {
				((InterchangeStation)curr).setNext(stn,getLineLetter());
			}
			((InterchangeStation) stn).setPrev(curr, getLineLetter());
			((InterchangeStation) stn).setNext(null, getLineLetter());
		}
		
		
	}
// create a method to print out the string of the transit line with containing line letter and list of
// station number of that specific line.
	public String toString() {
		String result = "";
		result = result + "Line " + getLineLetter() + ":";
		Station curr = firstStn;
		while (curr != null) {
			result = result + "  " + curr.getStnNo();
			if (curr instanceof InterchangeStation) {
				curr = ((InterchangeStation) curr).getNext(getLineLetter());
			}
			else {
				curr = curr.getNext();
			}
			
		}
		result = result + "  ";
		return result;
	}
}

