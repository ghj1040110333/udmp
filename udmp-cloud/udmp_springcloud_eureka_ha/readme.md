1.mvn clean package 生成执行jar <br />
2.执行java -jar 执行jar.jar --spring.profiles.active=profile名称

####例如：执行命令<br/>
<pre><code>
java -jar udmp_springcloud_eureka_ha.jar --spring.profiles.active=e1 --spring.application.name=eureka-1
java -jar udmp_springcloud_eureka_ha.jar --spring.profiles.active=e2 --spring.application.name=eureka-2
</code></pre>