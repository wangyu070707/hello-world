package com.zhiduan.axp.idl.${center}.service;

import java.util.List;

import com.zhiduan.axp.idl.${center}.service.entity.${modelName}Info;

/**
* @ClassName: ${modelName}
* @Description: ${bizName}服务接口
* @author ${author}
* @date ${curTime}
*/
public interface ${modelName}Service {

    /**
    * @Description: 新增${bizName}
    * @param  info
    * @return
    * @throws
    */
	void add${modelName}(${modelName}Info info) throws Exception;

	/**
    * @Description: 删除${bizName}
    * @param  info
    * @return
    * @throws
    */
	void del${modelName}(${modelName}Info info) throws Exception;

	/**
    * @Description: 更新${bizName}
    * @param  info
    * @return
    * @throws
    */
	void upd${modelName}(${modelName}Info info) throws Exception;

	/**
    * @Description: 根据主键查询${bizName}
    * @param  info
    * @return ${modelName}Info
    * @throws
    */
	${modelName}Info get${modelName}ByPK(${modelName}Info info) throws Exception;

	/**
    * @Description: 查询所有${bizName}
    * @param
    * @return List<${modelName}Info>
    * @throws
    */
	List<${modelName}Info> getAll() throws Exception;
}