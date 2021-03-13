import java.util.List;

//My AssassinManager takes a list of names for the assassin game and manages
//the list throughout the match
public class AssassinManager {
	private AssassinNode killRing; //Represents the players still alive
	private AssassinNode graveyard; //Represents the players that were killed
	
	//Throws IllegalArgumentException if an empty list of names is passed
	//Constructs an assassin manager from all the names in the list
	public AssassinManager(List<String> names) {
		if(names.isEmpty()) {
			throw new IllegalArgumentException("No one is playing");
		}
		this.killRing = new AssassinNode(names.get(0));
		AssassinNode current = killRing;
		for(int i = 1; i < names.size(); i++) {
			current.next = new AssassinNode(names.get(i));
			current = current.next;
		}
	}
	
	//Prints all the players still in the game
	public void printKillRing() {
		AssassinNode current = this.killRing;
		while(current.next != null) {
			System.out.println("    " + current.name + " is stalking " + current.next.name);
			current = current.next;
		}
		System.out.println("    " + current.name + " is stalking " + this.killRing.name);
	}
	
	//Prints all the players that have been killed
	public void printGraveyard() {
		AssassinNode current = this.graveyard;
		while(current != null) {
			System.out.println("    " + current.name + " was killed by " + current.killer);
			current = current.next;
		}
	}
	
	//Returns a boolean determining whether or not the game is over
	public boolean isGameOver() {
		return this.killRing.next == null;
	}
	
	//Returns the name of the winner in the form of a string
	public String winner() {
		if(isGameOver()) {
			return this.killRing.name;
		}
		return null;
	}
	
	//Returns a boolean determining whether or not the given name is still
	//participating in the game
	public boolean killRingContains(String name) {
		AssassinNode current = this.killRing;
		if(isGameOver()) {
			return name.toLowerCase().equals(current.name.toLowerCase());
		}
		while(current != null) {
			if(name.toLowerCase().equals(current.name.toLowerCase())) {
				return true;
			}else {
				current = current.next;
			}
		}
		return false;
	}
	
	//Returns a boolean determining whether or not the given name has been
	//removed from the game
	public boolean graveyardContains(String name) {
		if(this.graveyard == null) {
			return false;
		}
		
		AssassinNode current = this.graveyard;
		
		if(current.next == null) {
			return name.toLowerCase().equals(current.name.toLowerCase());
		}
		while(current != null) {
			if(name.toLowerCase().equals(current.name.toLowerCase())) {
				return true;
			}else {
				current = current.next;
			}
		}
		return false;
	}
	
	//Throws an IllegalStateException if the game is already over
	//Throws an IllegalArgumentException if the given name isn't 
	//a name from the people remaining in the game
	//Kills the person with the given name and reorganizes the kill ring
	//and the graveyard
	public void kill(String name) {
		if(isGameOver()) {
			throw new IllegalStateException("The game is already over");
		}
		if(!killRingContains(name)) {
			throw new IllegalArgumentException("The given name isn't in the kill ring");
		}
		AssassinNode current = this.killRing;
		AssassinNode holder = this.killRing;
		while(current.next != null) {
			if(name.toLowerCase().equals(current.next.name.toLowerCase())) {
				holder = current.next;
				holder.killer = current.name;
				current.next = current.next.next;
				holder.next = this.graveyard;
				this.graveyard = holder;
			}else {
				current = current.next;
			}
		}
		
		//This last if statement tests the case that the name the
		//user is looking for is at the beginning
		if(name.toLowerCase().equals(this.killRing.name.toLowerCase())) {
			holder = this.killRing;
			this.killRing = this.killRing.next;
			holder.next = this.graveyard;
			this.graveyard = holder;
			this.graveyard.killer = current.name;
		}
	}







	/**
 	* Each AssassinNode object represents a single node in a linked list
 	* for a game of Assassin.
 	*/
	private static class AssassinNode {
		public final String name;  // this person's name
		public String killer;      // name of who killed this person (null if alive)
		public AssassinNode next;  // next node in the list (null if none)
    
		/**
		 * Constructs a new node to store the given name and no next node.
		 */
		public AssassinNode(String name) {
			this(name, null);
		}

		/**
		 * Constructs a new node to store the given name and a reference
		 * to the given next node.
		 */
		public AssassinNode(String name, AssassinNode next) {
            this.name = name;
            this.killer = null;
            this.next = next;
        }
    }
}
