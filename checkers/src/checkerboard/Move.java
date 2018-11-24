package checkerboard;

import java.awt.Color;

public class Move {
	private CheckerHouse house;
	
	public Move(CheckerHouse house) {
		this.house = house;
	}
	
	public void exibirMovimento() {
		int row, col;
		if(house.getContentType()==1) {//peao
			if(house.getFgColor()==Color.DARK_GRAY) {//preto
				row = house.getRow();
				col = house.getCol();
				if((col-1)>=0 && (row-1>=0)) {
					//setar as casas possiveis se vazias
					//if(Color.LIGHT_GRAY && getContentType()==0) {//se houver casa com peao inimiga e 
						//come a inimiga sove e a sua é movida para 
					//}
				}
				
				
				
				
				
				
			}else if(house.getFgColor()==Color.LIGHT_GRAY) {//branco
				
			}
		}else if(house.getContentType()==2) {//rainha
			
		}
	}
	
	
	
}
