import javafx.scene.image.Image;

public class Plant extends Item{
	
	private int size;
	
	public Plant(int size, int x, int y, Image im) {
		super("Plant", x, y, im);
		this.size = size;
	}
	
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Increase the size of the icon
	 */
	void grow() {
		double sx = this.getIcon().getScaleX();
		double sy = this.getIcon().getScaleY();
		
		// At certain point go back to zero (death)
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
