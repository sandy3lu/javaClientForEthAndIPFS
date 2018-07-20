package com.launch.storage;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.InvalidAlgorithmParameterException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutionException;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.LookAndFeel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.TabbedPaneUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import org.bouncycastle.util.Strings;
import org.pushingpixels.substance.api.SubstanceConstants.ImageWatermarkKind;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.SubstanceSkin;
import org.pushingpixels.substance.api.skin.*;

import org.pushingpixels.substance.api.watermark.SubstanceImageWatermark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;
//import org.web3j.protocol.Web3jFactory;
import org.web3j.utils.Numeric;

import com.launch.storage.StorageCoin.ReNewFileInfoEventResponse;
//import com.launch.storage.StorageCoin.StoreInfoEventResponse;
import com.launch.storage.StorageCoin.UploadInfoEventResponse;

import rx.Observable;
import rx.Subscriber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;  

public class CmdParser {
	static Credentials credentials;
	static Web3j web3;
	final static String tag = "wallet-StorageCoin: ";
	final static String contractAddress = "0x2c47b6c642eed10c731efe56ca825b2473a6202a";//"0x99e0461fb28fd99af2973564d1c5d9cf295123fe";
	static StorageCoin contract;
	
	//for test perpus
	final static String testcontractAddress = "0xb9763eb250b94667dae1a95a28673dd8faf6da30";
	static TokenRewards trcontract;
	static boolean ipfsDaemonReady=false;
	
	static Configure con;
	
	static Logger logfile ;
	private static ProveStorage ps;
	
	private static JLabel lbl_balance;
	private static  JLabel lbl_balance2;
	static DefaultTableModel dtmFileList;
	static  JButton button_add ;
	static JFrame frameShowed;
	static JFrame ff_process;
	static Map<String, JLabel> map=new HashMap<>();
	static JSONArray arr;
	//static JSONArray arr_tx;
	static DefaultListModel<String> lstModel_share;
	static DefaultListModel<String> listMode_up;
	static JProgressBar processBar;
	static JLabel lbl_process;
	
	private static Thread t;
	private static Thread thread_ipfsDaemon;
	private static IpfsDaemonImplements mi;
	
	private static Thread t_prove;
	static int count;
	
	 static String osName = System.getProperty("os.name");
	 static String ipfsDir;
	 static String ipfsCMD; 
	 private static String ipfsNode;
	 private static HashSet<String> peers;
	 static Boolean onLine = false;
	 static boolean waiting;
	 static boolean first = false;
	 
	static ArrayList<JSONObject> files=new ArrayList<JSONObject>();
	
