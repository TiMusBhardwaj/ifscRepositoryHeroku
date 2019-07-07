package com.example.ifsc.service;

import java.util.List;

import com.example.ifsc.dto.BranchDetails;

public interface IfscSevice {

	
	/**
	 * @param ifsc
	 * @return Branch Details 
	 */
	public BranchDetails getBranchDetails(String ifsc); 
	
	
	/**
	 * @param bankName
	 * @param cityname
	 * @return List of Bank Details
	 */
	public List<BranchDetails> getBranchDetails(String bankName, String cityname);
	
}
