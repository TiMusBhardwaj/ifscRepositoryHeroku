package com.example.ifsc.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ifsc.entities.Branch;

public interface IBranchesRepository extends PagingAndSortingRepository<Branch, String>{

	public Branch findByIfsc(String ifsc);
	
	public List<Branch> findByBank_NameAndCity(String bankName, String City);
}
