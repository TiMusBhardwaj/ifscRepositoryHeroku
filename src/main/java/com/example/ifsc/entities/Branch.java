package com.example.ifsc.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author sumit.bhardwaj
 *
 */
@Entity
@Table(name="branches")
@Getter
@Setter
public class Branch {
	@Id
	private String ifsc;
	
	@ManyToOne
	@JoinColumn(name="bank_id")
	private Bank bank;
	private String branch;
	private String address;
	private String city;
	private String district;
	private String state;
	
	

}
