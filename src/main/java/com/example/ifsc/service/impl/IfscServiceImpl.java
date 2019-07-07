package com.example.ifsc.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ifsc.dto.BranchDetails;
import com.example.ifsc.entities.Branch;
import com.example.ifsc.repository.IBranchesRepository;
import com.example.ifsc.service.IfscSevice;

@Service
public class IfscServiceImpl implements IfscSevice{

	@Autowired
	private IBranchesRepository branchRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/*
	 * @EventListener(ContextRefreshedEvent.class) public void test() {
	 * getBranchDetails("AKJB0000012");
	 * getBranchDetails("ABHYUDAYA COOPERATIVE BANK LIMITED", "MUMBAI"); }
	 */
	
	@Override
	public BranchDetails getBranchDetails(String ifsc) {
		
		Branch branch = branchRepo.findByIfsc(ifsc);
		BranchDetails branchdetails = modelMapper.map(branch, BranchDetails.class);
		return branchdetails;
	}

	@Override
	public List<BranchDetails> getBranchDetails(String bankName, String city) {
		List<Branch> branch = branchRepo.findByBank_NameAndCity(bankName, city);
		List<BranchDetails> branchDeatails = branch.stream().map(x -> modelMapper.map(x, BranchDetails.class))
				.collect(Collectors.toList());
		return branchDeatails;
	}
	
	
}
