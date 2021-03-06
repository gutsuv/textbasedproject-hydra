package Model;

import java.util.ArrayList;
import View.View;
/**
 * Class: ObjectPuzzle
 * @author Team Hydra
 * @version 1.0
 * Course: ITEC 3860, Fall 2017
 * Written: November 24
 * 
 * This class stores and holds the functionality of object related puzzles in the game.
 * 
 * Purpose: To contain the methods and attributes needed to create object related puzzles.
 */
public class ObjectPuzzle extends Puzzle
{
	int itemId;
	View view;
	
	/**
     * Method: ObjectPuzzle()
     * ObjectPuzzle constructor
     * @param loadedString String representing String to be loaded
     * @return nothing
     */
	public ObjectPuzzle(String loadedString)
	{
		// loaded from file
		// int puzzleId String name, int itemId, String puzzleDesc, String hint
		try
		{
			String[] splitString = loadedString.split("-_");
			puzzleId = Integer.parseInt(splitString[0]);
			puzzleName = splitString[1];
			itemId = Integer.parseInt(splitString[2]);
			puzzleDesc = splitString[3];
			hint = splitString[4];
		}
		
		catch (Exception E)
		{
			System.out.println("Error");
		}
	}

	/**
     * Method: initialize()
     * 
     * @param none
     * @return nothing
     */
	@Override
	public void initialize()
	{
	}

	/**
     * Method: attemptSolve()
     * 
     * @param answer String value representing the answer
     * @return nothing
     */
	@Override
	public void attemptSolve(String answer)
	{
	}

	/**
     * Method: attemptSolve()
     * Displays whether the item solved the puzzle or not
     * @param itemId int value representing item ID
     * @return nothing
     */
	@Override
	public void attemptSolve(int itemId)
	{
		if (this.itemId == itemId)
		{
			view.line(125);
			view.print("Puzzle passed");
			solved = true;
		}
		
		else
		{
			view.line(125);
			view.print("Item doesn't solve puzzle");
		}

	}

	/**
     * Method: display()
     * Displays puzzle name and description
     * @param none
     * @return nothing
     */
	@Override
	public void display()
	{
		view.line(125);
		view.print(puzzleName);
		view.printString(puzzleDesc);
	}

	/**
     * Method: display()
     * Displays puzzle menu
     * @param items ArrayList representing items
     * @return nothing
     */
	public void display(ArrayList<Item> items)
	{
		try 
		{
			view.line(125);
			view.print(puzzleName);
			view.printString(puzzleDesc);
			view.print("---select item---");
			
			for (int i = 0; i < items.size(); i++)
			{
				view.print(i + ": " + items.get(i));
			}
			
			view.print("hint:");
			view.print("exit:");
		}
		
		catch(NullPointerException E)
		{
			view = new View();
			view.line(125);
			view.print(puzzleName);
			view.printString(puzzleDesc);
			view.print("---select item---");
			
			for (int i = 0; i < items.size(); i++)
			{
				view.print(i + ": " + items.get(i));
			}
			
			view.print("hint:");
			view.print("exit:");
		}
	}
}
