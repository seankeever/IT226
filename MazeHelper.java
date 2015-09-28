package ilstu.edu;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MazeHelper 
{
	public static int height;
	public static int width;
	public static int startX, startY, endX, endY;
	public static int[] mazeValues;
	public static String[] paths;
	
	public MazeHelper()
	{
			readFromFiles();
	}
	
	public void readFromFiles()
	{
		FileReader maze = null;
		FileReader path = null;
		try
		{
			maze = new FileReader("maze-20x20.txt");
			path = new FileReader("maze-20x20-paths.txt");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File ain't found");
			System.exit(0);
		}
		
		Scanner mazeFile = new Scanner(maze);
		Scanner pathFile = new Scanner(path);
		readMazeFile(mazeFile);
		readPathFile(pathFile);
	}
	
	public static boolean validArgument(String arg1, String arg3)
	{
		if(((arg1.equals("-m")) && (arg3.equals("-p"))) || ((arg1.equals("-p")) && (arg3.equals("-m"))))
		{
			return true;
		}
		
		return false;
	}
	
	public static boolean validCurrentPosition(int row, int column, int width, int height)
	{
		if(row < 0 || column < 0 || row >= height || column >= width)
		{
			return false;
		}
		return true;
	}
	
	
	public static void readMazeFile(Scanner mazeFile)
	{
		height = mazeFile.nextInt();
		mazeFile.nextLine();
		width = mazeFile.nextInt();
		mazeFile.nextLine(); 
		startY = mazeFile.nextInt();
		startX = mazeFile.nextInt();
		endY = mazeFile.nextInt();
		endX = mazeFile.nextInt();
		mazeFile.nextLine();
		
		mazeValues = new int[height*width];
		int index = 0;
		for(int i =0; i <height; i++)
		{
			for(int j =0; j < width; j++)
			{
				mazeValues[index] = mazeFile.nextInt();
				index++;
			}
			mazeFile.nextLine();
		}
	}
	
	public static void readPathFile(Scanner pathFile)
	{
		paths = new String[height*width];
		int index = 0;
		while(pathFile.hasNextLine())
		{
			paths[index] = pathFile.nextLine();
			index++;
		}
	
	}
	
	public static String getNextPosition(int row, int column)
	{
		StringTokenizer stk;
		int index = 0;
		int nextRow, nextColumn;
		while(index<paths.length)
		{
			stk = new StringTokenizer(paths[index], " ");
			if(row == Integer.parseInt(stk.nextToken()) && column  == Integer.parseInt(stk.nextToken()))
			{	
				nextRow = Integer.parseInt(stk.nextToken());
				nextColumn = Integer.parseInt(stk.nextToken());
				if (nextColumn != -1)
					return new String("The next position should be " + nextRow + " " + nextColumn + ".\n");
				else
					return new String("You're at the end of the maze!\n");
			}
			else
				index++;
		}
		//ideally, this is unreachable
		return new String ("There is no square in the maze with that position!");
	}
	
	public static void solveMaze(int row, int column)
	{
		StringTokenizer stk;
		int index;
		while (column != -1)
		{
			index = 0;
			while(index<paths.length)
			{
				stk = new StringTokenizer(paths[index], " ");
				if(row == Integer.parseInt(stk.nextToken()) && column  == Integer.parseInt(stk.nextToken()))
				{	
					row = Integer.parseInt(stk.nextToken());
					column = Integer.parseInt(stk.nextToken());
					if (column != -1)
						System.out.println(row + " " + column);
					else
						System.out.println("exit");
				}
				else
					index++;
			}
		}
		return;
	}
	public static void validStep(int row, int column, int rowTo, int columnTo)
	{
		int currentSpot = mazeValues[(row*height)+column];
		String binary = solveBinary(currentSpot);
		
		if(row == rowTo && column == columnTo +1)
		{
			if(binary.charAt(0) == '1')
				System.out.println("You can not make this move.");
			else
				System.out.println("You can make this move.");
				
		}
		else if(row == rowTo+1 && column == columnTo)
		{
			if(binary.charAt(1) == '1')
				System.out.println("You can not make this move.");
			else
				System.out.println("You can make this move.");
		}
		else if(row == rowTo && column == columnTo-1)
		{
			if(binary.charAt(2) == '1')
				System.out.println("You can not make this move.");
			else
				System.out.println("You can make this move.");
		}
		else if(row == rowTo-1 && column == columnTo)
		{
			if(binary.charAt(3) == '1')
				System.out.println("You can not make this move.");
			else
				System.out.println("You can make this move.");
		}
		else
			System.out.println("That is not a valid step: the two spots are not adjacent.");
	}
	
	public static String solveBinary(int currentSpot)
	{
		String binary = new String();
		int value = mazeValues[currentSpot];
		if(value/8 == 1)
		{
			binary+="1";
			value = value-8;
		}
		else
			binary+="0";
		
		if(value/4 == 1)
		{
			binary+="1";
			value = value-4;
		}
		else
			binary+="0";
		
		if(value/2 == 1)
		{
			binary+="1";
			value = value-2;
		}
		else
			binary+="0";
		
		if(value/1 == 1)
		{
			binary+="1";
		}
		else
			binary+="0";
		
		return binary;
	}

	public static void main(String[] args) throws FileNotFoundException
	{
		if(!validArgument(args[0], args[2]))
		{
			System.out.println("Invalid arguments. Please try again.");
			System.exit(1);
		}
		
		FileReader maze;
		FileReader path;
		if(args[0].equals("-m"))
		{
			maze = new FileReader(args[1]);
			path = new FileReader(args[3]);
		}
		else
		{
			maze = new FileReader(args[3]);
			path = new FileReader(args[1]);
		}
		Scanner mazeFile = new Scanner(maze);
		Scanner pathFile = new Scanner(path);
		readMazeFile(mazeFile);
		readPathFile(pathFile);
		Scanner kb = new Scanner(System.in);
		
		int input = 0;
		int row = 0;
		int column = 0;
		int rowTo = 0;
		int columnTo = 0;
		while(input != 4)
		{
			System.out.println("Please type which option number you want to do.");
			System.out.println("Option 1: Get a hint.\nOption 2: Check if a step is valid.\nOption 3: Solve maze.\nOption 4: Exit.");
			input = kb.nextInt();
			switch(input)
			{
			case 1:
				System.out.println("Please enter what current position you are in: (row column)");
				row = kb.nextInt();
				column = kb.nextInt();
				while(!validCurrentPosition(row, column, height, width))
				{
					System.out.println("Please enter a valid current position. (row column)");
					row = kb.nextInt();
					column = kb.nextInt();
				}
				System.out.println(getNextPosition(row, column));
				break;
			case 2:
				System.out.println("Please enter what current position you are in: (row column)");
				row = kb.nextInt();
				column = kb.nextInt();
				while(!validCurrentPosition(row, column, height, width))
				{
					System.out.println("Please enter a valid current position. (row column)");
					row = kb.nextInt();
					column = kb.nextInt();
				}
				
				System.out.println("Please enter what position you would like to go to: (row column)");
				rowTo = kb.nextInt();
				columnTo = kb.nextInt();
				while(!validCurrentPosition(rowTo, columnTo, height, width))
				{
					System.out.println("Please enter a valid current position. (row column)");
					rowTo = kb.nextInt();
					columnTo = kb.nextInt();
				}
				validStep(row, column, rowTo, columnTo);
				break;
			case 3:
				System.out.println("Please enter what current position you are in: (row column)");
				row = kb.nextInt();
				column = kb.nextInt();
				while(!validCurrentPosition(row, column, height, width))
				{
					System.out.println("Please enter a valid current position. (row column)");
					row = kb.nextInt();
					column = kb.nextInt();
				}
				solveMaze(row, column);
				break;
			case 4:
				break;
			default:
				System.out.println("Please type a valid option number.");
				break;
			}
			
		}
		
		
		
		
		kb.close();
	}
}
 