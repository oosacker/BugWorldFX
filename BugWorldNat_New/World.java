import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Bounds;
import javafx.scene.Scene;
import javafx.scene.image.Image;

/**
 * 
 * @author Natsuki Hasegawa <haseganats@myvuw.ac.nz>
 * @version 1.0
 * @since 1.0
 *
 */
public class World {

	int width = 1200;
	int height = 720;

	// Icons of the items (things on the screen)
	private Image stone = new Image("stone.png", 25, 25, true, true);
	private Image bug = new Image("bug.png", 25, 25, true, true);
	private Image plant = new Image("plant.png", 25, 25, true, true);

	Bug myBug;
	Plant myPlant;
	Obstacle myObs;
	ArrayList<Item> items = new ArrayList<Item>();

	Scene myscene;

	public World() {
		addPlant(100);
		addObstacle(100);
		addBug(5);
	}

	/**
	 * Add specified number of bugs to the world.
	 * 
	 * @param bugNum	Number of Bugs to add.
	 */
	public void addBug(int bugNum) {
		int temp = 0;

		while(temp < bugNum) {
			int randX = randomX();
			int randY = randomY();

			myBug = new Bug(randX, randY, bug, randomSize());

			if(isValidPoint(randX, randY)) {
				myBug.getIcon().setX(myBug.getX());
				myBug.getIcon().setY(myBug.getY());
				items.add(myBug);
				temp++;
			}
			else {
				//System.out.println("reject bug");
			}
		}
	}

	/**
	 * Add specified number of Plants to the world.
	 * 
	 * @param plNum	Number of Plants to add.
	 */
	public void addPlant(int plNum) {
		int temp = 0;

		while(temp < plNum) {
			int randX = randomX();
			int randY = randomY();

			myPlant = new Plant(randomSize(), randX, randY, plant);

			if(isValidPoint(randX, randY)) {
				myPlant.getIcon().setX(myPlant.getX());
				myPlant.getIcon().setY(myPlant.getY());	
				items.add(myPlant);
				temp++;
			}
			else {
				//System.out.println("reject obstacle");
			}
		}
	}

	/**
	 * Add specified number of Obstacles to the world.
	 * 
	 * @param obsNum	Number of Obstacles to add.
	 */
	public void addObstacle(int obsNum) {
		int temp = 0;

		while(temp < obsNum) {
			int randX = randomX();
			int randY = randomY();

			myObs = new Obstacle(randX, randY, stone);

			if(isValidPoint(randX, randY)) {
				myObs.getIcon().setX(myObs.getX());
				myObs.getIcon().setY(myObs.getY());
				items.add(myObs);
				temp++;
			}
			//System.out.println("reject obstacle");

		}
	}


/**
 * Generate a random integer in x direction which is inside the scene.
 * 
 * @return 	Returns the randomly generated integer x.
 */
	private int randomX() {
		int x = (int)((width-10) * Math.random() + 1);
		return x;
	}

	/**
	 * Generate a random integer in y direction which is inside the scene.
	 * 
	 * @return 	Returns the randomly generated integer y.
	 */
	private int randomY() {
		int y = (int)((height-10) * Math.random() + 1);
		return y;
	}

	/**
	 * Generate a random integer size between 1 and 10.
	 * 
	 * @return 	Returns the randomly generated size.
	 */
	private int randomSize() {
		return (int)((10-1) * Math.random() + 1);
	}


/**
 * Tests to see if Item myitem is inside the Scene myscene.
 * 
 * @param myitem	Item object to test.	
 * @param myscene	Scene object to test.
 * @return 	Returns true if myitem is inside myscene, false if not.
 */
	private boolean isInWindow(Item myitem, Scene myscene) {

		Bounds bounds =  myitem.getBoundsInParent();
		double sceneWidth = myscene.getWidth();
		double sceneHeight = myscene.getHeight();

		if (( bounds.getMinX() < 0 || bounds.getMaxX() > sceneWidth) ||
				(bounds.getMinY() < 0 || bounds.getMaxY() > sceneHeight)) {
			return false;
		}

		else {
			return true;
		}
	}

