package vtpd;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Bartek
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private TableView<PartsPOJO> tableView;
    @FXML private TableColumn<PartsPOJO, Integer> part_id;
    @FXML private TableColumn<PartsPOJO, String> part_name;
    @FXML private TableColumn<PartsPOJO, String> mark_name;
    @FXML private TableColumn<PartsPOJO, String> model_name;
    
    @FXML private ComboBox mark_choice;
    @FXML private ComboBox model_choice;
    
    @FXML private TextField chosen_id;
    @FXML private TextField searching_text;
    
    @FXML private TextField new_part;
    @FXML private TextField new_amount;
    
    @FXML private Label check_amount;
    @FXML private Label yes_no;
    
    
    
    
    public Connection con;
    
    void tableShowRefresh(){
        
        part_id.setCellValueFactory(
            new PropertyValueFactory<PartsPOJO,Integer>("partId"));   
        part_name.setCellValueFactory(
            new PropertyValueFactory<PartsPOJO,String>("partName"));        
        mark_name.setCellValueFactory(                
            new PropertyValueFactory<PartsPOJO,String>("markName"));   
        model_name.setCellValueFactory(                
            new PropertyValueFactory<PartsPOJO,String>("modelName")); 
        DBConnect dbc = new DBConnect();
        try{
            con = dbc.getConnection();
            tableData();
        }
        catch(ClassNotFoundException | SQLException ce){
            
        }
    }
   
    private ObservableList<PartsPOJO> data;

    public void tableData(){        
        data = FXCollections.observableArrayList();
        try{      
            String SQL = "select p.id_p, p.name_p, m.name_mod, mk.name "
                    + "from parts p, model m, mark mk where p.model_id=m.id_mod and p.mark_id=mk.id order by p.id_p";            
            ResultSet rs = con.createStatement().executeQuery(SQL);  
            while(rs.next()){
                PartsPOJO cm = new PartsPOJO();
                cm.partId.set(rs.getInt("p.id_p"));
                cm.partName.set(rs.getString("p.name_p"));                       
                cm.markName.set(rs.getString("mk.name"));
                cm.modelName.set(rs.getString("m.name_mod"));
                data.add(cm);                  
            }
            tableView.setItems(data);
        }
        catch(Exception e){
              
              System.out.println("Error on Building Data");            
        }
    }
    
    
        
    @FXML
    private void checkAmount (ActionEvent actionEvent) throws SQLException{
        
        Image yes = new Image("css/y.png");
        Image no = new Image("css/n.png");
        
        try{
            String checking_id = chosen_id.getText();
            String SQL = "select amount from parts where id_p='"+checking_id+"'";
            ResultSet rs = con.createStatement().executeQuery(SQL); 
            while(rs.next()){
                int amount=rs.getInt("amount");
                if(amount==0){
                    check_amount.setText("Id: ["+checking_id+"] Niedostępny");
                    yes_no.setGraphic(new ImageView(no));
                }
                else{
                    check_amount.setText("Id: ["+checking_id+"] Dostępny w ilości ("+amount+")");
                    yes_no.setGraphic(new ImageView(yes));
                }
            }
            chosen_id.clear();
            
        }
        catch(SQLException e){
              System.out.println("Error");
              throw e;
        } 
    }
    
    
    @FXML
    private void deletePart (ActionEvent actionEvent) throws SQLException{
        
        try{
            String deleting_id = chosen_id.getText();
            String SQL = "delete from parts where id_p='"+deleting_id+"'";
            con.createStatement().executeUpdate(SQL); 
        }
        catch(SQLException e){
              System.out.println("Error");
              throw e;
        }
        tableShowRefresh();
    }
    
    
    @FXML
    private void searchPart (ActionEvent actionEvent) throws SQLException{
        
        DBConnect dbc = new DBConnect();
        try{
            con = dbc.getConnection();
            ObservableList<PartsPOJO> data1 = FXCollections.observableArrayList();
            try{
                String searching = searching_text.getText();
                String SQL = "select distinct p.id_p, p.name_p, m.name_mod, mk.name from parts p, model m, mark mk "
                        + "where p.name_p like '%"+searching+"%' and p.model_id=m.id_mod and p.mark_id=mk.id";            
                ResultSet rs = con.createStatement().executeQuery(SQL);  
                while(rs.next()){
                    PartsPOJO sc = new PartsPOJO();
                    sc.partId.set(rs.getInt("p.id_p"));
                    sc.partName.set(rs.getString("p.name_p"));                       
                    sc.markName.set(rs.getString("mk.name"));
                    sc.modelName.set(rs.getString("m.name_mod"));
                    data1.add(sc);                  
                }
                tableView.setItems(data1);
                }
            catch(Exception e){
              e.printStackTrace();
              System.out.println("Error on Building Data");            
            }   
        }
        catch(ClassNotFoundException | SQLException ce){
            
        }
        searching_text.clear();
    }
    
    
    
    void markChoice() throws SQLException{
       
       try{
            String SQL = "select name from mark";
            ResultSet rs = con.createStatement().executeQuery(SQL); 
            while(rs.next()){
                mark_choice.getItems().addAll(rs.getString("name")); 
            }
            
        }
        catch(SQLException e){
              System.out.println("Error");
              throw e;
        }
    }
    
    
    
    @FXML private void modelChoice(ActionEvent actionEvent) throws SQLException{
        
           model_choice.getItems().clear();
           String selected_mark=(String) mark_choice.getValue();
           
           try{
                String SQL = "select id from mark where name='"+selected_mark+"'";
                ResultSet rs = con.createStatement().executeQuery(SQL); 
                while(rs.next()){
                    Integer selected_id = rs.getInt("id");
                    try{
                    String SQL2 = "select m.name_mod from model m, mark mk where mk.id=m.mark_id and m.mark_id='"+selected_id+"'";
                    ResultSet rs2 = con.createStatement().executeQuery(SQL2); 
                    while(rs2.next()){
                        model_choice.getItems().addAll(rs2.getString("m.name_mod")); 
                    }

                    }
                    catch(SQLException e){
                    System.out.println("Error");
                    throw e;
                    } 
                }

            }
            catch(SQLException e){
                  System.out.println("Error");
                  throw e;
            }

        }
    
    @FXML private void addPart(ActionEvent actionEvent) throws SQLException{
        
        
        String n_part = new_part.getText();
        String n_amount = new_amount.getText();
        String selected_mark=(String) mark_choice.getValue();
        String selected_model=(String) model_choice.getValue();
        
        try{
                String SQL = "select max(p.id_p), mk.id, m.id_mod from mark mk, model m, parts p "
                        + "where mk.name='"+selected_mark+"' and m.name_mod='"+selected_model+"'";
                ResultSet rs = con.createStatement().executeQuery(SQL); 
                while(rs.next()){
                    Integer selected_mark_id = rs.getInt("mk.id");
                    Integer selected_model_id = rs.getInt("m.id_mod");
                    Integer next_id = rs.getInt("max(p.id_p)");
                    next_id+=1;
                    try{
                    String SQL2 = "insert into parts (id_p, amount, name_p, mark_id, model_id) "
                            + "values ('"+next_id+"', '"+n_amount+"', '"+n_part+"', '"+selected_mark_id+"', '"+selected_model_id+"')";
                    con.createStatement().executeUpdate(SQL2); 
            
                    }
                    catch(SQLException e){
                        System.out.println("Error");
                        throw e;
                    }
                }

            }
            catch(SQLException e){
                  System.out.println("Error");
                  throw e;
            }
        new_part.clear();
        new_amount.clear();
        mark_choice.getItems().clear();
        model_choice.getItems().clear();
        markChoice();
        tableShowRefresh();
    }
    
    
    @FXML
    private void showRefresh(ActionEvent event){
        tableShowRefresh();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        tableShowRefresh();
        
        try {
            markChoice();
        } catch (SQLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        UnaryOperator<Change> filter = change -> {
        String text = change.getText();

        if (text.matches("[0-9]*")) return change;

        return null;
        };
        TextFormatter<String> textFormatter = new TextFormatter<>(filter);
        TextFormatter<String> textFormatter2 = new TextFormatter<>(filter);
        chosen_id.setTextFormatter(textFormatter);
        new_amount.setTextFormatter(textFormatter2);
        
    }    
    
}
