import java.util.Scanner;

public class Controller
{
	//Scanner control = new Scanner(System.in);
	Scanner control;
	ObjectHolder model;
	View menuView = new View();

	public Controller(ObjectHolder main) 
	{
		model = main;
		while (true)
		{
			determine(control.nextLine());
		}
	}

	public Controller(ObjectHolder main, Scanner control)
	{
		model = main;
		this.control = control;
		while (true)
		{
			determine(control.nextLine());
		}
	}
	
	public void controllerEnemy()
	{
		while (true)
		{
			menuView.print("Health: " + model.getUser().getHealth() + "/" + model.getUser().getMaxHealth());
			if (model.getCurrentRoom().getEnemy() != null)
			{
				model.getCurrentRoom().getEnemy().display();
			}
			if (determineEnemy(control.next()) == true)
			{
				break;
			}
		}
	}

	public void controllerItem()
	{
		while (true)
		{
			model.getUser().displayInventory();
			if (determineItem(control.next()) == true)
			{
				break;
			}
		}
	}

	public void controllerItem(int i)
	{
		model.getUser().getItem(i).displayCommands();
		determineItem(control.next(), i);
	}

	// Puzzle controller
	public void controllerObjectPuzzle()
	{
		String itemNumber;
		while (true)
		{
			((ObjectPuzzle) model.getCurrentRoom().getPuzzle()).display(model.getUser().getInventory());
			itemNumber = control.nextLine();
			if (itemNumber.equalsIgnoreCase("exit"))
			{
				break;
			}
			if (itemNumber.equalsIgnoreCase("hint"))
			{
				model.getCurrentRoom().getPuzzle().printHint();
			}
			if (itemNumber.matches("[0-9]+"))
			{
				model.getCurrentRoom().getPuzzle()
						.attemptSolve(model.getUser().getItem(Integer.parseInt(itemNumber)).getItemId());
			}
			if (model.getCurrentRoom().getPuzzle().isSolved())
			{
				model.getCurrentRoom().setPuzzle(null);
				model.user.removeItem(Integer.parseInt(itemNumber));
				break;
			}
		}
	}

	public void controllerPuzzleDebuff()
	{
		String answer;
		while (true)
		{
			model.getCurrentRoom().getPuzzle().display();
			answer = control.nextLine();
			if (answer.equalsIgnoreCase("exit"))
			{
				break;
			}

			if(model.getCurrentRoom().getPuzzle().isSolved())
			{
				 model.getCurrentRoom().setPuzzle(null);
				 break;
			}
			if (answer.equalsIgnoreCase("hint"))
			{
				model.getCurrentRoom().getPuzzle().printHint();
			}
			if(answer.equalsIgnoreCase("drink"))
			{
				((PuzzleDebuff) model.getCurrentRoom().getPuzzle()).printPoison();
				model.getUser().setPoisoned(true);
				model.getUser().updateStats();
			}
		}
	}

	public void controllerPuzzleNumber()
	{
		String answer;
		while (true)
		{
			model.getCurrentRoom().getPuzzle().display();
			answer = control.nextLine();
			if (answer.equalsIgnoreCase("exit"))
			{
				break;
			}
			if (answer.equalsIgnoreCase("hint"))
			{
				model.getCurrentRoom().getPuzzle().printHint();
			}
			if (answer.matches("[0-9]+"))
			{
				model.getCurrentRoom().getPuzzle().attemptSolve(answer);
				if (!model.getCurrentRoom().getPuzzle().isSolved())
				{
					model.getUser().receiveDamage(((PuzzleNumber) model.getCurrentRoom().getPuzzle()).getDamage());
				}
			}
			if (model.getCurrentRoom().getPuzzle().isSolved())
			{
				model.getCurrentRoom().setPuzzle(null);
				break;
			}
		}
	}

