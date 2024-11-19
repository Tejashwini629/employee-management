package com.wecodee.EmployeeManagement.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.EmployeeManagement.Service.EmployeeQueueService;
import com.wecodee.EmployeeManagement.entity.Employee;
import com.wecodee.EmployeeManagement.entity.EmployeeReqDto;
import com.wecodee.EmployeeManagement.responseDTO.ApiResponse;

import net.minidev.json.JSONObject;

@RestController
@RequestMapping("/employee-queue")
public class EmployeeQueueController {
	
	@Autowired
	private EmployeeQueueService employeeQueueService;

	@PostMapping("/search")
	public ApiResponse<List<Employee>> searchEmployee(@RequestBody EmployeeReqDto employeeReqDto){
		return employeeQueueService.searchRecords(employeeReqDto);
		
	}
}
