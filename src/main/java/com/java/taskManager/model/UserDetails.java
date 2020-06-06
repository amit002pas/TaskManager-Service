package com.java.taskManager.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "user_details")
@Data
public class UserDetails {

    @JsonProperty("first_name")
    @Field("first_name")
    private String firstName;

    @JsonProperty("last_name")
    @Field("last_name")
    private String lastName;

    @Indexed(unique = true)
    @JsonProperty("email_id")
    @Field("email_id")
    private String emailId;

    @JsonProperty("phone_number")
    @Field("phone_number")
    private String phoneNumber;

    @JsonProperty("password")
    @Field("password")
    private String password;

    @JsonProperty("is_verified")
    @Field("is_verified")
    private boolean isVerified;

    @JsonProperty("token")
    @Field("token")
    private String token;
}
