package com.albumen.domain.customer;

import com.albumen.customer.Customer;
import com.albumen.customer.CustomerMapper;
import com.albumen.user.User;
import com.albumen.user.UserMapper;
import com.albumen.worker.WorkerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public boolean register(String username, String password, Customer customer) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setRoleId(2);

        userMapper.insert(user);

        customer.setCustomerId(user.getUserId());
        customerMapper.insert(customer);

        return true;
    }

}
