DAO层编码规范

1.



问题1：在mapper.xml文件中，如果参数为单个字符串参数时，即 parameterType="String"
那么获取参数的时候，要使用${_parameter}