package com.RedisCache.RedisCache.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.RedisCache.RedisCache.model.Employee;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeService {

	@Cacheable(value = "employee", key = "#empId")
	public Employee getEmployee(String empId) {
		log.info("Get Employee by Id from service");
		return new Employee(empId, "firstName");
	}
}
