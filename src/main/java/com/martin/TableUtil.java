package com.martin;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.*;

/**
 * @author ZXY
 * @ClassName: com.martin.TableUtil
 * @Description: TODO
 * @date 2016/4/8 15:03
 */
public class TableUtil {

    /**
     * 数据库字段转换
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    public static Map<String, Object> transferTable(String tableName) throws Exception {
        DatabaseMetaData metaData = DBUtil.connectionDB().getMetaData();
        ResultSet tableRet = metaData.getTables(null, "%", tableName, new String[]{"TABLE"});
        if (tableRet.next()) {
            System.out.println("Table is: " + tableRet.getString("TABLE_NAME").toUpperCase());
        } else {
            throw new Exception("Table not found!");
        }


        String columnName = null;
        String displayName = null;
        String displayNameSpace = null;
        String jdbcType = null;
        String columnType = null;
        TableAttr tableAttr = null;
        TableAttr keyAttr = null;
        String columnLength = null;
        String isKey = "N";
        String comment = null;

        //获取主键信息
        ResultSet keyReset = metaData.getPrimaryKeys(null, null, tableName);
        Set keySet = new HashSet();
        while (keyReset.next()) {
            keySet.add(keyReset.getString("COLUMN_NAME"));
        }

        //获取列信息
        ResultSet colReset = metaData.getColumns(null, "%", tableName, "%");
        List<Object> columnList = new ArrayList<Object>();
        List<Object> keyList = new ArrayList<Object>();
        while (colReset.next()) {
            columnName = colReset.getString("COLUMN_NAME");//COMMISSION_RULE_ID
            displayName = TableUtil.getDisplayName(columnName);//CommissionRuleId
            displayNameSpace = TableUtil.getDisplayNameSpace(columnName);//Commission Rule Id
            jdbcType = colReset.getString("TYPE_NAME");
            if (jdbcType.equalsIgnoreCase("varchar") || jdbcType.equalsIgnoreCase("char")) {
                columnType = "String";
            } else if (jdbcType.equalsIgnoreCase("unsigned") || jdbcType.equalsIgnoreCase("int unsigned") || jdbcType.equalsIgnoreCase("int")) {
                jdbcType = "INTEGER";
                columnType = "Integer";
            } else if (jdbcType.equalsIgnoreCase("text")) {
                jdbcType = "LONGVARCHAR";
                columnType = "String";
            } else if (jdbcType.equalsIgnoreCase("bigint") || jdbcType.equalsIgnoreCase("bigint unsigned")) {
                jdbcType = "BIGINT";
                columnType = "Long";
            } else if (jdbcType.equalsIgnoreCase("smallint")) {
                columnType = "Short";
            } else if (jdbcType.equalsIgnoreCase("datetime")) {
                jdbcType = "DATE";
                columnType = "Date";
            } else if (jdbcType.equalsIgnoreCase("timestamp")) {
                jdbcType = "TIMESTAMP";
                columnType = "Date";
            } else if (jdbcType.equalsIgnoreCase("date")) {
                columnType = "Date";
            } else if (jdbcType.equalsIgnoreCase("tinyint") || jdbcType.equalsIgnoreCase("tinyint unsigned")) {
                columnType = "Integer";
            } else if (jdbcType.equalsIgnoreCase("bit")) {
                jdbcType = "INTEGER";
                columnType = "Integer";
            }

            columnLength = colReset.getString("COLUMN_SIZE");// datetime时null
            if (columnLength == null) {
                columnLength = "20";
            }

            comment = colReset.getString("REMARKS");

            if (keySet.contains(columnName)) {
                isKey = "Y";
                keyAttr = new TableAttr(columnName, displayName, displayNameSpace, jdbcType, columnType, null, null, comment);
                keyList.add(keyAttr);
            } else {
                isKey = "N";
            }

            tableAttr = new TableAttr(columnName, displayName, displayNameSpace, jdbcType, columnType, columnLength, isKey, comment);
            columnList.add(tableAttr);
        }

        /**调用关闭连接方法*/
        DBUtil.close();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyList", keyList);
        map.put("columnList", columnList);
        return map;
    }

    /**
     * 传入字段值 RULE_ID -> RuleId
     *
     * @param columnName
     * @return
     * @throws Exception
     */
    public static String getDisplayName(String columnName) throws Exception {
        String displayName = "";

        String[] array = columnName.split("_");
        for (int i = 0; i < array.length; i++) {
            if (i == 0) {
                displayName += array[i].substring(0, 1).toLowerCase() + array[i].substring(1).toLowerCase();
            } else {
                displayName += array[i].substring(0, 1).toUpperCase() + array[i].substring(1).toLowerCase();
            }
        }
        return displayName;
    }

    /**
     * 传入字段值 RULE_ID -> Rule Id
     *
     * @param columnName
     * @return
     * @throws Exception
     */
    public static String getDisplayNameSpace(String columnName) throws Exception {
        String displayNameSpace = "";

        String[] array = columnName.split("_");
        for (int i = 0; i < array.length; i++) {
            displayNameSpace += array[i].substring(0, 1).toUpperCase() + array[i].substring(1).toLowerCase() + " ";
        }

        return displayNameSpace.substring(0, displayNameSpace.length() - 1);
    }
}
