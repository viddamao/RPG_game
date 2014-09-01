package game_wm56;

import java.io.File;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gameMain extends Application {
	

public void start(Stage stage){
	 ImageView background=new ImageView();
	 File file = new File(".//res//background//map.jpg");
	 Image img = new Image(file.toURI().toString());
	 background.setImage(img);
	 Group root = new Group();
	 Scene scene = new Scene(root);
	 scene.setFill(Color.BLACK);
	 HBox box = new HBox();
	 
	 box.getChildren().add(background);
	 root.getChildren().add(box);
	 stage.setTitle("Legend of Paladins");
	 stage.setResizable(false);
	 stage.setScene(scene); 
	 stage.sizeToScene(); 
	 stage.show(); 
 
}


public static void main(String[] args){
    Application.launch(args);
	
}



}
