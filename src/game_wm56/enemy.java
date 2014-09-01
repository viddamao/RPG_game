package game_wm56;


public class enemy {

private static boolean[][] Enemy = new boolean[10][6];

public static void enemy_Init(){
	for (int row = 1; row < 10; row ++)
	    for (int col = 1; col < 6; col++)
	        Enemy[row][col] = false;
	Enemy[5][3]=true;
}

public static boolean hasEnemy(int x, int y)
{
	
	if(!(x<0)||(x>10)||(y<0)||(y>6))
	return Enemy[x][y];
	else return true;
}
	
}
