package ncu.im3069.demo.controller;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.json.*;

import ncu.im3069.demo.app.CourseHelper;
import ncu.im3069.tools.JsonReader;
public class CourseController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	private CourseHelper ch = CourseHelper.getHelper();
	
	public CourseController() {
		super();
		
	}
	
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	        /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
	        JsonReader jsr = new JsonReader(request);
	        /** 若直接透過前端AJAX之data以key=value之字串方式進行傳遞參數，可以直接由此方法取回資料 */
	        String id = jsr.getParameter("Course_ID");
	        System.out.println("no judge");
	        /** 判斷該字串是否存在，若存在代表要取回個別會員之資料，否則代表要取回全部資料庫內會員之資料 */
	        if (id.isEmpty()) {
	            /** 透過MemberHelper物件之getAll()方法取回所有會員之資料，回傳之資料為JSONObject物件 */
	            JSONObject query = ch.getAll();
	            
	            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	            JSONObject resp = new JSONObject();
	            resp.put("status", "200");
	            resp.put("message", "所有課程資料取得成功");
	            resp.put("response", query);
	    
	            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	            jsr.response(resp, response);
	           
	        }
	        else {
	            /** 透過CourseHelper物件的getByID()方法自資料庫取回該名會員之資料，回傳之資料為JSONObject物件 */
	            JSONObject query = ch.getByID(id);
	            
	            /** 新建一個JSONObject用於將回傳之資料進行封裝 */
	            JSONObject resp = new JSONObject();
	            resp.put("status", "200");
	            resp.put("message", "課程資料取得成功");
	            resp.put("response", query);
	    
	            /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
	            jsr.response(resp, response);
	           
	        }
	    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
	}
		
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			JsonReader jsr = new JsonReader(request);
	        JSONObject jso = jsr.getObject();
			
	        String id = jso.getString("Course_ID");
	        
	        JSONObject query = ch.deleteByID(id);
	        
	        JSONObject resp = new JSONObject();
	        resp.put("status", "200");
	        resp.put("message", "會員移除成功！");
	        resp.put("response", query);
	        
	        jsr.response(resp, response);
		
		
	}
		
	
}