	/**
	 * 
	 * Checks if current y x and y is a valid point.
	 * 
	 * @param x	x coordinate to test.
	 * @param y	y coordinate to test.
	 * @return	Returns true if it is a valid point, false if not.
	 */
	private boolean isValidPoint(int x, int y) {

		// check if x and y is not filled or outside world space
		if ( (x > width) || (y > height) ) {
			return false;
		}

		for (Item myItem : items) {
			if ( ((x == myItem.getX()) && (y == myItem.getY())) ) {
				return false;
			}
		}
		return true;
	}


	/**
	 * Finds all the Items that are next to the current Bug b.
	 * 
	 * @param b	Checks if there are any items next to this Bug.
	 * @return	Returns an ArrayList of Items that are next to Bug b.
	 */
	public List<Item> adjacentItems(Bug b) {

		List<Item> ret = new ArrayList<Item>();

		int bug_x = b.getX();
		int bug_y = b.getY();

		if(items == null)
			return null;

		for(Item o : items) {

			int item_x = o.getX();
			int item_y = o.getY();

			if ( 
					((bug_x == item_x) && (bug_y+1 == item_y)) ||
					((bug_x == item_x) && (bug_y-1== item_y)) ||
					((bug_x+1 == item_x) && (bug_y == item_y)) ||
					((bug_x-1 == item_x) && (bug_y == item_y)) ) 
			{

				ret.add(o);

			}

		}
		return ret;	
	}

	/**
	 * Increase the size of all Plants.
	 */
	public void growPlants() {
		Plant pl;

		if(items.isEmpty())
			return;

		for(Item it : items) {

			if(it instanceof Plant) {
				pl = (Plant)it;
				pl.grow();
			}	
		}
	}

	/**
	 * Increase the size of all Bugs.
	 */
	public void growBugs() {
		Bug b;

		if(items.isEmpty())
			return;

		for(Item it : items) {

			if(it instanceof Bug) {
				b = (Bug)it;
				b.grow();
			}

		}
	}


	/**
	 * 
	 * Moves all the Bugs inside the list.
	 * 
	 * @param myscene	The Scene object passed from the Graphics class.
	 */
	public void moveAllBugs(Scene myscene) {

		Bug b;
		List<Item> adjacent;

		if(items.isEmpty())
			return;

		for(Item item : items) {

			if(item instanceof Bug) {

				b = (Bug)item;
				adjacent = adjacentItems(b);

				if( adjacent.isEmpty() && isInWindow(b, myscene)) {

					moveRandom(b, myscene);

				}

			}

		}

	}


	/**
	 * 
	 * Moves the Bug in a random direction.
	 * 
	 * @param b	The Bug object to move.
	 * @param myscene	The Scene object passed from the Graphics class.
	 */
	public void moveRandom(Bug b, Scene myscene) {
		double d = Math.random();
		String dir;

		int currentx = b.getX();
		int currenty = b.getY();

		if (d < 0.25) { 
			dir = "N";
			if (isValidPoint(currentx, currenty-b.getStep()) ) {
				b.Move(dir);
			}
			else {
				//a.Move("S");
			}
		}
		else if (d < 0.5) {
			dir = "S";
			if (isValidPoint(currentx, currenty+b.getStep())) {
				b.Move(dir);
			}
			else {
				//a.Move("N");
			}
		}
		else if (d < 0.75) {
			dir = "E";
			if (isValidPoint(currentx+b.getStep(), currenty)) {
				b.Move(dir);
			}
			else {
				//a.Move("W");
			}
		}
		else {
			dir = "W";
			if (isValidPoint(currentx-b.getStep(), currenty) ) {
				b.Move(dir);
			}
			else {
				//a.Move("E");
			}
		}

	}


	/**
	 * Returns the ArrayList of Items in this class.
	 * 
	 * @return	The ArrayList of Items.
	 */
	public ArrayList<Item> getItems(){
		return items;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

}
