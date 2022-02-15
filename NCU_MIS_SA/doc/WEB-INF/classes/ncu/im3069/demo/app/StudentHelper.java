package ncu.im3069.demo.app;

import java.sql.*;
import java.time.LocalDateTime;
import org.json.*;

import ncu.im3069.demo.util.DBMgr;

// TODO: Auto-generated Javadoc
/**
 * <p>
 * The Class MemberHelper<br>
 * MemberHelper���O�]class�^�D�n�޲z�Ҧ��PMember�����P��Ʈw����k�]method�^
 * </p>
 * 
 * @author IPLab
 * @version 1.0.0
 * @since 1.0.0
 */

public class StudentHelper {
    
    /**
     * ��Ҥơ]Instantiates�^�@�ӷs���]new�^MemberHelper����<br>
     * �ĥ�Singleton���ݭn�z�Lnew
     */
    private StudentHelper() {
        
    }
    
    /** �R�A�ܼơA�x�sMemberHelper���� */
    private static StudentHelper th;
    
    /** �x�sJDBC��Ʈw�s�u */
    private Connection conn = null;
    
    /** �x�sJDBC�w�ǳƤ�SQL���O */
    private PreparedStatement pres = null;
    
    /**
     * �R�A��k<br>
     * ��@Singleton�]��ҼҦ��^�A�Ȥ��\�إߤ@��MemberHelper����
     *
     * @return the helper �^��MemberHelper����
     */
    public static StudentHelper getHelper() {
        /** Singleton�ˬd�O�_�w�g��MemberHelper����A�Y�L�hnew�@�ӡA�Y���h�����^�� */
        if(th == null) th = new StudentHelper();
        
        return th;
    }
    
