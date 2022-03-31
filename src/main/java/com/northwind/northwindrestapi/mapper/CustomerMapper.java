package com.northwind.northwindrestapi.mapper;

import com.northwind.northwindrestapi.dto.CustomerCreateDTO;
import com.northwind.northwindrestapi.entity.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer createDtoToEntity(CustomerCreateDTO customerCreateDTO);
}
