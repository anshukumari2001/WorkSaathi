package com.personal.WorkSaathi.dto;

import com.personal.WorkSaathi.enums.UserType;
import lombok.Data;
import lombok.NonNull;

@Data
public class UserDTO {
    @NonNull
    private String name;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private UserType type;
}
