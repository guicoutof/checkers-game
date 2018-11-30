

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import checkerboard.CheckerBoard;
import checkerboard.CheckerHouse;
import checkerboard.ClickListener;
import sun.security.x509.IPAddressName;

public class MainFrame extends JFrame {
	static int port = 9999;
    static boolean auxNick = true;
    static String nickname;
    static int jogador;
    static CheckerBoard checkerboard;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DatagramSocket clientSocket = null; //nÃ£o Ã© criado uma conexÃ£o entre os socket por ser UDP, portanto Datagrama
		        InetAddress IPAddress = null;
				try {
					clientSocket = new DatagramSocket();
					IPAddress = InetAddress.getByName("localhost");
				} catch (UnknownHostException e1) {
					e1.printStackTrace();
				}
				catch (SocketException e) {
					e.printStackTrace();
				}
		        
		        byte[] sendData = new byte[1024];
		        byte[] receiveData = new byte[1024];

		        do{
		        String nick = "10\n"; //login
		        nickname = JOptionPane.showInputDialog("Insira seu nickname");
		        nick += nickname;
		        nick += "\n";
		        sendData = nick.getBytes();
		        DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,IPAddress,port);
		        try {
					clientSocket.send(sendPacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        System.out.println("Aguardando servidor!");

		        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);      
		        try {
					clientSocket.receive(receivePacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        abririnterface(receivePacket,clientSocket);
		        }while(auxNick);
				
		        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);      
		        try {
					clientSocket.receive(receivePacket);
				} catch (IOException e) {
					e.printStackTrace();
				}
		        abririnterface(receivePacket,clientSocket);
		        
			}
		});
	}

	public static void abririnterface(DatagramPacket receivePacket,DatagramSocket clientSocket) {
		String sentence = new String(receivePacket.getData());
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        
        switch(sentence.charAt(0)){
        case '1':
        	auxNick = false;
        	if(sentence.charAt(1) == '1') {
        		JOptionPane.showMessageDialog(null,"Aguardando outro jogador");
        	}
        	if(sentence.charAt(1) == '2'){
        		try {
					String nick[] = sentence.split("\n");
					if(nick[1].equals(nickname)) {
						jogador = 1;
						MainFrame frame = new MainFrame(nick[1]+ " pedras Pretas",jogador);
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null,"Pedras Pretas");
						JOptionPane.showMessageDialog(null,"Você começa!");
						
						
						//CheckerBoard checkerboard = (CheckerBoard)frame.get;

						Thread cThread = new CheckersThread(clientSocket,checkerboard,nickname,jogador);
						cThread.start();
						

					}
					else if(nick[2].equals(nickname)) {
						jogador = 2;
						MainFrame frame = new MainFrame(nick[2]+ " pedras Brancas",jogador);
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null,"Pedras Brancas");
						JOptionPane.showMessageDialog(null,"Aguarde a sua vez!");
						
						//CheckerBoard checkerboard = (CheckerBoard)frame.getComponent(1);
						Thread cThread = new CheckersThread(clientSocket,checkerboard,nickname,jogador);
						cThread.start();
						
						
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        	break;
        case '2'://jogadas
        	
        	break;
        case '9'://erros
            if(sentence.charAt(1)==0){//erro no nick
                JOptionPane.showMessageDialog(null,"Nick invalido ou ja existente");
                System.out.println("Nick invalido ou ja existente");
                auxNick = true;
            }
            break;
        default:
            
            break;
        }
	}
	

	public MainFrame(String title,int jogador) {
		setTitle(title);
		setSize(new Dimension(680, 520));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		

		CheckerBoard checkerBoard = new CheckerBoard(8, 8, 3);
		checkerBoard.setPlayer2(jogador);
		this.checkerboard = checkerBoard;
		getContentPane().add(checkerBoard, BorderLayout.CENTER);
		
	}

}

class CheckersThread extends Thread{
    DatagramSocket clientSocket;
    CheckerBoard checkerBoard;
    String nickname;
    int jogador;
    int aux = jogador;



    public CheckersThread(DatagramSocket clientSocket,CheckerBoard checkerBoard,String nickname,int jogador) {
        this.clientSocket = clientSocket;
        this.checkerBoard = checkerBoard;
        this.nickname = nickname;
        this.jogador = jogador;
    }
    
    
    @Override
    public void run(){

        while(true){
            byte[] receiveData = new byte[1024];
            byte[] sendData = new byte[1024];
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            if(aux==2) {
            	try {
					clientSocket.receive(receivePacket);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	aux=0;
            	JOptionPane.showMessageDialog(null,"Seu turno!");
                checkerBoard.iniciarTurno();
                
                ByteArrayInputStream baos = new ByteArrayInputStream(receiveData);
                
                try{
                	ObjectInputStream oos = new ObjectInputStream(baos);
                	checkerBoard.setHouses((Map<Integer, CheckerHouse> )oos.readObject());
                }catch (Exception e) {
					// TODO: handle exception
				}
            }
            
            
            if(checkerBoard.getFimTurno()==1) {
            	try {
                	ByteArrayOutputStream baos = new ByteArrayOutputStream();
    	            ObjectOutputStream oos = new ObjectOutputStream(baos);
    	            
    	                oos.writeObject(checkerBoard.getHouses());
    	                oos.flush();
    	            	sendData = baos.toByteArray();
    	            	DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length,InetAddress.getByName("localhost"),9999);
    	            	clientSocket.send(sendPacket);
    	            	
    	            	clientSocket.receive(receivePacket);
    	            	JOptionPane.showMessageDialog(null,"Seu turno!");
                        checkerBoard.iniciarTurno();
                        
                        ByteArrayInputStream baosi = new ByteArrayInputStream(receiveData);
                        
                        try{
                        	ObjectInputStream oosi = new ObjectInputStream(baosi);
                        	checkerBoard.setHouses((Map<Integer, CheckerHouse> )oosi.readObject());
                        }catch (Exception e) {
        					// TODO: handle exception
        				}
                        
                        
                        
                        
                        
    	        }catch (Exception e) {
    			// TODO: handle exception
    	        }
            }
        }
    }
    
    
    
}

