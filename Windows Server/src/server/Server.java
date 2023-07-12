package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) throws IOException {
    	
    	Runtime runtime = Runtime.getRuntime();
        
    	
        int brK = 1;
        String temp;
        ServerSocket server = new ServerSocket(9001);
        String poruka = "";
        System.out.println("Server je upaljen");
        while(true) {
            Konekcija conn = new Konekcija(server.accept(), brK++);
            conn.start();
            Socket novaConn = conn.soket;
            BufferedReader in = new BufferedReader(new InputStreamReader(novaConn.getInputStream()));
            PrintWriter out = new PrintWriter(novaConn.getOutputStream(), true);
            while(true) {
            	poruka = in.readLine();
            	if(poruka.equals("kraj")) {
            		break;
            	}
            	
            	switch(poruka){
            			//sleep
            		case "fF0Q1AzMaX":
            			runtime.exec("Rundll32.exe user32.dll,LockWorkStation");
            			break;
            			//shutdown
            		case "shutdown":
            			runtime.exec("shutdown -s -t 0");
            			break;
            			//chrome
            		case "chrome":
            			runtime.exec("cmd /c start chrome.exe");
            			break;
            			//sleep
            		case "sleep":
            			runtime.exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
            			break;
            		default:
            			System.out.println("Wrong command");
            	}
            	
            	
            	
                System.out.println(poruka);
            }
        }
    }
}
class Konekcija extends Thread{
    public Socket soket;
    private int brKlijenata;
    public Konekcija(Socket soket, int brKlijenata) {
        this.soket = soket;
        this.brKlijenata = brKlijenata;
    }
    @Override
    public void run() {
//
//        try {
//            BufferedReader in = new BufferedReader(new InputStreamReader(soket.getInputStream()));
//            PrintWriter out = new PrintWriter(soket.getOutputStream(), true);
    	
            System.out.println("Konektovan klijent " + brKlijenata);
            
//            out.println("Konekcija sa serverom uspesna, konektovan klijent broj: " + brKlijenata);
//        }catch(IOException ex) {
//            System.out.println("Doslo je do greske prilikom inicijalizacije servera!");
//        }
    }
}
