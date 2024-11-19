package com.wecodee.EmployeeManagement.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.wecodee.EmployeeManagement.Exception.UserNotFoundException;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.wecodee.EmployeeManagement.Repository.EmployeeRepository;
import com.wecodee.EmployeeManagement.constant.ResponseMessage;
import com.wecodee.EmployeeManagement.entity.Employee;
import com.wecodee.EmployeeManagement.responseDTO.ApiResponse;
import com.wecodee.EmployeeManagement.responseDTO.SearchFilterDTO;

import net.minidev.json.JSONObject;

@Service
public class EmployeeService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository emprepo;

	public List<Employee> getemployee() {
		return this.emprepo.findAll();
	}

	// addemployee
//	aldada
	public Employee addEmployee(Employee e) {
		return this.emprepo.save(e);
	}

	// delete
	public String deleteEmployee(int id) {
		this.emprepo.deleteById(id);
		return "employee deleted";
	}

	// updateemployee
	// second commit dsdusdhus
	public Employee updateEmployee(Employee emp) {
		Employee oldEmploye = null;
		Optional<Employee> testEmployee = emprepo.findById(emp.getId());
		if (testEmployee.isPresent()) {
			oldEmploye = testEmployee.get();
			oldEmploye.setFirstName(emp.getFirstName());
			oldEmploye.setLastName(emp.getLastName());
			oldEmploye.setSalary(emp.getSalary());
			oldEmploye.setAge(emp.getAge());
			oldEmploye.setDob(emp.getDob());
			oldEmploye.setDepartment(emp.getDepartment());
			emprepo.save(oldEmploye);
		} else {
			return new Employee();
		}
		return oldEmploye;
	}

	public List<Employee> getEmployeeByGreaterSalary(long salary) {
		return emprepo.findBySalaryGreaterThan(salary);
	}

	public List<Employee> findByDepartmentName(String department) {
		return emprepo.findByDepartment(department);
	}

	public List<Employee> getEmployeeByAgeRange(int startAge, int endAge) {
		return emprepo.findByAgeRange(startAge, endAge);
	}

	public List<Employee> getEmployeeByDob(Date fromDate, Date toDate) {
		return emprepo.getEmployeeDetailsByDob(fromDate, toDate);
	}

	public Employee getEmployeeById(int id) throws UserNotFoundException {
		Employee employee = emprepo.findByUserId(id);
		if(ObjectUtils.isNotEmpty(employee))
		return employee;
		else
			throw new UserNotFoundException("User not found with the id "+id);
	}

//	pagination and sorting
	public List<Employee> getEmployeeWithSorting(String field) {
//		return emprepo.findAll(Sort.by(field));
		return emprepo.findAll(Sort.by(Sort.Direction.ASC, field));

	}

	public Page<Employee> findEmployeeWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize) {
		Page<Employee> employee = emprepo.findAll(PageRequest.of(pageNumber, pageSize));
		return employee;
	}

	public Page<Employee> getEmployeeWithPagination(int offset, int pageSize) {
		Page<Employee> employees = emprepo.findAll(PageRequest.of(offset, pageSize));
		return employees;
	}

//	public ApiResponse<JSONObject> searchByFilterfield(SearchFilterDTO<Employee> fields) {
	public ApiResponse<List<Employee>> searchByFilterfield(Employee filterFields) {

		List<Employee> filteredList = new ArrayList();
		List<Employee> tempList = new ArrayList<>();
		JSONObject jsonObject = new JSONObject();

		List<Employee> empFrmDb = emprepo.findAll();
		log.info("Emp list from db" + empFrmDb);
//		log.info("766666" + fields);
//		Employee filterFields = fields.getFilterFieldValues();
//		log.info("filterfield value" + filterFields);

		//tempList = empFrmDb;
		if (filterFields.getDepartment() != null && !filterFields.getDepartment().isBlank()) {
			tempList = empFrmDb.stream()
					.filter(c -> c.getDepartment().toLowerCase().contains(filterFields.getDepartment().toLowerCase()))
					.collect(Collectors.toList());
			log.info("department"+tempList);

		}

		if(filterFields.getAge()!=0) {
		log.info("age value"+filterFields.getAge());
		tempList = tempList.stream().filter(c -> c.getAge() == filterFields.getAge()).collect(Collectors.toList());
		log.info("age"+tempList);
//		filteredList = tempList;

		}
		filteredList.addAll(tempList);


//		jsonObject.put("items", filteredList);
		return ApiResponse.success("Record fetched Successfully", filteredList);

	}



}
