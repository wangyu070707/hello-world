<#assign typeParam><#list keyList as a>${a.columnType} ${a.displayName}<#if a_has_next>, </#if></#list></#assign>
package com.ly.bs.dao.${center}

import javax.inject.Named;
import java.util.List;

import com.ly.bs.modules.${center}.bean.${modelName}Bean;

/**
* @ClassName: ${modelName}Mapper
* @Description: ${bizName}数据库操作接口
* @author ${author}
* @date ${curTime}
*
*/
@Named("${modelName ? uncap_first}Mapper")
public interface ${modelName}Mapper {

    /**
    * @Description: 新增
    **/
    int insertSelective(${modelName}Bean bean);

    /**
    * @Description: 按主键ID删除
    **/
    int deleteByPrimaryKey(${typeParam});

    /**
    * @Description: 更新
    **/
    int updateByPrimaryKeySelective(${modelName}Bean bean);

    /**
    * @Description: 根据主键查询
    **/
    ${modelName}Bean selectByPrimaryKey(${typeParam});

    /**
    * @Description: 查询所有
    **/
    List<${modelName}Bean> selectAll();
}