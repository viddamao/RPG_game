package game_wm56;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public  class gameMain extends Application{
private static final boolean Is_Map = true;
private	Group root = new Group();
private Scene scene = new Scene(root);
private Image img=null;
private int CursorX = 100, CursorY = 400;
private int CursorX_p = 100, CursorY_p = 400;
private Stage MyStage = new Stage();
private boolean[][]map= new boolean [12][8];
private int[][] textValueLocation=new int [8][2];
private int[][] botLocation=new int[8][2];
private String[] filePath=new String[15];
private int[ ] heroStat = new int[8];
private int[][] enemyStat=new int [5][11];

public void start(Stage stage) throws IOException{
	
    loadData();
	initMenu();
   
	MyStage = stage;
	MyStage.setTitle("Legend of Paladin");
	MyStage.setResizable(false);
	MyStage.setWidth(1200);
	MyStage.setHeight(620);
	gameProcess();
}


private void initMenu() {
	 SetImage("background//menuscreen.png",0,0,Is_Map);
	 MyStage.setScene(scene);
	 MyStage.show();
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			 
			public void handle(KeyEvent event) {
		
				menuSelection(event);
			}

			
		});
}


private void menuSelection(KeyEvent e) {
	if ((e!=null)&&(e.getEventType()==KeyEvent.KEY_PRESSED))
		switch (e.getCode())	
		{
		case ENTER:
		{
			
		}
		case SPACE:
		{
			e.consume();
			initMenu();
		}
		}
	
}	
private void moveHero(KeyEvent e) {
	if ((e!=null)&&(e.getEventType()==KeyEvent.KEY_PRESSED))
		switch (e.getCode())	


			 {
			 case LEFT:
			 if (CursorX>0)
			 CursorX-=100;
			 if (!(canMove()))
				 CursorX+=100;
			 e.consume();

			 break;

			 case RIGHT:
			 if (CursorX<900)

			 CursorX+=100;
			 if (!(canMove()))
				 CursorX-=100;
			 e.consume();
			 break;

			

			 case UP:
			 if (CursorY>0)

			 CursorY-=100;
			 if (!(canMove()))
				 CursorY+=100;

			 e.consume();
			 break;

			 case DOWN:
			 if (CursorY<500)

			 CursorY+=100;
			 e.consume();
			 if (!(canMove()))
				 CursorY-=100;
				
		     break;

			
			 
			 default:	
		     
		     return;
			 
			 }	
	   
}

    
	


private boolean canMove() {

return map[CursorX/100][CursorY/100];

}

private void checkEnemy() {

	
	if ((enemy.hasEnemy(CursorX/100,CursorY/100)))
	{
		System.out.println("Enemy!");
		performAttack();
		CursorX=CursorX_p;
		CursorY=CursorY_p;
	}
	

}



private void performAttack() {
	int i=0;
	for (i=0;i<5;i++)
		if ((enemyStat[i][7]==CursorX)&&(enemyStat[i][8]==CursorY))
			break;
	int heroDmg = heroStat[5]-enemyStat[i][4];
	int enemyDmg = enemyStat[i][3]-heroStat[6];
	if (heroDmg>enemyStat[i][1])
	{
		enemyStat[i][0]=0;
		enemy.removeBot(CursorX,CursorY);
		heroStat[3]+=enemyStat[i][5];
		heroStat[7]+=enemyStat[i][6];
		if (heroStat[3]>=heroStat[4]) levelUp();
	}	
	else
	{
	    enemyStat[i][1]-=heroDmg;
	    heroStat[1]-=enemyDmg;
	    if (heroStat[1]<=0) gameOver();
	}
	
}


private void gameOver() {
	System.out.println("Game over!");
	SetImage("background//death.jpg",0,0,Is_Map);
    
	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 
		public void handle(KeyEvent e) {
			if ((e!=null)&&(e.getEventType()==KeyEvent.KEY_PRESSED))
				switch (e.getCode())	


					 {
				case ESCAPE:
					
				    {
	            	  initMenu();	
					}
				
			 }
		}	
	});
	
}


