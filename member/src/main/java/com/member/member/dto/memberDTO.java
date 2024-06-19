package com.member.member.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class memberDTO {
    private Long  id;
    private String email;
    private String password;
    private String name;
}
