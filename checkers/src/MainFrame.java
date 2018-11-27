

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import checkerboard.CheckerBoard;
import checkerboard.ClickListener;

public class MainFrame extends JFrame {
	static int port = 9999;
    static boolean auxNick = true;
    static String nickname;
    static int jogador;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				DatagramSocket clientSocket = null; //não é criado uma conexão entre os socket por ser UDP, portanto Datagrama
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
						MainFrame frame = new MainFrame(nick[1]+ " Pretas",jogador);
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null,"Pedras Pretas");
						JOptionPane.showMessageDialog(null,"Você começa!");
						
					}
					else if(nick[2].equals(nickname)) {
						jogador = 2;
						MainFrame frame = new MainFrame(nick[2]+ " Brancas",jogador);
						frame.setVisible(true);
						JOptionPane.showMessageDialog(null,"Pedras Brancas");
						JOptionPane.showMessageDialog(null,"Aguarde a sua vez!");
						
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
		getContentPane().add(checkerBoard, BorderLayout.CENTER);
		
	}

}
