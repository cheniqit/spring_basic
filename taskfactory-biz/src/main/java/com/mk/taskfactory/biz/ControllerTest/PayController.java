package com.mk.taskfactory.biz.ControllerTest;

import com.google.common.collect.Maps;
import com.mk.taskfactory.api.ValidRateTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.Map;


@Controller
public class PayController  extends DispatcherServlet {
	@Autowired
	private ValidRateTaskService validRateTaskService;
	@RequestMapping(value = "/testback", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<Map<String, Object>> testAA( ) {
		validRateTaskService.remove();
		Map<String, Object> rtnMap = Maps.newHashMap();
		rtnMap.put("code", "111");
		return new ResponseEntity<Map<String, Object>>(rtnMap, org.springframework.http.HttpStatus.OK);
	}

	@RequestMapping(value = "/testDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> testBB( ) {
		validRateTaskService.remove();
		Map<String, Object> rtnMap = Maps.newHashMap();
		rtnMap.put("code", "111");
		return new ResponseEntity<Map<String, Object>>(rtnMap, org.springframework.http.HttpStatus.OK);
	}
}
