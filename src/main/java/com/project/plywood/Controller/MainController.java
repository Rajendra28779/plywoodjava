/**
 * 
 */
package com.project.plywood.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.plywood.Service.MainService;

/**
 * Rajendra
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api")
public class MainController {

	@Autowired MainService mainserv;
	
	@PostMapping(value = "/sendmail")
	@ResponseBody
	public Void sendmail() {
		mainserv.sendmail();
		return null;
	}
	
	@PostMapping(value = "/rqstforcontact")
	@ResponseBody
	public Map<String,Object> rqstforcontact(@RequestBody Map<String,Object> mapobj) {
		Map<String,Object> map=new HashMap<>();
		try {
			map=mainserv.rqstforcontact(mapobj);
		}catch (Exception e) {
			map.put("status",400);
			map.put("message","Something Went Wrong !! Please try After Sometime .");
			map.put("error", e.getMessage());
		}
		return map;
	}
}
