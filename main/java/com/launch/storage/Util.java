package com.launch.storage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.pushingpixels.substance.api.skin.SubstanceBusinessLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCeruleanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceChallengerDeepLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceCremeLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceDustLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceEmeraldDuskLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGeminiLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteGlassLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceGraphiteLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMagellanLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMarinerLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistAquaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceMistSilverLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceModerateLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaBrickWallLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceNebulaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlack2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeBlue2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceOfficeSilver2007LookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceRavenLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceSaharaLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceTwilightLookAndFeel;

public class Util {

	public static String bytesToHexString(byte[] src){  
	    StringBuilder stringBuilder = new StringBuilder("");  
	    if (src == null || src.length <= 0) {  
	        return null;  
	    }  
	    for (int i = 0; i < src.length; i++) {  
	        int v = src[i] & 0xFF;  
	        String hv = Integer.toHexString(v);  
	        if (hv.length() < 2) {  
	            stringBuilder.append(0);  
	        }  
	        stringBuilder.append(hv);  
	    }  
	    return stringBuilder.toString();  
	}  
	
	 static String UTCToCST(String UTCStr) {
	        
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSS'Z'");
	        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
	        try {
				Date utcdate = sdf.parse(UTCStr); //"2018-04-20T07:26:37.443657404Z"
				
				SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	      
		        localFormater.setTimeZone(TimeZone.getDefault());
		        String localTime = localFormater.format(utcdate.getTime());
				return localTime;
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
	        return "";
	    }
		
	 
	 static String getIpfsCapacity(String ipfsDir) {
			String result ="";

	   		try {
	   			File ff = new File(ipfsDir + File.separator+"config");
	   			FileReader reader = new FileReader(ff);
				 BufferedReader bufferedReader = new BufferedReader(reader);
	             String string = null;             
	            
	        	 //"StorageMax": "10GB"		             
					                
					while ((string = bufferedReader.readLine()) != null) {    						
					    if(string.contains("StorageMax")) {
					    	String strv[] = string.split(":");						    	
					    	int indexcount = strv[1].lastIndexOf("GB");
					    	String ss =strv[1].substring(0, indexcount);
					    	result = ss.replace("\"", " ").trim();
					    	break;			    	
					    }
					 }
					bufferedReader.close();
					reader.close();				 
	                	 
				
				
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.toString(),"getIpfsCapicity",JOptionPane.ERROR_MESSAGE);
			}
	   	 return result;
		}
	 
	 static boolean updateIpfsCapacity(String num, String filedir) {
			boolean b_result = false;
				      		
	   		try {
	   			File ff = new File(filedir + File.separator+"config");
	   			FileReader reader = new FileReader(ff);
				 BufferedReader bufferedReader = new BufferedReader(reader);
	             String string = null;
	        	 //"StorageMax": "10GB"		             
				try {
					File ffnew = new File(filedir + File.separator+"config-new");
					 FileWriter fw;
						try {
							fw = new FileWriter(ffnew, false);
						} catch (IOException e) {
							// 
							JOptionPane.showMessageDialog(null, e.toString());
							return b_result;
						}  
		                 BufferedWriter bw = new BufferedWriter(fw);  
					while ((string = bufferedReader.readLine()) != null) {    						
					    if(string.contains("StorageMax")) {  //"StorageMax": "10GB",
					    	String strv[] = string.split(":");	
					    	int index = strv[1].lastIndexOf("GB");
					    	if(index!=-1) {
					    		String newStr = strv[0] + ": \""+num+strv[1].substring(index, strv[1].length());
					    		bw.write(newStr);
					    	}else {
					    		String newStr = strv[0] + ": \""+num+"GB\",";
					    		bw.write(newStr);
					    	}
					    						    	
					    	bw.newLine();			    	
					    }else {	
					    	bw.write(string);
					    	bw.newLine();	
					    }
					 }
					bufferedReader.close();
					reader.close();
					 bw.close();  
	                 fw.close();  
	                 
	                 File mm=new File(filedir + File.separator+"config.bak");  
	                 if(mm.exists()) {
	                	 mm.delete();
	                 }
	                 if(ff.renameTo(mm))  {
	                	 ffnew.renameTo(ff);
	                 }
	                 b_result = true;	 
				} catch (IOException e) {
					// 
					JOptionPane.showMessageDialog(null, e.toString(), "updateIpfsCapacity", JOptionPane.ERROR_MESSAGE);
				}
				
	             
			} catch (FileNotFoundException e1) {
				

				JOptionPane.showMessageDialog(null, e1.toString(), "updateIpfsCapacity", JOptionPane.ERROR_MESSAGE);
			}
	   		return b_result;
		}
		
	 

	 
	 static String getTimeFromUTCBigInteger(BigInteger utc) {
		   Date utcdate = new Date();  
		   utcdate.setTime(utc.longValue()*1000);// from second to millisecond
				SimpleDateFormat localFormater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	      
		        localFormater.setTimeZone(TimeZone.getDefault());
		        String localTime = localFormater.format(utcdate.getTime());
		        return localTime;
	 }
	 
	 
	 static String getFileName(String fileAbsolutePath) {
		 int index = fileAbsolutePath.lastIndexOf("\\");
		 if(index>=0) {
			 return fileAbsolutePath.substring(index+1);
		 }
		 index = fileAbsolutePath.lastIndexOf("/");	
		 if(index>=0) {
			 return fileAbsolutePath.substring(index+1);
		 }
		 return fileAbsolutePath;
	 }
	 
