package com.telran.entity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDetails {

    private String city;
    private String street;
    private String houseNumber;

    private String favouriteColor;
}
