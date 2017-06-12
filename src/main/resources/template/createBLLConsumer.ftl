
    <!-- ${bizName} -->
    <bean id="${modelName ? uncap_first}Dao" class="com.taobao.hsf.app.spring.util.HSFSpringConsumerBean" init-method="init">
        <property name="interfaceName" value="com.zhiduan.axp.idl.${center}.dao.${modelName}Dao" />
        <property name="version" value="1.0.0" />
        <property name="group" value="axp${centerCode}_${r"${lable}"}" />
    </bean>
</beans>