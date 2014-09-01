package game_wm56;

public class hero {

public final int Max_level=25;
	
//hero coordinate on the map, x from 1 to 10, y from 1 to 6	
public static int x=100;

public static int y=200;


//Hero type, 1 for paladin and 2 for mage
public int heroType;

//hero stats
public int playerLevel,currentHealth,totalHealth,playerAttack,playerDefense,experiencem;


public static int getX(){
	return x;
}

public static int getY(){
	return y;
}

public static void setX(int xValue){
	x=xValue;
}
public static void setY(int yValue){
	y=yValue;
}
		
}
