package com.launch.storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JOptionPane;

public class Configure implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	  String psw;// = "lalalawawawa";
	  String keyStore;// 
	  String account;// = "0x7c92f018e82ca9f4ae72c9aa4a20cd9bdf90ae36";
	  //String contractAddress = "0x99e0461fb28fd99af2973564d1c5d9cf295123fe";//"0x116aee53998b792f924f9d4f9891f8159619742c";//"0x5DA903E08D0F2Bb6cE8Ecb950E2b4baFCBbF3760";
	//final static String testContract=  "0x82636FE5d29B04918ABd892828C13568b9C38366";//"0x0B39bF7f24e4E5a0E55a57448Fa7805C69AD4f5a";
	 String HttpAddress="http://wallet-api-test.launchain.org:50000/";//"http://10.0.0.118:50000/";//

	public  String getKeyStore() {
		return keyStore;
	}
	public  void setKeyStore(String _keyStore) {
		keyStore = _keyStore;
	}
	public  String getAccount() {
		return account;
	}
	public  void setAccount(String _account) {
		account = _account;
	}

	public  String getHttpAddress() {
		return HttpAddress;
	}
	public  void setHttpAddress(String _httpAddress) {
		HttpAddress = _httpAddress;
	}


	
	  void saveCon() {
		File f=new File("config.txt");
		ObjectOutputStream objOutputStream;
		try {
			objOutputStream = new ObjectOutputStream(new FileOutputStream(f));
			objOutputStream.writeObject(this);
			objOutputStream.flush();
			objOutputStream.close();
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, e.toString(),"saveCon", JOptionPane.ERROR_MESSAGE);
		} 		
	}
	
	 static  Configure getCon() {
			File f=new File("config.txt");
			Configure con=null;
			if(f.exists()) {
				ObjectInputStream objInputStream;
				try {
					objInputStream = new ObjectInputStream(new FileInputStream(f));
					try {
						con =(Configure) objInputStream.readObject();
					} catch (ClassNotFoundException e) {
						JOptionPane.showMessageDialog(null, e.toString());
					}
					objInputStream.close();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.toString(),"getCon", JOptionPane.ERROR_MESSAGE);
					con = new Configure();
					con.saveCon();
				} 
				
			}else {
				con = new Configure();
				con.saveCon();
			}
			return con;
		}
}
