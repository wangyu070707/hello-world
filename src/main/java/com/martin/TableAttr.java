package com.martin;

/**
 * @author ZXY
 * @ClassName: com.martin.TableAttr
 * @Description: TODO
 * @date 2016/4/8 15:05
 */
public class TableAttr {
    public String columnName;//字段名 "OFFER_GROUP_ID"
    public String displayName;//显示名 OfferGroupId
    public String displayNameSpace;//显示名带空格 Offer Group Id
    public String jdbcType;//数据库字段类型
    public String columnType;//java代码字段类型
    public String columnLength;//最大长度

    public String comment;//备注
    public String isKey;//是否主键


    public TableAttr(String columnName, String displayName, String displayNameSpace, String jdbcType, String columnType, String columnLength, String isKey, String comment) {
        this.columnName = columnName;
        this.displayName = displayName;
        this.displayNameSpace = displayNameSpace;
        this.jdbcType = jdbcType;
        this.columnType = columnType;
        this.columnLength = columnLength;
        this.comment = comment;
        this.isKey = isKey;
    }

    public String getColumnName() {
        return this.columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getDisplayName() {
        return this.displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayNameSpace() {
        return this.displayNameSpace;
    }

    public void setDisplayNameSpace(String displayNameSpace) {
        this.displayNameSpace = displayNameSpace;
    }

    public String getJdbcType() {
        return jdbcType;
    }

    public void setJdbcType(String jdbcType) {
        this.jdbcType = jdbcType;
    }

    public String getColumnType() {
        return this.columnType;
    }

    public void setColumnType(String columnType) {
        this.columnType = columnType;
    }

    public String getColumnLength() {
        return this.columnLength;
    }

    public void setColumnLength(String columnLength) {
        this.columnLength = columnLength;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIsKey() {
        return this.isKey;
    }

    public void setIsKey(String isKey) {
        this.isKey = isKey;
    }
}
