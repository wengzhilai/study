package com.dependencies.mybatis.model.entity;

import com.wzl.commons.model.mynum.DatabaseGeneratedOption;
import com.wzl.commons.retention.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *
 */
@Data
@Table("sequence")
public class SequenceEntity {

    /**
     * 表名
     */
    @Key
    @Required
    @DatabaseGenerated(DatabaseGeneratedOption.Computed)
    @StringLength(20)
    @Column
    @ApiModelProperty(value = "表名")
    public String seq_name;
    /**
     * 当前值
     */
    @Required
    @Column
    @ApiModelProperty(value = "当前值")
    public int current_val;

    /**
     * 增加值
     */
    @Required
    @StringLength(20)
    @Column
    @ApiModelProperty(value = "增加值")
    public int increment_val;
}
