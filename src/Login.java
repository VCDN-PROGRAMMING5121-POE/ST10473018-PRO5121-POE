/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;

    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity(String password) {
    boolean hasUppercase = !password.equals(password.toLowerCase());
    boolean hasNumber = password.matches(".\\d.");
    boolean hasSpecial = password.matches(".[^a-zA-Z0-9 ].");
    boolean longEnough = password.length() >= 8;
    return hasUppercase && hasNumber && hasSpecial && longEnough;
}

    public boolean checkCellPhoneNumber(String cell) {
        return cell.startsWith("+27") && cell.length() == 12 && cell.substring(1).matches("\\d+");
    }

    public String registerUser(String username, String password, String firstName, String lastName, String cellPhone) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellPhone)) {
            return "Phone number is not valid. It must start with +27 and contain exactly 12 digits.";
        }

        this.storedUsername = username;
        this.storedPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;

        return "Username and password successfully captured.";
    }

    public boolean loginUser(String username, String password) {
        return username.equals(this.storedUsername) && password.equals(this.storedPassword);
    }

    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + this.firstName + ", " + this.lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
}