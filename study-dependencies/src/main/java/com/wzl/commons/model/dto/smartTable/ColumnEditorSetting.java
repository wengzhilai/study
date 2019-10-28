package com.wzl.commons.model.dto.smartTable;

import lombok.Data;

@Data
public class ColumnEditorSetting {
    /**
     * 'text' | 'textarea' | 'completer' | 'list' | 'checkbox'
     */
    public String type ;
    /**
     * completer, list
     */
    public ColumnEditorConfigSetting config ;
}
