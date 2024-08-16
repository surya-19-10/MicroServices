package com.online.retail.customer.impl;

import com.online.retail.customer.model.Address;
import com.online.retail.customer.model.Customer;
import com.online.retail.customer.repo.AddressRepository;
import com.online.retail.customer.repo.CustomerRepository;
import com.online.retail.customer.service.CustomerService;
import com.online.retail.customer.utils.CustomerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImplementation implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Override
    public Customer addCustomer(Customer customer) {
        Address billingAddress = addressRepository.save(CustomerUtils.getBillingAddress(customer));
        Address shippingAddress = addressRepository.save(CustomerUtils.getShippingAddress(customer));
        customer.setBillingAddress(billingAddress);
        customer.setShippingAddress(shippingAddress);
        Customer resultCustomer = customerRepository.save(customer);
        return resultCustomer;
    }

    @Override
    public Customer searchCustomer(int id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public Customer updateCustomer(int id, Customer customer) {
        Customer existingCustomer = customerRepository.findById(id).get();
        Address existingBilling = addressRepository.findById(existingCustomer.getBillingAddress().getAddressId()).get();
        Address existingShipping = addressRepository.findById(existingCustomer.getShippingAddress().getAddressId()).get();
        existingBilling.setDoorNo(customer.getBillingAddress().getDoorNo());
        existingBilling.setStreet(customer.getBillingAddress().getStreet());
        existingBilling.setCity(customer.getBillingAddress().getCity());
        existingBilling.setPincode(customer.getBillingAddress().getPincode());
        existingShipping.setDoorNo(customer.getShippingAddress().getDoorNo());
        existingShipping.setStreet(customer.getShippingAddress().getStreet());
        existingShipping.setCity(customer.getShippingAddress().getCity());
        existingShipping.setPincode(customer.getShippingAddress().getPincode());
        existingCustomer.setShippingAddress(existingShipping);
        existingCustomer.setBillingAddress(existingBilling);
        existingCustomer.setName(customer.getName());
        existingCustomer.setEmail(customer.getEmail());
        return customerRepository.save(existingCustomer);
    }

    @Override
    public String deleteCustomer(int id) {
        Customer customer = customerRepository.findById(id).get();
        int billing = customer.getBillingAddress().getAddressId();
        int shipping = customer.getShippingAddress().getAddressId();
        addressRepository.deleteById(billing);
        addressRepository.deleteById(shipping);
        customerRepository.deleteById(id);
        return "Deleted";
    }
}
