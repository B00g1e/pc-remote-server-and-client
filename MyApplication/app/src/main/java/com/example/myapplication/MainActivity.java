package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    Socket s;
    InputStreamReader streamReader;
    PrintWriter out;
    EditText etKomanda;
    EditText etIP;
    EditText etPort;
    String ip;
    int port;

    public Socket getS(){
        return s;
    }

    public void setS(Socket s){
        s = this.s;
    }

    public InputStreamReader getStreamReader() {
        return streamReader;
    }

    public void setStreamReader(InputStreamReader streamReader) {
        this.streamReader = streamReader;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btDebug = findViewById(R.id.btDebug);
        Button btLock = findViewById(R.id.btLock);
        Button btShutdown = findViewById(R.id.btShutdown);
        Button btChrome = findViewById(R.id.btChrome);
        Button btSleep = findViewById(R.id.btSleep);
        Button btConnect = findViewById(R.id.btConnect);
        etKomanda = findViewById(R.id.etKomanda);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);





        new Thread(new Runnable() {
            public void run() {
                btConnect.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        ip = etIP.getText().toString();
                        if (!etPort.getText().toString().equals(null) && !etPort.getText().toString().equals("")){
                            port = Integer.parseInt(etPort.getText().toString());
                        }

                        new Thread(new Runnable() {
                            public void run() {
                                try {
                                    s = new Socket(etIP.getText().toString(), port);
                                    streamReader = new InputStreamReader(s.getInputStream());
                                    out = new PrintWriter(s.getOutputStream(),true);
                                    //TESTIRANJE
                                    btDebug.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            stampaj(out);
                                        }
                                    });
                                    //lock
                                    btLock.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            stampaj(out, "fF0Q1AzMaX");
                                        }
                                    });
                                    //shutdown
                                    btShutdown.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            stampaj(out, "explorer");
                                        }
                                    });
                                    //sleep
                                    btSleep.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                            stampaj(out, "sleep");
                                        }
                                    });
                                    //chrome
                                } catch (UnknownHostException e) {
                                    System.err.println("Unknown Host.");
                                    // System.exit(1);
                                } catch (IOException e) {
                                    System.err.println("Couldn't get I/O for "
                                            + "the connection.");
                                    System.err.println(e);
                                    //  System.exit(1);
                                }
                            }
                        }).start();

                    }
                });
            }
        }).start();


    }

    public void runChrome(View view){
            stampaj(out, "chrome");
    }

    public void stampaj(PrintWriter pw){

        new Thread(new Runnable() {
            public void run() {
                out.println(etKomanda.getText());
            }
        }).start();


    }

    public void stampaj(PrintWriter pw, String komanda){

        new Thread(new Runnable() {
            public void run() {
                out.println(komanda);
            }
        }).start();


    }

    public void changeBtConnectText(String text){

    }

}