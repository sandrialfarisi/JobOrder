package id.sandri.joborder.Model;

public class Register {
    String username;
    String password;
    String email;
    String no_hp;
    String departmen;
    String message;

    Register(String username, String password, String email, String no_hp, String departmen, String message){
        this.username = username;
        this.password = password;
        this.email = email;
        this.no_hp = no_hp;
        this.departmen = departmen;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNo_hp() {
        return no_hp;
    }

    public void setNo_hp(String no_hp) {
        this.no_hp = no_hp;
    }

    public String getDepartmen() {
        return departmen;
    }

    public void setDepartmen(String departmen) {
        this.departmen = departmen;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
