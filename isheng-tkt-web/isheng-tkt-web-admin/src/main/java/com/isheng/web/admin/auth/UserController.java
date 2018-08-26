package com.isheng.web.admin.auth;

import java.util.concurrent.Callable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.auth.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Reference
	private UserService userService;
	
	public Callable<Object> list(UserQuery userQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

}
