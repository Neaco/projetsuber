package com.distrib.demo.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employee", path="employee")


public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	List<Employee> findAll();
	List<Employee>findByFirstName(String firstName);

}
