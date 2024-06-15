package com.myshop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Dto - DataTransferObject - typ obiektu który przekazuje dane pomiedzy poszczegolnymi warstwami
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {
    private String firstName;
    private String lastName;
    private String address;
    private String postCode;
    private String city;
}
