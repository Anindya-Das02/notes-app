package in.das.core.controller;

import in.das.core.services.EmployeeService;
import in.das.entity.Employee;
import in.das.shared.BizConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/")
    public String test(){
        return BizConstants.message;
    }

    @PostMapping("/create")
    public Employee createEmployee(RequestEntity<Employee> employeeRequestEntity){
        return employeeService.saveEmployee(employeeRequestEntity.getBody());
    }
}
