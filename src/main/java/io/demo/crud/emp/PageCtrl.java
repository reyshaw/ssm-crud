package io.demo.crud.emp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("empPageCtrl")
public class PageCtrl {
	
	@GetMapping("/list")
	public String toEmp() {
		return "list";
	}

}
