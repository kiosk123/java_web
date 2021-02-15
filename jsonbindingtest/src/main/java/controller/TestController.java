package controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import vo.YearAndMonth;

@Controller
public class TestController {

	private static Logger log = LoggerFactory.getLogger(TestController.class);

	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public @ResponseBody void test(@RequestBody YearAndMonth yearAndMonth) {

		log.info("year : " + yearAndMonth.getYear() + " month : " + yearAndMonth.getMonth());

	}
}
