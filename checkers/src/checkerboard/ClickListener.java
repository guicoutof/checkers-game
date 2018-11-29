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
			int row = house.getRow();
			int col = house.getCol();
			//System.out.println(row+" , "+col);
			CheckerBoard checkboard = (CheckerBoard) house.getParent();
			if(house.getContentType() != 0) {
				if(house.getFgColor()==checkboard.getPlayerColor()) {
					house.setSelectionMode(1);
					Move moveHouse = new Move(house,checkboard);
					moveHouse.exibirMovimento();
					anterior = house;
				}
			}

			
			
		}
		//System.out.println(e.getX()+". "+e.getY());
		
	}
}
