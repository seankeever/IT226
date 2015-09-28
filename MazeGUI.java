package ilstu.edu;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.SwingUtilities;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.Canvas;
import java.awt.Color;


public class MazeGUI extends JFrame
{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
					MazeGUI frame = new MazeGUI();
					frame.setVisible(true);
			}
		});
	}
	
	public MazeGUI()
	{
		DrawPanel draw = new DrawPanel();
		JScrollPane scrollPane = new JScrollPane(draw);
		add(scrollPane);
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 500);
		setTitle("Maze");
	}

}

class DrawPanel extends JPanel
{
	MazeHelper mazeHelp = new MazeHelper();
	int height = mazeHelp.height;
	int width = mazeHelp.width;
	
	public void drawMaze(Graphics g)
	{
		Graphics2D graphics = (Graphics2D) g;
		
		String binary;
		int tileSize = 20;
		graphics.setColor(Color.GREEN);
		graphics.fillOval((mazeHelp.startX * tileSize), (mazeHelp.startY * tileSize), tileSize, tileSize);
		graphics.setColor(Color.RED);
		graphics.fillOval((mazeHelp.endX * tileSize), (mazeHelp.endY * tileSize), tileSize, tileSize);
		graphics.setColor(Color.BLACK);
		
		for(int i = 0; i < height; i++)
		{
			for(int j = 0; j < width; j++)
			{
				binary = mazeHelp.solveBinary((i*width)+j);
				if (binary.charAt(0) == '1')
				{
					graphics.drawLine((j*tileSize), (i*tileSize), (j*tileSize), ((i*tileSize)+tileSize));
				}
				if (binary.charAt(1) == '1')
				{
					graphics.drawLine((j*tileSize), (i*tileSize), ((j*tileSize)+tileSize), (i*tileSize));
				}
				if (j == (width-1))
				{
					if (binary.charAt(2) == '1')
					{
						graphics.drawLine(((i*tileSize)+tileSize), (i*tileSize), ((i*tileSize)+tileSize), ((i*tileSize)+tileSize));
					}
				}
				if (i == (height-1))
				{
					if (binary.charAt(3) == '1')
					{
						graphics.drawLine((j*tileSize), ((i*tileSize)+tileSize), ((i*tileSize)+tileSize), ((i*tileSize)+tileSize));
					}
				}
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawMaze(g);
	}


}
