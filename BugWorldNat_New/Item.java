
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;

/**
 * 
 * @author Natsuki Hasegawa <haseganats@myvuw.ac.nz>
 * @version 1.0
 * @since 1.0
 *
 */
public class Item extends Circle{
	private String name;
	private ImageView icon;	// To display in the window; you cannot display "Image" objects
	private static int circleSize = 5;
	
	/**
	 * 
	 * @param name
	 * @param x
	 * @param y
	 * @param im
	 */
	public Item(String name, int x, int y, Image im) {
		super(x, y, circleSize);
		this.name = name;
		this.icon = new ImageView(im);
	}
	
	public ImageView getIcon() {
		return this.icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return (int) this.getCenterX();
	}

	public int getY() {
		return (int) this.getCenterY();
	}

}
