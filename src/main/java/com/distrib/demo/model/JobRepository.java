package com.distrib.demo.model;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "job", path="job")
public interface JobRepository extends CrudRepository<Job, Long> {
	List<Job> findAll();
	Job getByJobId(String jobId);
	List<Job> findByMinSalaryGreaterThanOrderByMaxSalaryDesc(BigDecimal minSalary);
}
