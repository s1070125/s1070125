package dice;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.Color;
import javax.swing.JOptionPane;

public class GUI extends JFrame
{
	private static final long serialVersionUID = 1l;

	private String[]    bStatus     = { "Roll", "Roll", "Rematch" };
	private String[]    filename    = { "1.png", "2.png", "3.png", "4.png", "5.png", "6.png" };
	private Icon[]      dice        = { new ImageIcon(getClass().getResource(filename[0])),
			new ImageIcon(getClass().getResource(filename[1])),
			new ImageIcon(getClass().getResource(filename[2])),
			new ImageIcon(getClass().getResource(filename[3])),
			new ImageIcon(getClass().getResource(filename[4])),
			new ImageIcon(getClass().getResource(filename[5]))  };

	private String[]    Player = { "Player 1", "Player 2" };

	public int bState = 0;

	public JLabel die1;
	public JLabel die2;
	public JLabel die3;
	public JLabel die4;
	public JLabel die5;
	public JLabel die6;

	public JLabel sStatusBar;

	public JButton bButton;

	public Component s1;
	public Component s2;

	public int p1Total = 0, p2Total = 0;

	public GUI()
	{
		super("Dice Game");
		Container c = getContentPane();
		c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
		c.setBackground(new Color(0, 115, 0));

		Player[0] = JOptionPane.showInputDialog("Enter Player 1's Name");
		Player[1] = JOptionPane.showInputDialog("Enter Player 2's Name");

		//Player 1 (Label)
		JLabel Player1 = new JLabel(Player[0]);
		Player1.setForeground(Color.WHITE);
		Player1.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Player 2 (Label)
		JLabel Player2 = new JLabel(Player[1]);
		Player2.setForeground(Color.WHITE);
		Player2.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Roll / New Game Button
		bButton = new JButton(bStatus[0]);
		bButton.setAlignmentX(Component.CENTER_ALIGNMENT);

		//Panel
		JPanel p = new JPanel();
		p.setAlignmentY(BOTTOM_ALIGNMENT);

		//Green Panels
		JPanel gp = new JPanel();
		gp.setBackground(new Color(0, 115, 0));

		JPanel gp2 = new JPanel();
		gp2.setBackground(new Color(0, 115, 0));

		//Status Bar (Label)
		sStatusBar = new JLabel(Player[0] + ", it's your turn.");
		sStatusBar.setAlignmentX(Component.LEFT_ALIGNMENT);

		die1 = new JLabel();
		die1.setAlignmentX(Component.CENTER_ALIGNMENT);
		die2 = new JLabel();
		die2.setAlignmentX(Component.CENTER_ALIGNMENT);
		die3 = new JLabel();
		die3.setAlignmentX(Component.CENTER_ALIGNMENT);
		die4 = new JLabel();
		die4.setAlignmentX(Component.CENTER_ALIGNMENT);
		die5 = new JLabel();
		die5.setAlignmentX(Component.CENTER_ALIGNMENT);
		die6 = new JLabel();
		die6.setAlignmentX(Component.CENTER_ALIGNMENT);

		die1.setIcon(dice[0]);
		die2.setIcon(dice[1]);
		die3.setIcon(dice[2]);
		die4.setIcon(dice[3]);
		die5.setIcon(dice[4]);
		die6.setIcon(dice[5]);

		s1 = Box.createVerticalStrut(31);
		s2 = Box.createVerticalStrut(31);

		c.add(Player1);
		c.add(gp);
		gp.add(s1);
		gp.add(die1);
		gp.add(die2);
		gp.add(die3);
		die1.setVisible(false);
		die2.setVisible(false);
		die3.setVisible(false);

		c.add(Player2);
		c.add(gp);
		gp.add(s2);
		gp.add(die4);
		gp.add(die5);
		gp.add(die6);		
		die4.setVisible(false);
		die5.setVisible(false);
		die6.setVisible(false);

		c.add(bButton);
		c.add(Box.createVerticalStrut(20));

		c.add(p);
		p.add(sStatusBar);

		bButton.addActionListener(

				new ActionListener()
				{
					public void actionPerformed(ActionEvent event)
					{
						bButtonActionPerformed(event);
					}

					private void bButtonActionPerformed(ActionEvent event) {
						// TODO Auto-generated method stub
						Random randomizer = new Random();
						if (bState == 0)
						{
							int n1 = 0, n2 = 0, n3 = 0;
							n1 = randomizer.nextInt(6);
							n2 = randomizer.nextInt(6);
							n3 = randomizer.nextInt(6);
							p1Total = n1+n2+n3;

							die1.setIcon(dice[n1]);
							die2.setIcon(dice[n2]);
							die3.setIcon(dice[n3]);

							s1.setVisible(false);
							die1.setVisible(true);
							die2.setVisible(true);
							die3.setVisible(true);
							sStatusBar.setText(Player[1] + ", it's your turn.");
							bButton.setText(bStatus[1]);
						}
						if (bState == 1)
						{
							int n1 = 0, n2 = 0, n3 = 0;
							n1 = randomizer.nextInt(6);
							n2 = randomizer.nextInt(6);
							n3 = randomizer.nextInt(6);
							p2Total = n1+n2+n3;

							die4.setIcon(dice[n1]);
							die5.setIcon(dice[n2]);
							die6.setIcon(dice[n3]);

							s2.setVisible(false);
							die4.setVisible(true);
							die5.setVisible(true);
							die6.setVisible(true);
							if (p1Total > p2Total)
								sStatusBar.setText(Player[0] + ", won!  Play again?");
							if (p1Total < p2Total)
								sStatusBar.setText(Player[1] + ", won!  Play again?");
							if (p1Total == p2Total)
								sStatusBar.setText("Draw, Play again?");
							bButton.setText(bStatus[2]);
						}
						bState++;
						if (bState == 3 )
						{
							die1.setVisible(false);
							die2.setVisible(false);
							die3.setVisible(false);
							die4.setVisible(false);
							die5.setVisible(false);
							die6.setVisible(false);
							s1.setVisible(true);
							s2.setVisible(true);
							sStatusBar.setText(Player[0] + ", it's your turn!");
							bButton.setText(bStatus[0]);
							bState = 0;
						}

					}


				}
				);
	}


}