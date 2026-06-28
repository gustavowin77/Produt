import java.sql.*;

public class Registrer_SQL {


    private String User;
    private String Passoword;



    private String Username;
    private String Passoword1;
    private String Passoword2;


    private static String urlServer =
            "jdbc:mysql://localhost:3306/" +
                    "?useSSL=false" +
                    "&allowPublicKeyRetrieval=true" +
                    "&serverTimezone=UTC";

    private static String urlDatabase =
            "jdbc:mysql://localhost:3306/dados" +
                    "?useSSL=false" +
                    "&allowPublicKeyRetrieval=true" +
                    "&serverTimezone=UTC";

   private static String user = "root";
   private static String passoword = "12345.";


    public Registrer_SQL(String Username, String Passoword1, String Passoword2){
        this.Username = Username;
        this.Passoword1 = Passoword1;
        this.Passoword2 = Passoword2;
    }

    public Registrer_SQL(String User, String Passoword){
        this.User = User;
        this.Passoword = Passoword;
    }

    public Registrer_SQL(){

    }

    public void Registrer_User(){



        try(Connection connection = DriverManager.getConnection(urlServer, user, passoword);
        Statement statement = connection.createStatement()){

            statement.execute("CREATE DATABASE IF NOT EXISTS dados;");

        } catch (SQLException e){
            e.printStackTrace();
        }


        try(Connection connection = DriverManager.getConnection(urlDatabase, user, passoword);
            Statement statement = connection.createStatement()){
            statement.execute("CREATE TABLE IF NOT EXISTS `User`(" +
                    "Username VARCHAR(50) NOT NULL UNIQUE," +
                    "Passoword1 VARCHAR(150) NOT NULL," +
                    "Passoword2 VARCHAR(150) NOT NULL" +
                    ");"
            );

    } catch (SQLException e){
            e.printStackTrace();
        }


        try(Connection connection = DriverManager.getConnection(urlDatabase, user, passoword)){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO User(Username, Passoword1, Passoword2) VALUES(?, ?, ?)"
            );

            preparedStatement.setString(1, Username);
            preparedStatement.setString(2, Passoword1);
            preparedStatement.setString(3, Passoword2);

            preparedStatement.executeUpdate();

        } catch (SQLException e){
            e.printStackTrace();
        }
    }



    public String getUser(){
        return User;
    }


    public void verifyUsers(){
        try(Connection connection = DriverManager.getConnection(urlDatabase, user, passoword)){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM User WHERE Username = ? AND Passoword1 = ? AND Passoword2 = ?");


            preparedStatement.setString(1, "");
            preparedStatement.setString(2, "");
            preparedStatement.setString(3, "");

            int verifiylines = preparedStatement.executeUpdate();


            if(verifiylines > 0){
                System.out.println("Usúario exclúido do Banco de dados com Sucesso");
            } else {
                System.out.println("Nimguém foi encontrado sem Username, Passoword1 e Passoword2");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean searchUser(){
        try(Connection connection = DriverManager.getConnection(urlDatabase, user, passoword)){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM User WHERE Username = ? AND Passoword1 = ?");

            preparedStatement.setString(1, User);
            preparedStatement.setString(2, Passoword);


            ResultSet resultSet = preparedStatement.executeQuery();

            return resultSet.next();

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }



    }

}
