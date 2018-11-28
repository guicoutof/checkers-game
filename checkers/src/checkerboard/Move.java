package checkerboard;

import java.awt.Color;

public class Move {
	private CheckerHouse house;
	private CheckerBoard checkboard;
	private CheckerHouse antigaPosicao;
	private int auxClick = 0;
	
	public Move(CheckerHouse house,CheckerBoard checkboard) {
		this.house = house;
		this.checkboard = checkboard;
	}
	
	public void exibirMovimento() {
		if(auxClick==0) {	
			int row, col;
			CheckerHouse visinho;
			if(house.getContentType()==1) {//peao
				if(house.getFgColor()==Color.DARK_GRAY) {//preto
					row = house.getRow();
					col = house.getCol();
					if(checkboard.getHouseAt(row-1, col-1)!=null && ((col-1)>=0 && (row-1>=0))){//primeira casa a diagonal esquerda valida
						if(checkboard.getHouseAt(row-1, col+1)!=null && ((col+1)<=7 && (row-1>=0))){//casa a diagonal direita valida
							if(checkboard.getHouseAt(row-2, col-2)!=null && ((col-2)>=0 && (row-2>=0))) {//segunda casa a esquerda valida
								if(checkboard.getHouseAt(row-2, col+2)!=null && ((col+2)<=7 && (row-2>=0))){//segunda casa a direita valida
									if((visinho=checkboard.getHouseAt(row-1, col-1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col-2)).getContentType()==0){//verificacao de condicao de comida
										if((visinho=checkboard.getHouseAt(row-1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col+2)).getContentType()==0){//verificacao de escolha
											visinho.setSelectionMode(2);
											visinho = checkboard.getHouseAt(row-2, col-2);
											visinho.setSelectionMode(2);
											auxClick = 2;//2 para realizar escolher um movimento de comida
											antigaPosicao = house;
										}else comerEsqCima(house);
									}else if((visinho=checkboard.getHouseAt(row-1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col+2)).getContentType()==0)
											comerDirCima(house);
								}else if((visinho=checkboard.getHouseAt(row-1, col-1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col-2)).getContentType()==0)
										comerEsqCima(house);
							}else if(checkboard.getHouseAt(row-2, col+2)!=null && ((col+2)<=7 && (row-2>=0)))
									if((visinho=checkboard.getHouseAt(row-1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col+2)).getContentType()==0)
										comerDirCima(house);
						}else {//andar esquerda ou direita
							if((visinho=checkboard.getHouseAt(row-1, col-1)).getContentType()==0)
								if((visinho=checkboard.getHouseAt(row-1, col+1)).getContentType()!=0) {
									visinho.setSelectionMode(2);
									visinho = checkboard.getHouseAt(row-1, col-1);
									visinho.setSelectionMode(2);
									auxClick = 1;//1 para realizar um movimento de andar normalmente
									antigaPosicao = house;
								}else {
									visinho.setSelectionMode(2);
									auxClick = 1;
									antigaPosicao = house;
								}
							else if((visinho=checkboard.getHouseAt(row-1, col+1)).getContentType()!=0) {
								visinho.setSelectionMode(2);
								auxClick = 1;
								antigaPosicao = house;
							}
						}
					}else {
						if((visinho=checkboard.getHouseAt(row-1, col-1)).getContentType()==0) {
							visinho.setSelectionMode(2);
							auxClick = 1;
							antigaPosicao = house;
						}
					}
					
				}else if(house.getFgColor()==Color.LIGHT_GRAY) {//branco
					row = house.getRow();
					col = house.getCol();
					if(checkboard.getHouseAt(row+1, col-1)!=null && ((col-1)>=0 && (row+1<=7))){//primeira casa a diagonal esquerda valida
						if(checkboard.getHouseAt(row+1, col+1)!=null && ((col+1)<=7 && (row+1<=7))){//casa a diagonal direita valida
							if(checkboard.getHouseAt(row+2, col-2)!=null && ((col-2)>=0 && (row+2<=7))) {//segunda casa a esquerda valida
								if(checkboard.getHouseAt(row+2, col+2)!=null && ((col+2)<=7 && (row+2<=7))){//segunda casa a direita valida
									if((visinho=checkboard.getHouseAt(row+1, col-1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row+2, col-2)).getContentType()==0){//verificacao de condicao de comida
										if((visinho=checkboard.getHouseAt(row+1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row+2, col+2)).getContentType()==0){//verificacao de escolha
											visinho.setSelectionMode(2);
											visinho = checkboard.getHouseAt(row+2, col-2);
											visinho.setSelectionMode(2);
											auxClick = 2;//2 para realizar escolher um movimento de comida
											antigaPosicao = house;
										}else comerEsqBaixo(house);
									}else if((visinho=checkboard.getHouseAt(row+1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row+2, col+2)).getContentType()==0)
											comerDirBaixo(house);
								}else if((visinho=checkboard.getHouseAt(row+1, col-1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row-2, col-2)).getContentType()==0)
										comerEsqBaixo(house);
							}else if(checkboard.getHouseAt(row+2, col+2)!=null && ((col+2)<=7 && (row+2<=7)))
									if((visinho=checkboard.getHouseAt(row+1, col+1)).getContentType()!=0 && (visinho=checkboard.getHouseAt(row+2, col+2)).getContentType()==0)
										comerDirBaixo(house);
						}else {//andar esquerda ou direita
							if((visinho=checkboard.getHouseAt(row+1, col-1)).getContentType()==0)
								if((visinho=checkboard.getHouseAt(row+1, col+1)).getContentType()!=0){
									visinho.setSelectionMode(2);
									visinho = checkboard.getHouseAt(row-1, col-1);
									visinho.setSelectionMode(2);
									auxClick = 1;//1 para realizar um movimento de andar normalmente
									antigaPosicao = house;
								}else {
									visinho.setSelectionMode(2);
									auxClick = 1;
									antigaPosicao = house;
								}
							else if((visinho=checkboard.getHouseAt(row+1, col+1)).getContentType()!=0){
								visinho.setSelectionMode(2);
								auxClick = 1;
								antigaPosicao = house;
							}
						}
					}else {
						if((visinho=checkboard.getHouseAt(row+1, col-1)).getContentType()==0){
							visinho.setSelectionMode(2);
							auxClick = 1;
							antigaPosicao = house;
						}
					}
				}
			}else if(house.getContentType()==2) {//rainha
				
			}
		}
	}
	
	public void comerEsqCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col-1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col-2);
		house.setContentType(1);
		
		
	}
	
	public void comerDirCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col+1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col+2);
		house.setContentType(1);

	}
	
	public void comerEsqBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col-1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col-2);
		house.setContentType(1);
		
	}
	
	public void comerDirBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col+1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col+2);
		house.setContentType(1);
		
	}
	
	
	
}
