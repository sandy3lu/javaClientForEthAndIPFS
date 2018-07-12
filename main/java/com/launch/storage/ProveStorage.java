package com.launch.storage;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;


public class ProveStorage {

	
	private  ECKeyPair eckey ;
	
	byte[] message;
	byte[] hash;
	SignatureData sd;
	
	ProveStorage(){
		 try {
			 eckey = org.web3j.crypto.Keys.createEcKeyPair();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	ProveStorage( ECKeyPair keys){
		 
			 eckey = keys;
		
	}
	void generateProve( int count) {
		
		if(eckey==null) {
			return;
		}
		
		String hexInput = Integer.toHexString(count);	     
		message = hexInput.getBytes();//Numeric.hexStringToByteArray(hexInput);//int2Bytes(count, 1);
		
		hash = Hash.sha3(message);
		//byte[] inputm = bytesToHexString(messageHash).getBytes();
		sd =Sign.signMessage(hash, eckey, false);			
		
		//System.out.println("message:" + Util.bytesToHexString(message) + " Hash:" + Util.bytesToHexString(hash)+ " V:"+ sd.getV() + " R:"+Util.bytesToHexString(sd.getR()) + " S:" + Util.bytesToHexString(sd.getS()));
	
	}
	

	
	
 
	 

	

}
