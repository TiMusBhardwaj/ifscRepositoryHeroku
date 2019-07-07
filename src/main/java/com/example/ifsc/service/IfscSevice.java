package com.example.ifsc.service;

import java.util.List;

import com.example.ifsc.dto.BranchDetails;
import com.example.ifsc.exception.BranchNotFoundException;

/**
 * @author sumit.bhardwaj
 *
 */
public interface IfscSevice {

	
	/**
	 * @param ifsc
	 * @return Branch Details 
	 * @throws BranchNotFoundException 
	 */
	public BranchDetails getBranchDetails(String ifsc) throws BranchNotFoundException; 
	
	
	/**
	 * @param bankName
	 * @param cityname
	 * @return List of Bank Details
	 */
	public List<BranchDetails> getBranchDetails(String bankName, String cityname);
	
}
