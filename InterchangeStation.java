//This is InterchangeStation java file for Que Hung Dang Student id 251205417. 
//which means a Station on 2 or more transit line. 
//This kind of station allows passengers to switch from one line to another

// create the class which is the subclass of the station
public class InterchangeStation extends Station {
//create variables to store the prevs and nexts transit line.
	private Station[] prevs; 
	private Station[] nexts;
//begining num for interchange index array.
	int num = 2;
	
//methed use super to use the parent function in initilizing the medthod)
	public InterchangeStation(int stnNo, String stnName, int x, int y) {
		super(stnNo, stnName, x, y);
		 prevs = new Station[num];
		 nexts = new Station[num];	
	}
@Override
//Can not use the getPrev,Next and setPrev,Next because it requires a transit line so throw the exception.
//return a string that notify the user can not use the normal function station.
	public Station getPrev() throws StationException{
		throw new StationException("InterchangeStation cannot use getPrev()");
	}
@Override
	public Station getNext() throws StationException {
		throw new StationException("InterchangeStation cannot use getNext()");
	}
@Override
	public void setPrev(Station stn) throws StationException {
		throw new StationException("InterchangeStation cannot use setPrev()");
	}
@Override
	public void setNext(Station stn) throws StationException {
		throw new StationException("InterchangeStation cannot use setNext()");
	}

	
//get previous linked of that specific transit line if there's nothing then return null	
	public Station getPrev(char lineLetter) {
		
		try {
		return prevs[(int) lineLetter - 65]; 	
		}
		catch(Exception e) {
			return null;
		}
	}

//get next linked of that specific transit line if there's nothing then return null	
	public Station getNext (char lineLetter) {
		
			try {
				return nexts[ (int) lineLetter - 65];
			}
			catch(Exception e) {
				return null;
			}
			}
		
		
//set the prev linked of the station with the specific transit line from the prevs array.
// if its a new line then get the new index of the prev array by minus 65 from line letter.
//expand the prevs and nexts array to make sure they have the same length.
	public void setPrev(Station stn, char lineLetter) {
		if ((int)lineLetter - 65 >= prevs.length) {
			int add =  (lineLetter -65) + 1;
			Station[] temp = new Station[add];
			Station[] temp2 = new Station[add];
			for (int i = 0; i < prevs.length; i++) {
				temp[i] = prevs[i];
				temp2[i] = nexts[i];
			}
			for (int i = prevs.length; i < temp.length;++i) {
				temp[i] = null;
				temp2[i] = null;
			}
			prevs = temp;
			nexts = temp2;
			prevs[(int) lineLetter - 65] = stn;
		}
		else {
		prevs[(int) lineLetter - 65] = stn;
		}
		
		}
//set the next linked of the station with the specific transit line from the next array.
// if its a new line then get the new index of the next array by minus 65 from line letter.
//expand the next and next array to make sure they have the same length.
	public void setNext(Station stn, char lineLetter) {
		if ((int)lineLetter - 65 >= nexts.length) {
			int add = (lineLetter -65) + 1;
			
			Station[] temp = new Station[add];
			Station[] temp2 = new Station[add];
			for (int i = 0; i < nexts.length; i++) {
				temp[i] = nexts[i];
				temp2[i] = prevs[i];
			}
			for (int i = prevs.length; i < temp.length;++i) {
				temp[i] = null;
				temp2[i] = null;
			}
			nexts = temp;
			prevs = temp2;
			nexts[(int)lineLetter - 65] = stn;

		}
		else {
		nexts[(int)lineLetter - 65] = stn;
		}
		}
//return the prevs array
	public Station[] getPrevArray() {
		return prevs;
	}
//return the next array
	public Station[] getNextArray() {
		return nexts;
	}
//return the station number of the prev array if null then __
	public String getPrevString () {
		String result = "";
		for (int i=0; i< prevs.length; ++i) {
			if (prevs[i] == null) {
				result = result + "" + "__" + "  ";
			}
			else {
			result = result + "" + prevs[i].getStnNo() + "  ";
			}
		}
		return result;
	}
//return the station number of the next array if null then __
	public String getNextString () {
		String result = "";
		for (int i=0; i< nexts.length; ++i) {
			if (nexts[i] == null) {
				result = result + "" + "__" + "  ";
			}
			else {
			result = result + "" + nexts[i].getStnNo() + "  ";
			}
		}
		return result;
	}
//return the interchange station string with all of the information and make sure 
//they are on all the line.
	public String toString() {
		String result = "";
		result = result + "Stn: " + getStnNo() + " (" + getname() + ")" + " on Lines: ";
		
		
		for (int i=0; i< prevs.length; ++i) {
			if (prevs[i] != null || nexts[i] != null) {
				result = result + (char)(i+65) + " ";
			}	
	}
		return result;

}
}

	
		
	
	
	
	
	

