<#assign typeParam><#list keyList as a>${a.columnType} ${a.displayName}<#if a_has_next>, </#if></#list></#assign>
package com.zhiduan.axp.idl.${center}.dao;

import java.util.List;

import com.zhiduan.axp.idl.${center}.dao.bean.${modelName}Bean;

/**
* @ClassName: ${modelName}
* @Description: ${bizName}数据操作接口
* @author ${author}
* @date ${curTime}
*/
public interface ${modelName}Dao {
    /**
    * 增加
    */
    void add${modelName}(${modelName}Bean bean) throws Exception;

    /**
    * 删除
    */
    void del${modelName}(${typeParam}) throws Exception;

    /**
    * 更改
    */
    void upd${modelName}(${modelName}Bean bean) throws Exception;

    /**
    * 根据主键查找
    */
    ${modelName}Bean get${modelName}ByPK(${typeParam}) throws Exception;

    /**
    * 查询所有
    */
    List<${modelName}Bean> getAll() throws Exception;
}