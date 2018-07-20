package com.launch.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;


public class IpfsDaemonImplements implements Runnable{ 
	private  Process p_daemon;
	private  String ipfsCMD;
	//private Timer timer;
	private String[] env; 
	
 public void run() {  
     
	 try {
		 String[] cmd = {ipfsCMD,"daemon"};
			p_daemon = Runtime.getRuntime().exec(cmd);//,new String[]{"IPFS_PATH=c:\\launch"}
		} catch (IOException e1) {
			// 
			e1.printStackTrace();
			return ;
		}
	 
	 File f = new File("logs\\daemon.log");
	 BufferedWriter bw=null ; 
	 try {
			
		  bw = new BufferedWriter(new FileWriter(f, false)); 
		 bw.write(new Date().toString());
			 
		
	} catch (IOException e) {
		//e.printStackTrace();
	}finally {
		try {
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}  
	 
		  
	 try {
			
		  bw = new BufferedWriter(new FileWriter(new File("logs\\daemonErr.log"), false)); 
		 bw.write(new Date().toString());
			 
		
	} catch (IOException e) {
		//e.printStackTrace();
	}finally {
		try {
			bw.flush();
			bw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}				
	}  
	 
		
		 String line = null;
		
		
	 
	//  timer = new Timer();
	// TaskRead task = new TaskRead(p_daemon);
   // long period = 1000 * 60  ;// 1 min   
	//timer.schedule(task, new Date(), period);
		Thread read = new Thread(new TaskRead(p_daemon));
		//read.setDaemon(true);
		read.start();
   
		Thread readErr = new Thread(new TaskReadErr(p_daemon));
		//read.setDaemon(true);
		readErr.start();
		
		try {
			p_daemon.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		CmdParser.ipfsDaemonReady = false;
			  
	     f = new File("logs\\daemon_exit.log");
	   
			try {
				
				InputStreamReader isr  = new InputStreamReader(p_daemon.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				
				 bw = new BufferedWriter(new FileWriter(f, false)); 				
				 
				while ( (line = br.readLine()) != null) {
					bw.write(line);					
				}
				
				br.close();
				isr.close();
				
				isr  = new InputStreamReader(p_daemon.getErrorStream());
				 br = new BufferedReader(isr);
				 
				 while ( (line = br.readLine()) != null) {
						bw.write(line);
						if(line.contains("cannot acquire lock")){
							JOptionPane.showMessageDialog(null, "无法启动ipfs，请退出客户端，并在任务列表中杀死ipfs_leaf2.exe");
						}
					}
				 
				 br.close();
				isr.close();
					
			} catch (IOException e) {
				
			}finally {
				try {
					bw.flush();
					bw.close();
					
					
				} catch (IOException e) {
					
				}
				
				
			} 
			
	
 }  
 
 
 public void exit() {
	 if(p_daemon!=null) {
		 //timer.cancel();
		 p_daemon.destroy();
	 }
	
 }

public  void setIpfsDir(String _ipfsDir) {
	ipfsCMD = _ipfsDir;
}

public  void setIpfsEnv(String[] _ipfsEnv) {
	env = _ipfsEnv;
}

} 



class TaskRead implements Runnable{
	private  Process p_daemon;
    public TaskRead(Process _daemon) {
    	p_daemon = _daemon;
	}

	@Override
    public void run() {
		InputStreamReader isr ;
		BufferedReader br;
		 BufferedWriter bw=null ;
		 
		 
		 isr = new InputStreamReader(p_daemon.getInputStream());
	     br = new BufferedReader(isr);
	    String line = null;
	    File f = new File("logs\\daemon.log");
	   
			try {
				
				 bw = new BufferedWriter(new FileWriter(f, true)); 
				 
				while ( (line = br.readLine()) != null) {
					bw.write(line);
					//System.out.println(line + " " + CmdParser.ipfsDaemonReady);
					if(!CmdParser.ipfsDaemonReady) {
						if(line.contains("Daemon is ready")) {
							CmdParser.ipfsDaemonReady = true;
							//System.out.println(" found " + CmdParser.ipfsDaemonReady);
						}
					}
					
					 
				}
							
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					bw.flush();
					bw.close();
					br.close();
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}  
	
    }
}


class TaskReadErr implements Runnable{
	private  Process p_daemon;
    public TaskReadErr(Process _daemon) {
    	p_daemon = _daemon;
	}

	@Override
    public void run() {
		InputStreamReader isr ;
		BufferedReader br;
		 BufferedWriter bw=null ;
		 
		 
		 isr = new InputStreamReader(p_daemon.getErrorStream());
	     br = new BufferedReader(isr);
	    String line = null;
	    File f = new File("logs\\daemonErr.log");
	   
			try {
				
				 bw = new BufferedWriter(new FileWriter(f, true)); 
				 
				while ( (line = br.readLine()) != null) {
					bw.write(line);
					if(line.contains("cannot acquire lock")){
						JOptionPane.showMessageDialog(null, "无法启动ipfs，请退出客户端，并在任务列表中杀死ipfs_leaf2.exe");
					}					 
				}
							
				
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				try {
					bw.flush();
					bw.close();
					br.close();
					isr.close();
				} catch (IOException e) {
					e.printStackTrace();
				}				
			}  
			
			       
    }
}
