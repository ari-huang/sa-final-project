package ncu.im3069.demo.app;
import java.sql.Timestamp;
import java.util.Calendar;

import org.json.*;
public class Message {
	private int Message_ID;
	private int QAArea_ID;
	private String FinalModifyTime;
	private String Message_content;
	private String Video_Name;
    private MessageHelper mh =  MessageHelper.getHelper();

	public Message() {
		
		
	}
	public  Message(String Message_content,String FianlModifyTime, String Video_Name) {
		this.Video_Name = Video_Name;
		this.Message_content = Message_content;
	
		this.FinalModifyTime = FianlModifyTime;
	}
	public  Message(int Message_ID,int QAarea_ID,String FianlModifyTime, String Message_content) {
		this.QAArea_ID = QAarea_ID;
		this.Message_content = Message_content;
		this.Message_ID = Message_ID;
		this.FinalModifyTime = FianlModifyTime;
	}
	public  Message(int QAarea_ID, String Message_content) {
		this.QAArea_ID = QAarea_ID;
		this.Message_content = Message_content;
	}
	public Message(int id) {
		this.Message_ID=id;
		
	}
	public int getMessage_ID(){
		return this.Message_ID;
		
	}
	public int getQAarea_ID() {
		return this.QAArea_ID;
	}
	public String getVideo_Name() {
		return this.Video_Name;
	}
	public String getFinalModifyTime() {
		
		return this.FinalModifyTime;
	}
	public String getMessage_content() {
		
		return this.Message_content;
	}
	public void setMessage(String sm) {
		
		this.Message_content=sm;
	}
	
	public JSONObject getData() {
        /** 透過JSONObject將該名會員所需之資料全部進行封裝*/ 
        JSONObject jso = new JSONObject();
        jso.put("Message_ID", getMessage_ID());
        jso.put("QAarea_ID", getQAarea_ID());
        jso.put("FinalModifyTime", getFinalModifyTime());
        jso.put("Message_content", getMessage_content());
      
        
        return jso;
    }

}