	private static Boolean connectToContract(boolean showMessage) {
		onLine = false; 
		
		if(con.getKeyStore()!=null) {
			
				//String pass = JOptionPane.showInputDialog(null,"输入密码: \n","密码",JOptionPane.PLAIN_MESSAGE);
				 JDialog  f = new JDialog (frameShowed, "输入密码:", true);
			     f.setSize(200, 160);
			    //Point p = frameShowed.getLocationOnScreen();
			    f.setLocationRelativeTo(null);
			    // f.setLocation(200,200);
			     
			     f.setLayout(null);
				JPanel j = new JPanel();
				f.setContentPane(j);
				j.setLayout(new BorderLayout());
				JLabel ll = new JLabel("请输入密码：");
				j.add(ll, BorderLayout.NORTH);
				JPasswordField txt = new JPasswordField();
				j.add(txt, BorderLayout.CENTER);
				 JButton b_ok = new JButton("确定");
				 b_ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						onLine = false;
						try {
							String pass = String.valueOf(txt.getPassword());
							
							 credentials =WalletUtils.loadCredentials(pass, con.getKeyStore());		 
								
						    BigInteger gasPrice = Convert.toWei("1", Convert.Unit.GWEI).toBigInteger();
						    BigInteger gasLimit = new BigInteger("400000");
								
							contract = StorageCoin.load(contractAddress , web3, credentials, gasPrice, gasLimit);
							    
							 if(!first) {
							    								 
								    t_prove = new Thread(new IpfsMonitorThread());
								    t_prove.setDaemon(true);
								    t_prove.start();
								    
									first = true;
							 }
								 
							 onLine = true;  
							 if(showMessage) {
								 JOptionPane.showMessageDialog(f, "认证成功！","成功",JOptionPane.INFORMATION_MESSAGE);
							 }
							
							
						}catch(Exception e1) {
							if(e1.toString().contains("Invalid password")) {
								JOptionPane.showMessageDialog(f, "密码错误！","失败",JOptionPane.INFORMATION_MESSAGE);
							}else {
								JOptionPane.showMessageDialog(f, e1.getMessage(),"失败",JOptionPane.INFORMATION_MESSAGE);
							}
							
							logfile.error("connectToContract"+e1.toString());
							
						}finally{
							 
							 f.dispose();
						}						
						
					}});
				 
				 j.add(b_ok, BorderLayout.SOUTH);
				f.setVisible(true);
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			
		}
		
		return onLine;
	}
	
	public static void main(String[] args) {
		
		 logfile = LoggerFactory.getLogger(CmdParser.class); 	
		 con= Configure.getCon();	
		
		peers = new HashSet<String>();
		
		 mi = new IpfsDaemonImplements();  
		 
		if(args.length>0) {
			ipfsDir = args[0];			
			ipfsCMD = ipfsDir+File.separator+"ipfs_leaf2";
			String[] env = new String[]{"IPFS_PATH="+ipfsDir};
			mi.setIpfsEnv(env);
		}else {
			 if(osName.toLowerCase().startsWith("win")){  
			    	ipfsDir = System.getProperty("user.home")+File.separator+".ipfs";
			    	String s = System.getProperty("user.dir");
			    	 ipfsCMD = s + File.separator+"go-ipfs"+File.separator + "ipfs_leaf2.exe";  
			    }else {//linux
			    	ipfsDir =System.getenv("HOME")+File.separator+".ipfs";//"/home/sandy/.ipfs";
			    	ipfsCMD = "ipfs_leaf2"; 
			    }  
			 mi.setIpfsEnv(null);
		}
		logfile.info("ipfsCMD = " + ipfsCMD + "; ipfsDir= " + ipfsDir);
		 mi.setIpfsDir(ipfsCMD);	
			
	   	 thread_ipfsDaemon = new Thread(mi);  // start daemon	
	   	 thread_ipfsDaemon.setDaemon(true);
	     thread_ipfsDaemon.start(); 
	        
	     ipfsNode = getIpfsNode();
	 
		  ps = new ProveStorage();		
			web3 = Web3j.build(new HttpService(con.getHttpAddress()));
			
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
		
			 try {
					UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
				} catch (UnsupportedLookAndFeelException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
			 
			 SwingUtilities.invokeLater(new Runnable() {  
	                public void run() {  
	                    
	                	showFrame(); //这个就是程序界面初始化
	                }  
	            });  
			 
			 SwingUtilities.invokeLater(new Runnable() {  
	                public void run() {  
	                    
	                	ff_process = showProcessWindow(); //这个就是程序界面初始化
	                }  
	            });  
			 
			
	}
	
	static JFrame showProcessWindow() {
		JFrame   f   =   new   UndecoratedFrame( "正在进行的任务列表"); 
		f.setSize(400,   400); 
		f.setLocationRelativeTo(null); 
		f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 		
		f.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 20, 40));// 向左对齐,水平间距是20，垂直间距为40像素
		 f.setVisible(false);
	    return f; 
	}

	static boolean sendAddTaskToProcess(String fileName, String fileHash, int _count) {
		
		
		 SwingUtilities.invokeLater(new Runnable() {  
	          public void run() {  
	        	  JLabel ll = new JLabel(fileName);
	        	  Timer timer = new Timer();
	        	  //前一次执行程序结束后 2000ms 后开始执行下一次程序
	              timer.schedule(new TimerTask() {
	                  @Override
	                  public void run() {
	                	 
	                	  JSONObject obj = getFileStorageFromServer(fileHash);
	                	 
	                      if(obj!=null) {
	                    	 JSONArray count = obj.getJSONArray("info");
	                    	 if (count.size() != 0) {
	                    		 ll.setText(fileName + " " + count.size());
	                    		 if (count.size()>= _count) {
	                    			 return; // exit this task
	                    		 }
	                    		 ff_process.repaint();
	                    		 System.out.println(fileName + " 已完成 " + count.size() +  "/" + _count);
	                    	 }
	                     }
	                  }
	              }, 0,2000);

	        	  
	        	  ff_process.getContentPane().add(ll);
	              ff_process.setVisible(true);
	          }  
	      });  
		 
	     
	return true;	 
		
	}
	
	
	static int getCoinBalance() {
		
		if(!onLine) {	
			button_add.setEnabled(false);
			return 0;
		}
		
		try {
			
			EthGetBalance ethGetBalance = web3.ethGetBalance(con.getAccount(), DefaultBlockParameterName.LATEST).sendAsync().get();
            BigInteger coinValue = ethGetBalance.getBalance();           
            
            float money = (float) (coinValue.floatValue()/1e18);
            DecimalFormat   fnum   =   new   DecimalFormat("##0.00");
            int money_int = (int)money;
			lbl_balance.setText("余额 : " + fnum.format(money));
			lbl_balance2.setText(lbl_balance.getText());
			
			 if(money>1) {
		        	button_add.setEnabled(true);
		        }
			return money_int;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"getCoinBalance", JOptionPane.ERROR_MESSAGE);
			logfile.error("getCoinBalance : "+e.toString());
		} 
		return 0;
	}
	
		
	 /*
	 static boolean addBootstrap() {

		 Process p;
		
			try {
				String[] cmds = {ipfsCMD, "bootstrap", "add", "/ip4/47.92.166.231/tcp/4001/ipfs/QmU5KpAJNSkgU9NivJ7K5jspYiMvHehgcEkxAgV5abEDTR"};
				p = Runtime.getRuntime().exec(cmds);
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}

					
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				return false;
			}
			if (p.exitValue() != 0) {
				clearForProcess(p,p.getErrorStream());
				return false;
			}else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	 			String response;
				try {
					response = reader.readLine();
					if(response ==null) {
						// no other point on line
						return true;
					}
					if(response.contains("Error: api not running")) {
		 				
						return false;
		 			}else {
		 				clearForProcess(p,p.getInputStream());
						return true;
		 			}			
				} catch (IOException e) {
				
					e.printStackTrace();
					return false;
				}				
			}
	 
	 }
	 */
	 static boolean testIpfsOnLine() {
		 Process p;
		 if(!ipfsDaemonReady){
			 JOptionPane.showMessageDialog(frameShowed, "ipfs daemon 尚未启动","testIpfsOnLine", JOptionPane.ERROR_MESSAGE);
			 return false;
		 }
		 
		 peers.clear();
		 
			try {
				String[] cmds = {ipfsCMD, "swarm", "peers"};
				p = Runtime.getRuntime().exec(cmds);
			} catch (IOException e) {
				
				JOptionPane.showMessageDialog(null, e.toString(),"testIpfsOnLine", JOptionPane.ERROR_MESSAGE);
				logfile.error("testIpfsOnLine : "+ e.toString());
				return false;
			}

				
			try {
				p.waitFor();
			} catch (InterruptedException e) {
				
				JOptionPane.showMessageDialog(null, e.toString(),"testIpfsOnLine", JOptionPane.ERROR_MESSAGE);
				logfile.error("testIpfsOnLine : "+ e.toString());
				return false;
			}
			if (p.exitValue() != 0) {
				String tag = "swarm peers with " + p.exitValue();
				clearForProcess(tag,p,p.getErrorStream());
				clearForProcess(tag,p,p.getInputStream());
				JOptionPane.showMessageDialog(null, "wrong! swarm peers with code "+p.exitValue(),"testIpfsOnLine", JOptionPane.ERROR_MESSAGE);
				p.destroy();
				return false;
			}else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	 			String response;
				try {
					response = reader.readLine();
					if(response ==null) {
						// no other point on line
						return false;
					}
					if(response.contains("ipfs/Qm")) {
		 				// get count
						
						int index = response.indexOf("ipfs/Qm");						
						peers.add(response.substring(index-2));
						
						while((response = reader.readLine())!=null) {
							 index = response.indexOf("ipfs/Qm");
							 if(index>2) {
								 peers.add(response.substring(index-2)); 
							 }
							
						}
						return true;
		 			}else {
		 				clearForProcess("swarm peers with wrong response " + response , p,p.getInputStream());
						return false;
		 			}			
				} catch (IOException e) {
				
					logfile.error("testIpfsOnLine : "+ e.toString());
					return false;
				}	
	 			
			}
	 }
	 
	 
	 
	 // get available space in G
	 static float getSpace() {
		 float totalHD=0;
		 if (osName.toLowerCase().contains("win")) {
			 int index = ipfsDir.indexOf(":");			 
			 String dirName = ipfsDir.substring(0, index+2);
			 File win = new File(dirName);
			 if (win.exists()) {
				// long total = (long) win.getTotalSpace();
				 long free = (long) win.getFreeSpace();
				 return (free/1024/1024/1024);//G
			 }else {
				 return 0;
			 }
		 }else {//linux
			 Runtime rt = Runtime.getRuntime();
			 Process p;
			try {
				p = rt.exec("df -hl /home");
				 BufferedReader in = null;
				 try {
				 in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				 String str = null;
				 String[] strArray = null;
				
					 while ((str = in.readLine()) != null) {
						 if(str.contains("/home")) {
							 int m = 0;
							 strArray = str.split(" ");
							 for (String tmp : strArray) {
								 //Filesystem      Size  Used Avail Use% Mounted on
								 ///dev/sda5       1.6T   15G  1.5T   1% /home
								 if (tmp.trim().length() == 0)
									 continue;
								 ++m;
								 if (m == 4) {//size
									 if (tmp.indexOf("G") != -1) {
									 
									 if (!tmp.equals("") && !tmp.equals("0")) {
										 totalHD = Float.parseFloat(tmp.substring(0, tmp.length() - 1));
										 
									 }
								 	}else if(tmp.indexOf("T") != -1) {
								 		if (!tmp.equals("") && !tmp.equals("0")) {
											 totalHD = Float.parseFloat(tmp.substring(0, tmp.length() - 1))*1024;
										 }
								 	}
								 }
							 }
							 
						 }
					 }
				 } catch (Exception e) {
					 e.printStackTrace();
					 logfile.error("getSpace : "+ e.toString());
				 } finally {
					 in.close();
				 }
			} catch (IOException e1) {
				
				e1.printStackTrace();
				logfile.error("getSpace : "+ e1.toString());
			}// df -hl 查看硬盘空间
			
		 }
		 return totalHD;
	 }
	
	 
	 static int estimateValue(int size) {
		 if(size>2048) {
			 return 20;
		 }
		 if(size>1024) {
			 return 10;
		 }
		 if(size>500) {
			 return 5;
		 }
		 if(size>200) {
			 return 2;
		 }
		 return 1;
	 }
	

	 static void getKeyStore(JButton button_change, JButton button_login, JTextField txt_account, JTextField txt2) {
		  	//need password
        JFrame ff = new JFrame("keystore");
	     ff.setSize(500, 300);
	     ff.setLocationRelativeTo(null);
	     //ff.setLocation(200, 200);
	     ff.setLayout(null);
	     
	     JPanel pp = new JPanel();
	     ff.setContentPane(pp);
	     
	     pp.setLayout(new BorderLayout());
	     JTextArea tt = new JTextArea();
	     tt.setLineWrap(true);        //激活自动换行功能 
	  pp.add(tt, BorderLayout.CENTER);
	  
	  JPanel ppp = new JPanel();
	  pp.add(ppp, BorderLayout.SOUTH);
	  
	  JButton bok = new JButton("确定");
	  ppp.setLayout(new FlowLayout(FlowLayout.CENTER));
	  JButton bcancel = new JButton("取消");
	  ppp.add(bok);
	  ppp.add(bcancel);
	  
	  
	  JPopupMenu pop = new JPopupMenu();	
	  JMenuItem paste = new JMenuItem("粘贴");
	  pop.add(paste);	  
	  //paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));

	  paste.addActionListener(new ActionListener() {
		  public void actionPerformed(ActionEvent e) {
		    tt.paste();
		  }
		  }); 
	  tt.add(pop);
	  tt.addMouseListener(new MouseListener() {

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			
			if (e.getButton() == MouseEvent.BUTTON3) {
				Clipboard clipboard = ff.getToolkit().getSystemClipboard();
				Transferable content = clipboard.getContents(this);
				try {
					if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
						paste.setEnabled(true);
					}else {
						paste.setEnabled(false);
					}
				} catch (Exception e0) {
					logfile.error("getKeyStore [mousePressed] : "+ e0.toString());
				} 
				
				pop.show(ff, e.getX(), e.getY());
				} 
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}});
	     ff.setVisible(true);
	     
	     bcancel.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				ff.dispose();
				 frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		   		 frameShowed.getGlassPane().setVisible(false);
		   		button_change.setEnabled(true); 
			} 
	    	 
	     });
	     
	  bok.addActionListener(new ActionListener(){ 
		public void actionPerformed(ActionEvent arg0) {
			String keystore = tt.getText();
			if(keystore==null) {
				JOptionPane.showMessageDialog(null, "无效的keystore!");
				return;
			}
			if(!keystore.contains("ciphertext")) {
				JOptionPane.showMessageDialog(null, "无效的keystore!");
				return;
			}
			if(!keystore.contains("address")) {
				JOptionPane.showMessageDialog(null, "无效的keystore!");
				return;
			}
		
			ff.setVisible(false);
	          boolean result = changeAccount(  keystore);
	          
	          if(!result) {
	        	  // retry
	        	  result = changeAccount(  keystore);
	          }
           
            if(result) {
            	txt_account.setText(con.getAccount());
            	txt2.setText(txt_account.getText());
            	button_login.setEnabled(true);
            	refreshTxList();
            	refreshFileList();
                
            }else {
            	// password wrong, reinput
            	
            }
         
            button_change.setEnabled(true); 
    	    frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
   		 	frameShowed.getGlassPane().setVisible(false);     		
            ff.dispose();
            
            return ;
	            
		} 
		
	  });
	 }
	 
	static void showFrame() {
		Dimension dimension = new Dimension(1400, 800);
		final Point origin = new Point(200, 200);
		boolean f_opaque = true;
			
		 frameShowed = new JFrame("元链网盘");
	     frameShowed.setSize(dimension);	     
	     frameShowed.setLocation(origin);
	     frameShowed.setLayout(null);
	     File f_img= new File("logo.png");
	     if(f_img.exists()) {
	    	 frameShowed.setIconImage(new ImageIcon("logo.png").getImage());
	     }
	     	     
	     MouseInputListener ml = new MouseInputAdapter() {};
	     frameShowed.getGlassPane().addMouseListener(ml);
	     frameShowed.getGlassPane().addMouseMotionListener(ml);
	   	  
	    
	     Util.changeLook(frameShowed);
	         
	     JTabbedPane jTabbedpane = new JTabbedPane();// 存放选项卡的组件    
	    
         String[] tabNames = { "共享", "用户" };   
         // 添加选项卡选中状态改变的监听器
         jTabbedpane.addChangeListener(new ChangeListener() {
             @Override
             public void stateChanged(ChangeEvent e) {
            	 //JPanel p = (JPanel) jTabbedpane.getSelectedComponent();
            	 frameShowed.pack();
            	
             }
         });
                 
       /*  
	     JPanel panel_layer = new JPanel();
	     panel_layer.setOpaque(false);
	     JPanel panel_account = new JPanel();
	     panel_account.setOpaque(false);
	     panel_layer.setLayout(new BorderLayout());
	     panel_layer.add(panel_account,BorderLayout.NORTH);
	     panel_layer.add(jTabbedpane,BorderLayout.CENTER);
	        
	     GridBagLayout layout_account = new GridBagLayout();
	     panel_account.setLayout(layout_account);
	     GridBagConstraints s0= new GridBagConstraints();
	     s0.insets = new Insets(2, 2, 2, 2); 
	     s0.fill = GridBagConstraints.BOTH;
        */ 
         frameShowed.setContentPane(jTabbedpane);
            
	        
        // 第一个标签下的JPanel  
        JPanel jpanelFirst = new JPanel();  		
        jTabbedpane.addTab(tabNames[0], null, jpanelFirst, "share");// 加入第一个页面  
        jTabbedpane.setMnemonicAt(0, KeyEvent.VK_0);// 设置第一个位置的快捷键为0  
        GridBagLayout layout = new GridBagLayout();
        jpanelFirst.setLayout(layout);
       // jpanelFirst.setOpaque(f_opaque);
        GridBagConstraints s= new GridBagConstraints();
        s.insets = new Insets(2, 2, 2, 2); // 组件彼此的间距
        s.fill = GridBagConstraints.BOTH;//使组件完全填满其显示区域
        
        // 第二个标签下的JPanel  
        JPanel jpanelSecond = new JPanel();  
       // jpanelSecond.setOpaque(f_opaque);
        jTabbedpane.addTab(tabNames[1], null, jpanelSecond, "user");
        jTabbedpane.setMnemonicAt(1, KeyEvent.VK_1);// 设置快捷键为1  
        GridBagLayout layout2 = new GridBagLayout();
        jpanelSecond.setLayout(layout2);
        GridBagConstraints s2= new GridBagConstraints();
        s2.insets = new Insets(2, 2, 2, 2); // 组件彼此的间距
        s2.fill = GridBagConstraints.BOTH;
        
       
        // first line  of panel 1      
        JLabel lbl_account =new JLabel("钱包地址"); 
        jpanelFirst.add(lbl_account);        
        JTextField txt_account = new JTextField(26);
        txt_account.setEnabled(false); 
        jpanelFirst.add(txt_account);  
        JButton button_login= new JButton("登录");       
        txt_account.setText(con.getAccount());          	
        if (txt_account.getText().length()>5) {
        	button_login.setEnabled(true);
        }else {
        	button_login.setEnabled(false);
        }
        
        JButton button_change= new JButton("更换帐号");  
        
        JButton button_exit= new JButton("退出");   
        button_exit.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					mi.exit();
	            	
	            	         	
				}catch(Exception e1) {
					
				}
				
                System.exit(0);
				
			} });
        
        JPanel pan_fill_exit = new JPanel();       
        pan_fill_exit.setOpaque(f_opaque);
        jpanelFirst.add(button_login);        
        jpanelFirst.add(button_change);
        jpanelFirst.add(pan_fill_exit); 
        jpanelFirst.add(button_exit); 
       
        
        s.gridwidth=1;//该方法是设置组件水平所占用的格子数
        s.weightx = 0;//该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        s.weighty=0;//该方法设置组件垂直的拉伸幅度
        layout.setConstraints(lbl_account, s);
        s.gridwidth=20;      
        s.weightx = 0;       
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(txt_account, s);
        s.fill = GridBagConstraints.NONE;
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(button_login, s);//设置组件
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(button_change, s);//设置组件       
        s.gridwidth=10;
        s.weightx = 1;
        s.weighty=0;
        layout.setConstraints(pan_fill_exit, s);//设置组件    
        s.gridwidth=GridBagConstraints.REMAINDER;//GridBagConstraints.REMAINDER为0，就说明该组件是该行的最后一个
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(button_exit, s);//设置组件
        
       // line 1 of panel 2 == panel 1
        JLabel lbl2 =new JLabel(lbl_account.getText());   
        JTextField txt2 = new JTextField();
        txt2.setText(txt_account.getText());
       
        JButton button_delete = new JButton("删除"); 
        
        button_login.addActionListener(new ActionListener(){       	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		
           		button_login.setEnabled(false);
                frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       		 	frameShowed.getGlassPane().setVisible(true);
       		 	
           			if (connectToContract(true)) {
           				getCoinBalance();      				
           				refreshFileList();
        				refreshTxList();
           			}
           			button_login.setEnabled(true);
           		 frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        		 	frameShowed.getGlassPane().setVisible(false);
           			return;
           	
           	}  
           	
        });  
        
        button_change.addActionListener(new ActionListener(){       	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		
           		button_change.setEnabled(false);
                frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
       		 	frameShowed.getGlassPane().setVisible(true);
       			
           	 getKeyStore(button_change, button_login,txt_account, txt2);
            
           	}  
           	
        });  
        
        jpanelSecond.add(lbl2);
        jpanelSecond.add(txt2);       
        txt2.setEnabled(false);        
       JPanel pan_fill2 = new JPanel();
       pan_fill2.setOpaque(f_opaque);
       jpanelSecond.add(pan_fill2); 
        
        // second line of panel 1
        lbl_balance =new JLabel("余额: 0");     
        jpanelFirst.add(lbl_balance);//1.2
        
        
        JButton button_test = new JButton("获取测试币");        
        button_test.addActionListener(new ActionListener(){       	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		
           		if(onLine) {
           			
           			int value = getCoinBalance();
           			if(value>1) {
           				JOptionPane.showMessageDialog(null, "只有余额低于1才能获取");
           				return;
           			}
           			BigInteger gasPrice = Convert.toWei("0", Convert.Unit.GWEI).toBigInteger();
				    BigInteger gasLimit = new BigInteger("400000");
						
           			trcontract = TokenRewards.load(testcontractAddress , web3, credentials, gasPrice, gasLimit);
           			
           			try {
						TransactionReceipt transactionReceipt = CmdParser.trcontract.tokenRewards().sendAsync().get();
						if(transactionReceipt.getStatus().contains("1")) {
							JOptionPane.showMessageDialog(null, "成功获取");
							getCoinBalance();
						}else {
							JOptionPane.showMessageDialog(null, "获取失败，只有余额低于1才能获取");
							logfile.error("button_test fail, tx=" + transactionReceipt.toString());
						}
					} catch (InterruptedException | ExecutionException e) {
						
						logfile.error("button_test fail " + e.toString());
					}
           			
           		}else {
           			JOptionPane.showMessageDialog(null, "请先登陆"); 
           		}
            
           	}  
           	
        });  
        
        jpanelFirst.add(button_test);
        JPanel pan_fill_12 = new JPanel(); 
        pan_fill_12.setOpaque(f_opaque);
        jpanelFirst.add(pan_fill_12);//fill Gap
        
        
     // second line of panel 2
        lbl_balance2 =new JLabel(lbl_balance.getText());  
        jpanelSecond.add(lbl_balance2);//2, same with first         
        JPanel jpanel2 = new JPanel(); 
        jpanel2.setOpaque(f_opaque);
        jpanelSecond.add(jpanel2);//fill Gap
      
        
        // third line of panel 1        
        JLabel lbl3 =new JLabel("空间大小(G):");  
        JButton button_cap = new JButton("确定");
        button_cap.setEnabled(false);
        JTextField txt_ipfsCap = new JTextField(8);
        txt_ipfsCap.setText(Util.getIpfsCapacity(ipfsDir));       
        txt_ipfsCap.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				
				String newCount = txt_ipfsCap.getText().trim();
				if(newCount.length()>0) {
					byte[] digit = newCount.getBytes();
					for (byte b : digit) {
						if((b>0x39) || (b<0x30)) {
							JOptionPane.showMessageDialog(jpanelFirst, "输入应该是 0-9", "错误的输入",JOptionPane.WARNING_MESSAGE); 
							return;
						}
					}
					button_cap.setEnabled(true);
				}
			}
			@Override
			public void removeUpdate(DocumentEvent arg0) {
				// TODO Auto-generated method stub
				
			}  });
                
        
        button_cap.addActionListener(new ActionListener(){       	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		button_cap.setEnabled(false);
           	 float used_d = Util.getDirSize(new File(ipfsDir))/1024;
           	  float total_available = getSpace();
           	  String num = txt_ipfsCap.getText().trim();
           	  // extra 1G for other space which we may not consider
           	  Integer number = Integer.valueOf(num);
           	  if((number-used_d)>(total_available-1)) {
           		JOptionPane.showMessageDialog(jpanelFirst, num + " 超过可以设置的范围!");
           		return;
           	  }
           		if(Util.updateIpfsCapacity(num, ipfsDir)) {
           			JOptionPane.showMessageDialog(jpanelFirst, "更新IPFS配置成功，重启客户端之后生效!");
           		}
           		
           	}
        }) 	;
        
        jpanelFirst.add(lbl3);
        jpanelFirst.add(txt_ipfsCap);
        jpanelFirst.add(button_cap);
        
        JLabel lbl_used =new JLabel("");
        float used_d = Util.getDirSize(new File(ipfsDir));
        lbl_used.setText("已用空间(M): "+String.valueOf(used_d)+ "   ");
        JLabel lbl_available =new JLabel(); 
        float total_available = getSpace();
        lbl_available.setText("硬盘剩余空间(G): "+String.valueOf(total_available)+"         ");
        jpanelFirst.add(lbl_used );
        jpanelFirst.add(lbl_available);
        
               
        JButton button_detail = new JButton("节点详情...");      
        button_detail.addActionListener(new ActionListener(){ 
        	
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		
           	  button_detail.setEnabled(false);
           	  
           		JFrame	frameDetail = new JFrame("节点详情");
           		frameDetail.setSize(500, 600);
           		frameDetail.setLocation(700, 200);
           		frameDetail.setLayout(new BorderLayout());
           		frameDetail.addWindowListener(new WindowListener() {

        			@Override
        			public void windowActivated(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}

        			@Override
        			public void windowClosed(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}

        			@Override
        			public void windowClosing(WindowEvent arg0) {
        						 button_detail.setEnabled(true);
        			}

        			@Override
        			public void windowDeactivated(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}

        			@Override
        			public void windowDeiconified(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}

        			@Override
        			public void windowIconified(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}

        			@Override
        			public void windowOpened(WindowEvent arg0) {
        				// TODO Auto-generated method stub
        				
        			}});
	           	
	             JLabel lbl_onLine =new JLabel("    相连节点: 0");  
	             JLabel lbl_onLineSuper =new JLabel("超级节点: 0");  
	             JLabel lbl_onLineLeaf =new JLabel("    子节点: 0");  
	             if(testIpfsOnLine()) {
	            	 lbl_onLine.setText("    相连节点: "+ peers.size() );	            	 
	             }else {
	            	 
	             }
	             JSONObject obj = getNodes(0);
	             if(obj!=null) {
	            	 lbl_onLineSuper.setText("超级节点: " + obj.getInt("count"));
	             }
	             obj = getNodes(1);
	             if(obj!=null) {
	            	 lbl_onLineLeaf.setText("    子节点: " + obj.getInt("count"));
	             }
	             JPanel ppp=new JPanel();
	             ppp.setLayout(new FlowLayout());
	             
	             ppp.add(lbl_onLineSuper);
	             ppp.add(lbl_onLineLeaf);	             
	             ppp.add(lbl_onLine);
	             frameDetail.add(ppp, BorderLayout.NORTH);
	             
	             DefaultListModel<String> lstModel_who = new DefaultListModel<String>();	                 
                 JList<String> jList1 = new JList<String>(lstModel_who);
                 JScrollPane jscrollList1 = new JScrollPane(jList1); 
                 jList1.setFont(Font.getFont("Courier New"));
                 frameDetail.add(jscrollList1, BorderLayout.CENTER);
                 lstModel_who.clear();
                 
                 obj = getNodeStorageFromServer();
                 if(obj!=null) {
                	 int count = obj.getInt("count");
                	 lstModel_who.addElement("本地共享存储列表: (" + count + ")");
                	 if(count>0) {
                		 JSONArray arr_tx = obj.getJSONArray("info");
                		 
                		for(int i=0;i<arr_tx.size();i++) {
                			JSONObject objfile = arr_tx.getJSONObject(i);            			
                			String hash = (String) objfile.get("hash");
                			String created_at = (String) objfile.get("created_at");
                			lstModel_who.addElement(hash + "     " +Util.UTCToCST(created_at));
                			
                		} 
                	 }
                	
                 }else {
                	 lstModel_who.addElement("本地共享存储列表: (0)");
                 }
	             frameDetail.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);       
	             frameDetail.setVisible(true);
       	     }
        }) 	;
        
        
        jpanelFirst.add(button_detail);              
        JPanel pan_fill1 = new JPanel();
        pan_fill1.setOpaque(f_opaque);
        jpanelFirst.add(pan_fill1);
        
        JLabel lbl4 =new JLabel("交易流水:");   
        jpanelFirst.add(lbl4);//4
        JButton b_refresh = new JButton("刷新");
        b_refresh.addActionListener(new ActionListener(){       	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		refreshTxList();
           	}
        }) 	;
        
        jpanelFirst.add(b_refresh);//4
        JPanel ptmp = new JPanel();
        ptmp.setOpaque(f_opaque);
        jpanelFirst.add(ptmp);//4
        
         lstModel_share = new DefaultListModel<String>();        
        JList jList1 = new JList(lstModel_share);
        jList1.setOpaque(false);
        JScrollPane jscrollList1 = new JScrollPane(jList1);        
        jpanelFirst.add(jscrollList1);//5
        jList1.updateUI();
        jList1.setFont(Font.getFont("Courier New"));
      
        
        s.fill = GridBagConstraints.BOTH;
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(lbl3, s);//设置组件
        s.gridwidth=14;
        s.weightx = 1;
        s.weighty=0;
        layout.setConstraints(txt_ipfsCap, s);
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        s.fill = GridBagConstraints.NONE;
        layout.setConstraints(button_cap, s);
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(lbl_used, s);//设置组件
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(lbl_available, s);//设置组件
        s.gridwidth=1;
        s.weightx = 0;
        s.weighty=0;
        s.fill = GridBagConstraints.NONE;
        layout.setConstraints(button_detail, s);       
        s.gridwidth=GridBagConstraints.REMAINDER;//GridBagConstraints.REMAINDER为0，就说明该组件是该行的最后一个
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(pan_fill1, s);//设置组件
        
        s.gridwidth=4;
        s.weightx = 1;
        s.weighty=0;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(lbl_balance, s);      
        s.gridwidth=2;
        s.weightx = 0;
        s.weighty=0;
        s.fill = GridBagConstraints.BOTH;
        layout.setConstraints(button_test, s); 
        s.gridwidth=0;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(pan_fill_12, s);   
        
        s.gridwidth=2;
        s.weightx = 1;
        s.weighty=0;
        layout.setConstraints(lbl4, s);   
        s.gridwidth=2;
        s.weightx =1;
        s.weighty=0;
        layout.setConstraints(b_refresh, s);        
        s.gridwidth=0;
        s.weightx = 0;
        s.weighty=0;
        layout.setConstraints(ptmp, s);
        
        s.gridwidth=0;
        s.weightx =1;
        s.weighty=1;
        layout.setConstraints(jscrollList1, s);
        
        //third line of panel 2
        JTable demoTable= new JTable(); 
        demoTable.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
        JScrollPane JSP= new JScrollPane(demoTable);
        dtmFileList = (DefaultTableModel) demoTable.getModel();
        String[] tableHeads = { " ","编号","文件名","大小","份数","有效期"," "," "," "," "};
        dtmFileList.setColumnIdentifiers(tableHeads);
        demoTable.getColumnModel().getColumn(0).setCellEditor(new CheckBoxCellEditor());
        demoTable.getColumnModel().getColumn(0).setCellRenderer(new CheckBoxCellEditor());        
        ButtonColumn buttonsColumn1= new ButtonColumn(demoTable, 7);
        ButtonColumn buttonsColumn2= new ButtonColumn(demoTable, 8);
        ButtonColumn buttonsColumn0= new ButtonColumn(demoTable, 6);
        ButtonColumn buttonsColumn3= new ButtonColumn(demoTable, 9);
       // demoTable.getColumnModel().getColumn(4).setCellRenderer(new ButtonRenderer());  
       // demoTable.getColumnModel().getColumn(4).setCellEditor(new ButtonEditor(new JTextField()));          
       
        jpanelSecond.add(JSP);//3
         
     // 5 line of panel 2
         button_add = new JButton("上传");   
         button_add.setEnabled(false);
       
        button_add.addActionListener(new ActionListener(){  
        	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		button_add.setEnabled(false);
           		
           		showAddDialog();
           		
           		button_add.setEnabled(true);
			}

			private void showAddDialog() {
				
				JDialog  f = new JDialog (frameShowed, "上传文件", true);
			     f.setSize(1000, 250);
			     //f.setLocation(400, 400);
			     f.setLocationRelativeTo(null);
			     f.setLayout(null);
				JPanel j = new JPanel();
				f.setContentPane(j);
				j.setLayout(new BorderLayout());
				
				JLabel lcost = new JLabel("需要花费：");
				
				
				JLabel ll = new JLabel("文件名：");
				 JButton b_choose = new JButton("浏览...");
				 JTextArea txt_file = new JTextArea(2, 40);
				 JScrollPane sp =  new JScrollPane(txt_file);
				 txt_file.setEnabled(false);
				 
				 JTextField txt_copyNum= new JTextField(3);
				 final String labels[] = { "30",  "90", "180", "360" }; 
					DefaultComboBoxModel model = new DefaultComboBoxModel(labels);
					JComboBox comboBox1 = new JComboBox(model); 
					
				 b_choose.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser chooser =new JFileChooser();   
		                chooser.showOpenDialog(frameShowed);  
		                File file=chooser.getSelectedFile();  
		                if(file==null) {
		                	 JOptionPane.showMessageDialog(frameShowed, "请选择一个文件!");
		                	 txt_file.setText("");
		                }else {
		                	 if(file.isDirectory()){  
		                         
		                         JOptionPane.showMessageDialog(frameShowed, "不支持路径, 请选择一个文件!");
		                         txt_file.setText("");
		                     } else {
		                    	 txt_file.setText(file.getAbsolutePath());
		                    	 // calculate cost
		                    	 File f = new File(txt_file.getText().trim());
		                    	 int fileSizeInM = Math.max((int) Math.floorDiv(f.length(), 1024*1024),1);
		                    	 int copycount = Integer.valueOf(txt_copyNum.getText().trim());
		                    	int month = Integer.valueOf((String) comboBox1.getSelectedItem())/30;
		                  		int needBalance = CmdParser.estimateValue(fileSizeInM) *copycount * month;
		                  		
		                  		lcost.setText("需要花费：" + String.valueOf(needBalance));
		                     }
		                }
						
					}});
				JPanel p_firstline = new JPanel();
               p_firstline.setLayout(new FlowLayout());
               p_firstline.add(ll);
				p_firstline.add(sp);
				 p_firstline.add(b_choose);
				
				j.add(p_firstline, BorderLayout.NORTH);
				
				JLabel lcopyNum = new JLabel("请输入文件备份份数[1~10]：");
				
				txt_copyNum.setText("3");
				txt_copyNum.getDocument().addDocumentListener(new DocumentListener() {
						@Override
						public void changedUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							
						}

						@Override
						public void insertUpdate(DocumentEvent arg0) {
							
							String newCount = txt_copyNum.getText().trim();
							if(newCount.length()>0) {
								byte[] digit = newCount.getBytes();
								for (byte b : digit) {
									if((b>0x39) || (b<0x30)) {
										JOptionPane.showMessageDialog(jpanelFirst, "输入应该是 0-9", "错误的输入",JOptionPane.WARNING_MESSAGE); 
										return;
									}
								}
								
							}
							int count = Integer.valueOf(newCount);
							if(count>10) {
								JOptionPane.showMessageDialog(jpanelFirst, "备份份数最大为10份", "错误的输入",JOptionPane.WARNING_MESSAGE); 
								//txt_copyNum.setText("3");
								return;
							}else {
								if(count==0) {
									JOptionPane.showMessageDialog(jpanelFirst, "备份份数最小为1份", "错误的输入",JOptionPane.WARNING_MESSAGE); 
									//txt_copyNum.setText("3");
									return;
								}	
								// count value
								String fileName = txt_file.getText().trim();
								if(fileName.length()== 0) {
									lcost.setText("需要花费：" );
									return;
								}
								
								 File f = new File(fileName);
		                    	 int fileSizeInM = Math.max((int) Math.floorDiv(f.length(), 1024*1024),1);
		                    	 int copycount = Integer.valueOf(txt_copyNum.getText().trim());
		                    	int month = Integer.valueOf((String) comboBox1.getSelectedItem())/30;
		                  		int needBalance = CmdParser.estimateValue(fileSizeInM) *copycount * month;
		                  		
		                  		lcost.setText("需要花费：" + String.valueOf(needBalance));
							}
						}
						@Override
						public void removeUpdate(DocumentEvent arg0) {
							// TODO Auto-generated method stub
							
						}  });
				
				JLabel lday = new JLabel("请选择文件保存时间（天）：");
				
				comboBox1.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						//count value
						String fileName = txt_file.getText().trim();
						if(fileName.length()== 0) {
							lcost.setText("需要花费：" );
							return;
						}
						 File f = new File(fileName);
                    	 int fileSizeInM = Math.max((int) Math.floorDiv(f.length(), 1024*1024),1);
                    	 int copycount = Integer.valueOf(txt_copyNum.getText().trim());
                    	int month = Integer.valueOf((String) comboBox1.getSelectedItem())/30;
                  		int needBalance = CmdParser.estimateValue(fileSizeInM) *copycount * month;
                  		
                  		lcost.setText("需要花费：" + String.valueOf(needBalance));
					}});
				
				JPanel p_2line = new JPanel();
	               p_2line.setLayout(new FlowLayout());
					p_2line.add(lcopyNum);
					p_2line.add(txt_copyNum);
					p_2line.add(lday);
					p_2line.add(comboBox1);
					p_2line.add(lcost);
					
				j.add(p_2line, BorderLayout.CENTER);
				
				JButton b_canel = new JButton("取消");
				b_canel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							
							f.dispose();
							
							
						}});
				
				 JButton b_ok = new JButton("确定");
				 b_ok.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						int copycount = Integer.valueOf(txt_copyNum.getText().trim());
	                 	int month = Integer.valueOf((String) comboBox1.getSelectedItem())/30; 
	                 	
	                 	if(copycount<1) {
	                 		JOptionPane.showMessageDialog(jpanelFirst, "备份份数最小为1份", "错误的输入",JOptionPane.WARNING_MESSAGE); 
							return;
	                 	}
	                 	if(copycount>10) {
	                 		JOptionPane.showMessageDialog(jpanelFirst, "备份份数最大为10份", "错误的输入",JOptionPane.WARNING_MESSAGE); 
							return;
	                 	}
	                 	if(month<1) {
	                 		JOptionPane.showMessageDialog(jpanelFirst, "备份时间最小为30天", "错误的输入",JOptionPane.WARNING_MESSAGE); 
							return;
	                 	}	
	                 	if(month>12) {
	                 		JOptionPane.showMessageDialog(jpanelFirst, "备份份数最大为360天", "错误的输入",JOptionPane.WARNING_MESSAGE); 
							return;
	                 	}
						Boolean rr = connectToContract(false);
		           		if (!rr) {
		           			
		           			return;
		           		}
		           		
		           	    
		                AddTask task = new AddTask(txt_file.getText().trim(), copycount, month);
		                task.addPropertyChangeListener(task);
		                task.execute();
						f.dispose();
						
					}});
				 
				 JPanel p_3line = new JPanel();
	               p_3line.setLayout(new FlowLayout());
	               p_3line.add(b_ok);
					p_3line.add(b_canel);
				 j.add(p_3line, BorderLayout.SOUTH);
				f.setVisible(true);
				f.pack();
			}  
              
        });  
        
            
        
        button_delete.addActionListener(new ActionListener(){  
        	 
           	@Override
			public void actionPerformed(ActionEvent arg0) {
           		button_delete.setEnabled(false);
           		demoTable.updateUI();
           		List<String> files=new ArrayList<String>();
           		List<String> filesHash=new ArrayList<String>();
           		for(int i=0;i<demoTable.getRowCount();i++){
           			Object onecell=	 dtmFileList.getDataVector().elementAt(i);
           			Vector c=(Vector)onecell;
           			if((boolean) c.elementAt(0)) {
           				String fileName = (String)c.elementAt(2);
           				files.add(fileName);
           				JSONObject jsonobj = CmdParser.files.get(i);
           				if(jsonobj.get("name").toString().compareToIgnoreCase(fileName)!=0) {
           					// somethingWrong
           					System.out.println("some index is wrong within button_delete");
           					for(int k=0;k<CmdParser.arr.size();k++) {
           	           			JSONObject objfile = CmdParser.arr.getJSONObject(k);
           	           			if(objfile.get("name").toString().compareToIgnoreCase(files.get(0))==0) {
           	           				String	fileNameQm=objfile.get("hash").toString();
           	           				filesHash.add(fileNameQm);	
           	           				break;		
           	           			}           					 
           	           		} 
           					
           				}else {
           					filesHash.add(jsonobj.get("hash").toString());
           				}
           				
           			}
           		}
           		
           		if(files.isEmpty()) {
           			JOptionPane.showMessageDialog(null, "请从上面的文件列表中勾选");
           			button_delete.setEnabled(true);
           		}
           		
           		int ret = JOptionPane.showConfirmDialog(frameShowed, "删除文件 " + files.toString() + "?" , "确认", JOptionPane.OK_CANCEL_OPTION);
				if(ret != JOptionPane.OK_OPTION) {
						
				}else {
					boolean deleteresult = true;
					 CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
					 
					for(int kk= 0; kk<filesHash.size();kk++) {
						deleteresult &= deleteFile(filesHash.get(kk), false);			     			
	           		}
					if (deleteresult) {
						JOptionPane.showMessageDialog(null, "删除成功");
					}
					refreshFileList(); 
					 CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
					 
				}
		          
                 button_delete.setEnabled(true);
                 
			}  
              
        });  
       
        processBar = new JProgressBar();// 创建进度条   
        processBar.setOpaque(f_opaque);
        processBar.setOrientation(JProgressBar.HORIZONTAL);
        processBar.setMinimum(0);
        processBar.setMaximum(100);
        processBar.setValue(0);
        processBar.setBorderPainted(false);
       processBar.setVisible(false);
        
        
        jpanelSecond.add(button_add);
        jpanelSecond.add(button_delete);
         lbl_process = new JLabel();
         jpanelSecond.add(processBar);
        jpanelSecond.add(lbl_process);
        
               
        JLabel lbl5 =new JLabel(lbl4.getText());
        jpanelSecond.add(lbl5);//5
        JPanel jptmp5 = new JPanel();
        jptmp5.setOpaque(f_opaque);
        jpanelSecond.add(jptmp5);//fill GAP
        
         listMode_up = new DefaultListModel<String>();
        JList jList2 = new JList(listMode_up);
        jList2.setFont(Font.getFont("Courier New"));
        JScrollPane jscrollList2 = new JScrollPane(jList2);        
        jpanelSecond.add(jscrollList2);//6
        
        s2.gridwidth=1;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(lbl2, s2);//设置组件
        s2.gridwidth=14;
        s2.weightx = 1;
        s2.weighty=0;
        layout2.setConstraints(txt2, s2);//设置组件
 
        s2.gridwidth=0;//为0，就说明该组件是该行的最后一个
        s2.weightx = 0;
        s2.weighty=0;
        s.fill = GridBagConstraints.NONE;
        layout2.setConstraints(pan_fill2, s2);//设置组件
        
        
        s2.gridwidth=2;
        s2.weightx = 1;
        s2.weighty=0;
        s.fill = GridBagConstraints.BOTH;
        layout2.setConstraints(lbl_balance2, s2);       
        s2.gridwidth=0;
        s2.weightx = 1;
        s2.weighty=0;
        layout2.setConstraints(jpanel2, s2);   
        
        s2.gridwidth=0;
        s2.weightx = 1;
        s2.weighty=1;
        layout2.setConstraints(JSP, s2);//设置组件
        
        s2.gridwidth=1;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(button_add, s2);
        s2.fill = GridBagConstraints.NONE;
        s2.gridwidth=1;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(button_delete, s2);
       
        s2.gridwidth=30;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(processBar, s2);        

        //s2.fill = GridBagConstraints.BOTH;
        s2.gridwidth=0;
        s2.weightx = 1;
        s2.weighty=0;
        layout2.setConstraints(lbl_process, s2);
        
        
        s2.gridwidth=1;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(lbl5, s2);                
        s2.gridwidth=0;
        s2.weightx = 0;
        s2.weighty=0;
        layout2.setConstraints(jptmp5, s2);
        
        s2.gridwidth=0;
        s2.weightx =1;
        s2.weighty=1;
        layout2.setConstraints(jscrollList2, s2);
       
   
        frameShowed.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				
				mi.exit();
            	//thread_ipfsDaemon.stop();
          	
                System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
        
       
        frameShowed.pack();
        frameShowed.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
        frameShowed.setVisible(true);
	}


	static boolean changeAccount(String keystore) {		
   		
		boolean result = false;
      	boolean found = false;
    	String account="";
      	 //"address":"7c92f018e82ca9f4ae72c9aa4a20cd9bdf90ae36",		             
		    
    	String kv[] =keystore.split(",");
    	for(String s:kv) {
    		if(s.contains("address")) {
    			String kv2[] =s.split(":");
    			String addr = kv2[1].substring(1, 41);
    			account = "0x"+addr;
    			found = true;
    			break;// break for
    		}
    	}
		  
		if(found)
		{
			
			try {
				
        		File f = new File("keystore.txt");
    			if(!f.exists()){
    				f.createNewFile(); 
    			}
                 
    			FileWriter fw=new FileWriter(f.getAbsoluteFile());  //true表示可以追加新内容  
                 //fw=new FileWriter(f.getAbsoluteFile()); //表示不追加
    			BufferedWriter bw=new BufferedWriter(fw);
    	         bw.write(keystore);
    	         bw.close();
    	            
    	            
				con.setKeyStore(f.getAbsolutePath());
				con.setAccount(account);
				
				if(connectToContract(true) ) {
					refreshFileList();
					refreshTxList();
					getCoinBalance();
					result = true;
					con.saveCon();
				}
				
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"changeAccount",JOptionPane.ERROR_MESSAGE);
				logfile.error("changeAccount" + e.toString());
		    }
		}
        return result;
	}
	
	
	// ipfs/config 
	static String getIpfsNode() {
		String result ="";

   		try {
   			File ff = new File(ipfsDir + File.separator+"config");
   			FileReader reader = new FileReader(ff);
			 BufferedReader bufferedReader = new BufferedReader(reader);
             String string = null;             
        		                
				while ((string = bufferedReader.readLine()) != null) {    						
				    if(string.contains("PeerID")) { //"PeerID": "QmZC2X4121r29aFdu9qcxFRUn9vf2QWfmQu9q93vehP26q",
				    	String strv[] = string.split(":");						    	
				    	int indexcount = strv[1].indexOf("Qm");
				    	result =strv[1].substring(indexcount,indexcount+46);
				    	
				    	break;			    	
				    }
				 }
				bufferedReader.close();
				reader.close();				 
                	 
						
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, e1.toString(),"getIpfsNode", JOptionPane.ERROR_MESSAGE);
			logfile.error("getIpfsNode : " + e1.toString());
		}
   	 return result;
	}
	
	
	/*
	 static void eventWatch() {
		
		Subscriber<UploadInfoEventResponse> subscriber = new Subscriber<UploadInfoEventResponse>() {
	        @Override
	        public void onCompleted() {
	        	logfile.debug("onCompleted");
	        }

	        @Override
	        public void onError(Throwable e) {
	        	logfile.debug("onError:" + e.toString());
	        }

	        @Override
	        public void onNext(UploadInfoEventResponse s) {
	        	
	        	if(s.fileName.startsWith("Qm")&& (s.fileName.length()==46)){
	        		
	        		if(s.owner.equalsIgnoreCase(con.getAccount())) {// my own file
	        			return;
	        		}
	        		logfile.debug("find new source:" + s.fileName);
	        		getFile(s.fileName);
	        	}
	        	
	        }
	    };
	    
	    DefaultBlockParameter startBlock=DefaultBlockParameterName.EARLIEST;
	    //get current blockNumber
	   try {
		BigInteger blockNumber = web3.ethBlockNumber().sendAsync().get().getBlockNumber();	
		startBlock = new DefaultBlockParameterNumber(blockNumber.subtract(BigInteger.valueOf(10)));
	} catch (InterruptedException e) {
		JOptionPane.showMessageDialog(null, e.toString());
	} catch (ExecutionException e1) {
		JOptionPane.showMessageDialog(null, e1.toString());
	}
	   
		Observable<UploadInfoEventResponse> ob=contract.uploadInfoEventObservable(startBlock, DefaultBlockParameterName.LATEST);
		ob.subscribe(subscriber);		
	}
*/	 
	
	
	static void continueProve()
	{	
		if(!onLine) {
			return;
		}
		
		int count = CmdParser.count;//	getCountFromLog();	
		if (count == 0) return;
		
		try {
			ps.generateProve(count);
			String hexresult = Util.bytesToHexString(ps.hash);
			String r = Util.bytesToHexString(ps.sd.getR());
			String s = Util.bytesToHexString(ps.sd.getS());
			TransactionReceipt transactionReceipt =contract.verifyStorageContinue(Integer.toHexString(count), hexresult, BigInteger.valueOf(ps.sd.getV()), r, s).sendAsync().get();
			String status = transactionReceipt.getStatus();
			if(status.contains("1")) {
				
				postHttpTrans(1, "1",transactionReceipt.getTransactionHash(),"1") ;//TODO: this 1 is a fake size
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
				String item = CmdParser.formatListItem("[+]", transactionReceipt.getTransactionHash(), "+1", format.format(new Date()));
				CmdParser.lstModel_share.insertElementAt(item, 0);;
		        getCoinBalance();
			}else {
				logfile.error("prove fail! " + transactionReceipt.toString());
			}
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"continueProve", JOptionPane.ERROR_MESSAGE);
			logfile.error("continueProve : " + e.toString());
		} 
				
		
	}
	
	
	static void clearForProcess(String tag, Process proc, InputStream stderr) {
		
        InputStreamReader isr = new InputStreamReader(stderr);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        logfile.warn("\n [" + tag + " ] clearForProcess -----");
        try {
			while ( (line = br.readLine()) != null)
				logfile.warn(line);
		} catch (IOException e) {
			// do nothing
		}
        
        try {
        	br.close();
        	isr.close();
			stderr.close();
		} catch (IOException e) {
			// do nothing
		}
        
	}

	// upload file   URL: /v1/ipfs
	static void postHttpQuestWithAes(String fileName, String hash, String Aes, String size, int copyNum, int valid_days) {
		HttpURLConnection connection=null;
		OutputStream os=null;
		 try {//con.getHttpAddress()+
			URL url = new URL(con.getHttpAddress() + "v1/ipfs/file");
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(15000);
				 connection.setReadTimeout(60000);
				 connection.setDoOutput(true);
				 connection.setDoInput(true);
				 connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				  os = connection.getOutputStream();
				 //name1=value1&name2=value2 
				 String strTest = URLEncoder.encode(fileName, "UTF-8");
				 String param = "address="+con.getAccount() + "&hash="+hash+"&name="+strTest;				
				 param = param + "&valid_days="+valid_days + "&node_address="+ipfsNode+"&file_count="+copyNum;
				 param = param +"&fsize="+size +  "&key="+Aes;
				 os.write(param.getBytes());
				 if (connection.getResponseCode() == 200) {
					 	

		            }else {
		            	
		            	JOptionPane.showMessageDialog(null, connection.getResponseMessage(),"postHttpQuestWithAes",JOptionPane.ERROR_MESSAGE);
		            	logfile.error("postHttpQuestWithAes resp:" + connection.getResponseMessage());
		            }
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"postHttpQuestWithAes",JOptionPane.ERROR_MESSAGE);
			logfile.error("postHttpQuestWithAes  : " + e.toString());
		}finally {
			
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                	//JOptionPane.showMessageDialog(null, e.toString());
                }
            }
           
 
            connection.disconnect();
		}
	}
	
	static void postHttpTrans(int inout, String amount,String hash, String fsize) {
		HttpURLConnection connection=null;
		OutputStream os=null;
		 try {
			URL url = new URL(con.getHttpAddress()+"v1/ipfs-trans");
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("POST");
				connection.setConnectTimeout(15000);
				 connection.setReadTimeout(60000);
				 connection.setDoOutput(true);
				 connection.setDoInput(true);
				 connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				  os = connection.getOutputStream();
				 //name1=value1&name2=value2 				 
				 String param = "address="+con.getAccount() + "&inout="+inout+"&amount="+amount+"&hash="+hash+"&fsize="+fsize;
				 os.write(param.getBytes());
				 if (connection.getResponseCode() == 200) {
					 
		                
		            }else {
		            	JOptionPane.showMessageDialog(null, connection.getResponseMessage(),"postHttpTrans",JOptionPane.ERROR_MESSAGE);
		            	logfile.error("postHttpTrans resp : " + connection.getResponseMessage());
		            }
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"postHttpTrans",JOptionPane.ERROR_MESSAGE);
			logfile.error("postHttpTrans  : " + e.toString());
		}finally {
			
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                	//JOptionPane.showMessageDialog(null, e.toString(),"postHttpTrans",JOptionPane.ERROR_MESSAGE);
                }
            }
           
     
            connection.disconnect();
		}
	}
	
	// update file valid time
	static void putHttpFile(String hash, int days) {
		HttpURLConnection connection=null;
		OutputStream os=null;
		 try {
			URL url = new URL(con.getHttpAddress()+"v1/ipfs/file");
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("PUT");
				connection.setConnectTimeout(15000);
				 connection.setReadTimeout(60000);
				 connection.setDoOutput(true);
				 connection.setDoInput(true);
				 connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
				  os = connection.getOutputStream();
				 //name1=value1&name2=value2 				 
				 String param = "&hash="+hash+"&valid_days="+days;
				 os.write(param.getBytes());
				 if (connection.getResponseCode() == 200) {
					 
		                //logfile.debug(tag, param);
		            }else {
		            	JOptionPane.showMessageDialog(null, connection.getResponseMessage(),"putHttpFile",JOptionPane.ERROR_MESSAGE);
		            	logfile.error("putHttpFile  resp: " + connection.getResponseMessage());
		            }
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"putHttpFile",JOptionPane.ERROR_MESSAGE);
			logfile.error("putHttpFile  : " + e.toString());
		}finally {
			
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                	//JOptionPane.showMessageDialog(null, e.toString());
                }
            }
           
          
            connection.disconnect();
		}
	}
	
	// get file info from server
	 static JSONObject getFileListFromServer(int page, int limit) {
		HttpURLConnection connection=null;
		StringBuffer buffer = new StringBuffer();  
		JSONObject jsonObject = null;
		String s = con.getAccount()+"?page="+page+"&limit="+limit;
		 try {
			URL url = new URL(con.getHttpAddress()+"v1/ipfs/"+s);
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(15000);
				 connection.setReadTimeout(60000);	
				
				 if (connection.getResponseCode() == 200) {

					 InputStream inputStream = connection.getInputStream();    
			            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
			            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
			    
			            String str = null;    
			            while ((str = bufferedReader.readLine()) != null) {    
			                buffer.append(str);    
			            }    
			            bufferedReader.close();    
			            inputStreamReader.close();    
			            
			            inputStream.close();    
			            inputStream = null;    
			               
			            jsonObject = JSONObject.fromObject(buffer.toString());  
			            
		            }else {
		            	logfile.error("getFileListFromServer  resp: " + connection.getResponseMessage());
		            }
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"getFileListFromServer",JOptionPane.ERROR_MESSAGE);
			logfile.error("getFileListFromServer  : " + e.toString());
		}finally {			
			 
            
            connection.disconnect();
		}
		 
		 return  jsonObject;
	}
	
	 static JSONObject getNodeStorageFromServer() {
			HttpURLConnection connection=null;
			StringBuffer buffer = new StringBuffer();  
			JSONObject jsonObject = null;
			
			 try {
				URL url = new URL(con.getHttpAddress()+"v1/ipfs/file-storage/nodes/"+ipfsNode);
				
					 connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(15000);
					 connection.setReadTimeout(60000);	
					
					 if (connection.getResponseCode() == 200) {

						 InputStream inputStream = connection.getInputStream();    
				            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
				            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
				    
				            String str = null;    
				            while ((str = bufferedReader.readLine()) != null) {    
				                buffer.append(str);    
				            }    
				            bufferedReader.close();    
				            inputStreamReader.close();    
				            // 释放资源    
				            inputStream.close();    
				            inputStream = null;    
				               
				            jsonObject = JSONObject.fromObject(buffer.toString());  
				            
			            }else {
			            	logfile.error("getNodeStorageFromServer  resp: " + connection.getResponseMessage());
			            }
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(),"getNodeStorageFromServer",JOptionPane.ERROR_MESSAGE);
				logfile.error("getNodeStorageFromServer  : " + e.toString());
			}finally {			
				 
	
	            connection.disconnect();
			}
			 
			 return  jsonObject;
		}
	 
	 ///v1/ipfs/file-storage/files/{hash}
	 static JSONObject getFileStorageFromServer(String fileHash) {
			HttpURLConnection connection=null;
			StringBuffer buffer = new StringBuffer();  
			JSONObject jsonObject = null;
			
			 try {
				URL url = new URL(con.getHttpAddress()+"v1/ipfs/file-storage/files/"+fileHash);
				
					 connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(15000*3);
					 connection.setReadTimeout(60000*3);	
					
					 if (connection.getResponseCode() == 200) {

						 InputStream inputStream = connection.getInputStream();    
				            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
				            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
				    
				            String str = null;    
				            buffer.append("{ \"info\": ");
				            while ((str = bufferedReader.readLine()) != null) {    
				                buffer.append(str);    
				            }    
				            buffer.append("}");
				            bufferedReader.close();    
				            inputStreamReader.close();    
				            // 释放资源    
				            inputStream.close();    
				            inputStream = null;    
				               
				            jsonObject = JSONObject.fromObject(buffer.toString());  
				            
			            }else {
			            	logfile.error("getFileStorageFromServer resp : " + connection.getResponseMessage());
			            }
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(),"getFileStorageFromServer",JOptionPane.ERROR_MESSAGE);
				logfile.error("getFileStorageFromServer  : " + e.toString());
			}finally {			
				 
	           
	            connection.disconnect();
			}
			 
			 return  jsonObject;
		}
	 
	// get subnode from server
		 static String getSubNodeInfoFromServer(String subNode) {
			HttpURLConnection connection=null;
			StringBuffer buffer = new StringBuffer();  
			JSONObject jsonObject = null;
			
			 try {
				URL url = new URL(con.getHttpAddress()+"v1/ipfs/sub-nodes/"+subNode);
				
					 connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("GET");
					connection.setConnectTimeout(15000);
					 connection.setReadTimeout(60000);	
					
					 if (connection.getResponseCode() == 200) {

						 InputStream inputStream = connection.getInputStream();    
				            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
				            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
				    
				            String str = null;    
				            while ((str = bufferedReader.readLine()) != null) {
				            	if (str.contains("updated_at")) {
				            		buffer.append(str); 
				            	}
				                   
				            }    
				            bufferedReader.close();    
				            inputStreamReader.close();    
				            
				            inputStream.close();    
				            inputStream = null;    
				               
				            str = buffer.toString();
				            if(str!=null) {
				            	String[] srv = str.split(":");
				            	if(srv.length>1) {
				            		return srv[1];
				            	}
				            	
				            }
				            
			            }else {
			            	logfile.error("getSubNodeInfoFromServer  resp: " + connection.getResponseMessage());
			            }
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(),"getSubNodeInfoFromServer",JOptionPane.ERROR_MESSAGE);
				logfile.error("getSubNodeInfoFromServer  : " + e.toString());
			}finally {			
				 
	            
	            connection.disconnect();
			}
			 
			 return  null;
		}
		 
		 
	private static JSONObject getHttpTrans(int page, int limit) {
		HttpURLConnection connection=null;
		StringBuffer buffer = new StringBuffer();  
		JSONObject jsonObject = null;
		String s = con.getAccount()+"?page="+page+"&limit="+limit;
		 try {
			URL url = new URL(con.getHttpAddress()+"v1/ipfs-trans/"+s);
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(15000*3);
				 connection.setReadTimeout(60000*3);	
				
				 if (connection.getResponseCode() == 200) {

					 InputStream inputStream = connection.getInputStream();    
			            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
			            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
			    
			            String str = null;    
			            while ((str = bufferedReader.readLine()) != null) {    
			                buffer.append(str);    
			            }    
			            bufferedReader.close();    
			            inputStreamReader.close();    
			            // 释放资源    
			            inputStream.close();    
			            inputStream = null;    
			               
			            jsonObject = JSONObject.fromObject(buffer.toString());  
			            
		            }else {
		            	logfile.error("getHttpTrans resp : " + connection.getResponseMessage());
		            }
				 
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"getHttpTrans",JOptionPane.ERROR_MESSAGE);
			logfile.error("getHttpTrans resp : " + e.toString());
		}finally {			
			 
            connection.disconnect();
		}
		 
		 return  jsonObject;
	}
	
	private static JSONObject getNodes(int type) {
		HttpURLConnection connection=null;
		StringBuffer buffer = new StringBuffer();  
		JSONObject jsonObject = null;
		URL url;
		 try {
			 if(type == 0) {
				 url = new URL(con.getHttpAddress()+"v1/ipfs/super-nodes"); 
			 }
			 else {
				 url = new URL(con.getHttpAddress()+"v1/ipfs/sub-nodes");
			 } 
			
				 connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(15000);
				 connection.setReadTimeout(60000);	
				
				 if (connection.getResponseCode() == 200) {

					 InputStream inputStream = connection.getInputStream();    
			            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");    
			            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);    
			    
			            String str = null;    
			            while ((str = bufferedReader.readLine()) != null) {    
			                buffer.append(str);    
			            }    
			            bufferedReader.close();    
			            inputStreamReader.close();    
			            // 释放资源    
			            inputStream.close();    
			            inputStream = null;    
			               
			            jsonObject = JSONObject.fromObject(buffer.toString());  
			            
		            }else {
		            	logfile.error("getNodes resp : " + connection.getResponseMessage());
		            }
				 
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"getNodes",JOptionPane.ERROR_MESSAGE);
			logfile.error("getNodes resp : " + e.toString());
		}finally {			
			 
            connection.disconnect();
		}
		 
		 return  jsonObject;
	}
	
	static void refreshFileList() {
		
		if(!onLine) {			
			return;
		}
		JSONObject obj = getFileListFromServer(0,10);
		if(obj==null) {
			obj = getFileListFromServer(0,10); // try again
			if(obj==null) {
				return;
			}
		}
		
		int count = obj.getInt("count");
		if(count==0) {
			obj = getFileListFromServer(0,10); // try again
			if(obj==null) {
				return;
			}
			count = obj.getInt("count");
			if (count == 0) {
				return;
			}
			
		}
		 arr = obj.getJSONArray("ipfs");
		
		if(count>10) {
			obj = getFileListFromServer(0,count);
			if(obj!=null) {
				arr = obj.getJSONArray("ipfs");
			} 
				
		}
		 
		 int rowCount = dtmFileList.getRowCount();
		 for(int j=0;j<rowCount;j++) {
			 dtmFileList.removeRow(rowCount-j-1);
		 }
		 
		 files.clear();
		 
		for(int i=0;i<arr.size();i++) {
			JSONObject objfile = arr.getJSONObject(i);	
			String s = (String) objfile.get("name");
			String fileName;
			String size = (String) objfile.get("fsize");
			Integer file_count = (Integer) objfile.get("file_count");
			
			String UTCStr = (String) objfile.get("expire_at"); //"expire_at": "2018-06-13T11:33:38.349Z",//过期时间
			int index = UTCStr.indexOf(".");
			if(index>0) {
				UTCStr = UTCStr.substring(0, index)+"Z\"";
			}
			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		        try {
					Date utcdate = sdf.parse(UTCStr); 
					SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	      
			        localFormater.setTimeZone(TimeZone.getDefault());
			        String localTime = localFormater.format(utcdate.getTime());
					if(utcdate.before(new Date())) { // overDue
						logfile.info("\n overDue :" + s + " " + UTCStr);
						continue;
					}
					
					try {
						fileName = URLDecoder.decode(s,"UTF-8");
						Object[] objdata = { new Boolean(false),i,fileName,Util.showSize(size),file_count,localTime,"续费","下载","删除","详情"};
						dtmFileList.addRow(objdata);
					} catch (UnsupportedEncodingException e) {
						
						logfile.error(e.toString());
						Object[] objdata = { new Boolean(false),i,s,Util.showSize(size),file_count,localTime,"续费","下载","删除","详情"};
						dtmFileList.addRow(objdata);
					}
					files.add(i, objfile);
					
				} catch (ParseException e) {
					logfile.error("refreshFileList : " + e.toString());					
				}
	
		}
		dtmFileList.fireTableDataChanged();
	}
	static String formatListItem(String icon, String hash, String amount, String date) {
		
		String s1 = String.format("%-5s %-70s", icon, hash.toUpperCase());
		String s2 = String.format("%20s %20s", amount,date.substring(0,16));
		return String.format("%-100s %50s", s1,s2);
	}
	
	
	
	static void refreshTxList() {
				
		if(!onLine) {
			return;
		}
		
		int limit = 100;
		JSONObject obj = getHttpTrans(0,1);//in order to  to get count
		if(obj==null) return;
		int count = obj.getInt("count");
		if(count==0) {
			return;
		}
		
		int page = 0;
		if(count>limit) {
			 page = Math.floorDiv(count-1, limit);		
		}
		 
		 lstModel_share.clear();
		 listMode_up.clear();
		 JSONArray arr_tx;
		while(true) {
			
			obj = getHttpTrans(page,limit);
			if(obj==null) { 
			  // maybe request lost, try again
				obj = getHttpTrans(page,limit);
				if(obj==null) return;				
			}
			arr_tx = obj.getJSONArray("info");
			
			for(int i=arr_tx.size();i>0;i--) {
				JSONObject objfile = arr_tx.getJSONObject(i-1);	
				int inout = (int) objfile.get("inout");
				String amount = (String) objfile.get("amount");
				String hash = (String) objfile.get("hash");
				String updated_at = (String) objfile.get("updated_at");
				
				if(inout==1) {
					String amountWithSig = "+" +amount;
					lstModel_share.addElement(formatListItem("[+]", hash, amountWithSig, Util.UTCToCST(updated_at)));
				}else {
					String amountWithSig = "-" +amount;
					listMode_up.addElement(formatListItem("[-]", hash, amountWithSig,Util.UTCToCST(updated_at)));
				}
		
			}
			if(page == 0) break;
			page--;
		}
		
		
	}
	
	 static boolean deleteFile(String fileNameQm, boolean showSuccessMsg) {	
		 
			// send removeTask by IPFS
		 if(!testIpfsOnLine()) {
			 JOptionPane.showMessageDialog(null, "IPFS 不在线，请重新启动客户端");	 			
		 }
		 
		
		
		 Process premove;
		   	try {
	 			String[] cmds = {ipfsCMD, "dht","removeTask",fileNameQm};
	 			 premove = Runtime.getRuntime().exec(cmds);
	 			premove.waitFor();
	 		
	 			if (premove.exitValue() != 0) {
	 				String tag = "dht removeTask " + fileNameQm + " with code " + 	premove.exitValue();
		 			CmdParser.clearForProcess(tag, premove, premove.getErrorStream()); 	
		 			CmdParser.clearForProcess(tag, premove, premove.getInputStream());
		 			JOptionPane.showMessageDialog(null, "IPFS removeTask 失败！ code:" + premove.exitValue(),"deleteFile",JOptionPane.ERROR_MESSAGE);
		 			return false;
	 				}
	 		} catch (Exception  e) {
	 			JOptionPane.showMessageDialog(null, e.toString(),"deleteFile",JOptionPane.ERROR_MESSAGE);
	 			logfile.error("deleteFile [ipfs dht remove " + fileNameQm + "] "+ e.toString() );
	 			return false;
	 		}
		   	CmdParser.clearForProcess("dht removeTask success ", premove, premove.getInputStream()); 	
		   	premove.destroy();
		 
		 boolean result = false;
			HttpURLConnection connection=null;
			
			String s = con.getAccount()+"/files/"+fileNameQm;
			 try {
				URL url = new URL(con.getHttpAddress()+"v1/ipfs/"+s);
				
					 connection = (HttpURLConnection) url.openConnection();
					connection.setRequestMethod("DELETE");
					connection.setConnectTimeout(15000);
					 connection.setReadTimeout(60000);	
					
					 if (connection.getResponseCode() == 204) {

						 result= true;
						 if(showSuccessMsg) {
							 JOptionPane.showMessageDialog(null, "删除成功!");  
						 }
						 
			            }else {
			            	logfile.error("deleteFile resp: " + connection.getResponseMessage() );
			            }
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString(),"deleteFile", JOptionPane.ERROR_MESSAGE);
				logfile.error("deleteFile  " +  e.toString() );
			}finally {			
	                       

	            connection.disconnect();
			}
			 
			  //TODO:delete file on ethereum
			 
			 
			 return  result;
		
	}


	public static void reNewFile(String fileNameQm, String fsize, int fcount, int index) {
		
		
		JDialog  f = new JDialog (frameShowed, "文件续费", true);
	     f.setSize(800, 200);
	     
	     f.setLocationRelativeTo(null);
	     f.setLayout(null);
		JPanel j = new JPanel();
		f.setContentPane(j);
		j.setLayout(new BorderLayout());
		
		JLabel ldays = new JLabel("续费时长(天)：");
		
		int month = 1;	
		int fileSize=Integer.valueOf(fsize);		
		int needBalance = CmdParser.estimateValue((int) (fileSize/1024/1024)) *fcount * month;
		JLabel lcost = new JLabel("      需要花费："+ String.valueOf(needBalance));
		
		Object[] possibleValues = { "30", "60", "90","180","360" };
		DefaultComboBoxModel model = new DefaultComboBoxModel(possibleValues);
		JComboBox comboBox1 = new JComboBox(model); 
		comboBox1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
			
			int month = Integer.valueOf((String) e.getItem())/30;
				
			int needBalance = CmdParser.estimateValue((int) (fileSize/1024/1024)) *fcount * month;
			lcost.setText("      需要花费："+ String.valueOf(needBalance));
			}
			});
		
		
		
		JPanel j_first = new JPanel();
		j_first.setLayout(new FlowLayout());
		j_first.add(ldays);
		j_first.add(comboBox1);
		j_first.add(lcost);
		
		JPanel j_second = new JPanel();
		j_second.setLayout(new FlowLayout());
		JButton b_ok = new JButton("确定");
		b_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				f.setVisible(false);
				Boolean result = connectToContract(false);
				if (result) {
					int month = Integer.valueOf((String) comboBox1.getSelectedItem())/30;
					int needBalance = CmdParser.estimateValue((int) (fileSize/1024/1024)) *fcount * month;
			 		if(  CmdParser.getCoinBalance()<needBalance) {
			 			JOptionPane.showMessageDialog(null, "余额不足 ! 需要 "+ needBalance);
			 			f.dispose();
			 			return ;
			 		}
			 		
			 		 		
					String valueStr = String.valueOf(needBalance);
					BigInteger value = Convert.toWei(valueStr + ".0", Convert.Unit.ETHER).toBigInteger();
					BigInteger monthNum = BigInteger.valueOf(month);
					
					TransactionReceipt transactionReceipt;
					try {
						transactionReceipt = CmdParser.contract.reNewFileDate(fileNameQm,monthNum,value).sendAsync().get();
						String status = transactionReceipt.getStatus();
						if(!status.contains("1")) {
							logfile.error("\n reNewFileDate fail, tx=" + transactionReceipt.toString());
							logfile.error("\n reNewFileDate fail, fileHash=" + fileNameQm + " month=" + monthNum + " value=" + value);
							 JOptionPane.showMessageDialog(null,  " 交易失败！");	
							 f.dispose();
							 return ;
						}
						
						 boolean b_res = false;
					        for (ReNewFileInfoEventResponse event : CmdParser.contract.getReNewFileInfoEvents(transactionReceipt)) {
					        	if(!event.owner.equalsIgnoreCase(CmdParser.con.getAccount())) {
					        		continue;
					        	}
					        	b_res = true;
					        	// tell the server
					        	putHttpFile(fileNameQm, month*30);
					        	postHttpTrans(2, valueStr,transactionReceipt.getTransactionHash(),fsize) ;
					        	
					        	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
								String item = CmdParser.formatListItem("[-]", transactionReceipt.getTransactionHash(), "-"+ valueStr, format.format(new Date()));
								listMode_up.insertElementAt(item, 0);
								// update view
								BigInteger utc = CmdParser.contract.getDate(fileNameQm).sendAsync().get();										            
								dtmFileList.setValueAt(Util.getTimeFromUTCBigInteger(utc), index, 5);
								
								JOptionPane.showMessageDialog(null, "续费成功！ ");
								logfile.info("\n reNewFileDate success, tx=" + transactionReceipt.toString());
								getCoinBalance();
								f.dispose();
								return ;
					        }
							
					        if(!b_res) {
					        	logfile.error("\n reNewFileDate fail, tx=" + transactionReceipt.toString());
					        	logfile.error("\n reNewFileDate fail, fileHash=" + fileNameQm + " month=" + monthNum + " value=" + value);
							    JOptionPane.showMessageDialog(null,  " 没有收到链上的消息，上链失败！");	
							 }

						
						
					} catch (Exception e1) {
						logfile.error("\n reNewFile" + e1.getMessage());
					}
			
				}
				f.dispose();
			}
			
		});
		JButton b_cancel = new JButton("取消");
		b_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				f.dispose();
			}
			
		});
		j_second.add(b_ok)	;
		j_second.add(b_cancel)	;
		
		j.setLayout(new BorderLayout());
		j.add(j_first, BorderLayout.CENTER);
		j.add(j_second, BorderLayout.SOUTH);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}


	public static void showDetails(String fileNameQm, int index) {
		
		
		JFrame ff = new JFrame("文件存储详情");
	     ff.setSize(500, 300);
	     //ff.setLocation(200, 200);
	     ff.setLocationRelativeTo(null);	     
	     ff.setLayout(null);
	     
	     JPanel pp = new JPanel();
	     ff.setContentPane(pp);
	     
	     pp.setLayout(new BorderLayout());
	     
	     DefaultListModel<String> lst = new DefaultListModel<String>();
	        
	        JList<String> jList1 = new JList<String>(lst);
	        JScrollPane jscrollList1 = new JScrollPane(jList1);        
	       
	  pp.add(jscrollList1, BorderLayout.CENTER);
	  lst.clear();
	  JSONObject obj = getFileStorageFromServer(fileNameQm);
	  if (obj == null ) {
		  // try again
		  obj = getFileStorageFromServer(fileNameQm);
	  }
      if(obj!=null) {
    	 JSONArray count = obj.getJSONArray("info");
    	 if (count.size() == 0) {
    		 //try again
    		 obj = getFileStorageFromServer(fileNameQm);
    		 if(obj != null) {
    			 count = obj.getJSONArray("info");
    		 }
    	 }
    	 if (count.size() == 0) {
    		 lst.addElement(" 该文件还未被其他节点保存，请耐心等待  " );
    	 }else {
    		 for (int i=0;i<count.size();i++) {
        		 JSONObject objfile = count.getJSONObject(i);	
        		 lst.addElement(" 节点地址：  "+ (String) objfile.getString("address") );
        	 } 
    	 }
    	 
      }else {
    	  lst.addElement(" 该文件还未被其他节点保存，请耐心等待  " );
      } 
      
      ff.setVisible(true);
      ff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	
	protected boolean checkForProvider(String FileName) {

		 //check the provider
		 JSONObject obj = CmdParser.getFileStorageFromServer(FileName);
		  if (obj == null ) {
			  // try again
			  obj = CmdParser.getFileStorageFromServer(FileName);
		  }
	      if(obj!=null) {
	    	 JSONArray count = obj.getJSONArray("info");
	    	 if (count.size() == 0) {
	    		 //try again
	    		 obj = CmdParser.getFileStorageFromServer(FileName);
	    		 if(obj != null) {
	    			 count = obj.getJSONArray("info");
	    		 }
	    	 }
	    	 if (count.size() == 0) {
	    		 
	    		 return false;
	    	 }else {
	    		 Calendar ca=Calendar.getInstance();
				ca.setTime(new Date());
				ca.add(Calendar.HOUR_OF_DAY, -2); // 2 hours ago	
				Date limit = ca.getTime();	
	    		 for (int i=0;i<count.size();i++) {
	        		 JSONObject objfile = count.getJSONObject(i);		        		 
	        		 String UTCStr = CmdParser.getSubNodeInfoFromServer((String) objfile.getString("address") );
	        		 int index = UTCStr.indexOf(".");
	     			if(index>0) {
	     				UTCStr = UTCStr.substring(0, index)+"Z\"";
	     			}
	     			 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	     		     sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	     		     Date utcdate;
					try {
						utcdate = sdf.parse(UTCStr);
						 if(utcdate.before(limit)) { // overDue
		     						
		     					continue;
		     			 }else {
		     				 // found provider
		     				 return true;
		     			 }
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
	     		    
	    		 
	    		 } 
	    		 return true;
	    	 }
	    	 
	      }else {
	    	  
	    		 return false;
	      } 
	      
		
	}
}

class CheckBoxCellEditor extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
	 private static final long serialVersionUID = 1L;
	 protected JCheckBox checkBox;
	 
	  public CheckBoxCellEditor() {
	    checkBox = new JCheckBox();
	    checkBox.setHorizontalAlignment(SwingConstants.CENTER);	   
	    checkBox.addActionListener(new ActionListener() {  
	  	  
            public void actionPerformed(ActionEvent e) {  
                fireEditingStopped();  
            }  
        });  
	  }
	  
	  //Returns the value contained in the editor.
	  public Object getCellEditorValue() {
	    return Boolean.valueOf(checkBox.isSelected());
	  }
	  //Sets an initial value for the editor. 
   public Component getTableCellEditorComponent(
	    JTable  table,
	    Object  value,
	    boolean isSelected,
	    int     row,
	    int     column) {
	    checkBox.setSelected(((Boolean) value).booleanValue());
	    
	    return checkBox;

	  }

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
			int column) {
		JCheckBox ck = new JCheckBox();
		
			 if (value instanceof Boolean) {
				 ck.setSelected(((Boolean) value).booleanValue());
				 ck.setForeground(table.getForeground());
				 ck.setBackground(table.getBackground());
			    }
		
		ck.setOpaque(true);
		ck.setHorizontalAlignment(SwingConstants.CENTER);
		return ck;
	}
	} 

 class ButtonColumn extends AbstractCellEditor implements  
    TableCellRenderer, TableCellEditor, ActionListener {  
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTable table;  
	JButton renderButton;  
	JButton editButton;  
	String text;  

public ButtonColumn(JTable table, int column) {  
    super();  
    this.table = table;  
    renderButton = new JButton();  
    editButton = new JButton();  
    editButton.setFocusPainted(false);  
    editButton.addActionListener(this);  

    TableColumnModel columnModel = table.getColumnModel();  
    columnModel.getColumn(column).setCellRenderer(this);  
    columnModel.getColumn(column).setCellEditor(this);  
}  

public Component getTableCellRendererComponent(JTable table, Object value,  
        boolean isSelected, boolean hasFocus, int row, int column) {  
    if (hasFocus) {  
        renderButton.setForeground(table.getForeground());  
        renderButton.setBackground(UIManager.getColor("Button.background"));  
    } else if (isSelected) {  
        renderButton.setForeground(table.getSelectionForeground());  
        renderButton.setBackground(table.getSelectionBackground());  
    } else {  
        renderButton.setForeground(table.getForeground());  
        renderButton.setBackground(UIManager.getColor("Button.background"));  
    }  

    renderButton.setText((value == null) ? " " : value.toString());  
    return renderButton;  
}  

public Component getTableCellEditorComponent(JTable table, Object value,  
        boolean isSelected, int row, int column) {  
    text = (value == null) ? " " : value.toString();  
    editButton.setText(text);  
    return editButton;  
}  

public Object getCellEditorValue() {  
    return text;  
}  

public void actionPerformed(ActionEvent e) {  
    fireEditingStopped();  
    int index = table.getSelectedRow();
	String fileName = table.getValueAt(index, 2).toString();
	
	String fileNameQm="";
	String filesize="";
	int file_count=0;
	
	
	JSONObject objfile = CmdParser.files.get(index);
	if(objfile.get("name").toString().compareToIgnoreCase(fileName)!=0) {
		// something wrong??!!
		CmdParser.logfile.info("\n files not match! " + fileName + " <> " + objfile.get("name").toString());
		for(int i=0;i<CmdParser.arr.size();i++) {
			 objfile = CmdParser.arr.getJSONObject(i);
			if(objfile.get("name").toString().compareToIgnoreCase(fileName)==0) {
								
				break;
			}
			 
		}
	}
	fileNameQm=objfile.get("hash").toString();
	filesize=objfile.get("fsize").toString();	
	 file_count = (Integer) objfile.get("file_count");
	 
	
    if(text.contains("下载")) {
    	int ret = JOptionPane.showConfirmDialog(null, "确定下载文件 " + fileName + "?" , "下载确认", JOptionPane.OK_CANCEL_OPTION);
    	if(ret != JOptionPane.OK_OPTION) {
    		return;
    	}
    	
    	DownloadTask task = new DownloadTask(fileNameQm,fileName);
         task.addPropertyChangeListener(task);
         task.execute();
    	
    	return;
    }
    if(text.contains("删除")) {
    	int ret = JOptionPane.showConfirmDialog(null, "确定删除文件 " + fileName + "?" , "删除确认", JOptionPane.OK_CANCEL_OPTION);
    	if(ret != JOptionPane.OK_OPTION) {
    		return;
    	}
    	 CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		 
    	// delete    	
    	if(CmdParser.deleteFile(fileNameQm, true)) {
    		//Deselects the rows from index0 to index1, inclusive.
    		//table.removeRowSelectionInterval(index, index);
    		//CmdParser.dtmFileList.removeRow(index);
    		CmdParser.refreshFileList();
    	}  
    	 CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		 
    	return;
    }
    
    if(text.contains("续费")) {
    	
    	
		CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		CmdParser.frameShowed.getGlassPane().setVisible(true);
		
    	CmdParser.reNewFile(fileNameQm, filesize, file_count,index);
    	CmdParser.dtmFileList.fireTableDataChanged();
    	    	
    	
		CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		CmdParser.frameShowed.getGlassPane().setVisible(false);
    	return;
    }
    
    // show file details
    CmdParser.showDetails(fileNameQm, index);
}  
}  
	

