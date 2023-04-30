package com.chandra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chandra.model.Employee;
@Repository
public interface EmployeeRespository extends JpaRepository<Employee, Integer>{

}
