package com.example.demo.controller;

import com.example.demo.exception.EmployeeAlreadyExistException;
import com.example.demo.exception.EmployeeNotFoundException;
import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }

    //for posting data
    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee){
        if(!employeeRepository.existsById(newEmployee.getId()))
              return employeeRepository.save(newEmployee);
        throw new EmployeeAlreadyExistException(newEmployee.getId());

    }

    //for getting data
    @GetMapping("/employees")
    List<Employee> all(String keyword){
        if(keyword!=null) return employeeRepository.searchByEmail(keyword);
        return employeeRepository.findAll();
    }

    //for getting employee info by id
    @GetMapping("/employee/{id}")
   Employee getEmployeeById(@PathVariable String id){
        return employeeRepository.findById(id).orElseThrow(()->new EmployeeNotFoundException(id));
    }

    //for update employee info by id
    @PutMapping("/employee/{id}")
    Employee updateUser(@RequestBody Employee newEmployee,@PathVariable String id){
        return employeeRepository.findById(id).map(employee->{employee.setFirst(newEmployee.getFirst());
            employee.setLast(newEmployee.getLast());employee.setEmail(newEmployee.getEmail());
            return employeeRepository.save(employee);})
                .orElseGet(()->{newEmployee.setId(id); return employeeRepository.save(newEmployee);});
    }

    //for delete employee by id
    @DeleteMapping("/employee/{id}")
    String deleteUser(@PathVariable String id){
        if(!employeeRepository.existsById(id)){
            throw new EmployeeNotFoundException((id));
        }
        employeeRepository.deleteById(id);
        return "Employee with id "+id+" deleted successfully";
    }
}
