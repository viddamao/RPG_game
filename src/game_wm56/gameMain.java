package game_wm56;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class gameMain extends Application {
	

public void start(Stage stage){
	 ImageView background=new ImageView();
	 File file = new File(".//res//background//grid.jpg");
	 Image map = new Image(file.toURI().toString());
	 
	 background.setImage(map);
	 Group root = new Group();
	 Scene scene = new Scene(root);
	 scene.setFill(Color.BLACK);
	 HBox bgbox = new HBox();
     bgbox.getChildren().add(background);
	 
     file = new File(".//res//hero//paladin.jpg");
     Image heroImage=new Image(file.toURI().toString(),80,80,true,false);
	 
	 ImageView hero=new ImageView();
	 hero.setImage(heroImage);
	 
	  AnchorPane anchorpane = new AnchorPane();
	 
	  
	  
	 HBox box2=new HBox();
	 box2.getChildren().add(hero);
	 box2.relocate(110, 210);
	 root.getChildren().add(bgbox);
	 root.getChildren().add(box2);
	 
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
