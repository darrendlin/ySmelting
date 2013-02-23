import java.awt.Graphics2D;

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
	
	public static void printRight(Graphics2D g, String s, int x, int y)
	{
		g.drawString(s, x - g.getFontMetrics().stringWidth(s), y);
	}
	

	/**
	 * @param x Center location
	 */
	public static void printCenter(Graphics2D g, String s, int x, int y, int width)
	{
		int len = (int)g.getFontMetrics().getStringBounds(s, g).getWidth();  
		int start = width/2 - len/2;  
		g.drawString(s, start + x, y);
	}
	public static double perHour(double gained) //money or exp etc
	{
		return gained / ((double)Variables.timer.getElapsed()/1000.0/3600.0);
	} 
}
