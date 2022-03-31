package com.northwind.northwindrestapi.mapper;

import com.northwind.northwindrestapi.dto.OrderDTO;
import com.northwind.northwindrestapi.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    @Mappings({
            @Mapping(target = "customerId", source = "customer.id"),
            @Mapping(target = "employeeId", source = "employee.id"),
            @Mapping(target = "order", source = ".")
    })
    OrderDTO entityToDto(Order order);
}
