<#assign bean>com.ly.bs.modules.${center}.bean.${modelName}Bean</#assign>
<#assign keys><#list keyList as a> ${a.columnName} = ${r"#"}{${a.displayName},jdbcType=${a.jdbcType}}</#list></#assign>
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.ly.bs.dao.${center}.${modelName}Mapper">
    <resultMap id="BaseResultMap" type="${bean}">
        <constructor >
            <#list keyList as a>
            <idArg column="${a.columnName}" jdbcType="${a.jdbcType}" javaType="java.lang.${a.columnType}" />
            </#list>
            <#list columnList as a>
            <#if a.isKey='N'><arg column="${a.columnName}" jdbcType="${a.jdbcType}" javaType="<#if a.columnType='Date'>java.util.${a.columnType}<#else>java.lang.${a.columnType}</#if>" /></#if>
            </#list>
        </constructor>
    </resultMap>
    <sql id="Base_Column_List" >
        <#list columnList as a>${a.columnName}<#if a_has_next>,</#if></#list>
    </sql>

    <insert id="insertSelective" parameterType="${bean}" >
        insert into ${tableName}
        <trim prefix="(" suffix=")" suffixOverrides="," >
        <#list columnList as a><#if a.isKey='N'>
            <if test="${a.displayName} != null" >
            ${a.columnName},
            </if>
        </#if></#list>
        </trim>
        <trim prefix="values (" suffix=")" suffixOverrides="," >
        <#list columnList as a><#if a.isKey='N'>
            <if test="${a.displayName} != null" >
            ${r"#"}{${a.displayName},jdbcType=${a.jdbcType}},
            </if>
        </#if></#list>
        </trim>
    </insert>
    <delete id="deleteByPrimaryKey" parameterType="java.lang.Long" >
        delete from ${tableName} where ${keys}
    </delete>
    <update id="updateByPrimaryKeySelective" parameterType="${bean}" >
        update ${tableName}
        <set >
        <#list columnList as a><#if a.isKey='N'>
            <if test="${a.displayName} != null" >
            ${a.columnName} = ${r"#"}{${a.displayName},jdbcType=${a.jdbcType}},
            </if>
        </#if></#list>
        </set>
        where ${keys}
    </update>
    <select id="selectByPrimaryKey" resultMap="BaseResultMap" parameterType="java.lang.Long" >
        select
        <include refid="Base_Column_List" />
        from ${tableName}
        where ${keys}
    </select>
    <select id="selectAll" resultMap="BaseResultMap" parameterType="java.lang.Long" >
        select  * from ${tableName}
    </select>
</mapper>
