import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Widgets;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.methods.widget.Camera;
import org.powerbot.game.api.util.Random;
import org.powerbot.game.api.wrappers.node.SceneObject;
import org.powerbot.game.api.wrappers.widget.WidgetChild;


public class SmeltOres extends Node
{
	@Override
	public boolean activate()
	{
		SceneObject furnace = SceneEntities.getNearest(Variables.furnaceIds);
		if (furnace == null || !furnace.validate())
			return false;
		
		return Functions.hasEnoughOres(Variables.barToMake) && Functions.closeEnough(furnace.getLocation(), 5);
	}
	
	@Override
	public void execute()
	{
		System.out.println("SmeltOres");
		
		SceneObject furnace = SceneEntities.getNearest(Variables.furnaceIds);
		
		if (furnace != null)
		{
			if (!furnace.isOnScreen())
			{
				Camera.turnTo(furnace, Random.nextInt(-10, 10));
			}
			
			furnace.interact("Smelt");
			
			WidgetChild smeltButton = Widgets.get(1370, 34);
			
			if (Utilities.waitFor(new Condition()
			{
				@Override
				public boolean validate()
				{
					return Widgets.get(1370, 34) != null && Widgets.get(1370, 34).validate();
				}
				
			}, 2500))
				
				if (smeltButton != null && smeltButton.validate())
				{
					//System.out.println("clicking button");
					smeltButton.click(true);
				}
			
			if (Utilities.waitFor(new Condition()
			{
				@Override
				public boolean validate()
				{
					//System.out.println("wtf is it doing this time?");
					return Players.getLocal().getAnimation() != 3243 && !Functions.hasEnoughOres(Variables.barToMake);
				}
				
			}, 25000))
			{
				Task.sleep(150, 1000);
			}
		}
		Task.sleep(500, 750);
	}
}
