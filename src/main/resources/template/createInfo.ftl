package com.zhiduan.axp.idl.${center}.service.entity;

import java.io.Serializable;
import java.util.Date;

/**
* @ClassName: ${modelName}Info
* @Description: ${bizName}
* @author ${author}
* @date ${curTime}
*/

public class ${modelName}Info implements Serializable {

    <#list columnList as a>
    /**
    * ${a.comment}
    **/
    private ${a.columnType} ${a.displayName};
    </#list>

    public ${modelName}Info() {
        super();
    }

    public ${modelName}Info(<#list columnList as a>${a.columnType} ${a.displayName}<#if a_has_next>, </#if></#list>) {
<#list columnList as a>
        this.${a.displayName} = ${a.displayName};
</#list>    }

    <#list columnList as a>
    public ${a.columnType} get${a.displayName ? cap_first}() {
        return ${a.displayName};
    }

    public void set${a.displayName ? cap_first}(${a.columnType} ${a.displayName}) {
        <#if a.columnType ? contains("String") >
        this.${a.displayName} = ${a.displayName} == null ? null : ${a.displayName}.trim();
        <#else >
        this.${a.displayName} = ${a.displayName};
        </#if>
    }

    </#list>
}