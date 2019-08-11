package com.telran.controller;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchDto {

    private String name;
    private String color;
    private String phoneNumber;
    private String phoneBrand;
    private String phoneModel;
}
