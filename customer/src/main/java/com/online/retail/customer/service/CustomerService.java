package com.online.retail.customer.service;

import com.online.retail.customer.model.Customer;
public interface CustomerService {
    Customer addCustomer(Customer customer);

    Customer searchCustomer(int id);

    Customer updateCustomer(int id, Customer customer);

    String deleteCustomer(int id);
}
