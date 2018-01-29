/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pc.controller.server;

/**
 *
 * @author sameer
 */
public class UserLogDetails {
    
    String username;
    String sess_strt_time;
    String sess_strt_date;
    String activity_name;
    String act_start_date;
    String act_start_time;
    String act_end_time;
    String act_end_date;
    
    UserLogDetails(String un, String sst,String ssd, String act, String asd, String ast, String aet, String aed){
        username = un;
        sess_strt_time = sst;
        sess_strt_date = ssd;
        activity_name = act;
        act_start_date = asd;
        act_start_time = ast;
        act_end_time = aet;
        act_end_date = aed;
        
    }
    
    public void setUsername(String un){username = un;}
    public String getUsername(){return username;}
    public void setSessionStrtTime(String sst){sess_strt_time = sst;}
    public String getSessionStrtTime(){return sess_strt_time;}
    public void setSessionStrtDate(String ssd){sess_strt_date = ssd;}
    public String getSessionStrtDate(){return sess_strt_date;}
    public void setActivity_name(String act_name){activity_name = act_name;}
    public String getActivity_name(){return activity_name;}
    public void setActStartDate(String asd){act_start_date = asd;}
    public String getActStartDate(){return act_start_date;}
    public void setActStartTime(String ast){act_start_time = ast;}
    public String getActStartTime(){return act_start_time;}
    public void setActEndTime(String aet){act_end_time = aet;}
    public String getActEndTime(){return act_end_time;}
    public void setActEndDate(String aed){act_end_date = aed;}
    public String getActEndDate(){return act_end_date;}
    
}
