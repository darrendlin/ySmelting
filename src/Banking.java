import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.tab.Inventory;
import org.powerbot.game.api.methods.widget.Bank;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.Tile;


public class Banking extends Node
{
	Condition bankOpen = new Condition()
	{
		@Override
		public boolean validate()
		{
			return Bank.open() && Bank.isOpen() &&
			Widgets.get(Bank.WIDGET_BANK, Bank.WIDGET_BUTTON_DEPOSIT_INVENTORY) != null &&
			Widgets.get(Bank.WIDGET_BANK, Bank.WIDGET_BUTTON_DEPOSIT_INVENTORY).validate();
		}
	};
	
	Condition isDepositAll = new Condition()
	{
		@Override
		public boolean validate()
		{
			return Bank.depositInventory() && Inventory.getCount() == 0;
		}
	};
	
	@Override
	public boolean activate()
	{
		return !Functions.hasEnoughOres(Variables.barToMake) && Functions.closeEnough(new Tile(3271, 3168, 0), 5);
	}
	
	@Override
	public void execute()
	{
		//System.out.println("Banking");
		
		depositAll(false);
		
		if (withdrawOres(Variables.barToMake))
		{
			Task.sleep(500, 1000);
			Bank.close();
		}
		Task.sleep(100, 300);
	}
	
	private void depositAll(boolean close)
	{
		if (Inventory.getCount() == 0)
			return;
		
		if (!Bank.getNearest().isOnScreen())
		{
			Utilities.cameraTurnTo(SceneEntities.getNearest(Bank.BANK_BOOTH_IDS));
			Camera.setPitch(Random.nextInt(22, 72));
		}
		
		if (Utilities.waitFor(bankOpen, 500))
		{
			Task.sleep(250, 500);
			
			if (Utilities.waitFor(isDepositAll, 500))
			{
				Task.sleep(250, 500);
				if (close)
				{
					Bank.close();
				}
			}
		}
	}
	
	private boolean withdrawOres(String barToMake)
	{
		Bank.setWithdrawNoted(false);
		
		if (barToMake.equalsIgnoreCase("bronze"))
		{
			return Bank.withdraw(Variables.copperOreId, 28/2) && Bank.withdraw(Variables.tinOreId, 28/2);
		}
		else if (barToMake.equalsIgnoreCase("iron"))
		{
			return Bank.withdraw(Variables.ironOreId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("silver"))
		{
			return Bank.withdraw(Variables.silverOreId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("steel"))
		{
			return Bank.withdraw(Variables.ironOreId, 9) && Bank.withdraw(Variables.coalId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("gold"))
		{
			return Bank.withdraw(Variables.goldOreId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("mithril"))
		{
			return Bank.withdraw(Variables.mithrilOreId, Bank.Amount.FIVE) && Bank.withdraw(Variables.coalId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("adamant"))
		{
			return Bank.withdraw(Variables.adamantiteOreId, 4) && Bank.withdraw(Variables.coalId, Bank.Amount.ALL);
		}
		else if (barToMake.equalsIgnoreCase("rune"))
		{
			return Bank.withdraw(Variables.mithrilOreId, 3) && Bank.withdraw(Variables.coalId, Bank.Amount.ALL);
		}
		
		return false;
	}
}
