package com.distrib.demo;

import com.distrib.demo.model.Employee;
import com.distrib.demo.model.EmployeeDTO;
import com.distrib.demo.model.EmployeeRepository;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	ModelMapper modelMapper= new ModelMapper();

	PropertyMap<Employee, EmployeeDTO> employeeMap = new PropertyMap<Employee, EmployeeDTO>(){
		protected void configure(){
			map().setFirstName(source.getFirstName());
			map().setLastName(source.getLastName());
			map().setDepartmentName(source.getDepartment().getDepartmentName());
		}
	};

	public EmployeeController(){
		modelMapper.addMappings(employeeMap);
	}
	
	@RequestMapping(value="/filterby/{firstName}", method=RequestMethod.GET)
	public Iterable<EmployeeDTO> listByFirstName(@PathVariable String firstName){
		Iterable<Employee> employeeList = employeeRepository.findByFirstName(firstName);
		List<EmployeeDTO> employeeDTOList= new ArrayList<EmployeeDTO>();
		
		for(Employee e : employeeList)
		{
			employeeDTOList.add(modelMapper.map(e, EmployeeDTO.class));

		}

	return employeeDTOList;
		
		
		
		
		
	}

}
