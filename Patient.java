//mehak@1310
package hospital_management_system;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Patient {
    private Connection connection;
    private Scanner scanner;
    
    public Patient(Connection connection , Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }
    
    //adding patients
    public void addPatient(){
        System.out.println("Enter Patient name :");
        String name = scanner.next();
        System.out.println("Enter Patient age :");
        int age = scanner.nextInt();
        System.out.println("Enter Patient gender :");
        String gender = scanner.next();
        
        try{
            String query = "INSERT INTO Patients(name , age , gender) VALUES (? ,? ,?)";
            //passing query
            PreparedStatement preparedStatemenet = connection.prepareStatement(query);
            //setting values
            preparedStatemenet.setString(1 , name);
            preparedStatemenet.setInt( 2 , age);
            preparedStatemenet.setString( 3 , gender);
            int affectedRows = preparedStatemenet.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add Patient!!");
            }
        }   
        catch(SQLException e){
            e.printStackTrace();
        }  
    }
    
    //viewing patients
    public void viewPatients() {
        String query = "SELECT * FROM Patients";
        try{
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery();
          System.out.println("patients : ");
          System.out.println("+------------+--------------------+----------+------------+");
          System.out.println("| Patient Id | Name               | Age      | Gender     |");
          System.out.println("+------------+--------------------+----------+------------+");
          while(resultSet.next()){
              int id = resultSet.getInt("Id");
              String name = resultSet.getString("Name");
              int age = resultSet.getInt("Age");
              String gender = resultSet.getString("Gender");
              System.out.printf("|%-12s|%-20s|%-10s|%-12s|" , id , name ,gender);
              System.out.println("+------------+--------------------+----------+------------+");
              
          }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean checkPaitentById(int id){
        String query = "SELECT * FROM Patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
    
}
    

