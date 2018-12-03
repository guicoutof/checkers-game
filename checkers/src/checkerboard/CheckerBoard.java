package checkerboard;

import java.awt.Color;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JPanel;

import sun.util.BuddhistCalendar;


/**
 * Tabuleiro.
 */
public class CheckerBoard extends JPanel{
	transient private int houseSide;
	transient private int rows;
	transient private int cols;
	private Map<Integer, CheckerHouse> houses;
	transient private Color blackHouseColor;
	transient private Color whiteHouseColor;
	transient private Color playerColor;
	transient private Color oponentColor;
	transient private Color selectionColor;
	
	transient private CheckerHouse anterior;
	transient private Color turno = Color.DARK_GRAY;
	transient private int fimTurno = 0;
	
	/**
	 * Função que irá "inverter"o campo<br> o tabuleiro não será invertido,<br> somente quem é o jogador de determinada pedra e seu oponente.
	 * @param player verifica se será necessário a inversão do lado.
	 */
	public void setPlayer2(int player) {
		if(player == 2) {
			playerColor = Color.LIGHT_GRAY;
			oponentColor = Color.DARK_GRAY;
		}
	}
	
	/**
	 *  Limpa as marcações das possiveis casas de movimentação em todo o tabuleiro.
	 */
	public void clearSelecteds() {
		CheckerHouse house;
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				house = getHouseAt(i, j);
				if(house.getSelectionMode()!=0)house.setSelectionMode(0);
			}
		}
	}
	
	/**
	 * Funcao para auxiliar na Thread,<br> ao encerrar um turno a Thread irá enviar as casas ao oponente.
	 */
	public void finalizarTurno() {
		turno = oponentColor;
		fimTurno = 1;
	}
	
	/**
	 * Funcao para auxiliar na Thread,<br> ao iniciar um turno a Thread não exercerá funcão até que o turno seja finalizado.
	 */
	public void iniciarTurno() {
		turno = playerColor;
		fimTurno = 0;
	}
	
	/**
	 * Verificação de vitoria do jogador 1.
	 */
	public boolean blackWinner() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if(getHouseAt(i,j).getFgColor()==Color.LIGHT_GRAY)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Verificação de vitoria do jogador 2.
	 */
	public boolean whiteWinner() {
		for(int i=0;i<rows;i++) {
			for(int j=0;j<cols;j++) {
				if(getHouseAt(i,j).getFgColor()==Color.DARK_GRAY)
					return false;
			}
		}
		return true;
	}
	
	/**
	 * Função para transformar a casa em rainha.
	 */
	public void verificarRainha(int row,int col) {
		if(getHouseAt(row, col).getFgColor()==Color.DARK_GRAY && row == 0)
			getHouseAt(row, col).setContentType(2);
		else if(getHouseAt(row, col).getFgColor()==Color.LIGHT_GRAY && row == 7)
			getHouseAt(row, col).setContentType(2);
	}
	
	public CheckerBoard(int rows, int cols, int rowsPieces) {
		this.rows = rows;
		this.cols = cols;
		
		houseSide = 60;
		houses = new HashMap<>();
		
		blackHouseColor = new Color(133, 94, 66);
		playerColor = Color.DARK_GRAY;
		whiteHouseColor = blackHouseColor.brighter().brighter();
		oponentColor = Color.LIGHT_GRAY;
		
		setLayout(null);
		rebuild(rows, cols, rowsPieces);
	}
	
	
	
	public void rebuild(int rows, int cols, int rowsPieces) {
		removeAll();		
		
		houses.clear();
		this.rows = rows;
		this.cols = cols;;
	
		for(int r = 0; r < rows; r++) {
			for(int c = 0; c < cols; c++) {
				CheckerHouse house = new CheckerHouse(rows - r - 1, c);				
				house.addMouseListener(new ClickListener());
				if((r + c) % 2 == 0) {
					house.setBgColor(blackHouseColor);
					if(r < rowsPieces) {
						house.setContentType(CheckerHouse.CONTENT_TYPE_MEN);
						house.setFgColor(playerColor);
					}
					else if(r >= rows - rowsPieces) {
						house.setContentType(CheckerHouse.CONTENT_TYPE_MEN);
						house.setFgColor(oponentColor);
					}
					
				}
				else {
					house.setBgColor(whiteHouseColor);
				}
				
				house.setBounds(c * houseSide, (rows - r - 1) * houseSide, houseSide, houseSide);
				
				houses.put((rows - r - 1) * cols + c, house);
				add(house);
			}
		}
	}


	public int getHouseSide() {
		return houseSide;
	}


	public void setHouseSide(int houseSide) {
		this.houseSide = houseSide;
	}

	
	
//	public CheckerHouse getHouseAt(int row, int col) {
//		if(houses.containsKey((rows - row - 1)  * cols + col)) {
//			return houses.get((rows - row - 1)  * cols + col);
//		}
//		else {
//			return null;
//		}
//	}
	
	public CheckerHouse getHouseAt(int row, int col) {
		if(houses.containsKey((cols*row) + col)){
			return houses.get((cols*row) + col);
		}
		else {
			return null;
		}
	}
	


	public int getRows() {
		return rows;
	}


	public int getCols() {
		return cols;
	}


	public Color getBlackHouseColor() {
		return blackHouseColor;
	}


	public void setBlackHouseColor(Color blackHouseColor) {
		this.blackHouseColor = blackHouseColor;
	}


	public Color getWhiteHouseColor() {
		return whiteHouseColor;
	}


	public void setWhiteHouseColor(Color whiteHouseColor) {
		this.whiteHouseColor = whiteHouseColor;
	}


	public Color getSelectionColor() {
		return selectionColor;
	}


	public void setSelectionColor(Color selectionBorderColor) {
		this.selectionColor = selectionBorderColor;
	}


	public Color getPlayerColor() {
		return playerColor;
	}


	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}


	public Color getOponentColor() {
		return oponentColor;
	}


	public void setOponentColor(Color oponentColor) {
		this.oponentColor = oponentColor;
	}
	
	public void setAnterior(CheckerHouse anterior) {
		this.anterior = anterior;
	}
	
	public CheckerHouse getAnterior() {
		return anterior;
	}

	public Map<Integer, CheckerHouse> getHouses() {
		return houses;
	}

	public void setHouses(Map<Integer, CheckerHouse> houses) {
		this.houses = houses;
	}
	
	public Color getTurno() {
		return turno;
	}

	public void setTurno(Color turno) {
		this.turno = turno;
	}

	public int getFimTurno() {
		return fimTurno;
	}

	public void setFimTurno(int fimTurno) {
		this.fimTurno = fimTurno;
	}

	
	
}