class AddTask extends SwingWorker<Boolean, Void> implements PropertyChangeListener{

	String FileName;
	int copycount;
	int month;
    public AddTask(String absolutePath, int _count, int _month) {
    	FileName=absolutePath;
    	copycount = _count;
    	month = _month;
	}

	/*
     * Executed in event dispatch thread
     */
    public void done() {
    	//doInBackground方法完成之后，SwingWorker调用done方法
    	CmdParser.getCoinBalance();// set enable according to balance
    	  setProgress(0);
    	  CmdParser.processBar.setBorderPainted(false); 
    	  CmdParser.processBar.setVisible(false);
    	  CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));    	  
    	  CmdParser.frameShowed.getGlassPane().setVisible(false);

    }

	@Override
	protected Boolean doInBackground() throws Exception {
		CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		 CmdParser.frameShowed.getGlassPane().setVisible(true);
		CmdParser.processBar.setBorderPainted(true); 
		CmdParser.processBar.setVisible(true);
         setProgress(0);
        
         if (!CmdParser.testIpfsOnLine()) {
        	 JOptionPane.showMessageDialog(null, "IPFS 掉线 ！请重新启动客户端");
  			return false;
         } 
         
 		
 		String fileNameQM;
 		File f = new File(FileName);
 		
 		int fileSizeInM = Math.max((int) Math.floorDiv(f.length(), 1024*1024),1);
 		
 		int needBalance = CmdParser.estimateValue(fileSizeInM) *copycount * month;
 		
 		if(  CmdParser.getCoinBalance()<needBalance) {
 			JOptionPane.showMessageDialog(null, "余额不足 ! 需要 "+ needBalance);
 			return false;
 		}
 		
 		 setProgress(10);
 		 String FileAdd = FileName;
 		String aeskey="";
 		
		//read public key from dist
		File fkey = new File("server-public.pem");
		if(fkey.exists()) {
				Process pp;
				try {
					  
					    if(CmdParser.osName.toLowerCase().startsWith("win")){ 
					    	String[] cmds = {"cryptoTest", "enc-r", FileName};
					    	pp = Runtime.getRuntime().exec(cmds);
					    }else {//linux
					    	String[] cmds = {"./cryptoTest", "enc-r", FileName};
					    	pp = Runtime.getRuntime().exec(cmds);
					    } 
					CmdParser.lbl_process.setText("加密文件 .....");
					pp.waitFor();
					if (pp.exitValue() != 0) {
						String tag = "cryptoTest enc-r with " + pp.exitValue();
						CmdParser.clearForProcess(tag, pp,pp.getErrorStream());
						CmdParser.clearForProcess(tag, pp,pp.getInputStream());
						JOptionPane.showMessageDialog(null, "加密失败! code = " + pp.exitValue(), "失败" , JOptionPane.ERROR_MESSAGE);
						pp.destroy();
						return false;
					}else {
						BufferedReader reader = new BufferedReader(new InputStreamReader(pp.getInputStream()));
			 			String aeskeyraw = reader.readLine();	
			 			 int beginIndex = aeskeyraw.indexOf("(");
			 			int endIndex = aeskeyraw.indexOf(")");
			 			aeskey = aeskeyraw.substring(beginIndex+1, endIndex).trim();				 			
						CmdParser.clearForProcess("cryptoTest enc-r success ",pp,pp.getInputStream());
						FileAdd = "tmp-aes";
						pp.destroy();
					}
				} catch (Exception e) {
					
					JOptionPane.showMessageDialog(null, e.toString(),"加密失败!",JOptionPane.ERROR_MESSAGE);
					CmdParser.logfile.error("cryptoTest enc-r " + e.toString());
					return false;
				} 
			
		}else {
			JOptionPane.showMessageDialog(null, "缺少server-public.pem文件,无法加密,请联系客服。");
			return false;
		}				
	
 		
 		//STEP 1. get file list for further getFile
 		
 		Process p;
 		CmdParser.lbl_process.setText("保存到本地IPFS .....");
		 setProgress(20);
 		try {
 			String[] cmds = {CmdParser.ipfsCMD, "add",FileAdd, "-q"};
 			p = Runtime.getRuntime().exec(cmds);
 		
 		} catch (IOException e) {
 			JOptionPane.showMessageDialog(null, e.toString(),"start IPFS ADD",JOptionPane.ERROR_MESSAGE);
 			CmdParser.logfile.error("ipfs add " + e.toString());
 			return false;
 		}
 				
 		
 		try {
 				p.waitFor();
 		} catch (InterruptedException e) {
 			JOptionPane.showMessageDialog(null, e.toString());
 			CmdParser.logfile.error("ipfs add " + e.toString());
 				return false;
 		}
 		if (p.exitValue() != 0) {
 			String tag = "ipfs add fail with " + 	p.exitValue();	
 			CmdParser.clearForProcess(tag, p, p.getErrorStream()); 		
 			CmdParser.clearForProcess(tag, p, p.getInputStream()); 	
 			JOptionPane.showMessageDialog(null, "IPFS add 失败！" + p.exitValue());
 			p.destroy();
 			return false;
 		}
 		
 		
 		try {
 			InputStream is = p.getInputStream();
 			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
 			StringBuffer sb = new StringBuffer(); 			
 			String s = reader.readLine();	
 			while(s!=null) {
 				sb.append(s);
 				s = reader.readLine();	
 			}
 			s = sb.toString();
 			CmdParser.logfile.info("ipfs add success : " + s);
 			is.close();
 			p.destroy();
 			
 			 setProgress(30);
 			
 			 File ftmp = new File(FileAdd);
 			 if(ftmp.exists()) {
 				 ftmp.delete(); //remove tmp-aes
 			 }
 			 
 			// extract QM.... 
 			String[] sourceStrArray = s.split(" ");
 			int nameindex = 1;
 			if(sourceStrArray.length==1) {
 				nameindex = 0;
 			}
			if(sourceStrArray[nameindex].startsWith("Qm")&& (sourceStrArray[nameindex].length()==46)) {
				fileNameQM = sourceStrArray[nameindex];
			}else {
				JOptionPane.showMessageDialog(null, "add出错  :" + s,"ipfs add",JOptionPane.ERROR_MESSAGE);
	 			return false;
			}
 		} catch (IOException e) {
 			JOptionPane.showMessageDialog(null, e.toString(),"ipfs add",JOptionPane.ERROR_MESSAGE );
 			CmdParser.logfile.error("add file " + e.toString());
 			return false;
 		}     
			
			
			
			CmdParser.lbl_process.setText("上链支付 .....");
			 setProgress(40);
			
			try {
				String valueStr = String.valueOf(needBalance );
				BigInteger value = Convert.toWei(valueStr + ".0", Convert.Unit.ETHER).toBigInteger();
				BigInteger copyNum = BigInteger.valueOf(copycount);
				BigInteger fileSize = BigInteger.valueOf(fileSizeInM);
				BigInteger months = BigInteger.valueOf(month);
				TransactionReceipt transactionReceipt=null;
				try {
					transactionReceipt =CmdParser.contract.uploadFile(fileNameQM,copyNum,fileSize,months,value).sendAsync().get();
					String status = transactionReceipt.getStatus();
					if(!status.contains("1")) {
						CmdParser.logfile.error("uploadFile fail, tx=" + transactionReceipt.toString());
						CmdParser.logfile.error("\n uploadFile fail, fileHash=" + fileNameQM + "copyNum=" + copyNum + " fileSize=" +fileSize + " month=" + months + " value=" + value);
						JOptionPane.showMessageDialog(null,  " 交易失败！");	
						return false;
					}else {
						CmdParser.logfile.info("uploadFile success, tx=" + transactionReceipt.toString());
					}
				}catch (Exception e) {
					JOptionPane.showMessageDialog(null,  " 交易失败！" + e.toString());	
					CmdParser.logfile.error("\n uploadFile fail, fileHash=" + fileNameQM + "copyNum=" + copyNum + " fileSize=" +fileSize + " month=" + months + " value=" + value);
					return false;	
				}
 						
				File fff = new File(FileName);
				
 				CmdParser.lbl_process.setText("支付确认 .....");
 				setProgress(60);
 				 boolean b_res = false;
		        for (StorageCoin.UploadInfoEventResponse event : CmdParser.contract.getUploadInfoEvents(transactionReceipt)) {
		        	if(!event.owner.equalsIgnoreCase(CmdParser.con.getAccount())) {
		        		continue;
		        	}
		        	b_res = true;
		        	
		        	CmdParser.postHttpTrans(2, valueStr,transactionReceipt.getTransactionHash(),String.valueOf(fff.length())) ;
					SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
					String item = CmdParser.formatListItem("[-]", transactionReceipt.getTransactionHash(), "-"+ valueStr, format.format(new Date()));
					CmdParser.listMode_up.insertElementAt(item, 0);
					
					
		       	// send addTask by IPFS
 				   	try {
 				   		setProgress(60);
 			 			String[] cmds = {CmdParser.ipfsCMD, "dht","addTask",fileNameQM,"-n", Integer.toString(copycount)};
 			 			Process padd = Runtime.getRuntime().exec(cmds);
 			 			CmdParser.lbl_process.setText("发布任务 .....");
 			 			padd.waitFor();
 			 		
 			 			if (padd.exitValue() != 0) {
 			 				String tttag = "dht addTask "	+ 	fileNameQM + " fail with code " + padd.exitValue();
	 			 			CmdParser.clearForProcess(tttag,padd, padd.getErrorStream()); 	
	 			 			CmdParser.clearForProcess(tttag,padd, padd.getInputStream()); 
	 			 			JOptionPane.showMessageDialog(null, "IPFS addTask 失败！");
	 			 			return false;
 			 				}
 			 		} catch (IOException e) {
 			 			JOptionPane.showMessageDialog(null, e.toString(),"add dht addTask",JOptionPane.ERROR_MESSAGE);
 			 			CmdParser.logfile.error("add dht addTask fail " + e.toString());
 			 			return false;
 			 		}
 				   	
 				  CmdParser.lbl_process.setText("通知服务器 .....");
		        	 setProgress(80);	
		        	String ll = Long.toString(f.length(),10);
		        	CmdParser.postHttpQuestWithAes( FileName, fileNameQM, aeskey,ll,copycount, month*30);
		        	
		        	CmdParser.refreshFileList();
		       
		        	//wait for 
		        	
		   		setProgress(100); 
		   		
		   		if(aeskey.length()>2) {
		   			JOptionPane.showMessageDialog(null, "上传 "+FileName + " 成功(密文)! 提示：您的文件将在一段时间后被其他节点保存，请不要立即退出客户端！");
		   		}else {
		   			JOptionPane.showMessageDialog(null, "上传 "+FileName + " 成功(明文)! 提示：您的文件将在一段时间后被其他节点保存，请不要立即退出客户端！");
		   		}
		   		
		   		     
	            //  CmdParser.sendAddTaskToProcess( FileName,  fileNameQM,  copycount); //这个就是程序界面初始化
	             
		   		
			}
		        
	       if(!b_res) {
	        JOptionPane.showMessageDialog(null,  " 没有收到链上的消息，上链失败！");	
	       CmdParser.logfile.error("uploadFile fail, tx=" + transactionReceipt.toString());
	       CmdParser.logfile.error("\n uploadFile fail, fileHash=" + fileNameQM + "copyNum=" + copyNum + " fileSize=" +fileSize + " month=" + months + " value=" + value);
			
	       }
	        
	       CmdParser.getCoinBalance();
	      
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString(),"ipfs add",JOptionPane.ERROR_MESSAGE);
			CmdParser.logfile.error("add file 上链失败 " + e.toString());
			 
		} 
 					
 		
 		CmdParser.lbl_process.setText("");
		return true;
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            CmdParser.processBar.setValue(progress);
          
        } 
	}
}

