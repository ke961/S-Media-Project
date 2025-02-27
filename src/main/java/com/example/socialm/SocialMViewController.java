package com.example.socialm;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

public class SocialMViewController {
    @FXML
    private TextField userNamePromtText;
    @FXML
    private PasswordField passwordPromtText;
    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField userNameText;
    @FXML
    private PasswordField confirmPasswordText;
    @FXML
    private CheckBox termsAndCondition;



    @FXML
    private TextField firstNameText;
    @FXML
    private TextField lastNameText1;


    boolean hasUpdateusername = false;  /// to suggest first name on user text field

    ArrayList<User>users; /// To save in database
    User current_logedin_User = null; /// To assign Name
    User Selected_Profile_User = null;

    @FXML
    private Label nameLable;
    @FXML
    private Label userNameLable;
    @FXML
    private Tab dashboardTab;
    @FXML
    private Button submissionButton;
    @FXML
    private TabPane mainTab;
    @FXML
    private Tab registrationTab;
    @FXML
    private Tab logInTab;
    @FXML
    private Tab profileTab;
    @FXML
    private Button addButton;
    @FXML
    private Tab postsTab;
    @FXML
    private Label profileUserNameLable;
    @FXML
    private ComboBox<String> usersComboBox;
    @FXML
    private Label profileNameLable;
    @FXML
    private Button removeButton;


    @FXML
    public void initialize() {
       users= new ArrayList<User>();
       dashboardTab.setDisable(true);/// to disable a tab
       submissionButton.setDisable(true); /// to disable submission button when the checkbox is not clicked
        profileTab.setDisable(true);
        postsTab.setDisable(true);


        users.add(new User("Aabila","abila1","Abila","khan","abila1",new ArrayList<>()));
        users.add(new User("Aminhaz","minu13","Minhaz","Uddin","minu13",new ArrayList<>()));
        users.add(new User("Atashrif","tashrif1","Tashrif","Mahmud","tashrif1" ,new ArrayList<>()));
        users.add(new User("Asazzad","sojib1","Sazzad","Hossain","sojib1" ,new ArrayList<>()));
        users.add(new User("Arafi","rafi1","Redwan","Rafi","rafi1" ,new ArrayList<>()));




    }

    @FXML
    public void logInBTOnAction(ActionEvent actionEvent) {
        String userName = userNamePromtText.getText();

        String[] Alphabets={"A","B","C","D","E","F"};
        Alert alert = new Alert(Alert.AlertType.ERROR);
        boolean hasAlphabet= false;
        for(String alphabet: Alphabets){
            if(userName . contains(alphabet)){
                hasAlphabet=true;
                break;
            }
        }
        if(!hasAlphabet){

            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username ");
            alert.setContentText("Please enter a valid username and your username must contain a Alphabet ");
            alert.showAndWait();
            return;
        }
        System.out.println(hasAlphabet);


        String password = passwordPromtText.getText();
        String[] num={"1","2","3","4","5","6","7","8","9"};
        boolean hasNum=false;
        for(String num1 : num ){
            if(password.contains(num1)){
                hasNum=true;
                break;
            }
        }

        System.out.println(hasNum);

        if(!hasNum){

            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please enter a valid password and your password must contain a numeric ");
            alert.showAndWait();
            return;
        }

        User Creat_user= null; ///Find user( any registered user is there or not)
        for(User user: users){
            if(user.getUsername().equals(userName)||user.getPassword().equals(password)){
                Creat_user=user;
                break;
            }
        }
        if (Creat_user == null) { ///if  not then this line will execute
            alert.setTitle("Error");
            alert.setHeaderText("Username or Password  is Incorrect");
            alert.showAndWait();
            return;
        }



        userNamePromtText.clear();
        passwordPromtText.clear();
//        alert.setAlertType(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Login Successfully");
//        alert.setHeaderText("Welcome,"+Creat_user.getUsername());
//        alert.showAndWait();

        dashboardTab.setDisable(false);
        profileTab.setDisable(false);
        postsTab.setDisable(false);
        current_logedin_User=Creat_user; ///To assign name
        nameLable.setText(current_logedin_User.getFirstname()+" "+current_logedin_User.getLastname());/// to change the name lable which is  N/A by-default
        userNameLable.setText(current_logedin_User.getUsername()); /// to change the name lable which is  N/A by-default


        usersComboBox.getItems().clear();/// to show users except the Current logedin user

        for(User user: users){ /// This loop is for ,if the users are not equal to the current logedin user then they will be added in the combobox except the current logedin user.
            if(!user.getUsername().equals(current_logedin_User.getUsername())){
                String key= user.getFirstname()+" "+user.getLastname()+"--"+user.getUsername();
                usersComboBox.getItems().add(key);
            }
        }


        registrationTab.setDisable(true);///to disable after logout
        logInTab.setDisable(true);///to disable after logout
        SingleSelectionModel<Tab> singleSelectionModel = mainTab.getSelectionModel();///to switch one tab to another
        singleSelectionModel.select(dashboardTab);



    }

