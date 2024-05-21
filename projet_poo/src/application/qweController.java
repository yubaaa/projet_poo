
package application;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author WINDOWS 10
 */
public class qweController implements Initializable {


   
	 @FXML
	    private Button backButton;
	 
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button close;

    @FXML
    private Button minimize;

    @FXML
    private Label username;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button addMed_btn;

    @FXML
    private Button pruchase_btn;

    @FXML
    private Button logout;

    @FXML
    private AnchorPane addMedicines_form;

    @FXML
    private TextField addMedicines_medicineID;

    @FXML
    private TextField addMedicines_brand;

    @FXML
    private TextField addMedicines_productName;

    @FXML
    private ComboBox<?> addMedicines_type;

    @FXML
    private ComboBox<?> addMedicines_status;

    @FXML
    private TextField addMedicines_price;

    @FXML
    private ImageView addMedicines_imageView;

    @FXML
    private Button addMedicines_importBtn;

    @FXML
    private Button addMedicines_addBtn;

    @FXML
    private Button addMedicines_updateBtn;

    @FXML
    private Button addMedicines_clearBtn;

    @FXML
    private Button addMedicines_deleteBtn;

    @FXML
    private TextField addMedicines_search;

    @FXML
    private TableView<medicineData> addMedicines_tableView;
    
    @FXML
    private TableColumn<medicineData, String> addMedicines_col_medicineID;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_brand;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_productName;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_type;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_price;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_status;

    @FXML
    private TableColumn<medicineData, String> addMedicines_col_date;



//    DATABASE TOOLS
    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;
    
    private Image image;
    
    
    
   
  
    
    
    
  
