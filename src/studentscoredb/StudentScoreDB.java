package studentscoredb;

/* Before running this program, start your MySQL database engine. */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * AT1 Activity 3 OOP 2 - Student Score DB
 * 
 * @version             1.00 07 Sep 2022
 * @author              Joshua Farrell
 */
public class StudentScoreDB {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/student_management";
        String user = "root";
        String password = "";
        
        Connection con = null;          /* JDBC connection */
        Statement stmt = null;          /* SQL statement object */
        String query;                   /* SQL query string */
        ResultSet result = null;        /* results after SQL execution */  
        
        try {
            con = DriverManager.getConnection(url, user, password); /* Connect to MySQL */
            con.setAutoCommit(false);
            stmt = con.createStatement();
			
			/* Create a student management database */
			createSMDB(url, user, password, con, stmt, "", result);
            
            /* Variables for updating and inserting subjects */
            String changeSubject = "";
            int changeScore = 0;
            
            /* Reusable static method to invoke SELECT * FROM student_score */
            selectRecord(url, user, password, con, stmt, "", result);
            
            /* Update a subject, then print the table */
            changeSubject = "Science";
            changeScore = 92;
            
            System.out.println("Updating " + changeSubject + " score to " + changeScore + "\r\n");
            
            query = "UPDATE student_score SET Score = " + changeScore + " WHERE Subject = \"" + changeSubject + "\";";
            stmt.executeUpdate(query);          /* Commit transaction */
            
            /* Insert a new subject to the table */
            changeSubject = "Coding";
            changeScore = 89;
            
            System.out.println("Inserting " + changeSubject + " score as " + changeScore + "\r\n");
            query = "INSERT INTO student_score VALUES (\"" + changeSubject + "\", " + changeScore + ");";
            stmt.executeUpdate(query);
            
            /* Delete an old subject from the table */
            changeSubject = "Math";
            
            System.out.println("Deleting " + changeSubject + " from student_score\r\n");
            query = "DELETE FROM student_score WHERE Subject = \"" + changeSubject + "\";";
            stmt.executeUpdate(query);
            
			/* Commit the changes to the student_score table */
            System.out.println("Committing changes to student_score table");
            con.commit();
            
            selectRecord(url, user, password, con, stmt, "", result);
        } catch (SQLException ex) {
            System.out.println("SQLException on database creation: " + ex.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException caught: " + ex.getMessage());
            }
        }
    }
	
	/* Method to create student_score table inside student_management database in MySQL */
	private static void createSMDB(String url, String user, String password, Connection con, Statement stmt, String query, ResultSet result) {
		try {
            con = DriverManager.getConnection(url, user, password); /* Connect to MySQL */
            stmt = con.createStatement();
            
			/* Create student_management database */
            System.out.println("\n#################################################");
            System.out.println(" Student Management Database is being created...");
            System.out.println("#################################################\n");
            query = "DROP DATABASE IF EXISTS student_management;";
            stmt.executeUpdate(query);
            query = "CREATE DATABASE student_management;";
            stmt.executeUpdate(query);
            query = "USE student_management;";
			
			/* Create student_score table inside student_management database */
            System.out.println("\n#########################################");
            System.out.println(" Student Score Table is being created...");
            System.out.println("#########################################\n");
            stmt.executeUpdate(query);
            query = """
                    CREATE TABLE student_score (
                    	Subject VARCHAR(25),
                        Score INTEGER
                    );
                    """;
            stmt.executeUpdate(query);
            query = """
                    INSERT INTO student_score
                    (Subject, Score)
                    VALUES
                    ("English", 95),
                    ("Math", 98),
                    ("Science", 89);
                    """;
            stmt.executeUpdate(query);
            
            
        } catch (SQLException ex) {
            System.out.println("SQLException on database creation: " + ex.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException caught: " + ex.getMessage());
            }
        }
	}
	
	/* Method to print out student_score table inside student_management database */
	private static void selectRecord(String url, String user, String password, Connection con, Statement stmt, String query, ResultSet result) {
		try {
            con = DriverManager.getConnection(url, user, password); /* Connect to MySQL */
            stmt = con.createStatement();
            
            query = "SELECT * FROM student_score;";
            result = stmt.executeQuery(query);
            System.out.println("Printing Student Score Table:");
            while (result.next()) {
                System.out.println(result.getString("Subject") + "\t\tScore: " + result.getInt("Score"));
            }
            System.out.println("");
            
        } catch (SQLException ex) {
            System.out.println("SQLException on database creation: " + ex.getMessage());
        } finally {
            try {
                if (result != null) {
                    result.close();
                }

                if (stmt != null) {
                    stmt.close();
                }

                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                System.out.println("SQLException caught: " + ex.getMessage());
            }
        }
	}
}