	 static String showSize(String size) {
		 int value=0;
		 try {
			  value = Integer.valueOf(size);
		 } catch (Exception e){
			 
		 }
		 
		 DecimalFormat   fnum   =   new   DecimalFormat("##0.00");
		 if(value<1024) {
			 return size + "B";
		 }
		 double kb = value/1024.0;
		 if(kb>1024) {
			 double mb = kb/1024.0;
			 if(mb>1024) {
				 double gb = mb/1024.0;
				 return  fnum.format(gb) + "GB";  
			 }else {
				 return  fnum.format(mb) + "MB";  
			 }
		 }else {
			
			 return  fnum.format(kb) + "KB";  
		 }
		 
	 }
	 
	 // in M
	 public static float getDirSize(File file) {     
	          
	        if (file.exists()) {     
	            //如果是目录则递归计算其内容的总大小    
	            if (file.isDirectory()) {     
	                File[] children = file.listFiles();     
	                float size = 0;     
	                for (File f : children)     
	                    size += getDirSize(f);     
	                return size;     
	            } else {//如果是文件则直接返回其大小,以“M”为单位   
	            	float size = (float) file.length() / 1024 / 1024;        
	                return size;     
	            }     
	        } else {     
	             
	            return (float) 0.0;     
	        }     
	    }

	public static void changeLook(JFrame f) {
		String[] laf= {"Twilight","Sahara","Raven","OfficeSilver2007","OfficeBlue2007","OfficeBlack2007","Nebula","NebulaBrickWall","Moderate","MistSilver","MistAqua","Mariner","Magellan","Graphite","GraphiteGlass","Gemini","EmeraldDusk","Dust","Creme","ChallengerDeep","Cerulean","Business" };  
	 	 
		 //创建并添加菜单栏
	     JMenuBar menuBar = new JMenuBar();
	     f.setJMenuBar(menuBar);
	     JMenu menuFile = new JMenu("皮肤",false);	     
	     menuBar.add(menuFile);
	     

	     ActionListener all = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				JMenuItem obj= (JMenuItem) e.getSource();
				String clazzName = obj.getText();
				//System.out.println(e.toString() + " " + clazzName);
				switch (clazzName){
				case "Twilight":
					try {
						UIManager.setLookAndFeel(new SubstanceTwilightLookAndFeel());
					} catch (UnsupportedLookAndFeelException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
					break;
				case "Sahara":
					try {
						UIManager.setLookAndFeel(new SubstanceSaharaLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Raven":
					try {
						UIManager.setLookAndFeel(new SubstanceRavenLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "OfficeSilver2007":
					try {
						UIManager.setLookAndFeel(new SubstanceOfficeSilver2007LookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "OfficeBlue2007":
					try {
						UIManager.setLookAndFeel(new SubstanceOfficeBlue2007LookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "OfficeBlack2007":
					try {
						UIManager.setLookAndFeel(new SubstanceOfficeBlack2007LookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Nebula":
					try {
						UIManager.setLookAndFeel(new SubstanceNebulaLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "NebulaBrickWall":
					try {
						UIManager.setLookAndFeel(new SubstanceNebulaBrickWallLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "Moderate":
					try {
						UIManager.setLookAndFeel(new SubstanceModerateLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "MistSilver":
					try {
						UIManager.setLookAndFeel(new SubstanceMistSilverLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "MistAqua":
					try {
						UIManager.setLookAndFeel(new SubstanceMistAquaLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "Mariner":
					try {
						UIManager.setLookAndFeel(new SubstanceMarinerLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;	
				case "Magellan":
					try {
						UIManager.setLookAndFeel(new SubstanceMagellanLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Graphite":
					try {
						UIManager.setLookAndFeel(new SubstanceGraphiteLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "GraphiteGlass":
					try {
						UIManager.setLookAndFeel(new SubstanceGraphiteGlassLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Gemini":
					try {
						UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "EmeraldDusk":
					try {
						UIManager.setLookAndFeel(new SubstanceEmeraldDuskLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Dust":
					try {
						UIManager.setLookAndFeel(new SubstanceDustLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Creme":
					try {
						UIManager.setLookAndFeel(new SubstanceCremeLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "ChallengerDeep":
					try {
						UIManager.setLookAndFeel(new SubstanceChallengerDeepLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "Cerulean":
					try {
						UIManager.setLookAndFeel(new SubstanceCeruleanLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
									 	 
				default:
					try {
						UIManager.setLookAndFeel(new SubstanceBusinessLookAndFeel());
					} catch (UnsupportedLookAndFeelException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}	
				}
				
				f.repaint();
			}};
	     
	     
		 for(String sname : laf) {
			 JMenuItem itemOpen = new JMenuItem(sname);
			   menuFile.add(itemOpen);
			   itemOpen.addActionListener(all);
		 }  
		
	}     
	 
	 
}
