package com.online.retail.composite.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private int addressId;
    private String doorNo;
    private String street;
    private String city;
    private long pincode;
}
