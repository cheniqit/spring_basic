package com.mk.taskfactory.biz.ControllerTest;

import com.mk.taskfactory.api.ValidRateTaskService;
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
public class PayController  extends DispatcherServlet {
	@Autowired
	private ValidRateTaskService validRateTaskService;
	@RequestMapping(value = "/testback", method = RequestMethod.POST)
	@ResponseBody
	public  ResponseEntity<String>  testAA( ) {
		validRateTaskService.dateReback();
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("code", "222");
		return new ResponseEntity<String>("123", org.springframework.http.HttpStatus.OK);

	}

	@RequestMapping(value = "/testDelete", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> testBB( ) {
<<<<<<< HEAD
=======
//		validRateTaskService.remove();
>>>>>>> 37377fa524534396cd97e0b889a461ebc5451cc1
		return new ResponseEntity<String>("111", org.springframework.http.HttpStatus.OK);
	}
}
