package checkerboard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
	private int auxClick = 0;
	CheckerHouse anterior;
	@Override
	public void mouseClicked(MouseEvent e) {

		if(auxClick == 0){

			CheckerHouse house = (CheckerHouse) e.getSource();
			CheckerBoard checkboard = (CheckerBoard) house.getParent();
			if(house.getContentType() != 0) {
				if(house.getFgColor()==checkboard.getPlayerColor()) {
					house.setSelectionMode(1);
					anterior = house;
				}
			}

			Move moveHouse = new Move(house,checkboard);
			
		}
		//System.out.println(e.getX()+". "+e.getY());
		
	}
}
