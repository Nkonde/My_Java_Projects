import csc2a.designpatterns.iRenderable;
import csc2a.ui.GamePane;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Entry Point for the JavaFX Application
 * @author Thabani
 *
 */
public class Main extends Application{
	Stage primaryStage = null;
	GamePane root = null;
	/**
	 * Main entry point for the program
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		/* TODO: launch Application */
		launch();
	}

	/**
	 * Default start method provided by the JavaFX Application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		/* TODO: Set up your wScene and Stage */
		root = new GamePane();
		
		Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());
		
		
		primaryStage.setTitle("Project X Game Application");
		primaryStage.setScene(scene);
		
		primaryStage.show();
	/*	
		System.out.print(root.getNumbE()+ " ");
		System.out.print(root.isDead());
		if(root.numbE == 5) {
			changeScene("You lose");
		}
		if(root.dead == true) {
			changeScene("You Win!!!");
		}
		*/
	}
	 
	
	private void changeScene(String ending) {
		Group grp = new Group();
		
		
		Scene endscene = new Scene(grp, 600,600, Color.BLACK);
		primaryStage.setScene(endscene);
		Label lbl = new Label(ending);
		lbl.setFont(Font.font("Times new roman", 32));
		
		VBox box = new VBox();
		box.getChildren().add(lbl);
		
		grp.getChildren().add(box);
		
	
	}

}
