//My name is Que Hung Dang and this is my code for HashDictionary.java 251205417

// import java util to use built in linkedlist for chaining and creating the hash table operations.
import java.util.*;

//create a public class hash dictionary that implements the dictionary ADT to use their ADT.
public class HashDictionary implements DictionaryADT{
	private LinkedList<Data>[] hashtable;
	private int size;
	private int numRecords;

//create a hashdictionary with a specified size (where size is a prime number)
	public HashDictionary(int size) {

// initialize all the private variables where numrecords is 0 and size become this size.
		this.numRecords = 0;

//hashtable will create a new linkedlist array with the given prime number size
		hashtable = new LinkedList[size];
		this.size = size;
		
// create a for loop to go through all the array in hashtable and make them as one of the linkedlist each
// this is to add the chaining later on when we insert new data.
		for (int i = 0; i < size; ++i) {
			hashtable[i] = new LinkedList<Data>();
		}
		
	}
	
//create a private hashfunction to turn the pattern of the code into hashnumber so it can be insert 
//inside the hash table.
	
	 private int hash(String config) {

//I use 5 different prime number for my hash function to reduce their collisions
	        int a = 1609;
	        int b = 4969;
	        int c = 5;
	        int d = 761;
	        int e = 43;

	        //create a cast 0 to store the casted result
	        int cast = 0;

//create a for loop that for each character on the board it will be take into a character from the string and then each characters will become integer
//then after that multiply that number with prime number or also add the prime number in it
//after that will mod it with the size of the table each function to make sure that it will never go out of bound during the hashing process    
	        for (int y = 0; y < config.length(); ++y) {
	            char x = config.charAt(y);
	            
	            //turn it into the integer from making the character x from the index of the string from config input to make it into the integer then the 
	            //process will be repeated for every loop until it equal to the length of config then break
	            cast = (cast + (int) x) % size;
	            cast = (cast * a + c) % size;
	            cast = (cast * b) % size;
	            cast = (cast * d) % size;
	            cast = (cast * e) % size;
	   
	        }  
	        
	        //after finishing the hashing process to create a unique integer then return the cast.
	        return cast;
	 }
	 
	 //create a put method to put the string into the hashtable where it throw dictionary exception if it already exist in the hashtable
	public int put(Data record) throws DictionaryException{
		
		//create a string config from data record function of getconfiguration()
		String config = record.getConfiguration();
		
		//now hash that string using our hash function this is the array of the hashtable to know where to put it in
		int hashcode = hash(config);
		
		
		//now check if that place is empty then add the record into that using the linkedlist method with add()
		if (hashtable[hashcode].isEmpty()) {
			hashtable[hashcode].add(record);
			
			//increase the numrecords from our data
			numRecords++;
			
			//then return 0 as the instruction from asn since it did not product a collisions
			return 0;
		}
		
		//if there something there then it produces a collision or record already exists
		else {
			
			//create a for loop to go through all the data in that specific index.
			for (int i = 0; i < hashtable[hashcode].size(); ++i) {
				
				//create a variable of data to get what is inside the linkedlist chain using the get() method already implements from linkedlist
				Data exist = hashtable[hashcode].get(i);
				
				//now check that data string by using getconfigurations to return the string to check if they're the same
				//if they are the same then throw the dictionary exception
				if (exist.getConfiguration() == record.getConfiguration()) {
					throw new DictionaryException();
				}
				
			}
			
			//if not then add the record inside the index of that function using the linkedlist function of .add(record) and return 1.
			hashtable[hashcode].add(record);
			numRecords++;
			return 1;
		}
		
	}
	
	//next create the remove function which also use the same mechanic as the put methods.
	public void remove(String config) throws DictionaryException {
		
		//hash the string once again to compare them in the hashtable
		int hashcode = hash(config);
		
		//if theres nothing at the index then return null;
		if(hashtable[hashcode].equals(null)) {
			throw new DictionaryException();
		}
		
		//if not then traverse through the specific index to check everything in the chain.
		else {
			
			//make a check boolean to false to make sure that after traversing if theres no data that has the exact same pattern of the board then
			//throw the excepttion of not exisiting
			boolean check = false;
			
			//create a for loop to traverse the chain
			for (int i = 0; i < hashtable[hashcode].size(); ++i) {
				
				//create a data exist to get the string from it then compare it with the given config to check its data
				Data exist = hashtable[hashcode].get(i);
				//if the string is equals to config then u remove it.
				if(exist.getConfiguration().equals(config)) {
					
					//now initlize a single variable that also has the same index of the existing hashtable index inside the chain itself
					// they both have the same object references so if u remove something from single it will also remove it from that specific index in the hashcode table.
					// single is not a new linked list it is a reference from hashtable so we can manipulate the data from single so they have same object memories.
					LinkedList<Data> single = hashtable[hashcode];
					
					//now when remove the same index of the given chain index it will also remove it from the hashtable since both of it has the same initialization.
					single.remove(i);
					
					//minus the numrecord to 1;
					numRecords--;
					
					//turn check to true so it doesnt throw the exception then break from the for loop to stop
					check = true;
					break;
				}
			}
			if (check == false) {
				throw new DictionaryException();
			}
		}
	}
	
	//create a get method to get the specific score of the specific pattern
	public int get(String config) {
		
		//hash the string once again
		int hashcode = hash(config);
		
		// if theres nothing in the index then return null
		if (hashtable[hashcode] == null) {
			return -1;
		}
		else {
			
			//create another for loop to go through the chain of that index.
			for (int i = 0; i < hashtable[hashcode].size(); ++i) {
				
				//create a exist data that get the data file from linkedlist in that index using the given get() method from setting that index as a linkedlist
				Data exist = hashtable[hashcode].get(i);
				
				// now compare the string using equals method if it the same then return the score
				if(exist.getConfiguration().equals(config)) {
					return exist.getScore();
				}
			}
			
			//after traversing the chain and if there no the same pattern in that index then return -1 as there is nothing in that index chain.
			return -1;
		}
		
	}
	
	//return the current numrecord when call the method.
	public int numRecords() {
		return numRecords;
	}
}
