package bizlogic;


import DB.CustomerData;

public class Customer extends Flights {
    private String firstName;
    private String lastName;
    private String address;
    private String zipCode;
    private String state;
    private String username;
    private String password;
    private String email;
    private String SSN;
    private String securityAnswer;
    private String birthday;
    static int customerID;

    public Customer() {

    }

    public String getFirstName() {

        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getZipcode() {
        return zipCode;
    }
    public void setZipcode(String zipcode) {
        this.zipCode = zipcode;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
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
    public String getSSN() {
        return SSN;
    }
    public void setSSN(String sSN) {
        SSN = sSN;
    }
    public String getSecurityAnswer() {
        return securityAnswer;
    }
    public void setSecurityAnswer(String securityAnswer) {
        this.securityAnswer = securityAnswer;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public int getCustomerID() {
        return customerID;
    }

    public boolean checkUser(String username) throws Exception{

        if (uniqueUser(username)) {

            register(firstName, lastName, email, address, username, password, SSN,
                    securityAnswer, zipCode, state, birthday);

            return true;
        }

        else {
            return false;
        }
    }
    public boolean filledCorrect(String firstName, String lastName, String email, String address,
                                 String username, String password, String ssn, String securityAnswer,
                                 String zipcode, String state, String birthday) throws Exception {

        if (address.trim().equals("") || zipcode.trim().equals("") || email.trim().equals("") ||
                state.trim().equals("") || securityAnswer.trim().equals("") ||
                firstName.trim().equals("") || lastName.trim().equals("") || username.trim().equals("")
                || ssn.trim().equals("") || password.trim().equals("") || birthday.trim().equals("")) {

            return false;
        }

        else {

            this.address = address;
            this.zipCode = zipcode;
            this.email = email;
            this.state = state;
            this.securityAnswer = securityAnswer;
            this.firstName = firstName;
            this.lastName = lastName;
            this.username = username;
            this.password = password;
            this.SSN = ssn;
            this.birthday = birthday;

            return true;
        }
    }
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}