    @FXML
    public void userNameOnKeyTyped(Event event) {
        System.out.println("userNameOnKeyTyped");
        String userName = userNamePromtText.getText();
        int userNameLength = userName.length();
        if(userNameLength < 4) {
            userNamePromtText.setStyle("-fx-border-color: red");
        }
        else {
            userNamePromtText.setStyle("-fx-border-color: green");
        }

    }

    @FXML
    public void passwordOnKeyTyped(Event event) {
        System.out.println("passwordOnKeyTyped");
        String password = passwordPromtText.getText();
        int passwordLength = password.length();
        if(passwordLength < 6) {
            passwordPromtText.setStyle("-fx-border-color: red");
        }
        else {
            passwordPromtText.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    public void submitBTOnAction(ActionEvent actionEvent) {
        System.out.println("submitBTOnAction");
        String firstName = firstNameText.getText();
        String lastName = lastNameText1.getText();
        String userName = userNameText.getText();


        Alert alert = new Alert(Alert.AlertType.ERROR);


        String[] Alphabets={"A","B","C","D","E","F"};
        boolean hasAlphabet=false;
        for(String alphabet: Alphabets){
            if(userName . contains(alphabet)){
                hasAlphabet=true;

                break;
            }
        }
        if(!hasAlphabet){

            alert.setTitle("Error");
            alert.setHeaderText("Invalid Username ");
            alert.setContentText("Please enter a valid username and your username must contain a Alphabet ");
            alert.showAndWait();
            return;
        }
        System.out.println(hasAlphabet);


        String password = passwordText.getText();
        String[] num={"1","2","3","4","5","6","7","8","9"};


        boolean hasNum=false;
        for(String num1 : num ) {
            if (password.contains(num1)) {
                hasNum = true;

                break;
            }
        }
        System.out.println(hasNum);

        if (!hasNum){

            alert.setTitle("Error");
            alert.setHeaderText("Incorrect Password");
            alert.setContentText("Please enter a valid password and your password must contain a numeric ");
            alert.showAndWait();
            return;

            }

        String confirmPassword = confirmPasswordText.getText();
        boolean is_validCred=this.validatePasswordAndConfirmPassword(password,confirmPassword);
        if(!is_validCred ){
            alert.setTitle("Error");
            alert.setHeaderText("Your Password and Confirm Password do not match");
            alert.showAndWait();
            return;
        }
        System.out.println("Passwords match! Proceeding...");




        for (User user : users) {    /// this line is to check if there is any same user is there or not
            if (user.getUsername().equals(userName)) {
                alert.setTitle("Error");
                alert.setHeaderText("Invalid Username");
                alert.setContentText(" This Username is Already Exist");
                alert.showAndWait();
                break;
            }
        }

        User new_user= new User(userName,password,firstName,lastName,confirmPassword,new ArrayList<>());/// if there is not then this line will execute
        users.add(new_user);
        String key= new_user.getFirstname()+" "+new_user.getLastname()+"--"+new_user.getUsername();
        usersComboBox.getItems().add(key);








        firstNameText.clear();
        lastNameText1.clear();
        userNameText.clear();
        passwordText.clear();
        confirmPasswordText.clear();

        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Registration Successfully");
        alert.setHeaderText("Welcome ,"  +firstName + " " + lastName);
        alert.showAndWait();


    }

    private boolean validatePasswordAndConfirmPassword(String password, String confirmPassword) {
        if(password==null || confirmPassword==null){
            return false;
        }
        if(!password.equals(confirmPassword)){
            return false;

        }

        return true;
    }

    @FXML
    public void confirmPasswordOnKeyTyped(Event event) {
        System.out.println("confirmPasswordOnKeyTyped");
        String password = confirmPasswordText.getText();
        int confirmPasswordLength = password.length();
        if(confirmPasswordLength < 6) {
            confirmPasswordText.setStyle("-fx-border-color: red");

        }
        else {
            confirmPasswordText.setStyle("-fx-border-color: green");
        }
    }

    @FXML
    public void firstNameOnKeyTyped(Event event) {
        if(hasUpdateusername){
            return; //early return
        }

      String Creat_username = firstNameText.getText()
              .toLowerCase()
              .replace(" ", "");

        userNameText.setText(Creat_username);     ///to suggest first name in username
    }

    @FXML
    public void userNameOnKeyType(Event event) {
        hasUpdateusername=true;   /// to suggest first name on user text field

        System.out.println("userNameOnKeyTyped");
        String userName = userNameText.getText();
        int userName_Length = userName.length();
        if(userName_Length < 4) {
            userNameText.setStyle("-fx-border-color: red");
        }
        else {
            userNameText.setStyle("-fx-border-color: green");
        }

    }


    @FXML
    public void passwordOnKeyType(Event event) {
        System.out.println("passwordOnKeyTyped");
        String password = passwordText.getText();
        int password_Length = password.length();
        if(password_Length < 6) {
            passwordText.setStyle("-fx-border-color: red");
        }
        else {
            passwordText.setStyle("-fx-border-color: green");
        }
    }



    @FXML
    public void termsAndConditionOnMouseClicked(Event event) {

        System.out.println("termsAndConditionOnMouseClicked");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);


        if (termsAndCondition.isSelected()) {
            submissionButton.setDisable(false);
        }

    }


    @FXML
    public void logOutBTOnAction(ActionEvent actionEvent) {
        current_logedin_User= null;
        registrationTab.setDisable(false);
        logInTab.setDisable(false);
        dashboardTab.setDisable(true);
        postsTab.setDisable(true);
        profileTab.setDisable(true);

        SingleSelectionModel<Tab> singleSelectionModel = mainTab.getSelectionModel();///to switch one tab to another
        singleSelectionModel.select(logInTab);

    }



    @FXML
    public void viewProfileBTOnAction(ActionEvent actionEvent) {

      String[] key = usersComboBox.getValue().split("--"); /// To split username
      String username = key[1];/// this because the string count starts for 0 and  this string into an array of two parts (0(name) and 1(username))
      
      for(User user: users){
          if(user.getUsername().equals(username)){ /// to find if there is any user exist which is selected
              Selected_Profile_User = user;
              break;

          }
      }

      if(Selected_Profile_User == null){
          Alert alert = new Alert(Alert.AlertType.ERROR); /// if not found then show error
          alert.setTitle("Error");
          alert.setHeaderText("User not found");
          alert.showAndWait();
          return;

      }

      profileNameLable.setText(Selected_Profile_User.getFirstname()+" "+Selected_Profile_User.getLastname());
      profileUserNameLable.setText(Selected_Profile_User.getUsername());


        ArrayList<User> Creat_user_friend = current_logedin_User.getFriends(); /// to check the selected user is friend or not
        boolean is_selected_user_a_friend = false;
        for (User friend : Creat_user_friend) {
            if (friend.getUsername().equals(Selected_Profile_User.getUsername()) &&
                    friend.getFirstname().equals(Selected_Profile_User.getFirstname()) &&
                    friend.getLastname().equals(Selected_Profile_User.getLastname())) {
                is_selected_user_a_friend = true;
                break;
            }

      }
        if (is_selected_user_a_friend) {
            addButton.setDisable(true);
            removeButton.setDisable(false);
        } else {
            addButton.setDisable(false);
            removeButton.setDisable(true);
        }


        SingleSelectionModel<Tab> singleSelectionModel = mainTab.getSelectionModel();///to switch one tab to another
        singleSelectionModel.select(profileTab);


    }


    @FXML
    public void removeBTOnAction(ActionEvent actionEvent) {
        current_logedin_User.getFriends().remove(Selected_Profile_User);
        Selected_Profile_User.getFriends().remove(current_logedin_User);
        addButton.setDisable(false);
        removeButton.setDisable(true);

    }


    @FXML
    public void addBTOnAction(ActionEvent actionEvent) {
        current_logedin_User.getFriends().add(Selected_Profile_User);///add 1st user
        Selected_Profile_User.getFriends().add(current_logedin_User);///add second user
        addButton.setDisable(true);
        removeButton.setDisable(false);

    }
}
