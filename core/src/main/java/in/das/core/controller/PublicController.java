package in.das.core.controller;

import in.das.core.services.EmployeeService;
import in.das.entity.Employee;
import in.das.shared.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/data")
    public String data(){
        return "accessing public data";
    }

    @GetMapping("/emp/all")
    public List<Employee> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    @GetMapping("/encode/{data}")
    public String encode(@PathVariable("data") final String data){
        return CommonUtils.base64Encode(data);
    }

    @GetMapping("/decode/{data}")
    public String decode(@PathVariable("data") final String data){
        return CommonUtils.base64Decode(data);
    }
}
