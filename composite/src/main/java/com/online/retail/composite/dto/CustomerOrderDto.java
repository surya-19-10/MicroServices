package com.online.retail.composite.dto;

import com.online.retail.composite.core.Customer;
import com.online.retail.composite.core.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderDto {
    private Customer customer;
    private List<Order> orders;
}