	public void controllerPuzzleRiddle()
	{
		String answer;
		while (true)
		{
			model.getCurrentRoom().getPuzzle().display();
			answer = control.nextLine();
			if (answer.equalsIgnoreCase("exit"))
			{
				break;
			} 
			else if (answer.equalsIgnoreCase("hint"))
			{
				model.getCurrentRoom().getPuzzle().printHint();
			} 
			else
			{
				model.getCurrentRoom().getPuzzle().attemptSolve(answer);
			}
			if (model.getCurrentRoom().getPuzzle().isSolved())
			{
				model.getCurrentRoom().setPuzzle(null);
				break;
			}
		}
	}

	public void controllerDoorPuzzle()
	{
		String itemNumber;
		while (true)
		{
			((DoorPuzzle) model.getCurrentRoom().getPuzzle()).display(model.getUser().getInventory());
			itemNumber = control.nextLine();
			if (itemNumber.equalsIgnoreCase("exit"))
			{
				break;
			}
			if (itemNumber.equalsIgnoreCase("hint"))
			{
				model.getCurrentRoom().getPuzzle().printHint();
			}
			if (itemNumber.matches("[0-9]+"))
			{
				try {
					model.getCurrentRoom().getPuzzle()
						.attemptSolve(model.getUser().getItem(Integer.parseInt(itemNumber)).getItemId());
				}
				catch(Exception E)
				{
					
				}
			}
			if (model.getCurrentRoom().getPuzzle().isSolved())
			{
				model.getCurrentRoom().setPuzzle(null);
				model.user.getInventory().remove(Integer.parseInt(itemNumber));
				break;
			}
		}
	}

	public void controllerWordPuzzle()
	{
		String answer;
		while (true)
		{
			model.getCurrentRoom().getPuzzle().display();
			answer = control.nextLine();
			if (answer.equalsIgnoreCase("exit"))
			{
				break;
			}
			model.getCurrentRoom().getPuzzle().attemptSolve(answer);
			if (model.getCurrentRoom().getPuzzle().isSolved())
			{
				model.getCurrentRoom().addItem(((WordPuzzle) model.getCurrentRoom().getPuzzle()).getItem());
				model.getCurrentRoom().setPuzzle(null);
				break;
			}
		}
	}
	
	private void controllerStats()
	{
		String input;
		while(true)
		{
			model.getUser().displayStats();
			model.getUser().displayEquippedItems();
			menuView.print("exit:");
			input = control.nextLine();
			//if(input.equalsIgnoreCase("exit"))
			if(true)
			{
				try
				{
					model.getCurrentRoom().display();
				}
				catch(Exception E)
				{
					
				}
				break;
			}
		}
	}
	
	private void controllerHelp()
	{
		String input;
		
		while(true)
		{
			menuView.line(125);
			menuView.print("To view your stats (health, min & max attack, defense, accuracy, equipped items), enter 'stats' ");
			menuView.print("To view your inventory (items tht have been picked up), enter 'inventory/inv' ");
			menuView.print("To navigate (corresponding letters or numbers near descriptions), enter 'n, s, w, e, 1, 2, 3, 4, 5, etc.'");
			menuView.print("To view an item (whilst viewing inventory) , enter corresponding number of item '0-9'");
			menuView.print("To use an item (whilst viewing item) , enter 'use'");
			menuView.print("To equip an item (whilst viewing item) , enter 'equip'");
			menuView.print("To drop an item (whilst viewing item) , enter 'drop'");
			menuView.print("To examine an item (whilst viewing item) , enter 'examine'");
			menuView.print("To exit item examination (whilst viewing item) , enter 'exit'");
			menuView.print("To exit inventory view (whilst viewing inventory) , enter 'exit'");
			menuView.print("To enter combat, enter 'combat'");
			menuView.print("To attack an enemy, enter 'attack'");
			menuView.print("To flee from an enemy (whilst in combat), enter 'exit'");
			menuView.print("To enter a puzzle, enter 'puzzle' ");
			menuView.print("To view a puzzle hint (whilst in puzzle sequence), enter 'hint' ");
			menuView.print("To leave a puzzle (whilst in puzzle sequence), enter 'exit'");
			menuView.print("Exit:");
			input = control.nextLine();
			if(input.equalsIgnoreCase("exit"))
			{
				try
				{
					model.getCurrentRoom().display();
				}
				catch(Exception E)
				{
					
				}
				break;
			}
		}
	}
	
