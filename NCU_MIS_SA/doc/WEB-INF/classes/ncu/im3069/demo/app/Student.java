package ncu.im3069.demo.app;

import java.util.Calendar;

import org.json.JSONObject;

public class Student{
	
	 /** id�A�|���s�� */
    //private int Member_ID;
    
    /** email�A�|���q�l�l��H�c */
    private String Student_email;
    
    /** name�A�|���m�W */
    private String Student_name;
    
    /** password�A�|���K�X */
    private String Student_password;
    
    /** login_times�A��s�ɶ��������� */
    private int Student_login_times;
    
    /** status�A�|�����էO */
    private String Student_status;
	
	private int Student_ID;
	private int Student_NumberOfRegisteredCourse;
	private StudentHelper sh =  StudentHelper.getHelper();
	
	public Student(String Student_email, String Student_password, String Student_name, int Student_login_times, String Student_status, int Student_ID, int Student_NumberOfRegisteredCourse) {
		
		//this.Member_ID = Member_ID;
		this.Student_email = Student_email;
		this.Student_name = Student_name;
		this.Student_password = Student_password;
		this.Student_login_times = Student_login_times;
		this.Student_status = Student_status;
		this.Student_ID = Student_ID;
		this.Student_NumberOfRegisteredCourse = Student_NumberOfRegisteredCourse;
		
	}
	public Student(String Student_email, String Student_password, String Student_name, int Student_ID) {
        this.Student_email = Student_email;
        this.Student_password = Student_password;
        this.Student_name = Student_name;
        this.Student_ID = Student_ID;
        getLoginTimesStatus();
        /** �p��|�����էO */
        calcAccName();
    }
	
	public Student(String Student_email, String Student_password, String Student_name) {
        this.Student_email = Student_email;
        this.Student_password = Student_password;
        this.Student_name = Student_name;
        
        update();
    }
	public int getID() {
        return this.Student_ID;
    }

    /**
     * ���o�|�����q�l�l��H�c
     *
     * @return the email �^�Ƿ|���q�l�l��H�c
     */
    public String getEmail() {
        return this.Student_email;
    }
    
    /**
     * ���o�|�����m�W
     *
     * @return the name �^�Ƿ|���m�W
     */
    public String getName() {
        return this.Student_name;
    }

    /**
     * ���o�|�����K�X
     *
     * @return the password �^�Ƿ|���K�X
     */
    public String getPassword() {
        return this.Student_password;
    }
    
    /**
     * ���o��s��Ʈɶ���������
     *
     * @return the login times �^�ǧ�s��Ʈɶ���������
     */
    public int getLoginTimes() {
        return this.Student_login_times;
    }
    
    /**
     * ���o�|����Ƥ��|���էO
     *
     * @return the status �^�Ƿ|���էO
     */
    public String getStatus() {
        return this.Student_status;
    }
    /*
    public String getStudent_ID() {
		return this.Student_ID;
	}
	*/
	
	public int getNumberOfRegisteredCourse() {
		return this.Student_NumberOfRegisteredCourse;
	}
    
    /**
     * ��s�|�����
     *
     * @return the JSON object �^��SQL��s�����G�P�����ʸˤ����
     */
    public JSONObject update() {
        /** �s�ؤ@��JSONObject�ΥH�x�s��s�ᤧ��� */
        JSONObject data = new JSONObject();
        /** ���o��s��Ʈɶ��]�Y�{�b���ɶ��^�������� */
        Calendar calendar = Calendar.getInstance();
        this.Student_login_times = calendar.get(Calendar.MINUTE);
        /** �p��b����ݤ��էO */
        calcAccName();
        
        /** �ˬd�ӦW�|���O�_�w�g�b��Ʈw */
        if(this.Student_ID != 0) {
            /** �Y���h�N�ثe��s�ᤧ��Ƨ�s�ܸ�Ʈw�� */
            sh.updateLoginTimes(this);
            /** �z�LMemberHelper����A��s�ثe���|����Ƹm��Ʈw�� */
            data = sh.update(this);
        }
        
        return data;
    }
    /**
     * ���o�ӦW�|���Ҧ����
     *
     * @return the data ���o�ӦW�|�����Ҧ���ƨëʸ˩�JSONObject����
     */
    public JSONObject getData() {
        /** �z�LJSONObject�N�ӦW�|���һݤ���ƥ����i��ʸ�*/ 
        JSONObject jso = new JSONObject();
        //jso.put("Member_ID", getID());
        jso.put("Student_name", getName());
        jso.put("Student_email", getEmail());
        jso.put("Student_password", getPassword());
        jso.put("Student_login_times", getLoginTimes());
        jso.put("Student_status", getStatus());
        jso.put("Student_ID", getID());
        jso.put("Student_NumberOfRegisteredCourse", getNumberOfRegisteredCourse());
        
        return jso;
    }
	
    /**
     * ���o��Ʈw������s��Ʈɶ������ƻP�|���էO
     *
     */
    private void getLoginTimesStatus() {
        /** �z�LMemberHelper����A���o�x�s���Ʈw����s�ɶ������ƻP�|���էO */
        JSONObject data = sh.getLoginTimesStatus(this);
        /** �N��Ʈw���x�s�ӦW�|����������ƫ�����Member�����ݩ� */
        this.Student_login_times = data.getInt("Student_login_times");
        this.Student_status = data.getString("Student_status");
    }
    
    /**
     * �p��|�����էO<br>
     * �Y�����ƫh���u���Ʒ|���v�A�Y���_�ƫh���u�_�Ʒ|���v
     */
    private void calcAccName() {
        /** �p��ثe�����Ƭ����ƩΩ_�� */
        String curr_status = (this.Student_login_times % 2 == 0) ? "偶數會員" : "奇數會員";
        /** �N�s���|���էO������Member���ݩ� */
        this.Student_status = curr_status;
        /** �ˬd�ӦW�|���O�_�w�g�b��Ʈw�A�Y���h�z�LMemberHelper����A��s�ثe���էO���A */
        if(this.Student_ID != 0) sh.updateStatus(this, curr_status);
    }
	

}
