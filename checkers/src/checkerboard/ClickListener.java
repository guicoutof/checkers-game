package checkerboard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
	private int auxClick = 0;
	private CheckerHouse anterior;
	@Override
	public void mouseClicked(MouseEvent e) {

		if(auxClick == 0){
			CheckerHouse house = (CheckerHouse) e.getSource();
			int row = house.getRow();
			int col = house.getCol();
			//System.out.println(row+" , "+col);
			CheckerBoard checkboard = (CheckerBoard) house.getParent();
			if(house.getContentType() != 0) {
				checkboard.clearSelecteds();
				if(house.getFgColor()==checkboard.getPlayerColor()) {
					house.setSelectionMode(1);
					checkboard.setAnterior(house);;
					Move moveHouse = new Move(house,checkboard);
					auxClick = moveHouse.exibirMovimento();
				}
			}else if(house.getContentType()==0 && house.getSelectionMode()==2) {
				//if(auxClick==1) {
					anterior = checkboard.getAnterior();
					anterior.setContentType(0);
					house.setContentType(1);
					house.setFgColor(checkboard.getPlayerColor());
					auxClick=0;
					checkboard.clearSelecteds();
				//}
			}

			
			
		}
		//System.out.println(e.getX()+". "+e.getY());
		
	}
}
