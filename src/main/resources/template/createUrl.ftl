//添加
http://localhost:${tomcatPort}/axp-acl/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}/add.htm?data={<#list columnList as a><#if a.isKey='N'>"${a.displayName}":""<#if a_has_next>,</#if></#if></#list>}
//删除
http://localhost:${tomcatPort}/axp-acl/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}/del.htm?data={<#list keyList as a>"${a.displayName}":""<#if a_has_next>,</#if></#list>}
//更新
http://localhost:${tomcatPort}/axp-acl/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}/upd.htm?data={<#list columnList as a>"${a.displayName}":""<#if a_has_next>,</#if></#list>}
//查询
http://localhost:${tomcatPort}/axp-acl/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}/getbyid.htm?data={<#list keyList as a>"${a.displayName}":""<#if a_has_next>,</#if></#list>}
//查询全部
http://localhost:${tomcatPort}/axp-acl/${center ? lower_case ? replace('center','')}/${modelName ? lower_case}/getall.htm