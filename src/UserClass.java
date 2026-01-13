package com.example;

public class UserClass {
    private int userId;           // 用户ID
    private String username;      // 用户名
    private String email;         // 邮箱
    private String password;      // 密码
    private String role;          // 角色：admin/teacher/student

    // 构造方法1：无参构造
    public UserClass() {
        this.userId = 0;
        this.username = "";
        this.email = "";
        this.password = "";
        this.role = "student";
    }

    // 构造方法2：带参数的构造
    public UserClass(String username, String email) {
        this();
        this.username = username;
        this.email = email;
    }

    // 构造方法3：全参数构造
    public UserClass(int userId, String username, String email, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    // Getter 和 Setter 方法
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // 验证用户方法
    public boolean validateUser(String inputUsername, String inputPassword) {
        return this.username.equals(inputUsername) &&
                this.password.equals(inputPassword);
    }

    // 判断是否为管理员
    public boolean isAdmin() {
        return "admin".equalsIgnoreCase(this.role);
    }

    @Override
    public String toString() {
        return "UserClass{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}