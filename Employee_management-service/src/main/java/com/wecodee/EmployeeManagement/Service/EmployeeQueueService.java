package com.wecodee.EmployeeManagement.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.wecodee.EmployeeManagement.constant.ResponseMessage;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecodee.EmployeeManagement.entity.Employee;
import com.wecodee.EmployeeManagement.entity.EmployeeReqDto;
import com.wecodee.EmployeeManagement.responseDTO.ApiResponse;

@Service
public class EmployeeQueueService {
	public Logger log = LoggerFactory.getLogger(EmployeeQueueService.class);

	@Autowired
	private EntityManager entityManager;

	public ApiResponse<List<Employee>> searchRecords(EmployeeReqDto employeeReqDto) {
		log.info("Inside search Records");
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<Employee> criteria = builder.createQuery(Employee.class);
			Root<Employee> root = criteria.from(Employee.class);

			List<Predicate> predicates = new ArrayList<Predicate>();

			if (ObjectUtils.isNotEmpty(employeeReqDto.getId()) && employeeReqDto.getId() != 0) {
				predicates.add(builder.equal(root.get("id"), employeeReqDto.getId()));
			}
			if (ObjectUtils.isNotEmpty(employeeReqDto.getDepartment())) {
				if (employeeReqDto.getDepartment().equalsIgnoreCase("CS")) {
					List<String> data = Arrays.asList("CS", "Computer science");
					predicates.add(root.get("department").in(data));
				} else {
					predicates.add(builder.like(builder.lower(root.get("department")),
							"%" + employeeReqDto.getDepartment() + "%"));
				}
			}
			if (!ObjectUtils.isEmpty(employeeReqDto.getFromDate())
					&& !ObjectUtils.isEmpty(employeeReqDto.getToDate())) {
				predicates.add(
						builder.between(root.get("dob"), employeeReqDto.getFromDate(), employeeReqDto.getToDate()));
			}

			criteria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));

			List<Employee> result = entityManager.createQuery(criteria).getResultList();

			if (!ObjectUtils.isEmpty(result)) {
				return ApiResponse.success("Record fetched Successfully", result);
			} else {
				return ApiResponse.failure("No records found");
			}
		} catch (Exception e) {
			log.info("Exception in search record", e);
			return ApiResponse.failure(ResponseMessage.OPERATION_FAILED.getMessage());
		}
	}
}
