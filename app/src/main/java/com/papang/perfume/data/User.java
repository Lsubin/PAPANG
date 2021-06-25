package com.papang.perfume.data;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    String email;
    String password;
    String name;
    String nickname;
    String gender;
    int birth;
    String address;
    String access;
    String phone;

    public String getEmail(){
        return this.email;
    }

    public String getPassword(){
        return this.password;
    }

    public String getName(){
        return this.name;
    }

    public String getNickname(){
        return this.nickname;
    }

    public String getGender(){
        return this.gender;
    }

    public int getBirth(){
        return this.birth;
    }

    public String getAddress(){
        return this.address;
    }

    public String getAccess(){
        return this.access;
    }

    public String getPhone(){
        return this.phone;
    }
}
