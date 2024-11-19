package com.wecodee.EmployeeManagement.Controller;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.wecodee.EmployeeManagement.Exception.UserNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecodee.EmployeeManagement.Repository.EmployeeRepository;
import com.wecodee.EmployeeManagement.Service.EmployeeService;
import com.wecodee.EmployeeManagement.entity.Employee;
import com.wecodee.EmployeeManagement.responseDTO.ApiResponse;
import com.wecodee.EmployeeManagement.responseDTO.SearchFilterDTO;

import net.minidev.json.JSONObject;

import javax.validation.Valid;

@RestController
@RequestMapping("/employee")
//@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private EmployeeService empservice;

    @GetMapping("/getEmployee")
    public List<Employee> getallemployee() {
        return this.empservice.getemployee();
    }

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody @Valid Employee emp) {

        return empservice.addEmployee(emp);
    }

    @GetMapping("/getEmployee/{id}")
    public Employee getEmployeeById(@PathVariable("id") int id) throws UserNotFoundException {
        return empservice.getEmployeeById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public String deleteById(@PathVariable("id") int id) {
        return empservice.deleteEmployee(id);
    }

    @PutMapping("/updateEmployee")
    public Employee updateEmployee(@RequestBody Employee e) {
        return empservice.updateEmployee(e);
    }

    @GetMapping("/getEmployeeWithGreaterSalary/{salary}")
    public List<Employee> getEmployeebysalary(@PathVariable("salary") long salary) {
        return empservice.getEmployeeByGreaterSalary(salary);
    }

    @GetMapping("/getByDepartment/{department}")
    public List<Employee> findByDepartment(@PathVariable("department") String department) {
        return empservice.findByDepartmentName(department);
    }

    @GetMapping("/getBetweenAge/{startAge}/{endAge}")
    public List<Employee> findByAge(@PathVariable("startAge") int startAge, @PathVariable("endAge") int endAge) {
        return empservice.getEmployeeByAgeRange(startAge, endAge);
    }

    @GetMapping("/getEmployeeByDob/{fromDate}/{toDate}")
    public List<Employee> getEmployeeByDate(@PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
                                            @PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        return empservice.getEmployeeByDob(fromDate, toDate);
    }

//	pagination and sorting

    @GetMapping("/sorting/{field}")
    public List<Employee> getEmployeeWithSorting(@PathVariable("field") String field) {
        return this.empservice.getEmployeeWithSorting(field);
    }

    @GetMapping("/pagination/{offset}/{pageSize}")
    public Page<Employee> getEmployeeWithPagination(@PathVariable("offset") int offset, @PathVariable int pageSize) {
        Page<Employee> employeewithpagination = empservice.getEmployeeWithPagination(offset, pageSize);
        return employeewithpagination;
    }

    @PostMapping("/searchEmployee")
    public ApiResponse<List<Employee>> searchByFilterfield(@RequestBody Employee fields) {
        log.info("fields*****888" + fields);
        return empservice.searchByFilterfield(fields);
    }
}
