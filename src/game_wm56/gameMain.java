package game_wm56;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
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
	 displayImage("background//menuscreen.png",0,0,0);
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
	if (heroDmg<0) heroDmg=0;
	if (enemyDmg<0) enemyDmg=0;
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
	displayImage("background//death.jpg",0,0,0);
    
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
	
	displayImage("background//background_Sept1.png",0,0,0);
    displayContents();
	monsterRespawn();
}

private void monsterRespawn() {
	for (int i=0;i<4;i++)
	
		if (enemyStat[i][0]==0)
		{
			enemyStat[i][9]+=1;
			if (enemyStat[i][9]>=enemyStat[i][10])
			{
				
			    enemyStat[i][0]=1;
			    enemyStat[i][1]=enemyStat[i][2];
				enemyStat[i][9]=0;	
				enemy.addBot(enemyStat[i][7],enemyStat[i][8]);
			}
			}
	
}


private void displayContents() {
	
	//can use a input file to save all the addresses and stuff 

	displayImage(filePath[11],botLocation[0][0],botLocation[0][1],1);
	displayImage(filePath[12],CursorX,CursorY,1);
  
	
	displayEnemy();
	displayValues();
	updateBar();
	
}




private void updateBar() {
	displayImage("background//HP_Bar.png",1050,303,2);
	displayImage("background//EXP_Bar.png",1050,360,3);
	MyStage.show();

}


private void displayValues() {
	for (int i=0;i<8;i++)
		setText((((Integer)heroStat[i]).toString()),textValueLocation[i][0],textValueLocation[i][1]);
	
}

private void setText(String text, int x,int y) {
	 Text t=new Text();
	 t.setText(text);
	 t.setFont(Font.font ("Verdana",FontWeight.BOLD, 20));
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
	 if (enemyStat[i][0]==1) displayImage(filePath[i+6],botLocation[i+1][0],botLocation[i+1][1],1);
	
	
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



private void displayImage(String directory,int x,int y,int flag) {
	ImageView background=new ImageView();
	 File file = new File(".//res//"+directory);
	switch (flag){
	
	case 0:
	{
	  img = new Image(file.toURI().toString(),1200,600,false,false);
	  break;
	}
	case 1:
	{
	  img = new Image(file.toURI().toString(),100,100,false,false);
	  break;
	}
	case 2:
	case 3:
	{
      Image Myimg = new Image(file.toURI().toString(),120,22,false,false);
      img = cropBar(flag,Myimg);
	}
	} 
	 background.setImage(img);
	 

	 scene.setFill(Color.BLACK);
	 HBox box = new HBox();
     box.getChildren().add(background);
 
     box.relocate(x, y);
     root.getChildren().add(box);
	 MyStage.setScene(scene); 
	
	 MyStage.show(); 

}

private Image cropBar(int flag,Image img) {
	PixelReader reader = img.getPixelReader();
	
	if (flag==2)
	{
	System.out.println(heroStat[1]);
	System.out.println(heroStat[2]);
	System.out.println((heroStat[1]*120)/heroStat[2]);
	System.out.println();
	
	WritableImage newImage = new WritableImage(reader, 0, 0, ((heroStat[1]*120)/heroStat[2]), 22);
	return newImage;
  	
	}
	else
	{
     WritableImage newImage = new WritableImage(reader, 0, 0, ((heroStat[3])*120)/heroStat[4]+1, 22);
	 return newImage;
	  		
	}
	
	
}


public static void main(String[] args){
    Application.launch(args);
	
}



}
