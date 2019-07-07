package com.example.ifsc.exception;


public class BranchNotFoundException extends Exception {
    
	
	private String ifsc;

    public static BranchNotFoundException createWith(String ifsc) {
        return new BranchNotFoundException(ifsc);
    }

    private BranchNotFoundException(String ifsc) {
        this.ifsc = ifsc;
    }

    @Override
    public String getMessage() {
        return "Branch for '" + ifsc + "' not found";
    }
}