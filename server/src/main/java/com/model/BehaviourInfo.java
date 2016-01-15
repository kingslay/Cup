package com.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by king on 15/11/12.
 */
@Entity
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class BehaviourInfo {
    @Id
    @GeneratedValue
    public Long behaviourid;
    public Long accountid;
    public Timestamp ts;
    //闹钟格式是12:23
    @Column(columnDefinition="CHAR(5)")
    public String clock;
    //温度
    public Integer temperature;

    //温度描述
    public String explanation;
}