class DownloadTask extends SwingWorker<Boolean, Void> implements PropertyChangeListener{

	String FileName;
	String fileOriName;
	
    public DownloadTask(String qm, String fileName) {
    	FileName=qm;
    	fileOriName=fileName;
    	
	}
    
	@Override
	protected Boolean doInBackground() throws Exception {
		
		 if (!CmdParser.testIpfsOnLine()) {
        	 JOptionPane.showMessageDialog(null, "IPFS 掉线 ！请重新启动客户端");
  			return false;
         } 
		 CmdParser.processBar.setVisible(true);
		 CmdParser.processBar.setBorderPainted(true);
		CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		CmdParser.frameShowed.getGlassPane().setVisible(true);
			
			
			 
			
		try {
			setProgress(30);
		   
	        //download from ipfs
	        Process p;
	        CmdParser.lbl_process.setText("从IPFS上获取资源 ...");
	        
			try {
				String[] cmd = {CmdParser.ipfsCMD, "get", FileName};
				CmdParser.logfile.info("\n [cmd] := " + cmd.toString());
				p = Runtime.getRuntime().exec(cmd);
				
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, e.toString());
				CmdParser.logfile.error("ipfs get file " +FileName +"  " + e.toString());
				return false;
			}
			
			
			try {
				
				p.waitFor();
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(null, e.toString(),"下载失败",JOptionPane.ERROR_MESSAGE);
				CmdParser.logfile.error("ipfs get file " +FileName +"  " + e.toString());
				return false;
			}
			if (p.exitValue() != 0) {
				String tag = "ipfs get " + FileName + " fail with  " + p.exitValue();
				CmdParser.clearForProcess(tag,p, p.getErrorStream());
				CmdParser.clearForProcess(tag,p, p.getInputStream());
				JOptionPane.showMessageDialog(null, "从IPFS下载 "+FileName + " 失败! code " +p.exitValue());
				p.destroy();
				return false;
			}else {
							
				CmdParser.clearForProcess("ipfs get success ",p, p.getInputStream());
				p.destroy();
			}
			
			CmdParser.lbl_process.setText("解密文件 ....");
				setProgress(70);
				
				File f = new File(Util.getFileName(fileOriName));
				if(f.exists()) {
					f = new File(new Date().getTime() +"__"+ f.getName());
				}
				
					//read public key from dist
					File fkey = new File("public.pem");
					File fkey_pri = new File("private.pem");
					if((!fkey.exists()) || (!fkey_pri.exists())) {
						String os = System.getProperty("os.name"); 
						Process ppp;
					    if(os.toLowerCase().startsWith("win")){  
					    	 ppp = Runtime.getRuntime().exec("cryptoTest init");
					    }else {//linux
					    	 ppp = Runtime.getRuntime().exec("./cryptoTest init");
					    }  
					    ppp.waitFor();
					}
					
					if(fkey.exists() && fkey_pri.exists()) {
						// get aes key from net
						
						String s = CmdParser.con.getAccount()+"/files/"+FileName;						
						String url = CmdParser.con.getHttpAddress()+"v1/ipfs/"+s;
						
							Process pp;
							try {
								 String os = System.getProperty("os.name");  
								    if(os.toLowerCase().startsWith("win")){  
								    	pp = Runtime.getRuntime().exec("cryptoTest URL "+ url);
								    }else {//linux
								    	pp = Runtime.getRuntime().exec("./cryptoTest URL "+ url);
								    }  
								
								pp.waitFor();
								if (pp.exitValue() != 0) {
									String ttag = "cryptoTest URL " + url + " fail with code " + pp.exitValue();
									CmdParser.clearForProcess(ttag, pp,pp.getErrorStream());
									CmdParser.clearForProcess(ttag, pp,pp.getInputStream());
									pp.destroy();
								}else {
									
									//success
									BufferedReader br= new BufferedReader(new InputStreamReader(pp.getInputStream()));
								        try {
								        	String line = br.readLine();
											if(line!=null) {
												if(line.matches("success")) {
													File ff = new File("tmp-aes");
													File ff_hash = new File(FileName);
													ff_hash.delete();// remove encrypted file
													// rename
													try {
														Path path = Files.move(ff.toPath(), f.toPath(), StandardCopyOption.REPLACE_EXISTING);
														JOptionPane.showMessageDialog(null, "下载 "+path.toString() + " 成功!");
													}catch (Exception e) {
														
														JOptionPane.showMessageDialog(null, "下载 "+ff.toString() + " 成功!");
														CmdParser.logfile.error("\n rename " +ff.toPath() +"  --> " + f.toPath() + " fail "+ e.toString());
													}
													
												}else {
													JOptionPane.showMessageDialog(null, line);
													CmdParser.logfile.error("\n cryptoTest URL " +url +"  " + line);
												}
											}
										} catch (IOException e) {
											JOptionPane.showMessageDialog(null, e.toString());
											CmdParser.logfile.error("\n cryptoTest URL " +url +"  " + e.toString());
										}
								       br.close(); 
									CmdParser.clearForProcess("cryptoTest URL success ",pp,pp.getInputStream());
									 
								}
							} catch (Exception e) {
								// 
								JOptionPane.showMessageDialog(null, e.toString());
								CmdParser.logfile.error("\n cryptoTest URL " +url +"  " + e.toString());
							}
				
					}else {
						
					}				
				
				
				CmdParser.lbl_process.setText("");
				setProgress(100);		
				
				return true;
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.toString());
			CmdParser.logfile.error("\n ipfs get  " + e.toString());
		} 
		
	
		return false;
	}
	
	
	public void done() {
    	//doInBackground方法完成之后，SwingWorker调用done方法
    	CmdParser.getCoinBalance();// set enable according to balance
    	  setProgress(0);
    	  CmdParser.processBar.setBorderPainted(false); 
    	  CmdParser.processBar.setVisible(false); 
    	  CmdParser.frameShowed.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    	  CmdParser.frameShowed.getGlassPane().setVisible(false);

    }

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// 
		if ("progress" == evt.getPropertyName()) {
            int progress = (Integer) evt.getNewValue();
            CmdParser.processBar.setValue(progress);
          
        } 
	}
}


