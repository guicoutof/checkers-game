package checkerboard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
	private int auxClick = 0;
	
	@Override
	public void mouseClicked(MouseEvent e) {
//		if(auxClick == 0){
//			CheckerHouse house = (CheckerHouse) e.getSource();
//			if(house.getContentType() != 0)house.setSelectionMode(1);
//		}
		System.out.println(e.getX()+". "+e.getY());
		
	}
}
