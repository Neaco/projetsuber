package com.distrib.demo.model;
import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "country", path="country")
public interface CountryRepository extends PagingAndSortingRepository<Country, Long> {

}
