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
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class Account_Login extends Application{

    public static boolean clicked;
    private static String color = "black";
    private static String colorButton = "white";




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

    private static CheckBox createCheckBox(Pane root, TextField username, TextField passoword1, double x, double y, double width, double heigth){

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


        Label labelTerms = createTerms(root, color,70, 243.5);
        Button Login = createButtonLogin(root, "Acess", 50, 350, 225, 55);


        checkBox.setOnAction(event -> {
            if(checkBox.isSelected()){
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

                Login.setOnAction(event1 -> {

                    new Registrer_SQL().verifyUsers();

                    String User = username.getText();
                    String Passoword = passoword1.getText();

                    if(User.trim().isEmpty() || Passoword.trim().isEmpty()) {
                        System.out.println("Falha ao tentar fazer login, parece que você não preencheu todas as informações exigidas");

                    } else{

                        Registrer_SQL registrerSql = new Registrer_SQL(User, Passoword);

                    }




                    if(new Registrer_SQL().searchUser()){
                        Stage stage = new Stage();
                        System.out.println("Login feito com sucesso");

                        Stage stage1 = (Stage) username.getScene().getWindow();
                        stage1.close();

                    } else{
                        System.out.println("Falha no login");
                    }

                });




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

        labelTerms.setStyle("-fx-text-fill: %s".formatted(color));
        root.getChildren().add(checkBox);

        return checkBox;
    }

    private static Label createLabelLogin(Pane root, String txt, double x, double y, double width, double heigth){
        Label label = new Label(txt);

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

    private static Button createRegistrerButton(Pane root, String txt, double x, double y){

        Button button = new Button();
        button.setText(txt);
        button.setFont(new Font("Arial", 13));

        button.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-faint-focus-color: transparent;" +
                "-fx-focus-color: transparent;" +
                "-fx-border-width: 0;" +
                "-fx-text-fill: black;"
        );

        button.setLayoutX(x);
        button.setLayoutY(y);

        button.setOnMouseClicked(event1 -> {
            try {
                Stage stage = new Stage();
                new Account_Registrer().start(stage);
                Stage stage1 = (Stage) button.getScene().getWindow();
                stage1.close();




            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        root.getChildren().add(button);

        return button;
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

    @Override
    public void start(Stage stage){
        Pane root = new Pane();


        Image image = new Image("https://get.pxhere.com/photo/water-nature-waterfall-river-body-of-water-nationalpark-waterfalls-wasserfall-flickrelite-milfordsound-realjourneys-sonydslra300-boatcruisemilfordsound-tamron1750mm-southislandnewzealand-water-feature-atmospheric-phenomenon-172282.jpg");

        createLabelLogin(root, "Login", 50, 50, 210, 35);
        TextField username = createTextField(root, "Username", 50, 130, 210, 35);
        TextField passoword1 = createTextField(root, "Passoword", 50, 190, 210, 35);

        createCheckBox(root, username, passoword1,50, 240, 0.5, 0.5);
        createRegistrerButton(root, "Cadastrar", 120, 325);






        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setY(0);
        imageView.setX(410);

        imageView.setFitHeight(700);





        imageView.setFitWidth(900);

        Scene scene = new Scene(root, 900, 515);

        stage.initStyle(StageStyle.UNDECORATED);


        root.getChildren().add(imageView);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
