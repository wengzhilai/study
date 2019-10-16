package com.wzl.commons.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;

import java.util.Date;
import java.util.List;

/**
 * 用户
 */
@Table("FA_USER")
public class FaUserEntity {


    /**
    * ID
    */
    @Key
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @Required
    @Display(Name = "ID")
    @Column("ID")
    public int id;

    /**
    * 姓名
    */
    @Display(Name = "姓名")
    @Column("NAME")
    public String name;

    /**
    * 登录名
    */
    @Display(Name = "登录名")
    @Column("LOGIN_NAME")
    public String loginName;

    /**
    * 头像图片
    */
    @Display(Name = "头像图片")
    @Column("ICON_FILES")
    public String iconFiles;

    /**
    * 归属地
    */
    @Required
    @Display(Name = "归属地")
    @Column("DISTRICT_ID")
    public int districtId;

    /**
    * 锁定
    */
    @Display(Name = "锁定")
    @Column("IS_LOCKED")
    public int isLocked;

    /**
    * 创建时间
    */
    @Display(Name = "创建时间")
    @Column("CREATE_TIME")
    public Date createTime;

    /**
    * 登录次数
    */
    @Display(Name = "登录次数")
    @Column("LOGIN_COUNT")
    public int loginCount;

    /**
    * 最后登录时间
    */
    @Display(Name = "最后登录时间")
    @Column("LAST_LOGIN_TIME")
    public Date lastLoginTime;

    /**
    * 最后离开时间
    */
    @Display(Name = "最后离开时间")
    @Column("LAST_LOGOUT_TIME")
    public Date lastLogoutTime;

    /**
    * 最后活动时间
    */
    @Display(Name = "最后活动时间")
    @Column("LAST_ACTIVE_TIME")
    public Date lastActiveTime;

    /**
    * 备注
    */
    @Display(Name = "备注")
    @Column("REMARK")
    public String remark;


    /**
     * 是管理管理员
     */
    public boolean isAdmin;

    /**
     * 是普通管理员
     */
    public boolean isLeader;
    /**
     * 用户角色
     */
    public List<Integer> roleIdList;
    /**
     * 可编辑的用户ID
     */
    public List<Integer> canEditIdList;

}
/*
select 
  ID id,
  NAME name,
  LOGIN_NAME loginName,
  ICON_FILES_ID iconFilesId,
  DISTRICT_ID districtId,
  IS_LOCKED isLocked,
  CREATE_TIME createTime,
  LOGIN_COUNT loginCount,
  LAST_LOGIN_TIME lastLoginTime,
  LAST_LOGOUT_TIME lastLogoutTime,
  LAST_ACTIVE_TIME lastActiveTime,
  REMARK remark 
from FA_USER
*/


/*
{
  "id": {
    "title": "ID",
    "type": "int",
    "editable": true
  },
  "name": {
    "title": "姓名",
    "type": "String",
    "editable": true
  },
  "loginName": {
    "title": "登录名",
    "type": "String",
    "editable": true
  },
	"roleIdList": {
		"title": "角色",
		"type": "string",
		"editable": true,
		"editor": {
			"type": "listAsyn",
			"config": {
                                "api": "query/GeListData",
                                "postEnt": {"code":"role"},
				"hasAllCheckBox": false,
				"maxHeight": 100
			}
		}
	},
  "iconFilesId": {
    "title": "头像图片",
    "type": "int",
    "editable": true
  },
  "districtId": {
    "title": "归属地",
    "type": "int",
    "editable": true
  },
  "isLocked": {
    "title": "锁定",
    "type": "String",
    "editable": true,
		"editor": {
			"type": "list",
			"config": {
				"list": [{
						"value": "1",
						"title": "是"
					},
					{
						"value": "0",
						"title": "否"
					}
				]
			}
		}
  },
  "createTime": {
    "title": "创建时间",
    "type": "Date",
    "editable": true
  },
  "loginCount": {
    "title": "登录次数",
    "type": "int",
    "editable": true
  },
  "lastLoginTime": {
    "title": "最后登录时间",
    "type": "Date",
    "editable": true
  },
  "lastLogoutTime": {
    "title": "最后离开时间",
    "type": "Date",
    "editable": true
  },
  "lastActiveTime": {
    "title": "最后活动时间",
    "type": "Date",
    "editable": true
  },
  "remark": {
    "title": "备注",
    "type": "String",
    "editable": true
  }
}
*/
