package com.isep.testjpa.controller;

import com.isep.testjpa.repository.EmpRepository;
import com.isep.testjpa.model.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
public class SimpleController {

    @Autowired
    private EmpRepository empRepository;

    @RequestMapping(value="/", method= RequestMethod.GET)
    public String hello(@Param("name") String name) {
        return "Hello " + name;
    }

    @RequestMapping(value="/employees", method= RequestMethod.GET)
    public List<Emp> getEmployees() {
        return empRepository.findAll();
    }

    @PostMapping(value="/employees")
    public Emp addEmployee(@RequestBody Emp emp) {
        return empRepository.save(emp);
    }

    @RequestMapping(value="/employee", method = RequestMethod.GET)
    public Optional<Emp> getById(@Param("id") Long id){
        return empRepository.findById(id);
    }
    @DeleteMapping(value="employees" )
    public void deleteById(@Param("id") Long id){
        empRepository.deleteById(id);
    }
    @PutMapping(value = "/employeeUpdate/{id}")
    public void updateJobUser(@PathVariable(value = "id") Long id, @Param("job") String job){
        List<Emp> allEmp = empRepository.findAll();
        for (Emp value : allEmp) {
            if (Objects.equals(value.getEmpno(), id)) {
                value.setJob(job);
                empRepository.save(value);
            }
        }

    }
}
