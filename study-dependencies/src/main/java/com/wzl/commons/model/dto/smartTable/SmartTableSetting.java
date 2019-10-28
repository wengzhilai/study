package com.wzl.commons.model.dto.smartTable;

import lombok.Data;

import java.util.List;

@Data
public class SmartTableSetting {
    public String heardBtn;
    public String rowsBtn;
    public boolean showCheckbox;
    public List<SmartTableColumnSetting> columnsList;
}
