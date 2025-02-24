
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Main extends Layout {
    private static String sql;
    private static String url = "jdbc:mysql://localhost:3306/employee_db";
    private static String user = "root"; 
    private static String password = "Tuananh2008"; 
    private static ArrayList<String[]> employees;

    public static void main(String[] args) { 
       Layout layout = new Main();
       layout.bodyUI(getEmployees());
       layout.show();
    }   

    @Override
    protected void buttonSaveDataListener() {
        super.buttonSaveDataListener();
        addEmployee(this.newEmp);
        bodyUI(getEmployees());
    }


    public static ArrayList<String[]> getEmployees() {
         employees = new ArrayList<>();

        try(Connection con = DriverManager.getConnection(url, user, password)) {
            Statement stmt = con.createStatement();

            sql = "SELECT * FROM employee";
            ResultSet resultSet = stmt.executeQuery(sql);

            while(resultSet.next()) {
                String[] employee = {
                                    resultSet.getString("id"),
                                    resultSet.getString("name"), 
                                    resultSet.getString("designation"),
                                    resultSet.getString("gender"),
                                    resultSet.getString("emailId"),
                                    resultSet.getString("dateOfBirth")};
                employees.add(employee);   
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static Employee addEmployee(Employee emp) {
            // abc
            // Connect with Database
            try(Connection con = DriverManager.getConnection(url, user, password)) {
                // Create statement
                String sql = "INSERT INTO employee (name, designation, gender, emailID, dateOfBirth) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement pstmt = con.prepareStatement(sql);
                
                pstmt.setString(1, emp.getName());
                pstmt.setString(2, emp.getDesignation());
                pstmt.setString(3, emp.getGender());
                pstmt.setString(4, emp.getEmailId());
                
                // type String date of birth into sql type Date
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
                Date utilDate = formatter.parse(emp.getDateOfBirth());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());    

                pstmt.setDate(5, sqlDate);

                
                pstmt.executeUpdate();
              
            } catch(Exception e) {
                e.printStackTrace();
            }

            return emp;
    }
}

