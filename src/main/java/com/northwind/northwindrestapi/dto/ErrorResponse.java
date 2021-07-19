/*
 * Copyright (C) 2017 adidas AG.
 */

package com.northwind.northwindrestapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ErrorResponse {

    private String message;

}
