package com.soecode.blog.entity;

public class RegisterParam {

//     private Integer id;

    private String nickname;

    private String password;

    private String phone;

    private String email;

    private Byte isAdmin;

    private Byte isDelete;

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public Byte getIsAdmin() {
//        return isAdmin;
//    }
//
//    public void setIsAdmin(Byte isAdmin) {
//        this.isAdmin = isAdmin;
//    }
//
//    public Byte getIsDelete() {
//        return isDelete;
//    }
//
//    public void setIsDelete(Byte isDelete) {
//        this.isDelete = isDelete;
//    }
}
