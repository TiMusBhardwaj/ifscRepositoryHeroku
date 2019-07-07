package com.example.ifsc.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.ifsc.entities.Bank;

/**
 * @author sumit.bhardwaj
 *
 */
public interface IBankRepository extends PagingAndSortingRepository<Bank, BigInteger>{
	
}
