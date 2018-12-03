package checkerboard;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ClickListener extends MouseAdapter {
	private int auxClick = 0;
	private CheckerHouse anterior;
	@Override
	
	/**
	 * Evento do click em uma casa.
	 */
	public void mouseClicked(MouseEvent e) {
		CheckerHouse house = (CheckerHouse) e.getSource();
		CheckerBoard checkboard = (CheckerBoard) house.getParent();
		int row = house.getRow();
		int col = house.getCol();
		
		//if(auxClick == 0) {
		if(auxClick == 0 && checkboard.getTurno()==checkboard.getPlayerColor()){
			//System.out.println(row+" , "+col);
			
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
					checkboard.verificarRainha(house.getRow(),house.getCol());
					checkboard.finalizarTurno();
				//}
			}

			
			
		}
		//System.out.println(e.getX()+". "+e.getY());
		
	}
}
