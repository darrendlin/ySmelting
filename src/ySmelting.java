import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.interactive.Players;
import org.powerbot.game.api.util.Random;

@Manifest(authors = { "xPropel" }, description = "Smelts your ores for you at Al-Kharid", name = "ySmelting", version = 1.0)

public class ySmelting extends ActiveScript
{
	private final List<Node> jobsCollection = Collections.synchronizedList(new ArrayList<Node>());
	private Tree jobContainer = null;
	
	public synchronized final void provide(final Node... jobs)
	{
		for (final Node job : jobs)
		{
			if(!jobsCollection.contains(job))
			{
				jobsCollection.add(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
	}
	
	public synchronized final void revoke(final Node... jobs)
	{
		for (final Node job : jobs)
		{
			if(jobsCollection.contains(job))
			{
				jobsCollection.remove(job);
			}
		}
		jobContainer = new Tree(jobsCollection.toArray(new Node[jobsCollection.size()]));
	}
	
	public final void submit(final Job... jobs)
	{
		for (final Job job : jobs)
		{
			getContainer().submit(job);
		}
	}
	
	public static double getVersion()
	{
		return 1.0;
	}
	
	
	
	
	
	@Override
	public void onStart()
	{
		System.out.println("Welcome to ySmelting");
		
		provide(new Banking(), new SmeltOres(), new WalkToFurnace(), new WalkToBank());
	}
	
	@Override
	public void onStop()
	{
		System.out.println("Thanks for using ySmelting");
	}
	
	@Override
	public int loop()
	{
		if (Game.isLoggedIn())
		{
			if (jobContainer != null)
			{
				final Node job = jobContainer.state();
				if (job != null)
				{
					jobContainer.set(job);
					getContainer().submit(job);
					job.join();
				}
			}
			
			if (Walking.getEnergy() > 40 && !Walking.isRunEnabled())
			{
				Walking.setRun(true);
				System.out.println("set run");
			}
			
			if (Players.getLocal().getInteracting() != null)
				System.out.println("what you doing " + Players.getLocal().getInteracting().getName());
		}
		
		return Random.nextInt(100, 500);
	}
}
