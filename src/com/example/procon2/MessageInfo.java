package com.example.procon2;

import java.util.List;
import org.msgpack.annotation.MessagePackMessage;


/********************************************************************/
/*DTN通信とチャット交換される情報群*/
/*ID番号の振り分けによってどの機能に渡すか決める*/

/********************************************************************/
@MessagePackMessage
public class MessageInfo{
	public int id;                       
	public String[] deviceName;  
	public String[] deviceIP;    
	public String[] chatMessage; 
	public String[] time;       
	public String[] hash;      
	//public String Latitude;  
	//public String Longitude;
	
	public MessageInfo(){

	}
	
	public MessageInfo(int id,List<String> deviceName,List<String> deviceIP,List<String> chatMessage,List<String> time,List<String> hash){
		this.id = id;
		this.deviceName  = (String[])deviceName.toArray(new String[0]);
		this.deviceIP    = (String[])deviceIP.toArray(new String[0]);
		this.chatMessage = (String[])chatMessage.toArray(new String[0]);
		this.time        = (String[])time.toArray(new String[0]);
		this.hash        = (String[])hash.toArray(new String[0]);
	}
}
