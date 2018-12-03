package checkerboard;

import java.awt.Color;

/**
 * Classe de movimentação de uma peça.
 */
public class Move {
	private CheckerHouse house;
	private CheckerBoard checkboard;
	private CheckerHouse antigaPosicao;
	private int auxClick = 0;
	
	public Move(CheckerHouse house,CheckerBoard checkboard) {
		this.house = house;
		this.checkboard = checkboard;
	}
	
	/**
	 * A função exibiá ou realizará um movimento <br> de acordo com a peça e a vizinhança da casa escolhida <br> 
	 * Para movimentação será necessario uma confirmação para qual casa andará <br> caso há possibilidade de pular pedras, a pedra do oponente será comida automaticamente.
	 */
	public int exibirMovimento() {
			int row, col;
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
									  else if(buscarCasa(row-1,col+1)!=null && verificarCasa(row-1,col+1))
											selecionarDoisVisinhosCima(house);
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
					if(buscarCasa(row+1,col-1)!=null && verificarCasa(row+1,col-1)){//primeira casa a diagonal esquerda valida
						if(buscarCasa(row+1,col+1)!=null && verificarCasa(row+1,col+1)){//primeira casa a diagonal direita valida
							if(buscarCasa(row+2,col-2)!=null && verificarCasa(row+2,col-2)) {//segunda casa a esquerda valida
								if(buscarCasa(row+2,col+2)!=null && verificarCasa(row+2,col+2)){//segunda casa a direita valida
									if((buscarCasa(row+1,col-1)).getContentType()!=0 && (buscarCasa(row+1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col-2)).getContentType()==0){//verificacao de condicao de comida
										if((buscarCasa(row+1,col+1)).getContentType()!=0 && (buscarCasa(row+1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col+2)).getContentType()==0){//verificacao de escolha
											selecionarComerDoisVisinhosBaixo(house);
											return 2;//2 para realizar escolher um movimento de comida
										}else comerEsqBaixo(house);
									}else if((buscarCasa(row+1,col+1)).getContentType()!=0 && (buscarCasa(row+1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col+2)).getContentType()==0)
											comerDirBaixo(house);
										  else selecionarDoisVisinhosBaixo(house);
								}else if((buscarCasa(row+1,col-1)).getContentType()!=0 && (buscarCasa(row+1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col-2)).getContentType()==0)
										comerEsqBaixo(house);
									  else if(buscarCasa(row+1,col+1)!=null && verificarCasa(row+1,col+1))
											selecionarDoisVisinhosBaixo(house);
									  else selecionarEsqBaixo(house);
							}else if(buscarCasa(row+2,col+2)!=null && verificarCasa(row+2,col+2)){
									if((buscarCasa(row+1,col+1)).getContentType()!=0 && (buscarCasa(row+1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col+2)).getContentType()==0)
										comerDirBaixo(house);
									else {
										if((buscarCasa(row+1,col+1)).getContentType()==0 && buscarCasa(row+1,col+1).getContentType()==0)
												selecionarDoisVisinhosBaixo(house);
										else if((buscarCasa(row+1,col+1)).getContentType()==0)
											selecionarDirBaixo(house);
										else if((buscarCasa(row+1,col-1)).getContentType()==0)
											selecionarEsqBaixo(house);
									}
								}else {
									if((buscarCasa(row+1,col+1)).getContentType()==0 && buscarCasa(row+1,col+1).getContentType()==0)
											selecionarDoisVisinhosBaixo(house);
									else if((buscarCasa(row+1,col+1)).getContentType()==0)
										selecionarDirBaixo(house);
									else if((buscarCasa(row+1,col-1)).getContentType()==0)
										selecionarEsqBaixo(house);
								}
						}else {//andar esquerda so
							if(buscarCasa(row+2,col-2)!=null && verificarCasa(row+2,col-2)) {
								if((buscarCasa(row+1,col-1)).getContentType()!=0 && (buscarCasa(row+1,col-1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col-2)).getContentType()==0)
									comerEsqBaixo(house);
								else if(buscarCasa(row+1,col-1).getContentType()==0)
									selecionarEsqBaixo(house);
							}else if(buscarCasa(row+1,col-1).getContentType()==0)
								selecionarEsqBaixo(house);
						}
					}else {//andar direita so
						if(buscarCasa(row+1,col+1)!=null && verificarCasa(row+1,col+1)) {
							if((buscarCasa(row+1,col+1)).getContentType()!=0 && (buscarCasa(row+1,col+1)).getFgColor()==checkboard.getOponentColor() && (buscarCasa(row+2,col+2)).getContentType()==0)
								comerDirBaixo(house);
							else if((buscarCasa(row+1,col+1)).getContentType()==0)
									selecionarDirBaixo(house);
						}else if((buscarCasa(row+1,col+1)).getContentType()==0)
							selecionarDirBaixo(house);
					}
				}
			}else if(house.getContentType()==2) {//rainha
				
			}
				
			return 0;
	}
	
	/**
	 * Verificação de casas possiveis.
	 */
	private boolean verificarCasa(int row,int col){
		if((row<0 || row>7) || (col<0 || col>7))
			return false;
		return true;
	}
	
	/**
	 * Busca de uma casa no tabuleiro.
	 */
	private CheckerHouse buscarCasa(int row,int col) {
		if(verificarCasa(row,col)){
			return checkboard.getHouseAt(row, col);
		}
		return null;
	}
	
	/**
	 * Movimentações.
	 */
	private void selecionarComerDoisVisinhosCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-2, col+2);
		if(verificarCasa(row-2,col+2))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row-2, col-2);
		if(verificarCasa(row-2,col-2))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarComerDoisVisinhosBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+2, col+2);
		if(house.getContentType()==0)
		if(verificarCasa(row+2,col+2))
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row+2, col-2);
		if(verificarCasa(row-2,col-2))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDoisVisinhosCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col+1);
		if(house.getContentType()==0)
		if(verificarCasa(row-1,col+1))
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row-1, col-1);
		if(verificarCasa(row-1,col-1))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarDoisVisinhosBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col+1);
		if(verificarCasa(row+1,col+1))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
		house = checkboard.getHouseAt(row+1, col-1);
		if(verificarCasa(row+1,col-1))
		if(house.getContentType()==0)
		house.setSelectionMode(2);
	}
	
	private void selecionarEsqCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col-1);
		if(house.getContentType()==0)
		if(verificarCasa(row-1,col-1))
		house.setSelectionMode(2);
	}
	
	private void selecionarDirCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row-1, col+1);
		if(house.getContentType()==0)
		if(verificarCasa(row-1,col+1))
		house.setSelectionMode(2);
	}
	
	private void selecionarEsqBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col-1);
		if(house.getContentType()==0)
		if(verificarCasa(row+1,col-1))
		house.setSelectionMode(2);
	}
	
	private void selecionarDirBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		antigaPosicao = house;
		house = checkboard.getHouseAt(row+1, col+1);
		if(house.getContentType()==0)
		if(verificarCasa(row+1,col+1))
		house.setSelectionMode(2);
	}
	
	
	
	private void comerEsqCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col-1);
		if(verificarCasa(row-1,col-1))
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col-2);
		if(verificarCasa(row-2,col-2))
		house.setContentType(1);	
	}
	
	private void comerDirCima(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row-1, col+1);
		if(verificarCasa(row-1,col+1))
		house.setContentType(0);
		house = checkboard.getHouseAt(row-2, col+2);
		if(verificarCasa(row-2,col+2))
		house.setContentType(1);
	}
	
	private void comerEsqBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col-1);
		if(verificarCasa(row+1,col-1))
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col-2);
		if(verificarCasa(row+2,col-2))
		house.setContentType(1);
	}
	
	private void comerDirBaixo(CheckerHouse house) {
		int row = house.getRow();
		int col = house.getCol();
		
		house.setContentType(0);
		house = checkboard.getHouseAt(row+1, col+1);
		if(verificarCasa(row+1,col+1))
		house.setContentType(0);
		house = checkboard.getHouseAt(row+2, col+2);
		if(verificarCasa(row+2,col+2))
		house.setContentType(1);
	}
	
	
	
}
