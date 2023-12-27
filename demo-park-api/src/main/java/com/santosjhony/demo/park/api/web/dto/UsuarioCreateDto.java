package com.santosjhony.demo.park.api.web.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UsuarioCreateDto {
    private String username;
    private String password;
}
