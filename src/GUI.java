import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.powerbot.game.api.util.net.GeItem;


public class GUI extends JFrame implements WindowListener
{
	private static final long serialVersionUID = 1L;
	private JPanel ySmeltingPanel = new JPanel();
	
	private JTabbedPane tabbedPane;
	private JPanel panel1Smelt;
	private JLabel lblLocation;
	private JComboBox<String> comboBoxLocation;
	private JComboBox<String> comboBoxOre;
	private JLabel lblSmelt;
	private JPanel paneAntiban;
	private JCheckBox chckbxAutoResponder;
	private JCheckBox chckbxCameraMovement;
	private JButton btnStart;
	
	/**
	 * Create the application.
	 */
	public GUI()
	{
		initialize();
	}
	
	public void start()
	{
		Variables.oreSelectIndex = comboBoxOre.getSelectedIndex();
		Variables.locationSelectIndex = comboBoxLocation.getSelectedIndex();
		
		Variables.barToMake = String.valueOf(comboBoxOre.getSelectedItem());
		Variables.location = Variables.locations[Variables.locationSelectIndex];
		Variables.bankToFurnace = Variables.pathTiles[Variables.locationSelectIndex];
		
		Variables.barExp = Variables.expList[Variables.oreSelectIndex];
		Variables.barProfit = GeItem.lookup(Variables.barIdList[Variables.oreSelectIndex]).getPrice();
		
		Variables.timer.reset();
		Variables.started = true;
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		ySmeltingPanel = new JPanel();
		
		ySmeltingPanel.setLayout(new BorderLayout(0, 0));
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		ySmeltingPanel.add(tabbedPane);
		
		panel1Smelt = new JPanel();
		tabbedPane.addTab("Smelt Ores", null, panel1Smelt, null);
		panel1Smelt.setLayout(null);
		
		lblLocation = new JLabel("Location:");
		lblLocation.setBounds(58, 14, 46, 14);
		panel1Smelt.add(lblLocation);
		
		comboBoxLocation = new JComboBox<String>();
		comboBoxLocation.setToolTipText("Choose bank location");
		comboBoxLocation.setModel(new DefaultComboBoxModel<String>(new String[] {"Al Kharid", "Edgeville"}));
		comboBoxLocation.setBounds(115, 11, 73, 20);
		panel1Smelt.add(comboBoxLocation);
		
		lblSmelt = new JLabel("Smelt:");
		lblSmelt.setBounds(58, 39, 30, 14);
		panel1Smelt.add(lblSmelt);
		
		comboBoxOre = new JComboBox<String>();
		comboBoxOre.setBounds(115, 36, 73, 20);
		comboBoxOre.setToolTipText("Bar to smelt");
		comboBoxOre.setModel(new DefaultComboBoxModel<String>(new String[] {"Bronze", "Iron", "Silver", "Steel", "Gold", "Mithril", "Adamant", "Rune"}));
		panel1Smelt.add(comboBoxOre);
		
		paneAntiban = new JPanel();
		tabbedPane.addTab("Antiban", null, paneAntiban, null);
		paneAntiban.setLayout(null);
		
		chckbxAutoResponder = new JCheckBox("Auto responder");
		chckbxAutoResponder.setEnabled(false);
		chckbxAutoResponder.setToolTipText("Automatically responds to common user chats (coming soon!)");
		chckbxAutoResponder.setBounds(77, 10, 101, 23);
		paneAntiban.add(chckbxAutoResponder);
		
		chckbxCameraMovement = new JCheckBox("Camera movement");
		chckbxCameraMovement.setToolTipText("Basic antiban technique");
		chckbxCameraMovement.setBounds(67, 43, 121, 23);
		paneAntiban.add(chckbxCameraMovement);
		
		btnStart = new JButton("Start!");
		btnStart.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				start();
			}
		});
		btnStart.setToolTipText("Press me whenever to start smelting");
		ySmeltingPanel.add(btnStart, BorderLayout.SOUTH);
		
		getContentPane().add(ySmeltingPanel);
	}

	@Override
	public void windowActivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		// TODO Auto-generated method stub
		
	}
}
