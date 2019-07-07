package com.example.ifsc.entities;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="banks")
@Getter
@Setter
public class Bank {
	@Id
	private BigInteger id;
	private String name;
	

}
