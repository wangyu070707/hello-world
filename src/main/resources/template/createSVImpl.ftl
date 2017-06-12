<#assign param><#list keyList as a>${a.displayName}<#if a_has_next>, </#if></#list></#assign>
<#assign info>${modelName}Info</#assign>
<#assign bean>${modelName}Bean</#assign>
package com.zhiduan.axp.bll.${center};

import com.zhiduan.axp.idl.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.ArrayList;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.zhiduan.axp.idl.${center}.dao.bean.${bean};
import com.zhiduan.axp.idl.${center}.dao.${modelName}Dao;
import com.zhiduan.axp.idl.${center}.service.entity.${info};
import com.zhiduan.axp.idl.${center}.service.${modelName}Service;

/**
* @ClassName: ${modelName}Impl
* @Description: ${bizName}服务实现
* @author ${author}
* @date ${curTime}
*/
@Service("${modelName ? uncap_first}Service")
public class ${modelName}ServiceImpl implements ${modelName}Service {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("${modelName ? uncap_first}Dao")
    private ${modelName}Dao dao;

    /**
    * @Description: 新增${bizName}
    * @param  info
    * @return
    * @throws
    */
    @Override
    public void add${modelName}(${info} info) throws Exception {
        ${bean} bean = new ${bean}();
        if(info == null){
            //参数不能为空
            throw new BusinessException("111");
        }
        //TODO 判断必填和数据格式
        BeanUtils.copyProperties(info, bean);
        dao.add${modelName}(bean);
    }

    /**
    * @Description: 删除${bizName}
    * @param  info
    * @return
    * @throws
    */
    @Override
    public void del${modelName}(${info} info) throws Exception {
        if(info == null){
            //参数不能为空
            throw new BusinessException("111");
        }
        <#list keyList as a>${a.columnType} ${a.displayName} = info.get${a.displayName ? cap_first}();</#list>
        //查询数据库，是否存在相关记录
        ${bean} dbBean = dao.get${modelName}ByPK(${param});
        if(dbBean != null){
            //TODO 判断必填和数据格式
            dao.del${modelName}(${param});
        }
        else {
            logger.info("未查询相关信息，无法删除");
            //未查询到相关信息，无法执行此操作
            throw new BusinessException("115");
        }
    }

    /**
    * @Description: 更新${bizName}
    * @param  info
    * @return
    * @throws
    */
    @Override
    public void upd${modelName}(${info} info) throws Exception {
        ${bean} bean = new ${bean}();
        if(info == null){
            //参数不能为空
            throw new BusinessException("111");
        }
        <#list keyList as a>${a.columnType} ${a.displayName} = info.get${a.displayName ? cap_first}();</#list>
        //查询数据库，是否存在相关记录
        ${bean} dbBean = dao.get${modelName}ByPK(${param});
        if(dbBean != null){
            //TODO 判断必填和数据格式
            BeanUtils.copyProperties(info, bean);
            dao.upd${modelName}(bean);
        }
        else {
            logger.info("未查询相关信息，无法更新");
            //未查询到相关信息，无法执行此操作
            throw new BusinessException("115");
        }
    }

    /**
    * @Description: 根据主键查询${bizName}
    * @param  info
    * @return ${info}
    * @throws
    */
    @Override
    public ${info} get${modelName}ByPK(${info} info) throws Exception {
        ${info} retInfo = new ${info}();
        <#list keyList as a>${a.columnType} ${a.displayName} = info.get${a.displayName ? cap_first}();</#list>
        ${bean} bean = dao.get${modelName}ByPK(${param});
        if (bean != null) {
            BeanUtils.copyProperties(bean, retInfo);
        } else {
            logger.info("未查询到相关信息");
        }
        return retInfo;
    }

    /**
    * @Description: 查询所有${bizName}
    * @param
    * @return List<${info}>
    * @throws
    */
    @Override
    public List<${info}> getAll() throws Exception {
        List<${info}> infoList = new ArrayList<>();
        List<${bean}> beanList = dao.getAll();
        BeanCopier copier = BeanCopier.create(${bean}.class, ${info}.class, false);
        for (${bean} bean : beanList) {
            ${info} info = new ${info}();
            copier.copy(bean, info, null);
            infoList.add(info);
        }
        return infoList;
    }
}