import org.parabot.environment.scripts.Script;

import java.util.ArrayList;

import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.framework.Strategy;

import org.rev317.min.api.methods.Inventory;

import org.parabot.environment.scripts.ScriptManifest;

@ScriptManifest(author = "Dittymasta", category = Category.THIEVING, description = "Steals from stalls according to your characters level. Note:Calculates estimated total from before and after ticket amounts, depositing tickets into ur pouch will cause this to not function properly\nAlso make sure you are at ::home", name = "Steal", servers = {
		"Dreamscape" }, version = 1.1)

public class Main extends Script {
	// Creates variables that will be used to calculate total tickets stolen
	private int millStackInitial = 0;
	private int billStackInitial = 0;
	private int millStackFinal;
	private int billStackFinal;
	private final ArrayList<Strategy> strategies = new ArrayList<Strategy>();

	@Override // All the things the script will do when it is first launched
	public boolean onExecute() {
		// Not 100% sure, but I think this adds the methods for the script in the
		// Parabot client interface
		strategies.add(new Steal());
		provide(strategies);

		System.out.println("Checking for tickets in inventory...");
		// If user has either of the tickets in the inventory, it will save this numbeer
		if (Inventory.contains(5022)) {
			billStackInitial = Inventory.getItem(5022).getStackSize();

		}
		if (Inventory.contains(5023)) {

			millStackInitial = Inventory.getItem(5023).getStackSize();

		}

		return true;
	}

	@Override
	public void onFinish() {
		// Gets the final amount of tickets you have and caclulates the difference from
		// initial to final
		if (Inventory.contains(5022)) {
			billStackFinal = Inventory.getItem(5022).getStackSize() - billStackInitial;
		}
		if (Inventory.contains(5023)) {
			millStackFinal = Inventory.getItem(5023).getStackSize() - millStackInitial;
		}

		System.out.println("Bill Ticket Count: " + billStackFinal + "\nMill Ticket Count:" + millStackFinal);
	}
}
