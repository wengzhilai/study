package com.wzl.commons.model.dto.smartTable;

import com.wzl.commons.model.mynum.TableColumnType;
import lombok.Data;

@Data
public class SmartTableColumnSetting {
    public String columnName;

    public String title;
    public String width;

    public boolean filter;
    public boolean editable;
    public boolean sort;

    /**
     * 'text'|'html'|'custom'
     */
    public String type;

    public ColumnEditorSetting editor;

    public boolean show;
}
