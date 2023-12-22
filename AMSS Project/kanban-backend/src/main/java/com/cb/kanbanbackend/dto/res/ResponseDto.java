package com.cb.kanbanbackend.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class ResponseDto {
    String responseCode;
    String responseMsg;
    Object content;
}
