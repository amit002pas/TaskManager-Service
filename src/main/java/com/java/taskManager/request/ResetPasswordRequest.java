package com.java.taskManager.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ResetPasswordRequest {

    private String userId;
    @NotNull
    @Size(min =  8,max =8,message = "Temporary Password must have length 8")
    private String token;
    @NotNull
    @Pattern(regexp="^[a-zA-Z0-9]{8}",message="length must be 8")
    private String newPassword;

}
