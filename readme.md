# 开发工具
Eclipse IDE

# 创建工程
1. new maven project
2. Create a simple project(skip archetype selection)
3. Group Id / Artifact Id / Packaging (war)
4. src/webapp/web.xml  
 	- Properties -> Project Facets -> Dynamic Web Module  
 	- cancel -> choose  
 	- add xml directory  
5. set mirror  and versions
	- Windows -> Preference -> Maven -> User Settings
		```xml
		<mirrors>
			<mirror>
				<id>alimaven</id>
				<name>aliyun maven</name>
				<url>http://maven.aliyun.com/nexus/content/groups/public/</url>
				<mirrorOf>central</mirrorOf>
			</mirror>
		</mirrors>
		<profiles>
			<profile>
				<id>jdk1.8</id>
				<activation>
					<activeByDefault>true</activeByDefault>
					<jdk>1.8</jdk>
				</activation>
				<properties>
					<maven.compiler.source>1.8</maven.compiler.source>
					<maven.compiler.target>1.8</maven.compiler.target>
					<maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
				</properties>
			</profile>
		</profiles>
		```  
		
# 添加依赖
# 引入bootstrap组件库
# 编写整合配置文件  
	- 配置web.xml
	- 配置springmvc(dipatcherServlet-servlet.xml)
	- 配置spring(applicationContext.xml)
# 配置tomcat
