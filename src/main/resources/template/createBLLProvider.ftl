
    <!-- ${bizName} -->
    <bean id="${modelName ? uncap_first}Provider" class="com.taobao.hsf.app.spring.util.HSFSpringProviderBean" init-method="init">
        <property name="serviceInterface" value="com.zhiduan.axp.idl.${center}.service.${modelName}Service" />
        <property name="target" ref="${modelName ? uncap_first}" />
        <property name="serviceVersion" value="1.0.0" />
        <property name="serviceGroup" value="axp${centerCode}_${r"${lable}"}" />
        <property name="clientTimeout" value="3000" />
    </bean>
</beans>