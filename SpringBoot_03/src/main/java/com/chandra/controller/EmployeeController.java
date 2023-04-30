package com.chandra.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chandra.model.Employee;
import com.chandra.service.EmployeeService;

@Controller
@RequestMapping("/rest/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	// 1.save Employee data
		@PostMapping("/save")
		public ResponseEntity<String> save(@RequestBody Employee employee) {
			ResponseEntity<String> resp = null;
			try {
				Integer id = service.saveEmployee(employee);
				resp = new ResponseEntity<String>("Employee " + id + "Created ", HttpStatus.OK);

			} catch (Exception e) {
				resp = new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
				e.printStackTrace();
			}
			return resp;
		}

		// 2.get All Records
		@GetMapping("/all")
		public ResponseEntity<?> getAll() {
			ResponseEntity<?> resp = null;
			List<Employee> list = service.getAllEmployees();
			if (list == null || list.isEmpty()) {
				String message = "No Data Fount";
				resp = new ResponseEntity<String>(message, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<List<Employee>>(list, HttpStatus.OK);
			}
			return resp;
		}

		// 3. delete based on id if exist
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<String> deleteById(@PathVariable Integer id) {
			ResponseEntity<String> resp = null;
			// check for exist
			boolean present = service.isPresent(id);
			if (present) {
				service.deleteEmployee(id);
				resp = new ResponseEntity<String>("Deleted " + id + " Successfully ", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>(" " + id + " Not Exist ", HttpStatus.BAD_REQUEST);
			}
			return resp;
		}

		// 4.Update data
		@PutMapping("/update")
		public ResponseEntity<String> update(@RequestBody Employee employee) {
			ResponseEntity<String> resp = null;
			// check for id exist
			boolean present = service.isPresent(employee.getEmpId());
			if (present) {
				service.updateEmployee(employee);
				resp = new ResponseEntity<String>("Updated Successfully ", HttpStatus.OK);
			} else {
				resp = new ResponseEntity<String>("Record " + employee.getEmpSal() + "Not Found", HttpStatus.BAD_REQUEST);
			}
			return resp;
		}

}