private void levelUp() {
heroStat[0]++;
heroStat[3]-=heroStat[4];
for (int i=1;i<7;i++)
	if (i!=3)
heroStat[i]=((Double)(heroStat[i]*1.2)).intValue();

heroStat[1]=heroStat[2];
}


private  void gameProcess() {

resetStage();
keyHandler();

}

private void keyHandler() {
/*
scene.setOnMouseClicked(new EventHandler<MouseEvent>(){

	@Override
	public void handle(MouseEvent event) {
	    setImage()
		event.consume();
	}
	
});	
scene.setOnMouseReleased(new EventHandler<MouseEvent>(){

	@Override
	public void handle(MouseEvent event) {
		
		event.consume();
	}
	
	
});
*/
scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 
		public void handle(KeyEvent event) {
	
			
			CursorX_p=CursorX;
			CursorY_p=CursorY;
			moveHero(event);
            checkEnemy();
            MyStage.setScene(scene);
            MyStage.show();

            gameProcess();

		}	
	});

}

private void resetStage() {
	
	SetImage("background//background.png",0,0,Is_Map);
    displayContents();
	
}

private void displayContents() {
	
	//can use a input file to save all the addresses and stuff 

	SetImage(filePath[11],botLocation[0][0],botLocation[0][1],!Is_Map);
	SetImage(filePath[12],CursorX,CursorY,!Is_Map);

	
	displayEnemy();
	displayValues();
	
}




private void displayValues() {
	for (int i=0;i<8;i++)
		setText((((Integer)heroStat[i]).toString()),textValueLocation[i][0],textValueLocation[i][1]);
	
}

private void setText(String text, int x,int y) {
	 Text t=new Text();
	 t.setText(text);
	 t.setFont(Font.font ("Verdana",FontWeight.BOLD, 26));
	 t.setFill(Color.BLACK);
	 HBox box = new HBox();
	 box.getChildren().add(t);
	 
	  box.relocate(x, y);
	  root.getChildren().add(box);
	  MyStage.setScene(scene); 
	  MyStage.show(); 

}



private void displayEnemy() {
	for (int i=0;i<5;i++)
	 if (enemyStat[i][0]==1) SetImage(filePath[i+6],botLocation[i+1][0],botLocation[i+1][1],!Is_Map);
	
	
}




private void loadData() throws IOException {
  
enemy.enemy_Init();
readFile(".\\res\\FilePath",0);
for (int i=0;i<5;i++)
{
readFile(filePath[i],i+1);
}
}

private void readFile(String fileName,int flag) throws FileNotFoundException {
	Scanner s = null;
	try{
		s=new Scanner(new BufferedReader(new FileReader(fileName)));
		
		int i = 0 , j = 0;
		while (s.hasNext())
		{
		   switch (flag){
		   case 0:
			   {
				   filePath[i]=s.next().toString();i++;continue;
			   }
		   case 1:
		   {
			   textValueLocation[i][j]=s.nextInt();
			   break;
		   }
		   case 2:
		   {
			   botLocation[i][j]=s.nextInt();   
	           break;
		   }
		   case 3:
		   {
			   map[i][j]=!(s.next().charAt(0)== "X".charAt(0));
				 
			 	 i++;
			 	 if (i==10)
			 	 {
				  i=0;
			 	  j++;
			 	 }
			 	 continue;
		   }
		   case 4:
		   {
			   heroStat[i]=s.nextInt();
			   i++;
			   continue;
		   }
		   case 5:
		   {
			   enemyStat[i][j]=s.nextInt();   
			   j++;
				if (j==11){
					j = 0;
					i++;
				}
				continue;

			   
		   }
		   }
			j++;
			if (j==2){
				j = 0;
				i++;
			}
		}
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
	  img = new Image(file.toURI().toString(),1200,600,false,false);
	 else
	  img = new Image(file.toURI().toString(),100,100,false,false);
	 
	 background.setImage(img);
	 

	 scene.setFill(Color.BLACK);
	 HBox box = new HBox();
     box.getChildren().add(background);
 
     box.relocate(x, y);
     root.getChildren().add(box);
	 MyStage.setScene(scene); 
	
	 MyStage.show(); 

}


public static void main(String[] args){
    Application.launch(args);
	
}



}
