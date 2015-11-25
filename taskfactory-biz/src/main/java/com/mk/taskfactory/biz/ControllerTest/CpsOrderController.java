package com.mk.taskfactory.biz.ControllerTest;

import com.mk.taskfactory.api.ValidRateTaskService;
import com.mk.taskfactory.biz.cps.impl.CpsOrderDetailTaskServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping(value = "/cps")
public class CpsOrderController extends DispatcherServlet {
	@Autowired
	private CpsOrderDetailTaskServiceImpl cpsOrderDetailTaskServiceImpl;
	@RequestMapping(value = "/cpsinit", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String>  testAA( ) {
		cpsOrderDetailTaskServiceImpl.cpsOrderProduce();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("code", "222");
		return new ResponseEntity<String>("123", org.springframework.http.HttpStatus.OK);

	}

	@RequestMapping(value = "/testDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> testBB( ) {
		return new ResponseEntity<String>("111", org.springframework.http.HttpStatus.OK);
	}
}