	private void controllerMenu()
	{
		String input;
		
		while(true)
		{
			menuView.line(125);
			//menuView.print("New Game:");
			menuView.print("Save One:");
			menuView.print("Save Two:");
			menuView.print("Save Three:");
			//menuView.print("Load Game:");
			menuView.print("Leave Game:");
			menuView.print("Exit:");

			input = control.nextLine();
			if(input.equalsIgnoreCase("exit")) 
			{
				try
				{
					model.getCurrentRoom().display();
				}
				catch(Exception E)
				{
					
				}
				break;
			}
			if(input.equalsIgnoreCase("New Game"))
			{
				// no code. It's done from main menu
			}
			if(input.equalsIgnoreCase("Load Game"))
			{
				// no code. It's done from main menu
			}
			if(input.equalsIgnoreCase("Save One"))
			{
				String number = "One";
				model.saveItems(number);
				/*model.saveEnemys(number);
				model.savePuzzles(number);
				model.saveInventory(number);
				model.savePlayer(number);*/
			}
			if(input.equalsIgnoreCase("Save Two"))
			{
				String number = "Two";
				model.saveItems(number);
				/*model.saveEnemys(number);
				model.savePuzzles(number);
				model.saveInventory(number);
				model.savePlayer(number);*/
			}
			if(input.equalsIgnoreCase("Save Three"))
			{
				String number = "Three";
				model.saveItems(number);
				/*model.saveEnemys(number);
				model.savePuzzles(number);
				model.saveInventory(number);
				model.savePlayer(number);*/
			}
			if(input.equalsIgnoreCase("Leave Game"))
			{
				int exit = 1/0;
				/*
				 * Controller throws itself
				 * The Code will pick back up Initializer
				 * Main Menu*
				 */
			}
		}
	}

	private void determine(String input)
	{
		if (input.length() == 1)
		{
			try
			{
				navigate(input);
			} 
			catch (Exception E)
			{
				
			}
		} 
		else if (input.equalsIgnoreCase("pick up") || input.equalsIgnoreCase("pickup"))
		{
			pickUpAll();
		} 
		else if (input.equalsIgnoreCase("inventory") || input.equalsIgnoreCase("inv"))
		{
			controllerItem();
		} 
		else if (input.equalsIgnoreCase("combat") && model.getCurrentRoom().getEnemy() != null)
		{
			controllerEnemy();
		} 
		else if (input.equalsIgnoreCase("puzzle") && model.getCurrentRoom().getPuzzle() != null)
		{
			if (model.getCurrentRoom().getPuzzle() instanceof DoorPuzzle)
			{
				controllerDoorPuzzle();
			}
			if (model.getCurrentRoom().getPuzzle() instanceof ObjectPuzzle)
			{
				controllerObjectPuzzle();
			}
			if (model.getCurrentRoom().getPuzzle() instanceof PuzzleDebuff)
			{
				controllerPuzzleDebuff();
			}
			if (model.getCurrentRoom().getPuzzle() instanceof PuzzleNumber)
			{
				controllerPuzzleNumber();
			}
			if (model.getCurrentRoom().getPuzzle() instanceof PuzzleRiddle)
			{
				controllerPuzzleRiddle();
			}
			if (model.getCurrentRoom().getPuzzle() instanceof WordPuzzle)
			{
				controllerWordPuzzle();
			}
			try
			{
				model.getCurrentRoom().display();
			} 
			catch (Exception E)
			{
				
			}
		}
		else if (input.equalsIgnoreCase("stats"))
		{
			controllerStats();
		} 
		else if (input.equalsIgnoreCase("help"))
		{
			controllerHelp();
		}
		else if (input.equalsIgnoreCase("menu"))
		{
			controllerMenu();
		}
	}

