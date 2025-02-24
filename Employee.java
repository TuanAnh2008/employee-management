import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Employee {
     String name, designation, gender, emailId;
     String dateOfBirth;

    Employee(String name, String designation, String gender, String emailId, String dateOfBirth) {
        this.name = name;
        this.designation = designation;
        this.gender = gender;
        this.emailId = emailId;
        this.dateOfBirth = dateOfBirth;
    }

    Employee(String[] arr) {
        this.name = arr[1];
        this.designation = arr[2];
        this.gender = arr[3];
        this.emailId = arr[4];
        this.dateOfBirth = arr[5];
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getGender() {
        return gender;
    }
    
    public String getEmailId() {
        return emailId;
    }
    
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
