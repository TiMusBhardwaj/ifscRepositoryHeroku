package com.example.ifsc.controller;

import java.util.List;

import javax.ws.rs.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ifsc.dto.BranchDetails;
import com.example.ifsc.exception.BranchNotFoundException;
import com.example.ifsc.service.IfscSevice;

@RestController
@RequestMapping("/bank")
public class IfscController {

	@Autowired
	private IfscSevice ifscSevice;

	/**
	 * @param ifsc
	 * @return Branch Details
	 * @throws BranchNotFoundException
	 */
	@GetMapping(path = "/branch-details/{ifsc}", produces = "application/json")
	public ResponseEntity<BranchDetails> getBranchDetails(@PathVariable("ifsc") String ifsc)
			throws BranchNotFoundException {

		return ResponseEntity.ok().body(ifscSevice.getBranchDetails(ifsc));

	}

	/**
	 * @param bankName
	 * @param cityName
	 * @return List of Branch Details
	 */
	@GetMapping(path = "/branch-details", produces = "application/json")
	public ResponseEntity<List<BranchDetails>> getBranchDetails(@RequestParam("bank") String bankName,
			@RequestParam("city") String cityName) {

		return ResponseEntity.ok().body(ifscSevice.getBranchDetails(bankName, cityName));

	}
}
