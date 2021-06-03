package com.albumen.domain.customer.gateway;

import com.albumen.domain.customer.Customer;
import com.albumen.domain.customer.Credit;

//Assume that the credit info is in antoher distributed Service
public interface CreditGateway {
    public Credit getCredit(String customerId);
}
