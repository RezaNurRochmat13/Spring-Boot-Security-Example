package com.rejak.springsecurityexamples.user.dao.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class DetailUserDto {
    private Integer userId;
    private String userName;
    private Integer userAge;
    private String userAddress;
    private String userRole;
}
