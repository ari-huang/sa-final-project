package ncu.im3069.demo.app;
import org.json.*;
public class Video {
	private int Video_ID;
	private String Video_url;
	private String Video_Name;
	public Video(int Video_ID, String Video_url) {
		this.Video_ID = Video_ID;
		this.Video_url = Video_url;
		
	}
	public Video(String Video_Name, String Video_url) {
		this.Video_Name = Video_Name;
		this.Video_url = Video_url;
	}
	public int getVideo_ID() {
		return this.Video_ID;
		
	}
	public String getVideo_Name(){
		return this.Video_Name;
		
	}
	public void setVideo_Name(String Video_Name) {
		this.Video_Name = Video_Name;
	}
	public String getVideo_url() {
		return this.Video_url;
		
	}
	
	public void setVideo_url(String url) {
		this.Video_url=url;
	}
	
	public JSONObject getData() {
		JSONObject jso = new JSONObject();
		jso.put("Video_ID", getVideo_ID());
		jso.put("Video_url",getVideo_url());
		
		return jso;
	}
}
