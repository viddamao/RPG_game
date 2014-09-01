package game_wm56;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public  class gameMain extends Application{
private static final boolean Is_Map = true;
private	Group root = new Group();
private Scene scene = new Scene(root);
private Image img=null;
private int CursorX = 0, CursorY = 0;



public void start(Stage stage) throws IOException{
	
	SetImage(stage,"background//map.jpg",0,0,Is_Map);
	LoadContents(stage);
	
	stage.setTitle("Legend of Paladins");
	stage.setResizable(false);
	 
	gameprocess(stage, null);
}


private  void gameprocess(Stage stage, KeyEvent e) {


displayContents(stage);
displayCursor(CursorX,CursorY, stage);

System.out.println("123");
if ((e!=null)&&(e.getEventType()==KeyEvent.KEY_PRESSED))
switch (e.getCode())	


	 {
	 case LEFT:
	 if (CursorX!=0)
	 CursorX-=100;
	 e.consume();

     System.out.println("Left key typed");
	 break;

	 case RIGHT:
	 if (CursorX!=1000)
	 CursorX+=100;
     System.out.println("Right key typed");
	 e.consume();
	 break;

	

	 case UP:
	 if (CursorY!=0)
	 CursorX-=100;
	 e.consume();
	 break;

	 case DOWN:
	 if (CursorY!=600)
	 CursorX+=100;
	 e.consume();
     break;

	 default:	
     
     return;
	 
	 }

System.out.println("456");	  

}

private void displayContents(Stage stage) {
	SetImage(stage,"hero//paladin.png",100,200,!Is_Map);
	SetImage(stage,"enemy//Skeleton_Mage.png",400,200,!Is_Map);
	
}


private void displayCursor(int cursorX, int cursorY, Stage stage) {
	SetImage(stage,"background//cursor_blue.png",100,200,!Is_Map);
	SetImage(stage,"background//cursor_blue.png",0,200,!Is_Map);
	SetImage(stage,"background//cursor_blue.png",200,200,!Is_Map);
	SetImage(stage,"background//cursor_blue.png",100,300,!Is_Map);
	SetImage(stage,"background//cursor_blue.png",100,100,!Is_Map);

	SetImage(stage,"background//cursor.png",100,400,!Is_Map);
	
}


private void LoadContents(Stage stage) throws IOException {
  Scanner s = null;
	try{
	s=new Scanner(new BufferedReader(new FileReader(".\\res\\data.txt")));
	while (s.hasNext()){
     System.out.println(s.next());		
		
	}
	
    

}

finally 
	{
		if (s!=null)
			s.close();
		
	}
}



private void SetImage(Stage stage, String directory,int x,int y,boolean flag) {
	ImageView background=new ImageView();
	 File file = new File(".//res//"+directory);
	if (flag)
	  img = new Image(file.toURI().toString(),1000,600,false,false);
	 else
	  img = new Image(file.toURI().toString(),100,100,false,false);
	 
	 background.setImage(img);
	 
	 scene.setFill(Color.BLACK);
	 HBox box = new HBox();
     box.getChildren().add(background);
     box.relocate(x, y);
     root.getChildren().add(box);
	 stage.setScene(scene); 
	 stage.setWidth(1000);
	 stage.setHeight(600);
	 stage.show(); 

}


public static void main(String[] args){
    Application.launch(args);
	
}



}
