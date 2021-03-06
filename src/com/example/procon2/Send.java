package com.example.procon2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.msgpack.MessagePack;
import android.os.Handler;
import android.util.Log;

public class Send{
	private static DatagramSocket sendSocket;
	private static DatagramPacket packet;
	private static InetAddress inetAddress;
	 
	private Timer timer;

	public Send() { 
		timer = new Timer(true);
		
		try {
			inetAddress = InetAddress.getByName("255.255.255.255");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void startDTNConnection(){
		final Handler dtnHandler = new Handler();
	    timer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	          // TODO Auto-generated method stub
	        	dtnHandler.post( new Runnable(){
	            public void run(){
	            	new Thread(new Runnable() {
	            		public void run() {          
	            			List<String> deviceName  = new ArrayList<String>();
	            			List<String> deviceIP    = new ArrayList<String>();
	            			List<String> chatMessage = new ArrayList<String>();
	            			List<String> time        = new ArrayList<String>();
	            			
	            			deviceName.add(DeviceInfo.getDeviceName());
	            			deviceIP.add(DeviceInfo.getDeviceIP());
	            			chatMessage.add("");
	            			time.add("");
	            			
	            			MessageInfo messageInfo = new MessageInfo(0, deviceName, deviceIP, chatMessage, time, DTNMessageCollection.getHash());
	            			
	            			sendByUDP(messageInfo);
	            		}
	            	}).start();
	                
	            }});
	        }
	      }, 0, DeviceInfo.getDtnUpdateTime()   //開始遅延(何ミリ秒後に開始するか)と、周期(何ミリ秒ごとに実行するか)
	    );
	}
	
	public synchronized static void sendByUDP(final MessageInfo messageInfo ) {
		new Thread(new Runnable() {
	    @Override
	    public void run() {
	    	MessagePack msgpack = new MessagePack();
	    	try{
	    		byte[] data = msgpack.write(messageInfo);
	    		sendSocket = new DatagramSocket();
	            packet     = new DatagramPacket(data, data.length, inetAddress, DeviceInfo.getUdpPort());
	            
	            //Log.d("Sending Data Size",Integer.toString(data.length));
	        
	            sendSocket.send(packet);
	            sendSocket.close();
	        }catch(Exception e){
	        	
	        }
	    }
	  }).start();
	}
}
