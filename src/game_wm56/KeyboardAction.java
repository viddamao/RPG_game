package game_wm56;

import java.awt.event.KeyAdapter;

import javafx.scene.input.KeyEvent;

public class KeyboardAction extends KeyAdapter	{
	
public void keyPressed(KeyEvent e){
	String KeyCode=e.getText();
    System.out.print(KeyCode);
}
public void keyReleased(KeyEvent e){
	
}
}
