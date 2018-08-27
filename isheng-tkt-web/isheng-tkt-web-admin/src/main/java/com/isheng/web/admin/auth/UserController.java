package com.isheng.web.admin.auth;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.config.annotation.Reference;
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.model.common.exception.BizException;
import com.isheng.model.common.pojo.JsonResult;
import com.isheng.model.common.pojo.Response;
import com.isheng.service.auth.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	
	@Reference
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Callable<Object> list(UserQuery userQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return userService.paging(userQuery, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}
		};
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Object add(User user) {
		JsonResult result = new JsonResult();
		if (null == user) {
			return result.setResponse(ErrMsg.PARAM_NULL);
		}
		try {
			Response resp = userService.save(user);
			if (!resp.isSuccess()) {
				return result.setCode(resp.getCode()).setMsg(resp.getMsg());
			}
		} catch (Exception e) {
			LOG.error("add user exception：", e.getMessage());
			throw new BizException(ErrMsg.EXP_ADD);
		}
		
		return result.setResponse(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Long...ids) {
		JsonResult result = new JsonResult();
		if (ObjUtil.isNull(ids)) {
			return result.setResponse(ErrMsg.PARAM_NULL);
		}
		
		try {
			Response resp = userService.batchDelByIds((Long[])ids);
			if (!resp.isSuccess()) {
				return result.setCode(resp.getCode()).setMsg(resp.getMsg());
			}
		} catch (Exception e) {
			LOG.error("delete user by ids exception:ids={}, msg={}", ids, e.getMessage());
			throw new BizException(ErrMsg.EXP_DEL);
		} 
		return result.setResponse(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/update")
	public Object update(User user) {
		JsonResult result = new JsonResult();
		if (ObjUtil.isNotNull(user)) {
			return result.setResponse(ErrMsg.PARAM_NULL);
		}
		
		user.setUpdateUser(updateUser);
	}

}
