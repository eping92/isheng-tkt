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
import com.isheng.common.exception.BizException;
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.domain.SessionUser;
import com.isheng.model.auth.entity.Role;
import com.isheng.model.auth.request.RoleQuery;
import com.isheng.service.auth.RoleMenuService;
import com.isheng.service.auth.RoleService;
import com.isheng.web.cms.common.AbstractBaseController;

@Controller
@RequestMapping("/role")
public class RoleController extends AbstractBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Reference
	private RoleService roleService;
	@Reference
	private RoleMenuService roleMenuService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Callable<Object> list(RoleQuery roleQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return roleService.getPaging(roleQuery, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}
		};
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Role role) {
		ResultModel result = this.dataValid(role);
		if (!result.isSuccess()) {
			return result;
		}
		
		SessionUser sessionUser = getCurrentUser();
		role.setCreateUser(sessionUser.getUserId());
		try {
			String id = roleService.add(role);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.addData("id", id);
		} catch (BizException e) {
			logger.error("菜单添加异常,role={},exception={}", role, e);
			return result.setResult(e);
		}
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String id) {
		ResultModel result = getModel();
		if (StringUtils.isBlank(id)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		try {
			int count = roleService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (BizException e) {
			logger.error("菜单删除失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(String id) {
		ResultModel result = getModel();
		try {
			Role role = roleService.getById(id);
			if (null == role) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.setResult(ErrMsg.SUCCESS).addData("role", role);
		}  catch (BizException e) {
			logger.error("菜单查询失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Role role) {
		ResultModel result = this.dataValid(role);
		if (!result.isSuccess()) {
			return result;
		}
		
		SessionUser user = getCurrentUser();
		role.setUpdateUser(user.getUserId());
		try {
			int count = roleService.update(role);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (BizException e) {
			logger.error("菜单更新异常,role={},exception={}", role, e);
			return result.setResult(e);
		} 
		
		return result;
	}
	
	/**
	 * 分配权限
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/allotMenu", method = RequestMethod.POST)
	public Object allotMenu(String roleId, String[] menuIds) {
		ResultModel result = new ResultModel();
		if (ObjUtil.isNull(roleId)) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("请选择角色");
		}
		if (null == menuIds || menuIds.length <= 0) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("请至少选择一个权限");
		}
		
		try {
			roleMenuService.batchAdd(roleId, Arrays.asList(menuIds));
		} catch (Exception e) {
			return result.setResult(e);
		}
		
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	@Override
	protected  ResultModel dataValid(Object object) {
		ResultModel result = new ResultModel();
		if (null == object) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		Role data = (Role) object;
		if (StringUtils.isEmpty(data.getName())) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("角色名称不能为空");
		}
		boolean isExist = roleService.isExist(data.getId(), "name", data.getName());
		if (isExist) {
			return result.setResult(ErrMsg.PARAM_REPET);
		}
		
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	
}
