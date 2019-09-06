import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.TimelineBuilder;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

@SuppressWarnings("deprecation")
public class Graphics extends Application {
	
	World w;
	Bug b;
	Obstacle o;
	Plant p;

	List<Item> items;

	Color bugColor = Color.RED;
	Color obsColor = Color.BLACK;
	Color plantColor = Color.GREEN;

	KeyFrame frame;
	Scene scene;
	Group root;
	Image myImg;
	
	BackgroundImage bgi;
	Background bg;
	VBox box;

	HBox hb;
	Button bugBtn, plBtn, obsBtn, grPlBtn, grBugBtn;
	
	int width, height;
	int x, y;
	
	ImageView img;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		w = new World();
		width = w.getWidth();
		height= w.getHeight();
		
		primaryStage.setTitle("Bug World FX by Natsuki");
		root = new Group(); 
		
		updateItems();
		
		myImg = new Image("ground.png", width, height, false, false);
		bgi = new BackgroundImage(myImg, null, null, null, null);
		bg = new Background(bgi);
		
		bugBtn = new Button("Add bug");
		plBtn = new Button("Add plant");
		obsBtn = new Button("Add obstacle");
		grPlBtn = new Button("Grow plant");
		grBugBtn = new Button("Grow bug");
		
		// HBox for the buttons
		hb = new HBox();
		hb.setSpacing(10);
		hb.getChildren().addAll(bugBtn, plBtn, obsBtn, grPlBtn, grBugBtn);
		hb.setAlignment(Pos.CENTER);
		
		// Needed because you cannot place backgrounds on a scene
		box = new VBox();
		box.getChildren().addAll(hb, root);
		box.setBackground(bg);
		
		scene = new Scene(box, width, height);	
		
		frame = new KeyFrame(Duration.millis(90), e-> {

			w.moveAllBugs(scene);
			
		});

		TimelineBuilder.create().cycleCount(javafx.animation.Animation.INDEFINITE).keyFrames(frame).build().play();

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.getIcons().add(new Image("file:bugicon.png"));
		primaryStage.show();
		
		/**
		 * Button event handling
		 */
		bugBtn.setOnAction(e->{
			w.addBug(1);
			updateItems();
		});
		
		plBtn.setOnAction(e->{
			w.addPlant(1);
			updateItems();
		});
		
		obsBtn.setOnAction(e->{
			w.addObstacle(1);
			updateItems();
		});
		
		grPlBtn.setOnAction(e->{
			w.growPlants();
		});
		
		grBugBtn.setOnAction(e->{
			w.growBugs();
		});
	}
	

	/**
	 * Get new list of items (bug, obstacle, plant) from the world
	 */
	public void updateItems() {
		
		items = w.getItems();
		
		// Must clear the old list
		root.getChildren().clear();
		
		for(Item item : items) {
			
			if(item instanceof Bug) {
				b = (Bug)item;
				img = b.getIcon();
			}
			
			if(item instanceof Plant) {
				p = (Plant)item;
				img = p.getIcon();
			}
			
			if(item instanceof Obstacle) {
				o = (Obstacle)item;
				img = o.getIcon();
			}		
			
			root.getChildren().add(img);
		}
	}

	public static void main(String[] args) {
		launch(args);

	}

}
