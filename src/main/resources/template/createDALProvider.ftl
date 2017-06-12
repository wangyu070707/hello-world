
    <!-- ${bizName} -->
    <bean id="${modelName ? uncap_first}MapperProvider" class="com.taobao.hsf.app.spring.util.HSFSpringProviderBean" init-method="init">
        <property name="serviceInterface" value="com.zhiduan.axp.idl.${center}.dao.mapper.${modelName}Mapper" />
        <property name="target" ref="${modelName ? uncap_first}Mapper" />
        <property name="serviceVersion" value="1.0.0" />
        <property name="serviceGroup" value="axp${centerCode}_${r"${lable}"}" />
        <property name="clientTimeout" value="3000" />
    </bean>
    <bean id="${modelName ? uncap_first}DaoProvider" class="com.taobao.hsf.app.spring.util.HSFSpringProviderBean" init-method="init">
        <property name="serviceInterface" value="com.zhiduan.axp.idl.${center}.dao.${modelName}Dao" />
        <property name="target" ref="${modelName ? uncap_first}Dao" />
        <property name="serviceVersion" value="1.0.0" />
        <property name="serviceGroup" value="axp${centerCode}_${r"${lable}"}" />
        <property name="clientTimeout" value="3000" />
    </bean>
</beans>