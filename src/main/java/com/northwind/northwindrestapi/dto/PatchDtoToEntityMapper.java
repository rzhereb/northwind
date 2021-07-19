package com.northwind.northwindrestapi.dto;

public interface PatchDtoToEntityMapper<E> {

  void mapFromPatch(E entity);

}
