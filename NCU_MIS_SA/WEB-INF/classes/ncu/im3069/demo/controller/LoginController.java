package ncu.im3069.demo.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import ncu.im3069.demo.app.StudentHelper;
import ncu.im3069.tools.JsonReader;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentHelper sh =  StudentHelper.getHelper();  
    /**
     * @see HttpServlet#HttpServlet()
     */
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 /** 透過JsonReader類別將Request之JSON格式資料解析並取回 */
        JsonReader jsr = new JsonReader(request);
          JSONObject jso = jsr.getObject();
     /** 取出經解析到JSONObject之Request參數 */
          String email = jso.getString("Student_email");
          String password = jso.getString("Student_password");
/** 透過MemberHelper物件的deleteByID()方法至資料庫刪除該名會員，回傳之資料為JSONObject物件 */
          Boolean login = sh.checkLogin(email,password);
          
          if(login== true){
/** 新建一個JSONObject用於將回傳之資料進行封裝 */
        	  JSONObject resp = new JSONObject();
        	  resp.put("status", "200");
        	  resp.put("message", "成功登入！");
        	  resp.put("response", login);
        	  /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        	  jsr.response(resp, response);
          }
          else{
        	  JSONObject resp = new JSONObject();
        	  resp.put("status", "200");
        	  resp.put("message", "帳號密碼有誤！");
        	  resp.put("response", login);

        	  /** 透過JsonReader物件回傳到前端（以JSONObject方式） */
        	  jsr.response(resp, response);

          }
	}


}