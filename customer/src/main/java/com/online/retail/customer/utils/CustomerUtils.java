package com.online.retail.customer.utils;

import com.online.retail.customer.model.Address;
import com.online.retail.customer.model.Customer;

public class CustomerUtils {
    public static Address getBillingAddress(Customer customer) {
        Address address = new Address();
        address.setCity(customer.getBillingAddress().getCity());
        address.setStreet(customer.getBillingAddress().getStreet());
        address.setPincode(customer.getBillingAddress().getPincode());
        address.setDoorNo(customer.getBillingAddress().getDoorNo());
        return address;
    }
    public static Address getShippingAddress(Customer customer) {
        Address address = new Address();
        address.setCity(customer.getShippingAddress().getCity());
        address.setStreet(customer.getShippingAddress().getStreet());
        address.setPincode(customer.getShippingAddress().getPincode());
        address.setDoorNo(customer.getShippingAddress().getDoorNo());
        return address;
    }
}