    /**
     * �z�L�|���s���]ID�^�R���|��
     *
     * @param id �|���s��
     * @return the JSONObject �^��SQL���浲�G
     */
    public JSONObject deleteByID(int id) {
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            
            /** SQL���O */
            String sql = "DELETE FROM `missa`.`students` WHERE `Student_ID` = ? LIMIT 1";
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, id);
            /** ����R����SQL���O�ðO���v�T����� */
            row = pres.executeUpdate();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }

        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��P�v�T��ơA�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);

        return response;
    }
    
    /**
     * ���^�Ҧ��|�����
     *
     * @return the JSONObject �^��SQL���浲�G�P�۸�Ʈw���^���Ҧ����
     */
    public JSONObject getAll() {
        /** �s�ؤ@�� Member ���� m �ܼơA�Ω�����C�@��d�ߦ^���|����� */
    	Student t = null;
        /** �Ω��x�s�Ҧ��˯��^���|���A�HJSONArray�覡�x�s */
        JSONArray jsa = new JSONArray();
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT * FROM `missa`.`students`";
            
            /** �N�ѼƦ^���SQL���O���A�Y�L�h���Υu�ݭn���� prepareStatement */
            pres = conn.prepareStatement(sql);
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** �z�L while �j�鲾��pointer�A���o�C�@���^�Ǹ�� */
            while(rs.next()) {
                /** �C����@���j���ܦ��@����� */
                row += 1;
                
                /** �N ResultSet ����ƨ��X */
                int student_ID = rs.getInt("Student_ID");
                String student_name = rs.getString("Student_name");
                String student_email = rs.getString("Student_email");
                String student_password = rs.getString("Student_password");
                int student_login_times = rs.getInt("Student_login_times");
                String student_status = rs.getString("Student_status");
                //String bankAccount = rs.getString("BankAccount");
                int numberOfRegisteredCourse = rs.getInt("Student_NumberOfRegisteredCourse");
                
                /** �N�C�@���|����Ʋ��ͤ@�W�sMember���� */
                t = new Student(student_email, student_password, student_name, student_login_times, student_status, student_ID, numberOfRegisteredCourse);
                /** ���X�ӦW�|������ƨëʸ˦� JSONsonArray �� */
                jsa.put(t.getData());
            }

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��B�v�T��ƻP�Ҧ��|����Ƥ�JSONArray�A�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * �z�L�|���s���]ID�^���o�|�����
     *
     * @param id �|���s��
     * @return the JSON object �^��SQL���浲�G�P�ӷ|���s�����|�����
     */
    public JSONObject getByID(String id) {
        /** �s�ؤ@�� Member ���� m �ܼơA�Ω�����C�@��d�ߦ^���|����� */
    	Student t = null;
        /** �Ω��x�s�Ҧ��˯��^���|���A�HJSONArray�覡�x�s */
        JSONArray jsa = new JSONArray();
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT * FROM `missa`.`students` WHERE `Student_ID` = ? LIMIT 1";
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, id);
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** �z�L while �j�鲾��pointer�A���o�C�@���^�Ǹ�� */
            /** ���T�ӻ���Ʈw�u�|���@���ӷ|���s������ơA�]�����i�H���Ψϥ� while �j�� */
            while(rs.next()) {
                /** �C����@���j���ܦ��@����� */
                row += 1;
                
                /** �N ResultSet ����ƨ��X */
                int student_ID = rs.getInt("Student_ID");
                String student_name = rs.getString("Student_name");
                String student_email = rs.getString("Student_email");
                String student_password = rs.getString("Student_password");
                int student_login_times = rs.getInt("Student_login_times");
                String student_status = rs.getString("Student_status");
                //String bankAccount = rs.getString("BankAccount");
                int numberOfRegisteredCourse = rs.getInt("Student_NumberOfRegisteredCourse");
                
                /** �N�C�@���|����Ʋ��ͤ@�W�sMember���� */
                t = new Student(student_email, student_password, student_name, student_login_times, student_status, student_ID, numberOfRegisteredCourse);
                /** ���X�ӦW�|������ƨëʸ˦� JSONsonArray �� */
                jsa.put(t.getData());
            }
            
        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��B�v�T��ƻP�Ҧ��|����Ƥ�JSONArray�A�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * ���o�ӦW�|������s�ɶ��P���ݤ��|���էO
     *
     * @param m �@�W�|����Member����
     * @return the JSON object �^�ǸӦW�|������s�ɶ��P���ݲէO�]�HJSONObject�i��ʸˡ^
     */
    public JSONObject getLoginTimesStatus(Student t) {
        /** �Ω��x�s�ӦW�|�����˯�����s�ɶ������ƻP�|���էO����� */
        JSONObject jso = new JSONObject();
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;

        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT * FROM `missa`.`students` WHERE `Student_ID` = ? LIMIT 1";
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, t.getID());
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();
            
            /** �z�L while �j�鲾��pointer�A���o�C�@���^�Ǹ�� */
            /** ���T�ӻ���Ʈw�u�|���@���ӹq�l�l�󤧸�ơA�]�����i�H���Ψϥ� while�j�� */
            while(rs.next()) {
                /** �N ResultSet ����ƨ��X */
                int student_login_times = rs.getInt("Student_login_times");
                String student_status = rs.getString("Student_status");
                /** �N��ʸ˦�JSONObject��� */
                jso.put("Student_login_times", student_login_times);
                jso.put("Student_status", student_status);
            }
            
        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }

        return jso;
    }
    
    /**
     * �ˬd�ӦW�|�����q�l�l��H�c�O�_���Ƶ��U
     *
     * @param m �@�W�|����Member����
     * @return boolean �Y���Ƶ��U�^��False�A�Y�ӫH�c���s�b�h�^��True
     */
    public boolean checkDuplicate(Student s){
        /** ����SQL�`��ơA�Y���u-1�v�N���Ʈw�˯��|������ */
        int row = -1;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT count(*) FROM `missa`.`students` WHERE `Student_email` = ?";
            
            /** ���o�һݤ��Ѽ� */
            String student_email = s.getEmail();
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, student_email);
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();

            /** �����в����̫�@�C�A���o�ثe���X��b��Ʈw�� */
            rs.next();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** 
         * �P�_�O�_�w�g���@���ӹq�l�l��H�c�����
         * �Y�L�@���h�^��False�A�_�h�^��True 
         */
        return (row == 0) ? false : true;
    }
    
    /**
     * �إ߸ӦW�|���ܸ�Ʈw
     *
     * @param m �@�W�|����Member����
     * @return the JSON object �^��SQL���O���椧���G
     */
    public JSONObject create(Student t) {
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "INSERT INTO `missa`.`students`(`Student_name`, `Student_email`, `Student_password`, `Student_modified`, `Student_created`, `Student_login_times`, `Student_status`)"
                    + " VALUES(?, ?, ?, ?, ?, ?, ?)";
            
            /** ���o�һݤ��Ѽ� */
            String student_name = t.getName();
            String student_email = t.getEmail();
            String student_password = t.getPassword();
            int student_login_times = t.getLoginTimes();
            String student_status = t.getStatus();
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, student_name);
            pres.setString(2, student_email);
            pres.setString(3, student_password);
            pres.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            pres.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()));
            pres.setInt(6, student_login_times);
            pres.setString(7, student_status);
            
            /** ����s�W��SQL���O�ðO���v�T����� */
            row = pres.executeUpdate();
            
            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(pres, conn);
        }

        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);

        /** �NSQL���O�B��O�ɶ��P�v�T��ơA�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("time", duration);
        response.put("row", row);

        return response;
    }
    
    /**
     * ��s�@�W�|�����|�����
     *
     * @param m �@�W�|����Member����
     * @return the JSONObject �^��SQL���O���浲�G�P���椧���
     */
    public JSONObject update(Student t) {
        /** �����^�Ǥ���� */
        JSONArray jsa = new JSONArray();
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "Update `missa`.`students` SET `Student_name` = ? ,`Student_password` = ? , `Student_modified` = ? WHERE `Student_email` = ?";
            /** ���o�һݤ��Ѽ� */
            String student_name = t.getName();
            String student_email = t.getEmail();
            String student_password = t.getPassword();
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, student_name);
            pres.setString(2, student_password);
            pres.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pres.setString(4, student_email);
            /** �����s��SQL���O�ðO���v�T����� */
            row = pres.executeUpdate();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(pres, conn);
        }
        
        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��P�v�T��ơA�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    /**
     * ��s�|����s��Ƥ�������
     *
     * @param m �@�W�|����Member����
     */
    public void updateLoginTimes(Student t) {
        /** ��s�ɶ��������� */
        int new_times = t.getLoginTimes();
        
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "Update `missa`.`students` SET `Student_login_times` = ? WHERE `Student_ID` = ?";
            /** ���o�|���s�� */
            int student_ID = t.getID();
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setInt(1, new_times);
            pres.setInt(2, student_ID);
            /** �����s��SQL���O */
            pres.executeUpdate();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(pres, conn);
        }
    }
    
    /**
     * ��s�|�����|���էO
     *
     * @param m �@�W�|����Member����
     * @param teacher_status �|���էO���r��]String�^
     */
    public void updateStatus(Student t, String student_status) {      
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "Update `missa`.`students` SET `Student_status` = ? WHERE `Student_ID` = ?";
            /** ���o�|���s�� */
            int student_ID = t.getID();
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, student_status);
            pres.setInt(2, student_ID);
            /** �����s��SQL���O */
            pres.executeUpdate();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(pres, conn);
        }
    }
    public boolean checkLogin(String email, String password ){
    	 
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";

        /** ����SQL�`��� */
        int row = 0;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;

        try {
        	/** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT count(*) FROM `missa`.`students` WHERE `Student_email` = ? AND `Student_password` = ? ";
            /** �N�ѼƦ^���SQL���O�� */

            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            pres.setString(2, password);
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** �����в����̫�@�C�A���o�ثe���X��b��Ʈw�� */
            rs.last();
            row = rs.getInt("count(*)");
            System.out.print(row);

        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn); //�_�hDB�|���աA�]���q�D�ƥةT�w
        }
        /** 
         * �P�_�O�_�w�g���@���ӹq�l�l��H�c�����
         * �Y�L�@���h�^��False�A�_�h�^��True 
         */
        return (row == 0) ? false : true;  
    }
    
    public JSONObject getByEmail(String email) {
        /** �s�ؤ@�� Member ���� m �ܼơA�Ω�����C�@��d�ߦ^���|����� */
    	Student t = null;
        /** �Ω��x�s�Ҧ��˯��^���|���A�HJSONArray�覡�x�s */
        JSONArray jsa = new JSONArray();
        /** �O����ڰ��椧SQL���O */
        String exexcute_sql = "";
        /** �����{���}�l����ɶ� */
        long start_time = System.nanoTime();
        /** ����SQL�`��� */
        int row = 0;
        /** �x�sJDBC�˯���Ʈw��^�Ǥ����G�A�H pointer �覡���ʨ�U�@����� */
        ResultSet rs = null;
        
        try {
            /** ���o��Ʈw���s�u */
            conn = DBMgr.getConnection();
            /** SQL���O */
            String sql = "SELECT * FROM `missa`.`students` WHERE `Student_email` = ? LIMIT 1";
            
            /** �N�ѼƦ^���SQL���O�� */
            pres = conn.prepareStatement(sql);
            pres.setString(1, email);
            /** ����d�ߤ�SQL���O�ðO����^�Ǥ���� */
            rs = pres.executeQuery();

            /** �����u����檺SQL���O�A�æL�X **/
            exexcute_sql = pres.toString();
            System.out.println(exexcute_sql);
            
            /** �z�L while �j�鲾��pointer�A���o�C�@���^�Ǹ�� */
            /** ���T�ӻ���Ʈw�u�|���@���ӷ|���s������ơA�]�����i�H���Ψϥ� while �j�� */
            while(rs.next()) {
                /** �C����@���j���ܦ��@����� */
                row += 1;
                
                /** �N ResultSet ����ƨ��X */
                int student_ID = rs.getInt("Student_ID");
                String student_name = rs.getString("Student_name");
                String student_email = rs.getString("Student_email");
                String student_password = rs.getString("Student_password");
                int student_login_times = rs.getInt("Student_login_times");
                String student_status = rs.getString("Student_status");
                //String bankAccount = rs.getString("BankAccount");
                int numberOfRegisteredCourse = rs.getInt("Student_NumberOfRegisteredCourse");
                
                /** �N�C�@���|����Ʋ��ͤ@�W�sMember���� */
                t = new Student(student_email, student_password, student_name, student_login_times, student_status, student_ID, numberOfRegisteredCourse);
                /** ���X�ӦW�|������ƨëʸ˦� JSONsonArray �� */
                jsa.put(t.getData());
            }
            
        } catch (SQLException e) {
            /** �L�XJDBC SQL���O���~ **/
            System.err.format("SQL State: %s\n%s\n%s", e.getErrorCode(), e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            /** �Y���~�h�L�X���~�T�� */
            e.printStackTrace();
        } finally {
            /** �����s�u������Ҧ���Ʈw�������귽 **/
            DBMgr.close(rs, pres, conn);
        }
        
        /** �����{����������ɶ� */
        long end_time = System.nanoTime();
        /** �����{������ɶ� */
        long duration = (end_time - start_time);
        
        /** �NSQL���O�B��O�ɶ��B�v�T��ƻP�Ҧ��|����Ƥ�JSONArray�A�ʸ˦�JSONObject�^�� */
        JSONObject response = new JSONObject();
        response.put("sql", exexcute_sql);
        response.put("row", row);
        response.put("time", duration);
        response.put("data", jsa);

        return response;
    }
    
    

}
