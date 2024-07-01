package com.mavericktube.MaverickHub.dtos.responds;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseResponse <T>{
    private int code;
    private boolean status;
    private T data;

}
