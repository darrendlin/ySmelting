import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.node.SceneEntities;
import org.powerbot.game.api.wrappers.node.SceneObject;


public class WalkToFurnace extends Node
{
	@Override
	public boolean activate()
	{
		SceneObject furnace = SceneEntities.getNearest(Variables.furnaceIds);
		if (furnace == null || !furnace.validate())
			return false;
		
		return Functions.hasEnoughOres(Variables.barToMake) && !Functions.closeEnough(furnace.getLocation(), 5);
	}

	@Override
	public void execute()
	{
		System.out.println("WalkToFurnace");
		
		Walking.newTilePath(Variables.bankToFurnace).traverse();
		Task.sleep(400, 700);
	}
}
