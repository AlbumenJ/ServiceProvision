package com.albumen.domain.customer;

import com.albumen.customer.Customer;

public interface CustomerService {
    boolean register(String username, String password, Customer customer);

}
