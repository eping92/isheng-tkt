package com.isheng.web.admin.auth;

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
import com.isheng.model.auth.entity.Menu;
import com.isheng.model.auth.enums.MenuType;
import com.isheng.model.auth.request.MenuQuery;
import com.isheng.service.auth.MenuService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
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
		ResultModel result = new ResultModel();
		
		String id = "";
		try {
			this.validate(menu);
			
			id = menuService.add(menu);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("菜单添加异常,menu={},exception={}", menu, e);
			throw e;
		}
		
		return result.setResult(ErrMsg.SUCCESS).addData("id", id);
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(String id) {
		ResultModel result = new ResultModel();
		if (StringUtils.isBlank(id)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
		try {
			int count = menuService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("菜单删除失败,id={},exception={}", id, e);
			throw e;
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	@ResponseBody
	@RequestMapping("/detail")
	public Object detail(String id) {
		ResultModel result = new ResultModel();
		Menu menu = null;
		try {
			menu = menuService.getById(id);
			if (null == menu) {
				return result.setResult(ErrMsg.FAILED);
			}
		}  catch (Exception e) {
			logger.error("菜单查询失败,id={},exception={}", id, e);
			throw e;
		} 
		
		return result.setResult(ErrMsg.SUCCESS).addData("menu", menu);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Menu menu) {
		ResultModel result = new ResultModel();
//		menu.setUpdatemenu(updatemenu);
		
		try {
			this.validate(menu);
			
			int count = menuService.update(menu);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("菜单更新异常,menu={},exception={}", menu, e);
			throw e;
		} 
		
		return result.setResult(ErrMsg.SUCCESS);
	}
	
	/**
	 * 获取下一个ID
	 */
	@ResponseBody
	@RequestMapping(value = "/nextSort")
	public Object nextSort(final String parentId, final MenuType menuType) {
		ResultModel result = new ResultModel();
		if (null == menuType) {
			return result.setCode(ErrMsg.PARAM_NULL.getCode()).setMsg("菜单类型为空");
		}
		long sort = 0;
		try {
			sort = menuService.getNextSort(parentId, menuType);
			if (sort <= 0) {
				sort = 1;
			}
		}  catch (Exception e) {
			logger.error("菜单排序号获取异常:parentId={}, menuType={}", parentId, menuType);
			throw e;
		}
		return result.setResult(ErrMsg.SUCCESS).addData("sort", sort);
	}
	
	/**
	 * 参数校验
	 * 
	 * @param menu
	 * @throws BizException
	 */
	private void validate(Menu menu) throws BizException {
		if (null == menu) {
			throw new BizException(ErrMsg.PARAM_NULL);
		}
		if (null == menu.getMenuType()) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "类型不能为空");
		}
		if (ObjUtil.isNull(menu.getName())) {
			throw new BizException(ErrMsg.PARAM_MISS.getCode(), "名称不能为空");
		}
	}
}
