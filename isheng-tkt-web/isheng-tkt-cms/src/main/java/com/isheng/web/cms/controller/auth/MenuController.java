package com.isheng.web.cms.controller.auth;

import java.util.concurrent.Callable;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.service.auth.MenuService;
import com.isheng.web.cms.common.AbstractBaseController;

@Controller
@RequestMapping("/menu")
public class MenuController extends AbstractBaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(MenuController.class);
	
	@Reference
	private MenuService menuService;
	
	@ResponseBody
	@RequestMapping("/list")
	public Callable<Object> list(MenuQuery menuQuery, @RequestParam(defaultValue="1")String pageNo, @RequestParam(defaultValue="10")String pageSize) {
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return menuService.getPaging(menuQuery, Integer.valueOf(pageNo), Integer.valueOf(pageSize));
			}
		};
	}
	
	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Object add(Menu menu) {
		ResultModel result = this.dataValid(menu);
		if (!result.isSuccess()) {
			return result;
		}
		
		SessionUser sessionUser = getCurrentUser();
		menu.setCreateUser(sessionUser.getUserId());
		
		try {
			String id = menuService.add(menu);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.addData("id", id);
		} catch (BizException e) {
			logger.error("菜单添加异常,menu={},exception={}", menu, e);
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
			int count = menuService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
			
			return result.setResult(ErrMsg.SUCCESS);
		} catch (BizException e) {
			logger.error("菜单删除失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
		
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(String id) {
		ResultModel result = getModel();
		try {
			Menu menu = menuService.getById(id);
			if (null == menu) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result.setResult(ErrMsg.SUCCESS).addData("menu", menu);
		}  catch (BizException e) {
			logger.error("菜单查询失败,id={},exception={}", id, e);
			return result.setResult(e);
		} 
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Menu menu) {
		ResultModel result = this.dataValid(menu);
		if (!result.isSuccess()) {
			return result;
		}
		
		SessionUser user = getCurrentUser();
		menu.setUpdateUser(user.getUserId());
		
		try {
			int count = menuService.update(menu);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
			return result;
		} catch (BizException e) {
			logger.error("菜单更新异常,menu={},exception={}", menu, e);
			return result.setResult(e);
		} 
	}
	
	/**
	 * 获取下一个排序
	 */
	@ResponseBody
	@RequestMapping(value = "/nextSort")
	public Object nextSort(final String parentId, final MenuType menuType) {
		ResultModel result = getModel();
		if (null == menuType) {
			return result.setCode(ErrMsg.PARAM_NULL.getCode()).setMsg("菜单类型为空");
		}
		
		try {
			long sort = menuService.getNextSort(parentId, menuType);
			if (sort <= 0) {
				sort = 1;
			}
			return result.setResult(ErrMsg.SUCCESS).addData("sort", sort);
		}  catch (BizException e) {
			logger.error("菜单排序号获取异常:parentId={}, menuType={}", parentId, menuType);
			return result.setResult(e);
		}
	}
	
	/**
	 * 加载权限信息
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/load")
	public Callable<Object> load(Model model) {
		SessionUser user = getCurrentUser();
		return new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				return menuService.getListByUserId(user.getUserId());
			}
		};
	}

	@Override
	protected  ResultModel dataValid(Object object) {
		ResultModel result = new ResultModel();
		if (null == object) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		Menu data = (Menu)object;
		if (null == data.getMenuType()) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("类型不能为空");
		}
		if (ObjUtil.isNull(data.getName())) {
			return result.setCode(ErrMsg.PARAM_MISS.getCode()).setMsg("名称不能为空");
		}
		return result.setResult(ErrMsg.SUCCESS);
	}

}
