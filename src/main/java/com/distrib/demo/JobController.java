package com.distrib.demo;

import com.distrib.demo.model.Job;
import com.distrib.demo.model.JobRepository;

import java.math.BigDecimal;

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
@RequestMapping("/job")
public class JobController {
	
	@Autowired
	JobRepository jobRepository;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public Iterable list(Model model){
		Iterable jobList=jobRepository.findAll();
		return jobList;
	}
	
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
	public String updateJob(@PathVariable String id, @RequestBody Job job){
		Job storedJob = jobRepository.getByJobId(id);
		if (null == storedJob){
			return"No Customer found for id ";
		}
		if (job.getJobTitle()!=null) storedJob.setJobTitle(job.getJobTitle());
		jobRepository.save(storedJob);		
		return"Product updated successfully";
	}
	
	@RequestMapping(value = "/jobsabove/{minSalary}", method=RequestMethod.GET)
	public Iterable listJobsAbove(Model model, @PathVariable BigDecimal minSalary){
		Iterable jobList = jobRepository.findByMinSalaryGreaterThanOrderByMaxSalaryDesc(minSalary);
		return jobList;
	}

}
