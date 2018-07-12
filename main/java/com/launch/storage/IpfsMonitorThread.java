package com.launch.storage;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import net.sf.json.JSONObject;

public class IpfsMonitorThread implements Runnable {
	
	
    public void run() {
    	JSONObject jsonObject = null;
       
        try {
        	
            ServerSocket ss = new ServerSocket(17888);
            byte[] buffer = new byte[8192];
           
            while (true) {
                Socket s = ss.accept();
                DataInputStream dis = new DataInputStream(s.getInputStream());
              
                dis.read(buffer);
                jsonObject = JSONObject.fromObject(new String(buffer));
               
                CmdParser.count = jsonObject.getInt("count");    
                
                //CmdParser.continueProve();               
                
                dis.close();
                s.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
