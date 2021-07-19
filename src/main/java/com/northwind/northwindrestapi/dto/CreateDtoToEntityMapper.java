package com.northwind.northwindrestapi.dto;

public interface CreateDtoToEntityMapper<E> {

  E mapFromCreate(E entity);

}
