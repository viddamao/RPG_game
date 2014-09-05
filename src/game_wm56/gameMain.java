package game_wm56;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public  class gameMain extends Application{
private	Group root = new Group();
private Scene scene = new Scene(root);
private Image img=null;
private boolean gameEnd=false;
private boolean isHardMode=false;
private ImageView heroNode = new ImageView();
File file = new File(".//res//hero//paladin.png");
private Image heroImg= new Image(file.toURI().toString(),100,120, false, false);

private int CursorX = 100, CursorY = 400;
private int CursorX_p = 100, CursorY_p = 400;
private Stage MyStage = null;
private boolean[][]map= new boolean [12][8];
private int[][] textValueLocation=new int [8][2];
private String[] filePath=new String[15];
private int[ ] heroStat = new int[8];
private int[][] enemyStat=new int [5][13];
private int currentI;

public void start(Stage stage) throws IOException{
   MyStage = stage;
	
    loadData();
    
    MyStage.setTitle("Legend of Paladin");
	MyStage.setResizable(false);
	MyStage.setWidth(1200);
	MyStage.setHeight(620);
    
	MyStage.show();
	initMenu();
   

	
	
}


private void initMenu() {
	
	 displayScreen(1);
	 
}


private void displayScreen(int flag) {
	ImageView Node = new ImageView();
	switch (flag){
	case 1:
	{
 	file = new File(".//res//background//menuscreen.png");
	break;
	}
	case 2:{
		
	
	file = new File(".//res//background//how to play.png");
	break;
	}
	case 3:
	{

		file = new File(".//res//background//cheat code.png");
	    break;
	}
	case 4:
	{
		file = new File(".//res//background//congratulations.png");
		break;
	}
	case 5:
	{
		file = new File(".//res//background//death.jpg");
		break;
	}
	case 6:
	{
		file = new File(".//res//background//shop_Sept4.png");
		break;
	}		
	
	}	
    Image img= new Image(file.toURI().toString(),1200,600, false, false);
    Node.setImage(img);
    Group root=new Group();
    
  
	root.getChildren().add(Node); 
	  if (flag==6)
	{
		updateBar();
		displayValues();
	}
	Scene scene = new Scene(root, 1200, 620, Color.RED);
	
	scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 
		public void handle(KeyEvent event) {
			Handle(event);

		}
	

		
		}
	
   	);
 MyStage.setScene(scene); 
    		    
  
   
		
}

private void Handle(KeyEvent event) {
	if ((event!=null)&&(event.getEventType()==KeyEvent.KEY_PRESSED))
			switch (event.getCode())	


				 {
			
			case R:
			
			case I:
			{
				isHardMode=true;
			
			
			}
		    case S:
				
			    {
			    	gameEnd=false;
			    	gameProcess();
			    	break;
				}
			
			case H:
			{
			     	displayScreen(2);
			     break;
			}
			case Q:
			{
				MyStage.close();
			    System.exit(0);
				break;
			}
			case ESCAPE:
				
		    {
        	 try {
				start(MyStage);
			} catch (IOException e) {
				e.printStackTrace();
			}
        	 break;
		    }

			case B:
			{
				displayScreen(1);
				break;
			}
			case C:
			{
				displayScreen(3);
			}
			
			case DIGIT1:
			{
			    heroStat[7]-=100;
				heroStat[1]+=heroStat[2]/2;
				if (heroStat[1]>heroStat[2])
					heroStat[1]=heroStat[2];
				
				break;
			}
			case DIGIT2:
			{
				heroStat[7]-=100;
				heroStat[5]+=10;
				break;
			}
			case DIGIT3:
			{
				heroStat[7]-=100;
				heroStat[6]+=10;
				break;
			}
				
			case DIGIT4:
			{
				CursorX=0;
				CursorY=400;
				gameProcess();  
				
			}
		default:
			break;
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

			 case A:
				 
			 heroStat[5]+=1000;
			 e.consume();

			 break;
		
			 case D:
			 heroStat[6]+=1000;

			 e.consume();
			 break;
			 
			 case H:
			 {
			 heroStat[1]= heroStat[2];
			 e.consume();
			 break;
			 }
			 case G:
			 {
			 heroStat[7]+=1000;	 
			 break;
			 }
			 case Q:
				{
					MyStage.close();
				    System.exit(0);
				}
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
		if (i==3) 
			{
			gameEnd=true;
			displayScreen(4);
			
			}
		
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
	    if (heroStat[1]<=0) {
	    	
	    	gameOver();
	    	
	    }
	}
	
}



