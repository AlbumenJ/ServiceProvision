package com.albumen.domain.customer.gateway;

import com.albumen.domain.customer.Customer;

public interface CustomerGateway {
    public Customer getByById(String customerId);
}
