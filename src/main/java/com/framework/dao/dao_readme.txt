DAO层编码规范

1.



问题1：在mapper.xml文件中，如果参数为单个字符串参数时，即 parameterType="String"
那么获取参数的时候，要使用${_parameter}


问题2：c3p0 debug报错java.lang.Exception: DEBUG -- CLOSE BY CLIENT STACK TRACE
destroy-method="close"
该错误源代码中，只要为debug模式时，就会抛出此错误代码。