package model;

import java.sql.Timestamp;

/**
 * Created by king on 15/11/12.
 */
public class BehaviourInfo {
    public Integer behaviourid;
    public Integer accountid;
    public Timestamp ts;
    //闹钟格式是12:23
    public String clock;
    //温度
    public Integer temperature;
    //温度描述
    public String explanation;
}
