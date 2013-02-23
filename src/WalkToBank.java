import org.powerbot.core.script.job.Task;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.wrappers.Tile;


public class WalkToBank extends Node
{
	@Override
	public boolean activate()
	{
		return !Functions.hasEnoughOres(Variables.barToMake) && !Functions.closeEnough(new Tile(3271, 3168, 0), 5);
	}

	@Override
	public void execute()
	{
		System.out.println("WalkToBank");
		
		Walking.newTilePath(Variables.bankToFurnace).reverse().traverse();
		Task.sleep(400, 700);
	}
}
