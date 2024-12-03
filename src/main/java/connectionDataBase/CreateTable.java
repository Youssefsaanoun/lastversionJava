package connectionDataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/iit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Ayouda123";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            if (connection != null) {
                System.out.println("Connected to the database!");

                String createEtudiantTable = "CREATE TABLE IF NOT EXISTS Etudiant (" +
                        "id INT AUTO_INCREMENT PRIMARY KEY, " +
                        "first_name VARCHAR(255) NOT NULL, " +
                        "last_name VARCHAR(255) NOT NULL, " +
                        "age INT NOT NULL, " +
                        "email VARCHAR(255) UNIQUE NOT NULL)";
                
                String createRoleTable = "CREATE TABLE IF NOT EXISTS Role (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "titre VARCHAR(255) NOT NULL)";
                
                String createEtudiantRoleTable = "CREATE TABLE IF NOT EXISTS Etudiant_Role (" +
                        "etudiant_id INT, " +
                        "role_id BIGINT, " +
                        "PRIMARY KEY (etudiant_id, role_id), " +
                        "FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ON DELETE CASCADE, " +
                        "FOREIGN KEY (role_id) REFERENCES Role(id) ON DELETE CASCADE)";
                
                String createContactTable = "CREATE TABLE IF NOT EXISTS Contact (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "telephone VARCHAR(20), " +
                        "skype VARCHAR(255), " +
                        "etudiant_id INT UNIQUE, " +
                        "FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ON DELETE CASCADE)";
                
                String createAdresseTable = "CREATE TABLE IF NOT EXISTS Adresse (" +
                        "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                        "ville VARCHAR(255) NOT NULL, " +
                        "code_postale INT NOT NULL, " +
                        "etudiant_id INT, " +
                        "FOREIGN KEY (etudiant_id) REFERENCES Etudiant(id) ON DELETE CASCADE)";

                try (Statement statement = connection.createStatement()) {
                    statement.execute(createEtudiantTable);
                    System.out.println("Table 'Etudiant' created successfully!");

                    statement.execute(createRoleTable);
                    System.out.println("Table 'Role' created successfully!");

                    statement.execute(createEtudiantRoleTable);
                    System.out.println("Table 'Etudiant_Role' created successfully!");

                    statement.execute(createContactTable);
                    System.out.println("Table 'Contact' created successfully!");

                    statement.execute(createAdresseTable);
                    System.out.println("Table 'Adresse' created successfully!");
                } catch (SQLException e) {
                    System.out.println("Failed to create tables!");
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }
}