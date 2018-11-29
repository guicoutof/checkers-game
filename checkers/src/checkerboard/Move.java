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
	
	public int exibirMovimento() {
			int row, col;
			CheckerHouse visinho;
			if(house.getContentType()==1) {//peao
				if(house.getFgColor()==Color.DARK_GRAY) {//preto
					row = house.getRow();
					col = house.getCol();
					if(buscarCasa(row-1,col-1)!=null && verificarCasa(row-1,col-1)){//primeira casa a diagonal esquerda valida
						if(buscarCasa(row-1,col+1)!=null && verificarCasa(row-1,col+1)){//primeira casa a diagonal direita valida
							if(buscarCasa(row-2,col-2)!=null && verificarCasa(row-2,col-2)) {//segunda casa a esquerda valida
								if(buscarCasa(row-2,col+2)!=null && verificarCasa(row-2,col+2)){//segunda casa a direita valida
									if((buscarCasa(row-1,col-1)).getContentType()!=0 && (buscarCasa(row-1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col-2)).getContentType()==0){//verificacao de condicao de comida
										if((buscarCasa(row-1,col+1)).getContentType()!=0 && (buscarCasa(row-1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col+2)).getContentType()==0){//verificacao de escolha
											selecionarComerDoisVisinhosCima(house);
											return 2;//2 para realizar escolher um movimento de comida
										}else comerEsqCima(house);
									}else if((buscarCasa(row-1,col+1)).getContentType()!=0 && (buscarCasa(row-1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col+2)).getContentType()==0)
											comerDirCima(house);
										  else selecionarDoisVisinhosCima(house);
								}else if((buscarCasa(row-1,col-1)).getContentType()!=0 && (buscarCasa(row-1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col-2)).getContentType()==0)
										comerEsqCima(house);
									  else selecionarEsqCima(house);
							}else if(buscarCasa(row-2,col+2)!=null && verificarCasa(row-2,col+2)){
									if((buscarCasa(row-1,col+1)).getContentType()!=0 && (buscarCasa(row-1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col+2)).getContentType()==0)
										comerDirCima(house);
									else {
										if((buscarCasa(row-1,col+1)).getContentType()==0 && buscarCasa(row-1,col+1).getContentType()==0)
												selecionarDoisVisinhosCima(house);
										else if((buscarCasa(row-1,col+1)).getContentType()==0)
											selecionarDirCima(house);
										else if((buscarCasa(row-1,col-1)).getContentType()==0)
											selecionarEsqCima(house);
									}
								}else {
									if((buscarCasa(row-1,col+1)).getContentType()==0 && buscarCasa(row-1,col+1).getContentType()==0)
											selecionarDoisVisinhosCima(house);
									else if((buscarCasa(row-1,col+1)).getContentType()==0)
										selecionarDirCima(house);
									else if((buscarCasa(row-1,col-1)).getContentType()==0)
										selecionarEsqCima(house);
								}
						}else {//andar esquerda so
							if(buscarCasa(row-2,col-2)!=null && verificarCasa(row-2,col-2)) {
								if((buscarCasa(row-1,col-1)).getContentType()!=0 && (buscarCasa(row-1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col-2)).getContentType()==0)
									comerEsqCima(house);
								else if(buscarCasa(row-1,col-1).getContentType()==0)
									selecionarEsqCima(house);
							}else if(buscarCasa(row-1,col-1).getContentType()==0)
								selecionarEsqCima(house);
						}
					}else {//andar direita so
						if(buscarCasa(row-1,col+1)!=null && verificarCasa(row-1,col+1)) {
							if((buscarCasa(row-1,col+1)).getContentType()!=0 && (buscarCasa(row-1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row-2,col+2)).getContentType()==0)
								comerDirCima(house);
							else if((buscarCasa(row-1,col+1)).getContentType()==0)
									selecionarDirCima(house);
						}else if((buscarCasa(row-1,col+1)).getContentType()==0)
							selecionarDirCima(house);
					}
					
				}else if(house.getFgColor()==Color.LIGHT_GRAY) {//branco
					row = house.getRow();
					col = house.getCol();
					
					
					
					
					
					
					
					
					
					
					
					
					
					
					
				}
			}else if(house.getContentType()==2) {//rainha
				
			}
			return 0;
	}
	
	private boolean verificarCasa(int row,int col){
		if((row<0 || row>7) && (col<0 || col>7))
			return false;
		return true;
	}
	
	private CheckerHouse buscarCasa(int row,int col) {
		if(verificarCasa(row,col)){
			return checkboard.getHouseAt(row, col);
		}
		return null;
	}
	
	private void selecionarComerDoisVisinhosCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-2, col+2);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row-2, col-2);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarComerDoisVisinhosBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+2, col+2);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row+2, col-2);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDoisVisinhosCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col+1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row-1, col-1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDoisVisinhosBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col+1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row+1, col-1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarEsqCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col-1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDirCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col+1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarEsqBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col-1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDirBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col+1);
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	
	
	private void comerEsqCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col-1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col-2);
		house.setContentType(1);	
	}
	
	private void comerDirCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col+1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col+2);
		house.setContentType(1);
	}
	
	private void comerEsqBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col-1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col-2);
		house.setContentType(1);
	}
	
	private void comerDirBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col+1);
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col+2);
		house.setContentType(1);
	}
	
	
	
}
