import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.rev317.min.api.methods.Inventory;
import org.rev317.min.api.methods.Players;
import org.rev317.min.api.methods.SceneObjects;
import org.rev317.min.api.methods.SceneObjects.Option;
import org.rev317.min.api.methods.Skill;
import org.rev317.min.api.wrappers.SceneObject;

/*
 *Author:Dittymasta
 * 
 *Description:
 *The strategy class from the Parabot client API.
 * 
 * */
public class Steal implements Strategy {
	// Declares a variable to keep track of what stall is being stolen from, to
	// calculate what tickets are being stolen
	private int currentStallId;

	// Check method to decide if the user can execute the script
	public boolean activate() {
		// Declare an int to get the players thieving level every time the script checks
		// for prereqs
		int thieveLevel = Skill.THIEVING.getLevel();
		// Checks to see what thieving stall is optimal based off of player level
		if (thieveLevel < 50 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
			currentStallId = 4874;

			return true;
		} // Checks to see if user can steal from stall level 50-69
		else if (thieveLevel >= 50 && thieveLevel < 70 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
			currentStallId = 4875;
			return true;
		} // Checks to see if user can steal from stall level 70-84
		else if (thieveLevel >= 70 && thieveLevel < 85 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
			currentStallId = 4876;
			return true;
		} // Checks to see if user can steal from stalls level 85-99
		else if (thieveLevel >= 85 && thieveLevel < 99 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
			currentStallId = 4877;
			return true;

		} // Checks for 99 thieving
		else if (thieveLevel >= 99 && !Players.getMyPlayer().isInCombat() && !Inventory.isFull()) {
			currentStallId = 4878;
			return true;
		}
		return false;
	}

	public void execute() {
		// Finds the Object with the ID of the stall id's set in activation
		for (SceneObject i : SceneObjects.getNearest(currentStallId)) {
			System.out.println("Stealing from stall...");
			// Uses the right click option "Steal From" to interact with the object
			i.interact(Option.STEAL_FROM);
			Time.sleep(1000 * 10);

		}
	}

}
