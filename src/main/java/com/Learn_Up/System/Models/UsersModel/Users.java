package com.Learn_Up.System.Models.UsersModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    private long id;
    private String name;
    private String email;
    private String password;
    private String role;
}
