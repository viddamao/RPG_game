package game_wm56;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
private int CursorX = 100, CursorY = 400;
private Stage MyStage = new Stage();
private boolean encounterEnemy = false;
private final boolean Is_Red=true;


public void start(Stage stage) throws IOException{
	
	SetImage("background//map.jpg",0,0,Is_Map);
	LoadContents();
	MyStage = stage;
	
	MyStage.setTitle("Legend of Paladins");
	MyStage.setResizable(false);
	 
	gameProcess();
}

private void moveCursor(KeyEvent e) {
		int i = 0,j = 0;
	if ((e!=null)&&(e.getEventType()==KeyEvent.KEY_PRESSED))
		switch (e.getCode())	


			 {
			 case LEFT:
			 if (CursorX>0)
			 CursorX-=100;
			 e.consume();

		     System.out.println("Left key typed");
			 break;

			 case RIGHT:
			 if (CursorX<900)
			 CursorX+=100;
		     System.out.println("Right key typed");
			 e.consume();
			 break;

			

			 case UP:
			 if (CursorY>0)
			 CursorY-=100;
			 e.consume();
			 System.out.println("UP key typed");
				
			 break;

			 case DOWN:
			 if (CursorY<500)
			 CursorY+=100;
			 e.consume();
			 System.out.println("DOWN key typed");
				
		     break;

			 case Z:
			 
				 confirmAction();
				 checkEnemy();
				 return;
			 
			 case X:
				 
				 cancelAction();
				 return;
			 
			 default:	
		     
		     return;
			 
			 }	
	   
}

private void checkEnemy() {

	
	if ((enemy.hasEnemy(CursorX/100,CursorY/100+1))||
	(enemy.hasEnemy(CursorX/100-1,CursorY/100))||
	(enemy.hasEnemy(CursorX/100+1,CursorY/100))||
	(enemy.hasEnemy(CursorX/100,CursorY/100-1)))
	{
		encounterEnemy = true;
		resetStage();

		displayCursor(CursorX-100,CursorY,Is_Red);
		displayCursor(CursorX+100,CursorY,Is_Red);
		displayCursor(CursorX,CursorY+100,Is_Red);
		displayCursor(CursorX,CursorY-100,Is_Red);

	}
	

}

private void displayCursor(int i, int j,boolean color) {
	if (color)
	SetImage("background//cursor.png",i,j,!Is_Map);	
	else
	SetImage("background//cursor_blue.png",i,j,!Is_Map);



}

private void cancelAction() {
	CursorX=hero.getX();
	CursorY=hero.getY();
}

private void confirmAction() {
	if ((CursorX!=hero.getX())||(CursorY!=hero.getY()))
			{
		   hero.setX(CursorX);
		   hero.setY(CursorY);
		   
			}
	//resetStage();
	
}

private  void gameProcess() {


if (!encounterEnemy)
resetStage();

scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 
		public void handle(KeyEvent event) {
			 moveCursor(event);

			MyStage.setScene(scene);
			MyStage.show();

			 gameProcess();
		}	
	});



}

private void resetStage() {
	
	SetImage("background//map.jpg",0,0,Is_Map);
    displayContents();
	displayCursor(CursorX,CursorY,false);
	
}

private void displayContents() {
	
	//can use a input file to save all the addresses and stuff 
	SetImage("hero//paladin.png",hero.getX(),hero.getY(),!Is_Map);
	SetImage("enemy//Skeleton_Warrior.png",200,100,!Is_Map);
	SetImage("enemy//Skeleton_Mage.png",500,300,!Is_Map);
	SetImage("enemy//Boss.png",900,400,!Is_Map);
	SetImage("background//final.png",900,500,!Is_Map);
	
	
}




private void LoadContents() throws IOException {
  Scanner s = null;
	try{
	s=new Scanner(new BufferedReader(new FileReader(".\\res\\data.txt")));
	while (s.hasNext()){
     System.out.println(s.next());		
		
	}
  enemy.enemy_Init();	
    

}

finally 
	{
		if (s!=null)
			s.close();
		
	}
}



private void SetImage(String directory,int x,int y,boolean flag) {
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
	 MyStage.setScene(scene); 
	 MyStage.setWidth(1000);
	 MyStage.setHeight(600);
	 MyStage.show(); 

}


public static void main(String[] args){
    Application.launch(args);
	
}



}
