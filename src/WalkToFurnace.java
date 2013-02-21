import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;


public class WalkToFurnace extends Node
{
	@Override
	public boolean activate()
	{
		return Functions.hasEnoughOres(Variables.barToMake) && !Functions.closeEnough(new Tile(3274, 3190, 0), 5);
	}

	@Override
	public void execute()
	{
		//System.out.println("WalkToFurnace");
		Walking.newTilePath(Variables.bankToFurnace).traverse();
		Task.sleep(700, 1000);
	}
}
