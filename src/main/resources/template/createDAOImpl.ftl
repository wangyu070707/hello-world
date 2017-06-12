<#assign mapper>${modelName ? uncap_first}Mapper</#assign>
<#assign typeParam><#list keyList as a>${a.columnType} ${a.displayName}<#if a_has_next>, </#if></#list></#assign>
<#assign param><#list keyList as a>${a.displayName}<#if a_has_next>, </#if></#list></#assign>
package com.zhiduan.axp.dal.${center};

import java.util.List;

import com.zhiduan.axp.idl.${center}.dao.${modelName}Dao;
import com.zhiduan.axp.idl.${center}.dao.bean.${modelName}Bean;
import com.zhiduan.axp.idl.${center}.dao.mapper.${modelName}Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
* @ClassName: ${modelName}
* @Description: ${bizName}数据操作实现类
* @author ${author}
* @date ${curTime}
*/
@Repository("${modelName ? uncap_first}Dao")
public class ${modelName}DaoImpl implements ${modelName}Dao {

    @Autowired
    @Qualifier("${mapper}")
    private ${modelName}Mapper ${mapper};

    /**
    * 增加
    */
    @Override
    public void add${modelName}(${modelName}Bean bean) throws Exception{
        ${mapper}.insertSelective(bean);
    }

    /**
    * 根据主键删除
    */
    @Override
    public void del${modelName}(${typeParam}) throws Exception {
        ${mapper}.deleteByPrimaryKey(${param});
    }

    /**
    * 更改
    */
    @Override
    public void upd${modelName}(${modelName}Bean bean) throws Exception {
        ${mapper}.updateByPrimaryKeySelective(bean);
    }

    /**
    * 根据主键查找
    */
    @Override
    public ${modelName}Bean get${modelName}ByPK(${typeParam}) throws Exception {
        return ${mapper}.selectByPrimaryKey(${param});
    }

    /**
    * 查询所有
    */
    @Override
    public List<${modelName}Bean> getAll() throws Exception {
        return ${mapper}.selectAll();
    }
}