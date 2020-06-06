package com.java.taskManager.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @NotNull(message = "First Name can't be empty")
    @JsonProperty("first_name")
    private String firstName;

    @NotNull(message = "Last Name can't be empty")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "email id cannot be null")
    @JsonProperty("email_id")
    @Pattern(regexp ="^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$",message = "Email format invalid")
    private String emailId;

    @NotNull(message = "phone number must not be null")
    @JsonProperty("phone_number")
    @Size(min =  10, max = 10, message = "Phone No. must have 10 digit")
    private String phoneNumber;


    @NotNull(message = "password cannot be null")
    @JsonProperty("password")
    @Pattern(regexp="^[a-zA-Z0-9]+$",message="Password should be alphanumeric")
    @Size(min =  8, max = 14, message = "Password length must be 8-14")
    private String password;
}