private void gameOver() {
	 displayScreen(5);
     gameEnd=true;
	 System.out.println("Game over!");
    
}




private void levelUp() {

heroStat[0]++;
heroStat[3]-=heroStat[4];
for (int i=1;i<7;i++)
	if (i!=3)
heroStat[i]=((Double)(heroStat[i]*1.2)).intValue();

heroStat[1]=heroStat[2];
if (heroStat[3]>=heroStat[4]) levelUp();

}


private  void gameProcess() {
moveBoss();
resetStage();
keyHandler();

}

private void moveBoss() {

enemy.removeBot(enemyStat[3][8],enemyStat[3][9]);

}


private void keyHandler() {

	
scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		 
	

		public void handle(KeyEvent event) {
	
			
			CursorX_p=CursorX;
			CursorY_p=CursorY;
			moveHero(event);
            checkEnemy();
            checkMerchant();
           if (!gameEnd)
            gameProcess();

		}

	
	});

}

private void checkMerchant() {
	if((CursorX==0)&&(CursorY==500))
	{
		
	}
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

	displayImage(filePath[10],0,500,1);
	
	displayEnemy();
	updateEnemyHealthBars();
	displayValues();
	updateBar();
	if (!((CursorX==0)&&(CursorY==500)))displayImage(filePath[11],CursorX,CursorY,1);
	else 
	{
		
		root=new Group();
		scene=new Scene(root, 0, 0);
		showShopMenu();
		
	}
}




private void showShopMenu() {
	
	displayScreen(6);
}


private void updateEnemyHealthBars() {
	for (int i=0;i<4;i++)
	{
	if 	(enemyStat[i][0]!=0)
	{
	currentI=i;
	
	displayImage("background//enemyhealth.png",enemyStat[i][11],enemyStat[i][12],4);
	}
	}
	 
}


private void updateBar() {
	
	displayImage("background//HP_Bar.png",1050,303,2);
	displayImage("background//EXP_Bar.png",1050,360,3);
	 

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
	  
}



private void displayEnemy() {
	for (int i=0;i<5;i++)
	 if (enemyStat[i][0]==1) displayImage(filePath[i+5],enemyStat[i][7],enemyStat[i][8],1);
	
	
}




private void loadData() throws IOException {
	 CursorX = 100; 
	 CursorY = 400;
	 CursorX_p = 100; 
	 CursorY_p = 400;
enemy.enemy_Init();
readFile(".\\res\\FilePath",0);
heroNode.setImage(heroImg);

for (int i=0;i<4;i++)
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
			   map[i][j]=!(s.next().charAt(0)== "X".charAt(0));
				 
			 	 i++;
			 	 if (i==10)
			 	 {
				  i=0;
			 	  j++;
			 	 }
			 	 continue;
		   }
		   case 3:
		   {
			   heroStat[i]=s.nextInt();
			   i++;
			   continue;
		   }
		   case 4:
		   {
			   enemyStat[i][j]=s.nextInt();   
			   j++;
				if (j==13){
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

		 scene.setFill(Color.BLACK);
	  break;
	}
	case 1:
	{
	  img = new Image(file.toURI().toString(),100,120,false,false);
	  break;
	}
	case 2:
	case 3:
	{
      img = new Image(file.toURI().toString(),120,22,false,false);
      img = cropBar(flag,img);
      break;
	}
	case 4:
	{
		 img = new Image(file.toURI().toString(),50,10,false,false);
		 img = cropBar(flag,img) ;
		 break;
	}
	} 
	 background.setImage(img);
	 

	 HBox box = new HBox();
     box.getChildren().add(background);
 
     box.relocate(x, y);
     root.getChildren().add(box);
	 MyStage.setScene(scene); 
	
	   

}

private Image cropBar(int flag,Image img) {
	PixelReader reader = img.getPixelReader();
	
	switch (flag)
	
	{
	case 2:
	{
	WritableImage newImage = new WritableImage(reader, 0, 0, ((heroStat[1]*120)/heroStat[2]), 22);
	return newImage;
  	
	}
	case 3:
	{
     WritableImage newImage = new WritableImage(reader, 0, 0, ((heroStat[3])*120)/heroStat[4]+1, 22);
	 return newImage;
	  		
	}
	case 4:
	{
	 WritableImage newImage = new WritableImage(reader, 0, 0, ((enemyStat[currentI][1]*50)/enemyStat[currentI][2]), 10);
	 return newImage;
		 	
	}
	default:
	 return img;
	}
	
}


public static void main(String[] args){
    Application.launch(args);
	
}



}
