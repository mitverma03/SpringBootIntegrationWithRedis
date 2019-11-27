package com.RedisCache.RedisCache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.RedisCache.RedisCache.model.Employee;
import com.RedisCache.RedisCache.scheduler.RedisScheduler;
import com.RedisCache.RedisCache.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private RedisScheduler redisScheduler;
	
	
	@GetMapping(path = "/employee/{empid}")
	public ResponseEntity<Employee> getEmployee(@PathVariable("empid") String empId ) {
		log.info("Entered Controller with Empolyee Id: " + empId);
		Employee emp = employeeService.getEmployee(empId);
		return new ResponseEntity<Employee>(emp, HttpStatus.OK);
	}
	
	@GetMapping(path = "/refresh")
	public ResponseEntity<String> refreshRedis() {
		redisScheduler.refresh();
		return new ResponseEntity<String>("Refreshed Successfully", HttpStatus.OK);
	}
}
