import javafx.scene.image.Image;

/**
 * 
 * @author Natsuki Hasegawa
 * @version 1.0
 * @since 2019-08-05
 *
 */
public class Bug extends Item {

	private String species;
	private String name;
	private int ID = 0;
	private static int bugNum = 0;
	private static int step = 2;
	private int size;
	
	/**
	 * 
	 * @param x
	 * @param y
	 * @param im
	 * @param size
	 */
	public Bug(int x, int y, Image im, int size) {
		super("Bug", x, y, im);
		this.species = "Bug";
		this.ID = bugNum++;
		this.size = size;
	}
	
	public int getStep() {
		return Bug.step;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public static int getBugNum() {
		return bugNum;
	}
	
	public void Move(String dir) {

		// The coordinates in array space are NOT the same as on screen!!!!!
		// Must use getTranslateY() or will go off screen!!!!
		if ("N".equals(dir)) {
			//this.setTranslateY(this.getTranslateY() - step);
			this.getIcon().setTranslateY(this.getIcon().getTranslateY() - step);
		}
		if ("S".equals(dir)) {
			//this.setTranslateY(this.getTranslateY() + step);
			this.getIcon().setTranslateY(this.getIcon().getTranslateY() + step);
		}
		if ("E".equals(dir)) {
			//this.setTranslateX(this.getTranslateX() + step);
			this.getIcon().setTranslateX(this.getIcon().getTranslateX() + step);
		}
		if ("W".equals(dir)) {
			//this.setTranslateX(this.getTranslateX() - step);
			this.getIcon().setTranslateX(this.getIcon().getTranslateX() - step);
		}
		
	}
	
	void grow() {
		double sx = this.getIcon().getScaleX();
		double sy = this.getIcon().getScaleY();
		
		if(size == 10) {
			size = 0;
			this.getIcon().setScaleX(0);
			this.getIcon().setScaleY(0);
		}
		else {
			this.size++;
			this.getIcon().setScaleX(sx+0.1);
			this.getIcon().setScaleY(sy+0.1);
		}
	}


}
