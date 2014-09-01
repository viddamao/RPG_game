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
private int CursorX = 100, CursorY = 200;



public void start(Stage stage) throws IOException{
	
	SetImage(stage,"background//map.jpg",0,0,Is_Map);
	LoadContents(stage);
	
	stage.setTitle("Legend of Paladins");
	stage.setResizable(false);
	 
	gameProcess(stage);
}

private void moveCursor(int i, int j, Stage stage) {
	
   if (j==1) {confirmAction(stage); return;}
   if (i==1) {cancelAction();return;}
   if (i==-1) {attackAction(stage);return;}
   CursorX+=i;
   CursorY+=j;
   
}

private void attackAction(Stage stage) {

	
	if ((enemy.hasEnemy(CursorX/100,CursorY/100+1))||
	(enemy.hasEnemy(CursorX/100-1,CursorY/100))||
	(enemy.hasEnemy(CursorX/100+1,CursorY/100))||
	(enemy.hasEnemy(CursorX/100,CursorY/100-1)))
	{
		displayRedCursor(stage);
	}
}

private void displayRedCursor(Stage stage) {
	SetImage(stage,"background//cursor.png",CursorX+100,CursorY,!Is_Map);	
	SetImage(stage,"background//cursor.png",CursorX-100,CursorY,!Is_Map);
	SetImage(stage,"background//cursor.png",CursorX,CursorY+100,!Is_Map);
	SetImage(stage,"background//cursor.png",CursorX,CursorY-100,!Is_Map);
  
 	
}

private void cancelAction() {
	CursorX=hero.getX();
	CursorY=hero.getY();
}

private void confirmAction(Stage stage) {
	if ((CursorX!=hero.getX())||(CursorY!=hero.getY()))
			{
		   hero.setX(CursorX);
		   hero.setY(CursorY);
		   
			}
	resetStage(stage);
	
}

private  void gameProcess(Stage stage) {



resetStage(stage);


addButton(stage,800,480,"   UP   ",0,-100);
addButton(stage,800,520,"  DOWN  ",0,100);
addButton(stage,720,520,"  LEFT  ",-100,0);
addButton(stage,880,520,"  RIGHT ",100,0);

addButton(stage,80,500,"CONFIRM",0,1);
addButton(stage,160,500,"CANCEL ",1,0);
addButton(stage,240,500,"ATTACK ",-1,0);

stage.setScene(scene);
stage.show();





/*
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
*/

}

private void resetStage(Stage stage) {
	
	SetImage(stage,"background//map.jpg",0,0,Is_Map);
    displayContents(stage);
	displayCursor(CursorX,CursorY, stage);
	
}

private void addButton(Stage stage, int LayoutX, int LayoutY,String ButtonName, int i, int j) {
	Button btn = new Button();
	btn.setLayoutX(LayoutX);
	btn.setLayoutY(LayoutY);
	btn.setText(ButtonName);
	btn.setOnAction(new EventHandler<ActionEvent>() {
		 
	    public void handle(ActionEvent event) {
	        moveCursor(i,j,stage);
	        gameProcess(stage);
	    }	
	});
	root.getChildren().add(btn);
}

private void displayContents(Stage stage) {
	SetImage(stage,"hero//paladin.png",hero.getX(),hero.getY(),!Is_Map);
	SetImage(stage,"enemy//Skeleton_Mage.png",400,200,!Is_Map);
	
}


private void displayCursor(int cursorX, int cursorY, Stage stage) {
	SetImage(stage,"background//cursor_blue.png",cursorX,cursorY,!Is_Map);
	
	//SetImage(stage,"background//cursor.png",cursorX+100,cursorY,!Is_Map);


}


private void LoadContents(Stage stage) throws IOException {
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
