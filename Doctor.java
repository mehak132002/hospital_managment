
package hospital_management_system;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Doctor {
        private final Connection connection;
    
    
    public Doctor(Connection connection){
        this.connection = connection;
       
    }
    //viewing doctors
    public void viewDoctors() {
        String query = "SELECT * FROM Doctors";
        try{
          PreparedStatement preparedStatement = connection.prepareStatement(query);
          ResultSet resultSet = preparedStatement.executeQuery();
          System.out.println("doctors : ");
          System.out.println("+------------+-------------------+------------------+");
          System.out.println("| Doctor Id | Name               | Specialization   |");
          System.out.println("+------------+-------------------+------------------+");
          while(resultSet.next()){
              int id = resultSet.getInt("Id");
              String name = resultSet.getString("Name");
              String Specialization = resultSet.getString("Specialization");
              System.out.printf("|%-12s|%-20s|%-18s|\n" , id , name, Specialization);
              System.out.println("+------------+-------------------+------------------+");
              
          }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public boolean getDoctorById(int id){
        String query = "SELECT * FROM Doctors WHERE id = ?";
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
