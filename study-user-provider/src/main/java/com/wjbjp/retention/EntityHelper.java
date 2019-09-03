package com.wjbjp.retention;

import com.wjbjp.model.mynum.DatabaseGeneratedOption;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class EntityHelper<T> {

    public String tableName;
    public String dbKey;
    public DatabaseGeneratedOption dbKeyType;
    public String classKey;
    public T data;
    public Class<T> classType;

    public EntityHelper(Class<T> _classType){
        classType=_classType;
        try {
            data=classType.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        this.tableName=getTableName(this.data);
        getKey(this.data);
    }


    public EntityHelper(T _inClass){
        this.data=_inClass;
        classType=(Class<T>)_inClass.getClass();

        this.tableName=getTableName(_inClass);
        getKey(_inClass);
    }

    public T getMyClass(){
        return data;
    }

    /**
     * 获取对象名值，和值
     * @param saveFieldList
     * @param ignoreFieldList
     * @return
     */
    public Map getEntityMapValue(List<String> saveFieldList, List<String> ignoreFieldList){
        HashMap<String ,Object> info = new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();//获取类成员变量
        for (Field field: fields) {//遍历
            if (
                    (saveFieldList != null && saveFieldList.size() != 0 && !saveFieldList.contains(field.getName())) ||
                            (ignoreFieldList != null && ignoreFieldList.size() != 0 && ignoreFieldList.contains(field.getName()))
                    ) {
                continue;
            }
            if (field.isAnnotationPresent(Column.class)) {//判断是不是EmployeeName类型注解
                Column column = field.getAnnotation(Column.class);
                Object value = null;
                try {
                    value = field.get(data);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                if (value != null && column != null && column.value() != null) {
                    info.put(column.value(), value);//获取注解的值
                }
            }
        }
        return info;
    }

    /**
     * 字段和占位符
     * @param saveFieldList
     * @param ignoreFieldList
     * @return
     */
    public  HashMap<String, String> getEntityMapMybits(List<String> saveFieldList, List<String> ignoreFieldList) {
        HashMap<String, String> info = new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();//获取类成员变量
        for (Field field : fields) {//遍历
            if (
                    (saveFieldList != null && saveFieldList.size() != 0 && !saveFieldList.contains(field.getName())) ||
                            (ignoreFieldList != null && ignoreFieldList.size() != 0 && ignoreFieldList.contains(field.getName()))
                    ) {
                continue;
            }
            if (field.isAnnotationPresent(Column.class)) {//判断是不是EmployeeName类型注解
                Column column = field.getAnnotation(Column.class);
                info.put(column.value() == null || column.value().equals("") ? field.getName() : column.value(), "#{data.data." + field.getName() + "}");//获取注解的值
            }
        }
        return info;
    }

    /**
     * 获取属于和字段，用于查询替换
     * @return
     */
    public  HashMap<String, String> getEntityMapNameColumn() {
        HashMap<String, String> info = new HashMap<>();
        Field[] fields = data.getClass().getDeclaredFields();//获取类成员变量
        for (Field field : fields) {//遍历
            if (field.isAnnotationPresent(Column.class)) {//判断是不是EmployeeName类型注解
                Column column = field.getAnnotation(Column.class);
                info.put(field.getName(), column.value() == null || column.value().equals("") ? field.getName() : column.value());//获取注解的值
            }
        }
        return info;
    }



    /**
     * 获取表名
     * @param clazz
     * @param <T>
     * @return
     */
    protected <T> String getTableName(T clazz) {
        if (clazz.getClass().isAnnotationPresent(Table.class)) {
            Table type = clazz.getClass().getAnnotation(Table.class);
            return type.value();
        } else {
            return clazz.getClass().getName();
        }
    }

    /**
     * 获取所有数据库的字段名，用于插入
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> List<String> getFiledList(T clazz){
        ArrayList<String> reList=new ArrayList<>();
        Field[] fields = clazz.getClass().getDeclaredFields();//获取类成员变量
        for (Field field: fields) {//遍历
            //判断是不是EmployeeName类型注解
            if (field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                if(column.value()==null){
                    reList.add(field.getName());
                }else
                {
                    reList.add(column.value());
                }
            }
        }
        return reList;
    }


    /**
     * 获取所有查询别名数据，用于查询
     * @param allItems
     * @return
     */
    public List<String> getSelectFiledList(List<String> allItems) {
        List<String> reList = new ArrayList<>();
        Field[] fields = this.data.getClass().getDeclaredFields();//获取类成员变量
        for (Field field : fields) {//遍历
            //判断是不是EmployeeName类型注解
            if (field.isAnnotationPresent(Column.class) && (allItems == null || allItems.size() == 0 || allItems.contains(field.getName()))) {
                Column column = field.getAnnotation(Column.class);
                if (column.value() == null || column.value().equals("")) {
                    reList.add(field.getName());
                } else {
                    reList.add(column.value() + " " + field.getName());
                }
            }
        }
        return reList;
    }

    /**
     * 获取主键
     * @param clazz
     * @return
     * @throws Exception
     */
    protected void getKey(T clazz){
        Field[] fields = clazz.getClass().getDeclaredFields();//获取类成员变量
        for (Field field: fields) {//遍历
            //判断是不是EmployeeName类型注解
            if (field.isAnnotationPresent(Key.class) && field.isAnnotationPresent(Column.class)) {
                Column column = field.getAnnotation(Column.class);
                DatabaseGenerated generatedType = field.getAnnotation(DatabaseGenerated.class);
                if (generatedType == null) this.dbKeyType = DatabaseGeneratedOption.Computed;
                this.dbKeyType = generatedType.value();
                this.dbKey = StringUtils.isAnyBlank(column.value()) ? field.getName() : column.value();
                this.classKey = field.getName();
                return;
            }
        }
    }


    public List<String> getTableFields(List<String> saveFieldList, List<String> ignoreFieldList) {
        List<String> reFieldStr = new ArrayList<>();   //所有数据字段名
        Field[] fields = data.getClass().getDeclaredFields();//获取类成员变量
        for (Field proInfo : fields) {
            if (
                    (saveFieldList != null && saveFieldList.size() != 0 && !saveFieldList.contains(proInfo.getName())) ||
                    (ignoreFieldList != null && ignoreFieldList.size() != 0 && ignoreFieldList.contains(proInfo.getName()))
                    ) {
                continue;
            }
            if (proInfo.isAnnotationPresent(Column.class)) {//判断是不是EmployeeName类型注解
                Column column = proInfo.getAnnotation(Column.class);
                reFieldStr.add(column.value()==null?proInfo.getName():column.value());
            }
        }
        return reFieldStr;
    }

    /// <summary>
    /// 新增加数据，并返回增加的ID
    /// </summary>
    /// <param name="errorMessage"></param>
    /// <param name="sql"></param>
    /// <returns></returns>
    public String GetSaveSql(List<String> saveFieldList, List<String> ignoreFieldList)
    {
        String sql = null;
        if (ignoreFieldList == null) ignoreFieldList =Arrays.asList(this.classKey);
        Map<String, String> map=getEntityMapMybits(saveFieldList,ignoreFieldList);

        sql = "INSERT INTO  " + this.tableName + "(" + StringUtils.join(map.keySet(),",") + ") VALUES(" + StringUtils.join(map.values(),",") + ")";
        sql += "\r\n select @@IDENTITY ";
        return sql;
    }


    /// <summary>
    /// 生成查询的SQL语句
    /// 如果没有传条件值，则默认为主键
    /// </summary>
    /// <param name="saveFieldList">保存的字段</param>
    /// <param name="ignoreFieldList">忽略的字段</param>
    /// <param name="whereList">条件字段</param>
    /// <returns></returns>
    public String GetUpdateSql(List<String> saveFieldList , List<String> ignoreFieldList, List<String> whereList)
    {
        if (ignoreFieldList == null) ignoreFieldList =  Arrays.asList(this.classKey);
        if (whereList == null) whereList = Arrays.asList(this.classKey);
        Map<String, String> map=getEntityMapMybits(saveFieldList,ignoreFieldList);
        List<String> upItem=new ArrayList<>();
        for (String key : map.keySet()) {
            upItem.add(key+"="+map.get(key));
        }

        Map<String, String> whereMap=getEntityMapMybits(whereList,null);
        List<String> whereItem=new ArrayList<>();
        for (String key : whereMap.keySet()) {
            whereItem.add(key+"="+whereMap.get(key));
        }
        String sql = null;
        sql = "UPDATE " +this.tableName + " SET ";
        sql += StringUtils.join(upItem,",");
        sql += " WHERE ";
        sql +=  StringUtils.join(whereMap,",");
        return sql;
    }


    public String GetUpdateSql(String upStr, String whereStr)
    {
        String sql = String.format("UPDATE %1$s SET %2$s WHERE %3$s", this.tableName, upStr, whereStr);
        return sql;
    }

    /// <summary>
    /// 获取删除SQL
    /// </summary>
    /// <param name="filterList"></param>
    /// <returns></returns>
    public String GetDeleteSql(List<String> filterList)
    {
        if (filterList == null || filterList.size() == 0)
        {
            return  null;
        }
        Map<String, String> map=getEntityMapMybits(filterList,null);
        List<String> upItem=new ArrayList<>();
        for (String key : map.keySet()) {
            upItem.add(key+"="+map.get(key));
        }

        String sql = "DELETE FROM %s WHERE %2";
        sql = String.format(sql, this.tableName, StringUtils.join(upItem," AND "));
        return sql;
    }

    public String GetDeleteSql(String whereStr)
    {
        if (StringUtils.isAnyBlank(whereStr))
        {
            return  null;
        }
        String sql = "DELETE FROM %s WHERE %s";
        sql = String.format(sql,this.tableName, whereStr);
        return sql;
    }



    public String GetFindAllSql(String whereStr, List<String> allItems)
    {
        String sql = "SELECT %s FROM %s WHERE %s";
        if (StringUtils.isAnyBlank(whereStr))
        {
            sql = "SELECT %s FROM %s";
            sql = String.format(sql, StringUtils.join(this.getSelectFiledList(allItems),","), this.tableName);
        }
        else
        {
            sql = String.format(sql, StringUtils.join(this.getSelectFiledList(allItems),","), this.tableName ,whereStr);
        }
        return sql;
    }



    /// <summary>
    /// 获取单条SQL
    /// </summary>
    /// <param name="filterList"></param>
    /// <returns></returns>
    public String GetSingleSql(List<String> filterList, String orderByStr)
    {
        if (!StringUtils.isAnyBlank(orderByStr))
        {
            if (orderByStr.toLowerCase().trim().indexOf("order by") == -1)
            {
                orderByStr = "order by " + orderByStr;
            }
        }

        String sql = "SELECT top 1 %s FROM %s WHERE %s %s";
        if (filterList == null || filterList.size() == 0)
        {
            sql = "SELECT top 1 %s FROM %s %s";
            sql = String.format(sql, StringUtils.join(this.getSelectFiledList(null),","), this.tableName, orderByStr);
        }
        else
        {
            Map<String, String> whereMap=getEntityMapMybits(filterList,null);
            List<String> whereItem=new ArrayList<>();
            for (String key : whereMap.keySet()) {
                whereItem.add(key+"="+whereMap.get(key));
            }

            sql = String.format(sql,
                    StringUtils.join(this.getSelectFiledList(null),","),
                    this.tableName,
                    StringUtils.join(whereItem," and "),
                    orderByStr);
        }
        return sql;
    }

    /// <summary>
    /// 获取单条SQL 根据主键获取单条
    /// </summary>
    /// <returns></returns>
    public String GetSingleSql() {
        String sql = "SELECT %s FROM %s WHERE %s=%s limit 1";
        Map<String, String> whereMap = getEntityMapMybits(Arrays.asList(classKey), null);
        sql = String.format(sql, StringUtils.join(this.getSelectFiledList(null), ","), this.tableName, dbKey, whereMap.get(dbKey));
        return sql;
    }

    public String GetSingleSql(String whereStr) {
        String sql = "SELECT top 1 %s FROM %s WHERE %s";
        sql = String.format(sql, StringUtils.join(this.getSelectFiledList(null), ","), this.tableName, whereStr);
        return sql;
    }

    public String GetSingleSql(String whereStr, String orderByStr) {
        if (!StringUtils.isAnyBlank(orderByStr)) {
            if (orderByStr.toLowerCase().trim().indexOf("order by") == -1) {
                orderByStr = "order by " + orderByStr;
            }
        }
        String sql = "SELECT  %s FROM %s WHERE %s %s";
        sql = String.format(sql, StringUtils.join(this.getSelectFiledList(null), ","), this.tableName, whereStr, orderByStr);
        return sql;
    }




}
