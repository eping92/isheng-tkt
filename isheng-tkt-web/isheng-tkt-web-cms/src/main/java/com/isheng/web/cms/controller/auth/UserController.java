package com.isheng.web.cms.controller.auth;

import java.util.Arrays;
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
import com.isheng.common.enums.ErrMsg;
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.entity.User;
import com.isheng.model.auth.request.UserQuery;
import com.isheng.service.auth.UserRoleService;
import com.isheng.service.auth.UserService;
import com.isheng.web.cms.common.AbstractBaseController;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Reference
	private UserService userService;
	@Reference
	private UserRoleService userRoleService;
	
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
		
		SessionUser sessionUser = getCurrentUser();
		user.setCreateUser(sessionUser.getUserId());
		try {
			String id = userService.add(user);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.addData("id", id);
		}  catch (Exception e) {
			logger.error("用户添加异常：", e);
			return result.setResult(e);
		}
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
			logger.error("用户删除异常", e);
			return result.setResult(e);
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
			logger.error("用户详情查询异常", e);
			return result.setResult(e);
		} 
		
		return result.setResult(ErrMsg.SUCCESS).addData("user", user);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(User user) {
		ResultModel result = new ResultModel();
		if (ObjUtil.isNull(user)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		SessionUser sessionUser = getCurrentUser();
		user.setUpdateUser(sessionUser.getUserId());
		try {
			int count = userService.update(user);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("用户更新异常", e);
			return result.setResult(e);
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	/**
	 * 分配角色
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allotRole", method = RequestMethod.POST)
	public Object allotRole(String userId, String[] roleIds) {
		ResultModel result = new ResultModel();
		if (ObjUtil.isNull(userId)) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("请选择用户");
		}
		if (null == roleIds || roleIds.length <= 0) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("请至少选择一个角色");
		}
		
		try {
			userRoleService.batchAdd(userId, Arrays.asList(roleIds));
		} catch (Exception e) {
			return result.setResult(e);
		}
		
		return result.setResult(ErrMsg.SUCCESS);
	}

	@Override
	protected ResultModel dataValid(Object object) {
		ResultModel result = new ResultModel();
		if (null == object) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		User data = (User) object;
		if (ObjUtil.isNull(data.getLoginName())) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("登录名不能为空");
		}
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	

}
