package com.chinmay.bhide.hr.dl;
import java.util.*;
import java.math.*;
public class EmployeeDTO implements java.io.Serializable , Comparable<EmployeeDTO>{
	private String employeeId ;
	private String name ;
	private int designationCode;
	private String designation;
	private Date dateOfBirth;
	private String gender;
	private boolean isIndian;
	private BigDecimal basicSalary;
	private String panNumber;
	private String aadharNumber;
	public EmployeeDTO(){
		this.employeeId = "";
		this.name = "";
		this.designationCode = 0;
		this.designation = "";
		this.dateOfBirth = null;
		this.gender = "";
		this.isIndian = false;
		this.basicSalary = null;
		this.panNumber = "";
		this.aadharNumber = "";
	}
	public void setEmployeeId(String employeeId){
		this.employeeId = employeeId;
	}
	public String getEmployeeId(){
		return this.employeeId;
	}
	public void setName(java.lang.String name){
		this.name = name;
	}
	public java.lang.String getName(){
		return this.name;
	}
	public void setDesignationCode(int designationCode){
		this.designationCode = designationCode;
	}
	public int getDesignationCode(){
		return this.designationCode;
	}
	public void setDesignation(java.lang.String title){
		this.designation = designation;
	}
	public java.lang.String getDesignation(){
		return this.designation;
	}
	public void setDateOfBirth(java.util.Date dateOfBirth){
		this.dateOfBirth = dateOfBirth;
	}
	public java.util.Date getDateOfBirth(){
		return this.dateOfBirth;
	}
	public void setGender(java.lang.String gender){
		this.gender = gender;
	}
	public java.lang.String getGender(){
		return this.gender;
	}
	public void setIsIndian(boolean isIndian){
		this.isIndian = isIndian;
	}
	public boolean getIsIndian(){
		return this.isIndian;
	}
	public void setBasicSalary(java.math.BigDecimal basicSalary){
		this.basicSalary = basicSalary;
	}
	public java.math.BigDecimal getBasicSalary(){
		return this.basicSalary;
	}
	public void setPanNumber(java.lang.String panNumber){
		this.panNumber = panNumber;
	}
	public java.lang.String getPanNumber(){
		return this.panNumber;
	}
	public void setAadharNumber(java.lang.String aadharNumber){
		this.aadharNumber = aadharNumber;
	}
	public java.lang.String getAadharNumber(){
		return this.aadharNumber;
	}
	public int hashCode(){
		return employeeId.hashCode();
	}
	public boolean equals(Object object){
		if(!(object instanceof EmployeeDTO)) return false;	
		EmployeeDTO employee = (EmployeeDTO)object;
		return this.employeeId==employee.employeeId;
	}
	public int compareTo(EmployeeDTO employee){
		return this.employeeId.compareTo(employee.employeeId);
	}
}