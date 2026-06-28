import com.mysql.cj.log.Log;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Account_Registrer extends Application {
    private static String color;
    private static boolean clicked;
    private  static String colorButton;





    private static Label createTerms(Pane root, String color, double x, double y){
        Label label = new Label();
        label.setText("Aceito os Termos de serviço");

        label.setFont(new Font("Arial", 11.5));

        label.setStyle(
                "-fx-text-fill: %s;".formatted(color)
        );

        label.setLayoutX(x);
        label.setLayoutY(y);

        root.getChildren().add(label);


        return label;
    }


    public static Label createErrorLabel(Pane root, TextField textField, String txt, double x, double y){

        Label label = new Label();
        label.setText(txt);

        label.setFont(new Font("Arial", 11));

        label.setStyle(
                "-fx-text-fill: #BD2A2E;"
        );


        int Xtextfield = (int) textField.getLayoutX();
        int Ytextfield = (int) textField.getLayoutY();

        System.out.println("X TextField: " + Xtextfield + "\n" + "Y TextField: " + Ytextfield);


        label.setLayoutX(Xtextfield);
        label.setLayoutY(Ytextfield + 37 );

        int labelX = (int) label.getLayoutX();
        int labelY = (int) label.getLayoutY();


        System.out.println("Posição do Label: " + "\n" + "X: "+ labelX + "\n" + "Y: " + labelY);

        root.getChildren().add(label);


        return label;
    }

    private static Label createLabelRegistrer(Pane root, String txt, double x, double y, double width, double heigth){


        Label label = new Label();
        label.setText(txt);

        label.setLayoutX(x);
        label.setLayoutY(y);

        label.setPrefWidth(width);
        label.setPrefHeight(heigth);

        label.setFont(new Font("Arial", 26));
        label.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0px;"
        );

        root.getChildren().add(label);

        return  label;
    }
    private static Button createButtonLogin(Pane root, String txt, double x, double y, double width, double heigth){
        Button button = new Button();
        button.setText(txt);



        button.setLayoutX(x);
        button.setLayoutY(y);


        button.setPrefWidth(width);
        button.setPrefHeight(heigth);

        button.setMinSize(width, heigth);
        button.setMaxSize(width, heigth);


        button.setFont(new Font("Arial", 19));


        System.out.println(colorButton);

        button.setStyle(
                "-fx-background-color: %s;".formatted(colorButton) +
                        "-fx-background-radius: 15px;" +
                        "-fx-background-insets: 0px;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-focus-color: transparent;" +
                        "-fx-border-width: 1px;" +
                        "-fx-border-color: black;" +
                        "-fx-border-radius: 15px;"
        );



        System.out.println(clicked);
        if(clicked){
            colorButton = "#FF7A48";
            System.out.println("Cor alterada");
        } else {
            System.out.println("Cor não alterada");
        }

        root.getChildren().add(button);

        return button;
    }

    private static CheckBox createCheckBox(Pane root, TextField UsernameTE, TextField Passoword1TE, TextField Passoword2TE, double x, double y, double width, double heigth){

        CheckBox checkBox = new CheckBox();

        checkBox.setLayoutX(x);
        checkBox.setLayoutY(y);


        checkBox.setPrefWidth(width);
        checkBox.setPrefHeight(heigth);


        checkBox.setStyle(
                "-fx-background-color: transparent;" +
                        "-fx-background-insets: 0px;" +
                        "-fx-faint-focus-color: transparent;" +
                        "-fx-focus-color: transparent;"

        );


        Label error1 = createErrorLabel(root, UsernameTE, "%s", 50, 50);
        Label error2 = createErrorLabel(root, Passoword1TE, "%s", 50, 50);
        Label error3 = createErrorLabel(root, Passoword2TE, "%s", 50, 50);

        error1.setVisible(false);
        error2.setVisible(false);
        error3.setVisible(false);




        Label labelTerms = createTerms(root, color,70, 287.5);
        Button Login = createButtonLogin(root, "Acess", 50, 350, 225, 55);


        checkBox.setOnAction(event -> {
            if(checkBox.isSelected()){


                Login.setOnAction(event1 -> {

                    new Registrer_SQL().verifyUsers();





                    String Username = UsernameTE.getText();
                    String Passoword1 = Passoword1TE.getText();
                    String Passoword2 = Passoword2TE.getText();

                    if(Username.trim().isEmpty()){
                        error1.setVisible(true);
                        error1.setText("Informação incompleta, preencha!");

                    } else{
                        error1.setVisible(false);
                    }

                    if(Passoword1.trim().isEmpty()){
                        error2.setVisible(true);
                        error2.setText("Informação incompleta, preencha!");
                    } else{
                        error2.setVisible(false);
                    }

                    if(Passoword2.trim().isEmpty()){
                        error3.setVisible(true);
                        error3.setText("Informação incompleta, preencha!");
                    } else{
                        error3.setVisible(false);
                    }


                    if(Username.trim().isEmpty()|| Passoword1.trim().isEmpty() || Passoword2.trim().isEmpty()){



                        checkBox.setLayoutY(300);
                        labelTerms.setLayoutY(303);

                        System.out.println("Falha ao tentar registrar uma conta, parece que vc não preencheu todas as informações exigidas");

                    } else {

                        if(Username.length() > 10 || Passoword1.length() > 5 || Passoword2.length() > 5) {

                            System.out.println(Username + Passoword1 + Passoword2);

                            Registrer_SQL registrerSql = new Registrer_SQL(Username, Passoword1, Passoword2);

                            registrerSql.Registrer_User();
                            Stage stage = new Stage();

                            new Account_Login().start(stage);

                            Stage stage1 = (Stage) Login.getScene().getWindow();
                            stage1.close();
                        } else {

                            System.out.println("Não foi digitado informações suficientes para o cadastro(username, passoword1 e passoword2 apresentam apenas poucas palavras, preencha)");
                        }
                    }
                });
                clicked = true;
                labelTerms.setStyle("-fx-text-fill: %s;".formatted("#0593A2"));
                Login.setStyle(
                        "-fx-background-color: %s;".formatted("#9AEBA3") +
                                "-fx-background-radius: 10px;" +
                                "-fx-background-insets: 0px;" +
                                "-fx-faint-focus-color: transparent;" +
                                "-fx-focus-color: transparent;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-color: black;" +
                                "-fx-border-radius: 10px;"

                );


            } else {
                clicked = false;
                color = "black";
                labelTerms.setStyle(
                        "-fx-text-fill: %s".formatted("black")
                );

                Login.setStyle(
                        "-fx-background-color: %s;".formatted("white") +
                                "-fx-background-radius: 15px;" +
                                "-fx-background-insets: 0px;" +
                                "-fx-faint-focus-color: transparent;" +
                                "-fx-focus-color: transparent;" +
                                "-fx-border-width: 1px;" +
                                "-fx-border-color: black;" +
                                "-fx-border-radius: 15px;"
                );
            }
        });

        labelTerms.setStyle("-fx-text-fill: %s".formatted("black"));
        root.getChildren().add(checkBox);

        return checkBox;
    }


    private static TextField createTextField(Pane root, String txt, double x, double y, double width, double heigth){



        TextField textField = new TextField();


        textField.setPromptText(txt);

        textField.setLayoutX(x);
        textField.setLayoutY(y);

        textField.setPrefHeight(heigth);

        textField.setMinSize(width, heigth);

        textField.setMaxSize(width, heigth);


        textField.setFont(new Font(13));
        textField.setPrefWidth(width);

        textField.setStyle(
                "-fx-text-fill: black;" +
                        "-fx-background-insets: 0px;" +
                        "-fx-border-color: transparent transparent #9AEBA3 transparent;" +
                        "-fx-background-color: transparent;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-border-radius: 5px;" +
                        "-fx-faint-focus-color:transparent;" +
                        "-fx-focus-color: transparent;"
        );



        root.getChildren().add(textField);

        return textField;
    }

    public void start(Stage stage){
        Pane root = new Pane();


        Image image = new Image("https://get.pxhere.com/photo/water-nature-waterfall-river-body-of-water-nationalpark-waterfalls-wasserfall-flickrelite-milfordsound-realjourneys-sonydslra300-boatcruisemilfordsound-tamron1750mm-southislandnewzealand-water-feature-atmospheric-phenomenon-172282.jpg");

        createLabelRegistrer(root, "Cadastro", 50, 50, 210, 35);


        TextField UsernameT = createTextField(root, "Username", 50, 130, 210, 35);
        TextField Passoword1T = createTextField(root, "Passoword", 50, 190, 210, 35);
        TextField Passoword2T = createTextField(root, "Confirm Passorword", 50, 245, 210, 35);




        createCheckBox(root, UsernameT, Passoword1T, Passoword2T, 50, 285, 10, 10);


        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setY(0);
        imageView.setX(410);

        imageView.setFitHeight(700);





        imageView.setFitWidth(900);

        Scene scene = new Scene(root, 900, 515);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();

        root.getChildren().add(imageView);

    }
}