    public void addMedicinesAdd(){
        
        String sql = "INSERT INTO medicine (medicine_id, brand, productName, type, status, price, image, date_added) "
                + "VALUES(?,?,?,?,?,?,?,?)";
        
        connect = database.connectDb();
        
        try{
            
            Alert alert;
            
            if(addMedicines_medicineID.getText().isEmpty()
                    || addMedicines_brand.getText().isEmpty()
                    || addMedicines_productName.getText().isEmpty()
                    || addMedicines_type.getSelectionModel().getSelectedItem() == null
                    || addMedicines_status.getSelectionModel().getSelectedItem() == null
                    || addMedicines_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                // CHECK IF THE MEDICINE ID YOU WANT TO INSERT EXIST
                String checkData = "SELECT medicine_id FROM medicine WHERE medicine_id = '"
                        +addMedicines_medicineID.getText()+"'";
                
                statement = connect.createStatement();
                result = statement.executeQuery(checkData);
                
                if(result.next()){
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Medicine ID: " + addMedicines_medicineID.getText() + " was already exist!");
                    alert.showAndWait();
                }else{
                    prepare = connect.prepareStatement(sql);
                    prepare.setString(1, addMedicines_medicineID.getText());
                    prepare.setString(2, addMedicines_brand.getText());
                    prepare.setString(3, addMedicines_productName.getText());
                    prepare.setString(4, (String)addMedicines_type.getSelectionModel().getSelectedItem());
                    prepare.setString(5, (String)addMedicines_status.getSelectionModel().getSelectedItem());
                    prepare.setString(6, addMedicines_price.getText());

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");

                    prepare.setString(7, uri);

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());

                    prepare.setString(8, String.valueOf(sqlDate));
                    
                    prepare.executeUpdate();
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Added!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                    
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineUpdate(){
        
        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");
        
        String sql = "UPDATE medicine SET brand = '"
                +addMedicines_brand.getText()+"', productName = '"
                +addMedicines_productName.getText()+"', type = '"
                +addMedicines_type.getSelectionModel().getSelectedItem()+"', status = '"
                +addMedicines_status.getSelectionModel().getSelectedItem()+"', price = '"
                +addMedicines_price.getText()+"', image = '"+uri+"' WHERE medicine_id = '"
                +addMedicines_medicineID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(addMedicines_medicineID.getText().isEmpty()
                    || addMedicines_brand.getText().isEmpty()
                    || addMedicines_productName.getText().isEmpty()
                    || addMedicines_type.getSelectionModel().getSelectedItem() == null
                    || addMedicines_status.getSelectionModel().getSelectedItem() == null
                    || addMedicines_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Medicine ID:" + addMedicines_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Updated!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineDelete(){
        
        String sql = "DELETE FROM medicine WHERE medicine_id = '"+addMedicines_medicineID.getText()+"'";
        
        connect = database.connectDb();
        
        try{
            Alert alert;
            
            if(addMedicines_medicineID.getText().isEmpty()
                    || addMedicines_brand.getText().isEmpty()
                    || addMedicines_productName.getText().isEmpty()
                    || addMedicines_type.getSelectionModel().getSelectedItem() == null
                    || addMedicines_status.getSelectionModel().getSelectedItem() == null
                    || addMedicines_price.getText().isEmpty()
                    || getData.path == null || getData.path == ""){
                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to DELETE Medicine ID:" + addMedicines_medicineID.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();
                
                if(option.get().equals(ButtonType.OK)){
                    statement = connect.createStatement();
                    statement.executeUpdate(sql);
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Deleted!");
                    alert.showAndWait();
                    
                    addMedicineShowListData();
                    addMedicineReset();
                }
            }
        }catch(Exception e){e.printStackTrace();}
    }
    
    public void addMedicineReset(){
        addMedicines_medicineID.setText("");
        addMedicines_brand.setText("");
        addMedicines_productName.setText("");
        addMedicines_price.setText("");
        addMedicines_type.getSelectionModel().clearSelection();
        addMedicines_status.getSelectionModel().clearSelection();
        
        addMedicines_imageView.setImage(null);
        
        getData.path = "";
        
    }
    
    private String[] addMedicineListT = {"Hydrocodone", "Antibiotics", "Metformin", "Losartan", "Albuterol"};
    public void addMedicineListType(){
        List<String> listT = new ArrayList<>();
        
        for(String data: addMedicineListT){
            listT.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listT);
        addMedicines_type.setItems(listData);
        
    }
    
    
    
    private String[] addMedicineStatus = {"Available", "Not Available"};
    public void addMedicineListStatus(){
        List<String> listS = new ArrayList<>();
        
        for(String data: addMedicineStatus){
            listS.add(data);
        }
        
        ObservableList listData = FXCollections.observableArrayList(listS);
        addMedicines_status.setItems(listData);
    }
    
    public void addMedicineImportImage(){
        
        FileChooser open = new FileChooser();
        open.setTitle("Import Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image File", "*jpg", "*png"));
        
        File file = open.showOpenDialog(main_form.getScene().getWindow());
        
        if(file != null){
            image = new Image(file.toURI().toString(), 118, 147, false, true);
            
            addMedicines_imageView.setImage(image);
            
            getData.path = file.getAbsolutePath();
        }
        
    }
    
    public ObservableList<medicineData> addMedicinesListData(){
        
        String sql = "SELECT * FROM medicine";
        
        ObservableList<medicineData> listData = FXCollections.observableArrayList();
        
        connect = database.connectDb();
        
        try{
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();
            
            medicineData medData;
            
            while(result.next()){
                medData = new medicineData(result.getInt("medicine_id"), result.getString("brand")
                        , result.getString("productName"), result.getString("type")
                        , result.getString("status"), result.getDouble("price")
                        , result.getString("image"), result.getDate("date"));
                
                listData.add(medData);
            }
            
        }catch(Exception e){e.printStackTrace();}
        return listData;
    }
    
    private ObservableList<medicineData> addMedicineList;
    public void addMedicineShowListData(){
        addMedicineList = addMedicinesListData();
        
        addMedicines_col_medicineID.setCellValueFactory(new PropertyValueFactory<>("medicineId"));
        addMedicines_col_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        addMedicines_col_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        addMedicines_col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        addMedicines_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        addMedicines_col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        addMedicines_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        
        addMedicines_tableView.setItems(addMedicineList);
        
    }
    
    public void addMedicineSearch(){
        
        FilteredList<medicineData> filter = new FilteredList<>(addMedicineList, e-> true);
        
        addMedicines_search.textProperty().addListener((Observable, oldValue, newValue) ->{
            
            filter.setPredicate(predicateMedicineData ->{
                
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                
                String searchKey = newValue.toLowerCase();
                
                if(predicateMedicineData.getMedicineId().toString().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getBrand().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getProductName().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getType().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getStatus().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getPrice().toString().contains(searchKey)){
                    return true;
                }else if(predicateMedicineData.getDate().toString().contains(searchKey)){
                    return true;
                }else return false;
            });
        });
        
        SortedList<medicineData> sortList = new SortedList<>(filter);
        
        sortList.comparatorProperty().bind(addMedicines_tableView.comparatorProperty());
        addMedicines_tableView.setItems(sortList);
        
    }
    
    public void addMedicineSelect(){
        medicineData medData = addMedicines_tableView.getSelectionModel().getSelectedItem();
        int num = addMedicines_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < - 1){return;}
        
        addMedicines_medicineID.setText(String.valueOf(medData.getMedicineId()));
        addMedicines_brand.setText(medData.getBrand());
        addMedicines_productName.setText(medData.getProductName());
        addMedicines_price.setText(String.valueOf(medData.getPrice()));
        
        String uri = "file:" + medData.getImage();
        
        image = new Image(uri, 118, 147, false, true);
        addMedicines_imageView.setImage(image);
        
        getData.path = medData.getImage();
        
    }
    
    private double totalP;
    private double totalPriceD;
    
    private double balance;
    private double amount;
    
    private SpinnerValueFactory<Integer> spinner;
    
    public void defaultNav(){
        dashboard_btn.setStyle("-fx-background-color:linear-gradient(to bottom right, #41b170, #8a418c);");
    }
    
   
    
    public void displayUsername(){
    //    String user = getData.username;
        String user = "pffffff";

                        // BIG LETTER THE FIRST LETTER THEN THE REST ARE SMALL LETTER
        username.setText(user.substring(0, 1).toUpperCase() + user.substring(1));
        
    }
    
    private double x = 0;
    private double y = 0;

    public void logout() {

        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {
                // HIDE THE DASHBOARD FORM
                logout.getScene().getWindow().hide();
                // LINK YOUR LOGIN FORM
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);

                root.setOnMousePressed((MouseEvent event) -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged((MouseEvent event) -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased((MouseEvent event) -> {
                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);

                stage.setScene(scene);
                stage.show();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void close() {
        System.exit(0);
    }
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    
    public void back() {
    	   
    	try {
    	backButton.getScene().getWindow().hide();
    	  // LINK YOUR DASHBOARD FORM
        Parent root = FXMLLoader.load(getClass().getResource("menuAya.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        
        root.setOnMousePressed((MouseEvent event) -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged((MouseEvent event) -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

        });
        
        stage.initStyle(StageStyle.TRANSPARENT);
        
        stage.setScene(scene);
        stage.show();
        
    
     }catch(Exception e){e.printStackTrace();}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
        
    }

}