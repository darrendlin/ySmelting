import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import org.powerbot.core.event.events.MessageEvent;
import org.powerbot.core.event.listeners.MessageListener;
import org.powerbot.core.event.listeners.PaintListener;
import org.powerbot.core.script.ActiveScript;
import org.powerbot.core.script.job.Job;
import org.powerbot.core.script.job.state.Node;
import org.powerbot.core.script.job.state.Tree;
import org.powerbot.game.api.Manifest;
import org.powerbot.game.api.methods.Game;
import org.powerbot.game.api.methods.Walking;
import org.powerbot.game.api.methods.input.Mouse;
import org.powerbot.game.api.util.Random;

@Manifest(authors = {"xPropel"}, description = "Smelts your ores for you at Al-Kharid", name = "ySmelting", version = 1.0)

public class ySmelting extends ActiveScript implements PaintListener, MessageListener
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
		return ySmelting.class.getAnnotation(Manifest.class).version();
	}
	
	
	public GUI frmYSmelting;
	private BufferedImage bgImage;
	private BufferedImage cursor;
	
	private final Color color1 = new Color(255, 255, 255);
	private final Color color2 = new Color(0, 0, 0);
	private final BasicStroke stroke1 = new BasicStroke(1);
	
	private void initialize()
	{
		System.out.println("ySmelting is initializing...");
		
		try
		{
			EventQueue.invokeLater(new Runnable()
			{
				public void run()
				{
					try
					{
						frmYSmelting = new GUI();
						frmYSmelting.setResizable(false);
						frmYSmelting.setTitle("ySmelting v" + getVersion());
						frmYSmelting.setBounds(100, 100, 277, 168);
						frmYSmelting.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frmYSmelting.setLocationRelativeTo(null);
						frmYSmelting.setVisible(true);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}
			});
			
			Variables.initialized = true;
			
			provide(new Banking(), new SmeltOres(), new WalkToFurnace(), new WalkToBank());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	
	@Override
	public void onStart()
	{
		System.out.println("Welcome to ySmelting");
		
		initialize();
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
			if (Variables.initialized && Variables.started)
			{
				frmYSmelting.setVisible(false);
				
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
			}
		}
		
		return Random.nextInt(100, 500);
	}
	
	@Override
	public void onRepaint(Graphics g1)
	{
		if (Variables.started)
		{
			if (bgImage == null)
				bgImage = getImage(Variables.bgLink);
			if (cursor == null)
				cursor = getImage(Variables.cursorLink);
			
			Graphics2D g = (Graphics2D)g1;
			
			g.setColor(color1);
			g.fillRect(5, 393, 507, 114);
			g.setColor(color2);
			g.setStroke(stroke1);
			g.drawRect(5, 393, 507, 114);
			g.drawImage(bgImage, 5, 393, null);
			
			g.setColor(color2);
			g.drawOval(109 + (Variables.oreSelectIndex*50), 463, 45, 45);
			
			Functions.printCenter(g, (double)Variables.bars > 999 ? String.format("%.2fk", ((double)Variables.bars)/1000.0) : String.format("%.0f", (double)Variables.bars), 109+(Variables.oreSelectIndex*50), 463+23, 50);
			//g.drawString((double)Variables.bars > 999 ? String.format("%.2fk", ((double)Variables.bars)/1000.0) : String.format("%.0f", (double)Variables.bars), 109+5 + (Variables.oreSelectIndex*50), 463+10);
			
			g.setFont(new Font("Dialog", Font.PLAIN, 15));
			Functions.printRight(g, Variables.timer.toElapsedString(), 475, 415);
			
			Functions.printRight(g, ((double)Variables.profit > 50000 ? String.format("%.2fk", ((double)Variables.profit)/1000.0) : String.format("%.0f", (double)Variables.profit))
					+ "("
					+ (Functions.perHour((double)Variables.profit) > 8500 ? String.format("%.2fk/hr", Functions.perHour((double)Variables.profit)/1000.0) : String.format("%.1f/hr", Functions.perHour((double)Variables.profit)))
					+ ")"
					, 475, 437);
			
			Functions.printRight(g, (Variables.expGained > 8500 ? String.format("%.2fk", Variables.expGained/1000.0) : String.format("%.1f", Variables.expGained)
					+ "("
					+(Functions.perHour(Variables.expGained) > 8500 ? String.format("%.2fk/hr", Functions.perHour(Variables.expGained)/1000.0) : String.format("%.1f/hr", Functions.perHour(Variables.expGained))))
					+ ")"
					, 475, 460);
			
			g.drawImage(cursor, Mouse.getX(), Mouse.getY(), null);
		}
	}
	
	public BufferedImage getImage(String link)
	{
		try 
		{
			BufferedImage image = ImageIO.read(new URL(link));
			System.out.println("Got image from " + link);
			return image;
		} catch(IOException e)
		{
			System.out.println("Error retrieving image: " + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void messageReceived(MessageEvent e)
	{
		String m = e.getMessage();
		if (m.contains("You retrieve a bar of"))
		{
			Variables.bars++;
			Variables.expGained += Variables.barExp;
			Variables.profit += Variables.barProfit;
		}
	}
}
