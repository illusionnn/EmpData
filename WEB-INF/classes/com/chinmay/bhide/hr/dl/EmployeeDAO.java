package com.chinmay.bhide.hr.dl;
import com.chinmay.bhide.hr.dl.*;
import java.sql.*;
import java.util.*;
import java.math.*;
public class EmployeeDAO{
	public List<EmployeeDTO> getAll() throws DAOException{
		List<EmployeeDTO> employees;
		employees = new LinkedList<>();
		try{
			Connection connection = DAOConnection.getConnection();
			Statement statement;
			statement = connection.createStatement();
			ResultSet resultSet;
			resultSet = statement.executeQuery("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_number from employee inner join designation on employee.designation_code= designation.code order by employee.name");
			EmployeeDTO employeeDTO;
			int employeeId;
			String name;
			int designationCode;
			String designation;
			java.util.Date dateOfBirth;
			String gender;
			boolean isIndian;
			BigDecimal basicSalary;
			String panNumber;
			String aadharNumber;
			while(resultSet.next()){
				employeeId = resultSet.getInt("id");
				name = resultSet.getString("name");
				designationCode = resultSet.getInt("designation_code");
				designation = resultSet.getString("title");
				dateOfBirth = resultSet.getDate("date_of_birth");
				gender = resultSet.getString("gender");
				isIndian = resultSet.getBoolean("is_indian");
				basicSalary = resultSet.getBigDecimal("basic_salary");
				panNumber = resultSet.getString("pan_number").trim();
				aadharNumber = resultSet.getString("aadhar_number").trim();
				employeeDTO = new EmployeeDTO();
				employeeDTO.setEmployeeId("A"+employeeId);
				employeeDTO.setName(name);
				employeeDTO.setDesignationCode(designationCode);
				employeeDTO.setDesignation(designation);
				employeeDTO.setDateOfBirth(dateOfBirth);
				employeeDTO.setGender(gender);
				employeeDTO.setIsIndian(isIndian);
				employeeDTO.setBasicSalary(basicSalary);
				employeeDTO.setPanNumber(panNumber);
				employeeDTO.setAadharNumber(aadharNumber);
				employees.add(employeeDTO);
			}
			resultSet.close();
			statement.close();
			connection.close();
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		} 
		return employees;
	}
	public void add(EmployeeDTO employee) throws DAOException{
		try{
			String panNumber = employee.getPanNumber();
			String aadharNumber = employee.getAadharNumber();
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select id from employee where pan_number = ?");
			preparedStatement.setString(1,panNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("PAN number : "+panNumber+" exists.");
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("select id from employee where aadhar_number = ?");
			preparedStatement.setString(1,aadharNumber);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Aadhar number : "+aadharNumber+" exists.");
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("insert into employee (name,designation_code,date_of_birth,gender,is_indian,basic_salary,pan_number,aadhar_number) values (?,?,?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1,employee.getName());
			preparedStatement.setInt(2,employee.getDesignationCode());
			java.util.Date dateOfBirth = employee.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());	
			preparedStatement.setDate(3,sqlDate);
			preparedStatement.setString(4,employee.getGender());
			preparedStatement.setBoolean(5,employee.getIsIndian());
			preparedStatement.setBigDecimal(6,employee.getBasicSalary());
			preparedStatement.setString(7,panNumber);
			preparedStatement.setString(8,aadharNumber);
			preparedStatement.executeUpdate();
			resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);
			employee.setEmployeeId("A"+id);
			resultSet.close();
			preparedStatement.close();
			connection.close();					
		}catch(SQLException e){
			throw new DAOException(e.getMessage());
		}
	}
	public boolean panNumberExists(String panNumber)throws DAOException{
		boolean exists = false;
		try{
			//String title = designationDTO.getTitle();
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select pan_number from employee where pan_number = ?");
			preparedStatement.setString(1,panNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			exists = resultSet.next();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return exists;
	}
	public boolean aadharNumberExists(String aadharNumber)throws DAOException{
		boolean exists = false;
		try{
			//String title = designationDTO.getTitle();
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select aadhar_number from employee where aadhar_number = ?");
			preparedStatement.setString(1,aadharNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			exists = resultSet.next();
			resultSet.close();
			preparedStatement.close();
			connection.close();
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return exists;
	}
	public void deleteByEmployeeId(String employeeId)throws DAOException{
		int actualEmployeeId = 0;
		try{
			actualEmployeeId = Integer.parseInt(employeeId.substring(1));
		}catch(Exception e){
			throw new DAOException("Invalid employee id : "+employeeId);
		}
		try{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select gender from employee where id = ?");
			preparedStatement.setInt(1,actualEmployeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==false){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid Employee ID : "+employeeId);
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("delete from employee where id = ?");
			preparedStatement.setInt(1,actualEmployeeId);
			preparedStatement.executeUpdate();
			preparedStatement.close();
			connection.close(); 
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
	}
	public EmployeeDTO getByEmployeeId(String employeeId)throws DAOException{
		EmployeeDTO employeeDTO = null;
		int actualEmployeeId = 0;
		try{
			actualEmployeeId = Integer.parseInt(employeeId.substring(1));
		}catch(Exception e){
			throw new DAOException("Invalid employee id : "+employeeId);
		}
		try{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_number from employee inner join designation on employee.designation_code= designation.code and id = ?");
			preparedStatement.setInt(1,actualEmployeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==false){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid Employee ID : "+employeeId);
			}
			int id;
			String name;
			int designationCode;
			String designation;
			java.util.Date dateOfBirth;
			String gender;
			boolean isIndian;
			BigDecimal basicSalary;
			String panNumber;
			String aadharNumber;
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			designationCode = resultSet.getInt("designation_code");
			designation = resultSet.getString("title").trim();
			dateOfBirth = resultSet.getDate("date_of_birth");
			gender = resultSet.getString("gender");
			isIndian = resultSet.getBoolean("is_indian");
			basicSalary = resultSet.getBigDecimal("basic_salary");
			panNumber = resultSet.getString("pan_number").trim();
			aadharNumber = resultSet.getString("aadhar_number").trim();
			employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId("A"+employeeId);
			employeeDTO.setName(name);
			employeeDTO.setDesignationCode(designationCode);
			employeeDTO.setDesignation(designation);
			employeeDTO.setDateOfBirth(dateOfBirth);
			employeeDTO.setGender(gender);
			employeeDTO.setIsIndian(isIndian);
			employeeDTO.setBasicSalary(basicSalary);
			employeeDTO.setPanNumber(panNumber);
			employeeDTO.setAadharNumber(aadharNumber);
			resultSet.close();
			preparedStatement.close();
			connection.close(); 
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return employeeDTO;
	}
	public void update(EmployeeDTO employee) throws DAOException{
		try{
			String employeeId = employee.getEmployeeId();
			int actualEmployeeId = 0;
			try{
				actualEmployeeId = Integer.parseInt(employeeId.substring(1));
			}catch(Exception e){
				System.out.println("Hello4");
				throw new DAOException("Invalid employee id : "+employeeId);
			}
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select gender from employee where id = ?");
			preparedStatement.setInt(1,actualEmployeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next() == false){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				System.out.println("Hello");
				throw new DAOException("Invalid employee id : "+employeeId);
			}
			resultSet.close();
			preparedStatement.close();
			String panNumber = employee.getPanNumber();
			String aadharNumber = employee.getAadharNumber();
			preparedStatement = connection.prepareStatement("select id from employee where pan_number = ? and id <>?");
			preparedStatement.setString(1,panNumber);
			preparedStatement.setInt(2,actualEmployeeId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("PAN number : "+panNumber+" exists.");
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("select id from employee where aadhar_number = ? and id <>?");
			preparedStatement.setString(1,aadharNumber);
			preparedStatement.setInt(2,actualEmployeeId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Aadhar number : "+aadharNumber+" exists.");
			}
			resultSet.close();
			preparedStatement.close();
			preparedStatement = connection.prepareStatement("update employee set name = ?, designation_code = ?, date_of_birth = ? , gender = ? , is_indian = ? , basic_salary = ? , pan_number = ? , aadhar_number = ? where id = ?");
			preparedStatement.setString(1,employee.getName());
			preparedStatement.setInt(2,employee.getDesignationCode());
			java.util.Date dateOfBirth = employee.getDateOfBirth();
			java.sql.Date sqlDate = new java.sql.Date(dateOfBirth.getYear(),dateOfBirth.getMonth(),dateOfBirth.getDate());	
			preparedStatement.setDate(3,sqlDate);
			preparedStatement.setString(4,employee.getGender());
			preparedStatement.setBoolean(5,employee.getIsIndian());
			preparedStatement.setBigDecimal(6,employee.getBasicSalary());
			preparedStatement.setString(7,panNumber);
			preparedStatement.setString(8,aadharNumber);
			preparedStatement.setInt(9,actualEmployeeId);
			preparedStatement.executeUpdate();
			resultSet.close();
			preparedStatement.close();
			connection.close();					
		}catch(SQLException e){
			System.out.println("Hello3");
			throw new DAOException(e.getMessage());
		}
	}
	public boolean employeeIdExists(String employeeId)throws DAOException{
		boolean exists = false;
		EmployeeDTO employeeDTO = null;
		int actualEmployeeId = 0;
		try{
			actualEmployeeId = Integer.parseInt(employeeId.substring(1));
		}catch(Exception e){
			throw new DAOException("Invalid employee id : "+employeeId);
		}
		try{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select gender from employee where id = ?");
			preparedStatement.setInt(1,actualEmployeeId);
			ResultSet resultSet = preparedStatement.executeQuery();
			exists = resultSet.next();			
			resultSet.close();
			preparedStatement.close();
			connection.close(); 
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return exists;
	}
	public EmployeeDTO getByPanNumber(String panNumber)throws DAOException{
		EmployeeDTO employeeDTO = null;
		try{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_number from employee inner join designation on employee.designation_code= designation.code and pan_number = ?");
			preparedStatement.setString(1,panNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==false){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid Pan Number: "+panNumber);
			}
			int id;
			String name;
			int designationCode;
			String designation;
			java.util.Date dateOfBirth;
			String gender;
			boolean isIndian;
			BigDecimal basicSalary;
			//String panNumber;
			String aadharNumber;
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			designationCode = resultSet.getInt("designation_code");
			designation = resultSet.getString("title").trim();
			dateOfBirth = resultSet.getDate("date_of_birth");
			gender = resultSet.getString("gender");
			isIndian = resultSet.getBoolean("is_indian");
			basicSalary = resultSet.getBigDecimal("basic_salary");
			panNumber = resultSet.getString("pan_number").trim();
			aadharNumber = resultSet.getString("aadhar_number").trim();
			employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId("A"+id);
			employeeDTO.setName(name);
			employeeDTO.setDesignationCode(designationCode);
			employeeDTO.setDesignation(designation);
			employeeDTO.setDateOfBirth(dateOfBirth);
			employeeDTO.setGender(gender);
			employeeDTO.setIsIndian(isIndian);
			employeeDTO.setBasicSalary(basicSalary);
			employeeDTO.setPanNumber(panNumber);
			employeeDTO.setAadharNumber(aadharNumber);
			resultSet.close();
			preparedStatement.close();
			connection.close(); 
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return employeeDTO;
	}
	public EmployeeDTO getByAadharNumber(String aadharNumber)throws DAOException{
		EmployeeDTO employeeDTO = null;
		try{
			Connection connection = DAOConnection.getConnection();
			PreparedStatement preparedStatement;
			preparedStatement = connection.prepareStatement("select employee.id,employee.name,employee.designation_code,designation.title,employee.date_of_birth,employee.gender,employee.is_indian,employee.basic_salary,employee.pan_number,employee.aadhar_number from employee inner join designation on employee.designation_code= designation.code and aadhar_number = ?");
			preparedStatement.setString(1,aadharNumber);
			ResultSet resultSet = preparedStatement.executeQuery();
			if(resultSet.next()==false){
				resultSet.close();
				preparedStatement.close();
				connection.close();
				throw new DAOException("Invalid Aadhar Number: "+aadharNumber);
			}
			int id;
			String name;
			int designationCode;
			String designation;
			java.util.Date dateOfBirth;
			String gender;
			boolean isIndian;
			BigDecimal basicSalary;
			String panNumber;
			//String aadharNumber;
			id = resultSet.getInt("id");
			name = resultSet.getString("name");
			designationCode = resultSet.getInt("designation_code");
			designation = resultSet.getString("title").trim();
			dateOfBirth = resultSet.getDate("date_of_birth");
			gender = resultSet.getString("gender");
			isIndian = resultSet.getBoolean("is_indian");
			basicSalary = resultSet.getBigDecimal("basic_salary");
			panNumber = resultSet.getString("pan_number").trim();
			aadharNumber = resultSet.getString("aadhar_number").trim();
			employeeDTO = new EmployeeDTO();
			employeeDTO.setEmployeeId("A"+id);
			employeeDTO.setName(name);
			employeeDTO.setDesignationCode(designationCode);
			employeeDTO.setDesignation(designation);
			employeeDTO.setDateOfBirth(dateOfBirth);
			employeeDTO.setGender(gender);
			employeeDTO.setIsIndian(isIndian);
			employeeDTO.setBasicSalary(basicSalary);
			employeeDTO.setPanNumber(panNumber);
			employeeDTO.setAadharNumber(aadharNumber);
			resultSet.close();
			preparedStatement.close();
			connection.close(); 
		}catch(Exception e){
			throw new DAOException(e.getMessage());
		}
		return employeeDTO;
	}
}