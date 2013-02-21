import org.powerbot.game.api.methods.Calculations;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.wrappers.Locatable;


public class Functions
{
	public static boolean hasEnoughOres(String barToMake)
	{
		if (barToMake.equalsIgnoreCase("bronze"))
		{
			return Inventory.getCount(Variables.copperOreId) >= 1 && Inventory.getCount(Variables.tinOreId) >= 1;
		}
		else if (barToMake.equalsIgnoreCase("iron"))
		{
			return Inventory.getCount(Variables.ironOreId) >= 1;
		}
		else if (barToMake.equalsIgnoreCase("silver"))
		{
			return Inventory.getCount(Variables.silverOreId) >= 1;
		}
		else if (barToMake.equalsIgnoreCase("steel"))
		{
			return Inventory.getCount(Variables.ironOreId) >= 1 && Inventory.getCount(Variables.coalId) >= 2;
		}
		else if (barToMake.equalsIgnoreCase("gold"))
		{
			return Inventory.getCount(Variables.goldOreId) >=1;
		}
		else if (barToMake.equalsIgnoreCase("mithril"))
		{
			return Inventory.getCount(Variables.mithrilOreId) >= 1 && Inventory.getCount(Variables.coalId) >= 4;
		}
		else if (barToMake.equalsIgnoreCase("adamant"))
		{
			return Inventory.getCount(Variables.adamantiteOreId) >= 1 && Inventory.getCount(Variables.coalId) >= 6;
		}
		else if (barToMake.equalsIgnoreCase("rune"))
		{
			return Inventory.getCount(Variables.runiteOreId) >= 1 && Inventory.getCount(Variables.coalId) >= 8;
		}
		
		return false;
	}
	
	public static boolean closeEnough(Locatable locatable, int distance)
	{
		if (locatable == null)
		{
			return false;
		}
		
		return Calculations.distanceTo(locatable.getLocation()) <= distance;
	}
}
