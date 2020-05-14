package io.demo.crud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 该类必须处于所有包的顶层
 * @author reyshaw
 *
 */
@SpringBootApplication
@ServletComponentScan(basePackages="io.demo.crud.filter")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

}
