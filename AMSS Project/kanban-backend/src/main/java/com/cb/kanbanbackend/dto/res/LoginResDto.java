package com.cb.kanbanbackend.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

// Lombok Dto for response Login
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class LoginResDto {
    private Integer userId;
    private String username;
    private String email;
    private String calenderUrl;
}
