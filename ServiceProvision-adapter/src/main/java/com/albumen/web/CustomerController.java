package com.albumen.web;

import com.albumen.customer.Customer;
import com.albumen.domain.customer.CustomerService;
import com.albumen.result.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/register")
    public CommonResult<Void> register(@RequestParam("username") String username,
                                       @RequestParam("password") String password, Customer customer) {
        customerService.register(username, password, customer);
        return new CommonResult<Void>().success();
    }

}