	private boolean determineItem(String input)
	{
		if (input.matches("[0-9]+"))
		{
			try
			{
				controllerItem(Integer.parseInt(input));
			} 
			catch (Exception E)
			{

			}
		}
		if (input.equalsIgnoreCase("exit"))
		{
			try
			{
				model.getCurrentRoom().display();
			} 
			catch (Exception E)
			{
				
			}
			return true;
		}
		return false;
	}

	private boolean determineItem(String input, int i)
	{
		try
		{
			if (input.equalsIgnoreCase("exit"))
			{
				return true;
			} 
			else if (input.equalsIgnoreCase("use"))
			{
				if (model.getUser().getItem(i) instanceof Consumable)
				{
					model.getUser().receiveDamage(-((Consumable) model.getUser().getItem(i)).getHealthEffect());
					model.getUser().updateMaxHealth(-((Consumable) model.getUser().getItem(i)).getMaxHealthEffect());
					model.getUser().drop(i);
				}
				if (model.getUser().getItem(i) instanceof PuzzleItem)
				{
					if(model.getUser().getItem(i).getItemId() == 102
							&& ((PuzzleDebuff) model.getRoom(12).getPuzzle()).isActive())
					{
						model.getUser().removeItem(i);
						model.getUser().setPoisoned(false);
						model.getUser().updateStats();
						model.getRoom(12).setPuzzle(null);
						menuView.line(125);
						menuView.print("puzzle solved");
						
					}
				}
				// model.getUser().getItem(i).use();
				// ran into problem. AoK work around
				return true;
			} 
			else if (input.equalsIgnoreCase("drop"))
			{
				model.currentRoom.addItem(model.getUser().getItem(i));
				model.getUser().getItem(i).drop();// this part of code does nothing most of the time
				model.getUser().drop(i);
				return true;
			} 
			else if (input.equalsIgnoreCase("equip"))
			{
				model.getUser().equip(i);
				return true;
			} 
			else if (input.equalsIgnoreCase("examine"))
			{
				model.getUser().getItem(i).examine();
				return true;
			}
		} catch (Exception E)
		{
		}
		return false;
	}

	private boolean determineEnemy(String input)
	{
		if (input.equalsIgnoreCase("exit"))
		{
			try
			{
				model.getCurrentRoom().display();
			} 
			catch (Exception E)
			{
				
			}
			return true;
		} 
		else if (input.equalsIgnoreCase("attack"))
		{
			int j = model.getCurrentRoom().getEnemy().getDefense();
			int i = model.getUser().dealDamage() - j;
			
			if (i <= 0)
			{
				menuView.print("The attack Missed");
			} 
			else
			{
				model.getCurrentRoom().getEnemy().receiveDamage(i);
				if (model.getCurrentRoom().getEnemy().isAlive())
				{
					menuView.print("Player attack: " + i + " damage");
				}
			}
			if (!model.getCurrentRoom().getEnemy().isAlive())
			{
				model.getCurrentRoom().enemyDeathEffects();
				return true;
			} 
			else
			{
				int f = model.getUser().getDefense();
				int g = model.getCurrentRoom().getEnemy().dealDamage() - f;
				
				if (g <= 0)
				{
					menuView.print("Enemy attack Missed");
				} 
				else
				{
					menuView.print("Enemy attack: " + g + " damage");
					model.getUser().receiveDamage(g);
					if (!model.getUser().isAlive())
					{
						menuView.print("player died");
						menuView.print("game over");
						System.exit(0);
					}
				}
			}
		} 
		else if (input.equalsIgnoreCase("inventory"))
		{
			controllerItem();
		}
		return false;
	}

	private void navigate(String input) throws Exception
	{
		model.navigate(input.charAt(0));
	}

	private void pickUpAll()
	{
		while (model.getCurrentRoom().getItems().size() > 0)
		{
			model.getUser().pickUp(model.getCurrentRoom().getItems().get(0));
			model.getCurrentRoom().getItems().remove(0);
		}
		menuView.line(125);
		menuView.print("Items Picked Up");
		try
		{
			model.getCurrentRoom().display();
		} 
		catch (Exception e)
		{
			
		}
	}
	
}
