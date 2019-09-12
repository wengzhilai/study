package com.user.consumer.model.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class FaLogin implements Serializable {
    /**
    * 
    */
    private Integer id;

    /**
    * 
    */

    private String loginName;

    /**
    * 
    */
    private String password;

    /**
    * 
    */
    private String phoneNo;

    /**
    * 
    */
    private String emailAddr;

    /**
    * 
    */
    private String verifyCode;

    /**
    * 
    */
    private Date verifyTime;

    /**
    * 
    */
    private Integer isLocked;

    /**
    * 
    */
    private Date passUpdateDate;

    /**
    * 
    */
    private String lockedReason;

    /**
    * 
    */
    private Integer failCount;

    private static final long serialVersionUID = 1L;
}