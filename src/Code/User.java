package Code;
public abstract class User {
    protected String username, firstName, lastName, dateJoined, email, birthDate, loginPassword, role;

    public User(String username, String firstName, String lastName, String dateJoined, String email, String birthDate, String loginPassword, String role) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateJoined = dateJoined;
        this.email = email;
        this.birthDate = birthDate;
        this.loginPassword = loginPassword;
        this.role = role;
    }

    

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateJoined() {
        return dateJoined;
    }

    public String getEmail() {
        return email;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getLoginPassword() {
        return loginPassword;
    }

    public String getRole() {
        return role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateJoined(String dateJoined) {
        this.dateJoined = dateJoined;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setLoginPassword(String loginPassword) {
        this.loginPassword = loginPassword;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    public void dashboard(){
    }
    
    
}
