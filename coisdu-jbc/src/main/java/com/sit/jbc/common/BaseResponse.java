package com.sit.jbc.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * Created by MAMUN on 10-Sep-18.
 */
@Getter
@Setter

public class BaseResponse {

    private MessagType status;
    private String message;
    private String details;
    private String timestamp;


}
