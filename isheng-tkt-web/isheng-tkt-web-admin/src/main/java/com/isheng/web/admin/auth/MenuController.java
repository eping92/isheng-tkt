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
import com.isheng.common.model.ResultModel;
import com.isheng.common.util.ObjUtil;
import com.isheng.model.auth.entity.Menu;
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
		if (null == menu) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		try {
			String id = menuService.add(menu);
			if (StringUtils.isBlank(id)) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("add menu exception：", e.getMessage());
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
			int count = menuService.deleteById(id);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("delete menu by ids exception:id={}, msg={}", id, e.getMessage());
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
		
		Menu menu = null;
		try {
			menu = menuService.getById(id);
			if (null == menu) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("query menu by id exception:id={}, msg={}", id, e.getMessage());
			return result.setResult(ErrMsg.EXP_QUERY);
		} 
		
		return result.setResult(ErrMsg.SUCCESS).addData("menu", menu);
	}
	
	@ResponseBody
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Object update(Menu menu) {
		ResultModel result = new ResultModel();
		if (ObjUtil.isNotNull(menu)) {
			return result.setResult(ErrMsg.PARAM_NULL);
		}
		
//		menu.setUpdatemenu(updatemenu);
		
		try {
			int count = menuService.update(menu);
			if (count <= 0) {
				return result.setResult(ErrMsg.FAILED);
			}
		} catch (Exception e) {
			logger.error("update menu exception:id={}, msg={}", menu.getId(), e.getMessage());
			return result.setResult(ErrMsg.EXP_UP);
		} 
		return result.setResult(ErrMsg.SUCCESS);
	}
}
