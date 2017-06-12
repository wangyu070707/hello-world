<#assign SV>${modelName ? uncap_first}Service</#assign>
package com.zhiduan.axp.acl.${center};

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

import com.zhiduan.axp.idl.common.service.ResultInfo;
import com.zhiduan.axp.idl.${center}.service.entity.${modelName}Info;
import com.zhiduan.axp.idl.${center}.service.${modelName}Service;
import com.zhiduan.axp.tools.JsonUtils;
import com.zhiduan.axp.base.BaseController;

/**
* @ClassName: ${modelName}Controller
* @Description: ${bizName}控制器
* @author ${author}
* @date ${curTime}
*
*/
@Controller
@RequestMapping("/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}")
public class ${modelName}Controller extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("${SV}")
    private ${modelName}Service ${SV};

    /**
    * @Method: add${modelName}
    * @Description: 新增${bizName}
    * @param request
    * @return ResultInfo 返回类型
    * @author ${author}
    * @throws
    * @date ${curTime}
    **/
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultInfo add${modelName}(HttpServletRequest request) {
        String data = request.getParameter("data");
        if (StringUtils.isBlank(data)) {
            return new ResultInfo(-1, "111", "参数不能为空");
        }

        ResultInfo result;
        try {
            ${modelName}Info info = JsonUtils.readValue(data, ${modelName}Info.class);
            ${SV}.add${modelName}(info);
            result = new ResultInfo(0, "", "新增成功");
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }


    /**
    * @Method: del${modelName}
    * @Description: 删除${bizName}
    * @param request
    * @return ResultInfo 返回类型
    * @author ${author}
    * @throws
    * @date ${curTime}
    **/
    @ResponseBody
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public ResultInfo del${modelName}(HttpServletRequest request) {
        String data = request.getParameter("data");
        if(StringUtils.isBlank(data)){
            return new ResultInfo(-1, "111", "参数不能为空");
        }

        ResultInfo result;
        try {
            ${modelName}Info info = JsonUtils.readValue(data, ${modelName}Info.class);
            ${SV}.del${modelName}(info);
            result = new ResultInfo(0, "", "删除成功");
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }

    /**
    * @Method: upd${modelName}
    * @Description: 更新${bizName}
    * @param request
    * @return ResultInfo 返回类型
    * @author ${author}
    * @throws
    * @date ${curTime}
    **/
    @ResponseBody
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public ResultInfo upd${modelName}(HttpServletRequest request) {
        String data = request.getParameter("data");
        if(StringUtils.isBlank(data)){
            return new ResultInfo(-1, "111", "参数不能为空");
        }

        ResultInfo result;
        try {
            ${modelName}Info info = JsonUtils.readValue(data, ${modelName}Info.class);
            ${SV}.upd${modelName}(info);
            result = new ResultInfo(0, "", "更新成功");
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }

    /**
    * @Method: get${modelName}ByPK
    * @Description: 根据主键查询${bizName}
    * @param request
    * @return ResultInfo 返回类型
    * @author ${author}
    * @throws
    * @date ${curTime}
    **/
    @ResponseBody
    @RequestMapping(value = "/getbyid", method = RequestMethod.POST)
    public ResultInfo get${modelName}ById(HttpServletRequest request) {
        String data = request.getParameter("data");
        if(StringUtils.isBlank(data)){
            return new ResultInfo(-1, "111", "参数不能为空");
        }

        ResultInfo result;
        try {
            ${modelName}Info info = JsonUtils.readValue(data, ${modelName}Info.class);
            ${modelName}Info retInfo = ${SV}.get${modelName}ByPK(info);
            result = new ResultInfo(0, "", "根据主键查询成功", retInfo);
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }

    /**
    * @Method: getAll${modelName}
    * @Description: 获取所有${bizName}
    * @param request
    * @return ResultInfo 返回类型
    * @author ${author}
    * @throws
    * @date ${curTime}
    **/
    @ResponseBody
    @RequestMapping(value = "/getall", method = RequestMethod.POST)
    public ResultInfo getAll${modelName}(HttpServletRequest request) {
        ResultInfo result;
        try {
            List<${modelName}Info> retInfo = ${SV}.getAll();
            result = new ResultInfo(0, "", "查询全部成功", retInfo);
        } catch (Exception e) {
            result = processException(e, logger);
        }
        return result;
    }
}
