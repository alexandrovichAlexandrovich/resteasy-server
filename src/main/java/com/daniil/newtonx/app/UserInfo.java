package com.daniil.newtonx.app;

public class UserInfo {
    private String firstName;
    private String lastName;

    public UserInfo() { }

    public UserInfo(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    @Override
    public String toString() {
        String result = new StringBuilder()
                .append("{\"firstName\": \"")
                .append(this.firstName)
                .append("\", \"lastName\": \"")
                .append(this.lastName)
                .append("\"}")
                .toString();

        System.out.println(result);
        return result;
    }

    public boolean isInvalidEntry() {
        return this.firstName == null || this.lastName == null;
    }
}
