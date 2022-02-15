package ncu.im3069.demo.app;
import org.json.*;

public class Course {
	
	private int Course_ID;
	private String WatchProgress;
	private String Course_Name;
	public Course(int id, String Course_Name) {
		this.Course_ID = id;
		this.Course_Name = Course_Name;
	}
	
	public Course(int id) {
		this.Course_ID=id;
		
	}
	public String getCourse_Name() {
		return this.Course_Name;
	}
	
	public int getCourse_ID() {
		return this.Course_ID;
		
	}
	public String getWatchProgress() {
		return this.WatchProgress;
	}
	
	public JSONObject getData() {
		/** 透過JSONObject將該項課程所需之資料全部進行封裝*/
        JSONObject jso = new JSONObject();
        jso.put("Course_ID", getCourse_ID());
        jso.put("Course_Name", getCourse_Name());
        

        return jso;
		
	}
}

