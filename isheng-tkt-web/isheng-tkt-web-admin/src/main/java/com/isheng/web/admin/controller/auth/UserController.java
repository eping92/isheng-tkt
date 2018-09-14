package com.isheng.web.admin.controller.auth;

import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.base.BaseController;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.auth.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController<User>{
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Reference
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Callable<Object> list(UserQuery userQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return userService.getPaging(userQuery, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}
		};
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(User user) {
		ResultModel result = this.dataValid(user);
		if (!result.isSuccess()) {
			return result;
		}
		
		try {
			String id = userService.add(user);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("add user exception：", e.getMessage());
			return result.setResult(ErrMsg.EXP_ADD);
		}
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String id) {
		ResultModel result = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		try {
			int count = userService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("delete user by ids exception:id={}, msg={}", id, e.getMessage());
			return result.setResult(ErrMsg.EXP_DEL);
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(String id) {
		ResultModel result = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		User user = null;
		try {
			user = userService.getById(id);
			if (null == user) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("query user by id exception:id={}, msg={}", id, e.getMessage());
			return result.setResult(ErrMsg.EXP_QUERY);
		} 
		
		return result.setResult(ErrMsg.SUCCESS).addData("user", user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(User user) {
		ResultModel result = new ResultModel();
		if (ObjUtil.isNotNull(user)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
//		user.setUpdateUser(updateUser);
		
		try {
			int count = userService.update(user);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("update user exception:id={}, msg={}", user.getId(), e.getMessage());
			return result.setResult(ErrMsg.EXP_UP);
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}

	@Override
	protected ResultModel dataValid(User t) {
		ResultModel result = new ResultModel();
		if (null == t) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		if (ObjUtil.isNull(t.getLoginName())) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("登录名不能为空");
		}
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	

}
