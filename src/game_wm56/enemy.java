package game_wm56;


public class enemy {

private static boolean[][] Enemy = new boolean[11][7];

public static void enemy_Init(){
	for (int row = 1; row < 10; row ++)
	    for (int col = 1; col < 6; col++)
	        Enemy[row][col] = false;
	Enemy[2][1]=true;
	Enemy[5][3]=true;
	Enemy[8][0]=true;
	Enemy[9][4]=true;

}

public static boolean hasEnemy(int x, int y)
{
	
	if(!((x<0)||(x>10)||(y<0)||(y>6)))
	{
	
	return Enemy[x][y];
	
    }
	
	else return true;
}

public static void removeBot(int cursorX, int cursorY) {
	Enemy[cursorX/100][cursorY/100]=false;
}


}
