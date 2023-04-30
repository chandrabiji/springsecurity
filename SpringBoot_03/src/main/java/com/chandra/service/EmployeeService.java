package com.chandra.service;

import java.util.List;
import java.util.Optional;

import com.chandra.model.Employee;

public interface EmployeeService {

	public Integer saveEmployee(Employee e);
	public void updateEmployee(Employee e);
	public void deleteEmployee(Integer id);
	public Optional<Employee> getOneEmployee(Integer id);
	public List<Employee> getAllEmployees();
	public boolean isPresent(Integer id);
}
