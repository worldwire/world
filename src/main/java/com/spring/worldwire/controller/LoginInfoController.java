package com.spring.worldwire.controller;

import com.spring.worldwire.enums.StatusCodeEnum;
import com.spring.worldwire.model.LoginInfo;
import com.spring.worldwire.query.LoginInfoQuery;
import com.spring.worldwire.service.LoginInfoService;
import com.spring.worldwire.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/login")
public class LoginInfoController {
	
	@Autowired
	private LoginInfoService loginInfoService;
	@Autowired
	private UserAccountService userAccountService;
	
	@RequestMapping("/registerByMail")
	@ResponseBody
	public Map<String,Object> registerByEamil(String userName, String password,String email){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			LoginInfo info = new LoginInfo();
			info.setUserName(userName);
			info.setPassword(password);
			info.setEmail(email);
			Integer result = loginInfoService.registerByMail(info);
			if(result >0){
				map.put("data", result);
				map.put("msg", StatusCodeEnum.SUCCCESS.getMsg());
				map.put("status", StatusCodeEnum.SUCCCESS.getCode());
				return map;
			}
			map.put("data", result);
			map.put("msg", StatusCodeEnum.FAIL.getMsg());
			map.put("status", StatusCodeEnum.FAIL.getCode());

		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", StatusCodeEnum.ERROR.getMsg());
			map.put("status", StatusCodeEnum.ERROR.getCode());
		}
		return map;
	}

	@RequestMapping("/loginByMail")
	public Map<String,Object> loginByMail(String email, String password, HttpServletResponse response){
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			LoginInfoQuery query = new LoginInfoQuery();
			query.setEmail(email);
			query.setPassword(password);
			List<LoginInfo> list = loginInfoService.selectByQuery(query);
			if(CollectionUtils.isEmpty(list)){
				map.put("data", null);
				map.put("msg", StatusCodeEnum.EMPTY.getMsg());
				map.put("status", StatusCodeEnum.EMPTY.getCode());
				return map;
			}
//			handleCookie(response,list.get(0).getId());
//
//			handleSignUp(list.get(0).getId());

			map.put("data", list.get(0));
			map.put("msg", StatusCodeEnum.SUCCCESS.getMsg());
			map.put("status", StatusCodeEnum.SUCCCESS.getCode());
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", StatusCodeEnum.ERROR.getMsg());
			map.put("status", StatusCodeEnum.ERROR.getCode());
		}
		return map;
	}

}
