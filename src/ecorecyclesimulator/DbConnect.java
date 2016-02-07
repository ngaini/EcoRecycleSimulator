/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecorecyclesimulator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nathan
 */
public class DbConnect {
    private Connection conn;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pst;
//    ExpenseTracker exp = new ExpenseTracker();
    private String [][] values ;
    public static int count =0;

    ArrayList<Rcm> rcmStat = new ArrayList<Rcm>();
    ArrayList<String> strVal = new ArrayList<String>();
    private Rcm rcmObj;


//    private DbConnect dbconn;
/**
 * Constructor for DbConnect Class
 */
public DbConnect()
{
//    JOptionPane.showMessageDialog(null, "before try");
//    ExpenseTracker e
    try
    {
        // to load the driver
        Class.forName("com.mysql.jdbc.Driver");

        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecodb","root","");
//        JOptionPane.showMessageDialog(null, "Connection Established");
//        return conn;
    }
    catch(Exception ex)
            {
                System.out.println("Error :"+ex);
                JOptionPane.showMessageDialog(null, ex);
//                System.err.print("ClassNotFoundException: ");
//                System.err.println(ex.getMessage());

//                return null;
            }
//    finally
//    {
//        try
//        {
//            pst.close();
//            resultSet.close();
//            conn.close();
//        } catch (Exception e)
//        {
//            JOptionPane.showMessageDialog(null, e);
//        }
//    }
}

/**
 * fetch the item id of the recyclable item type
 * @param itemType
 * @return
 * @throws SQLException
 */
int getItemTypeID(String itemType) throws SQLException
{
    int id=0;
    String query = "SELECT item_id FROM items WHERE type='"+itemType+"'";
    stmt = conn.createStatement();
//    System.out.println(query);
    resultSet = stmt.executeQuery(query);

    while(resultSet.next())
    {
        id= (int)resultSet.getDouble("item_id");
    }
    return id;
}
public ArrayList<String> fetchItemIds() throws SQLException{
     ArrayList<String> getId = new ArrayList<String>();
           String query = "Select * from items;";
//     

        pst = conn.prepareStatement(query);
        
        resultSet = pst.executeQuery();
        while(resultSet.next()){
            getId.add(resultSet.getString("type")+" ");
        }
        return getId;
     
     
     
            
    }
 

//}

public String noOFTransactions() throws SQLException{
    String total_Count="" ;
    String sql = "select count(1) as count from transaction";
    pst = conn.prepareStatement(sql);
    resultSet = pst.executeQuery();
    while(resultSet.next()){
        total_Count = resultSet.getString("count");
    }
    return total_Count;
}
public String getRcm_name(String id) throws SQLException
 {
     String s =id;String a="";
     //getting names of RCM
        String sql = "Select name from rcm where rcm_id=?";
        pst= conn.prepareStatement(sql);
        pst.setString(1, s);
        resultSet=pst.executeQuery();
        while(resultSet.next()){
           a = resultSet.getString("name");
        }
     
     return a ;
     
 }
 public Object[] getDetails() throws SQLException{
     String query = "SELECT rcm_id, count(1) as count from transaction group by rcm_id";
        //        select count(1) as totalCount from Transaction
        Statement st = conn.createStatement();

        // execute the query, and get a java resultset
        resultSet = st.executeQuery(query);
        Object[] my_obj = new Object[10];
        ArrayList<String> idList = new ArrayList<String>();
        ArrayList<String> countList = new ArrayList<String>();
        while(resultSet.next()){
            idList.add(resultSet.getString("rcm_id"));
            countList.add(resultSet.getString("count"));
        }
        ArrayList<String> rcm_name =  new ArrayList<String>();
        
        
  
        
        
        for (int i = 0; i < idList.size(); i++) {
		String j = idList.get(i);
                rcm_name.add(getRcm_name(j));
                System.out.println("Names : " + j  );       
                
		}
              
        my_obj[0] = rcm_name;
        my_obj[1] = countList;
        return my_obj;
 }
/** 
 * fetches the item name and price from items table
 * @param itemType
 * @return
 * @throws SQLException 
 */
    String getItemTypeIdFromRcmTable(String itemName) throws SQLException
{
//    int id=0;
//    ArrayList<String> id= new ArrayList<String>();
    String query = "SELECT * FROM rcm WHERE name='"+itemName+"'";
    stmt = conn.createStatement();
//    System.out.println(query);

    resultSet = stmt.executeQuery(query);
//    int i=1;
    String temp="";
    while(resultSet.next())
    {   
        
//        for(String temp: id)
//        {
//            System.out.println(" help");
//            temp = resultSet.getString("item_list");
//            System.out.println("temp :"+temp);
//            id.add(temp);
//        }
        temp =  resultSet.getString("item_list");
//        id.add(temp);
////      
    }
//    System.out.println("temp :"+temp);
//    String a1 = temp.replaceAll("//[","");
//    System.out.println("if :"+id);
    

//    System.out.println("after removeing the breces id:"+id);
    return temp;
}

/**
 * method for inserting the RCM into the RCM table in DB
 * @param idVal
 * @param nameVal
 * @param capacityVal
 * @param locationVal
 */
void insertRCM(ArrayList<Integer> itemList,String nameVal,int capacityVal, String locationVal, int amountVal)
{
//             System.out.println("Showing id values from Dbconn ");
//            for(Integer number: idVal)
//            {
//                System.out.println(number);
//            }
//            System.out.println(idVal.size());
//    String query = "insert into expensetrackertable (description, amount, category, datecol) values(?,?,?,?);";
//          pst = conn.prepareStatement(query);
//          pst.setString(1, descriptionValue);
//          pst.setString(2, amountValue);
//          pst.setString(3, cbSelection);
//          pst.setString(4, datevalue);
//          pst.execute();
//          pst.close();
//          conn.close();
     try
     {
        String query = "insert into rcm (name, location, active, item_list,total_capacity, total_amount, remaining_amount,remaining_capacity) values (?,?,?,?,?,?,?,?);";

//         String query= "INSERT INTO rcm (rcm_id, name, location, active, item_list, total_capacity, total_amount) VALUES (NULL, '"+nameVal+"', '"+locationVal+"', '1', '"+11+"', '"+capacityVal+"', '"+amountVal+"');";
     pst = conn.prepareStatement(query);

//        stmt= conn.createStatement();
//        stmt.executeUpdate(query);
         System.out.println(query);
     pst.setString(1,nameVal);
     pst.setString(2,locationVal);
     //Since we are adding the RCM we are going to keep the RCM active by default
     pst.setInt(3, 1);
//     for idVal which is a array list
//    int [] a= new int [3];
//    int i=0;
//     for(Integer id: idVal)
//     {
//         a[i]=id;
         pst.setString(4,itemList.toString());
////         i++;
////     }
     pst.setInt(5,capacityVal);
     pst.setInt(6,amountVal);
      pst.setInt(7,amountVal);
       pst.setInt(8,capacityVal);
      pst.executeUpdate();
//     pst.executeUpdate(query);
//     System.out.println(query);
         System.out.println("name:"+nameVal+" location:"+locationVal+" active:1 item_list:"+11+", cap:"+capacityVal+", amt:"+amountVal);
     }
     catch (Exception e)
     {
         JOptionPane.showMessageDialog(null, e);
     }






}


public void updateRcm(String name, int capacity, String location,boolean active, ArrayList<Integer> itemList, int amountVal) throws SQLException
{
    try
     {
        String query = "update rcm set name = ?, total_capacity = ?, location = ?, active = ?,item_list=?,total_amount=?,remaining_amount=?,remaining_capacity=? where rcm_id = ?";


//         String query= "INSERT INTO rcm (rcm_id, name, location, active, item_list, total_capacity, total_amount) VALUES (NULL, '"+nameVal+"', '"+locationVal+"', '1', '"+11+"', '"+capacityVal+"', '"+amountVal+"');";
        pst = conn.prepareStatement(query);

//        stmt= conn.createStatement();
//        stmt.executeUpdate(query);
//         System.out.println(query);
         pst.setString(1,name);
         int id = getRcmID(name);
         pst.setInt(2,capacity);
         //Since we are adding the RCM we are going to keep the RCM active by default
         pst.setString(3, location);
//     for idVal which is a array list
//    int [] a= new int [3];
//    int i=0;
//     for(Integer id: idVal)
//     {
//         a[i]=id;
         pst.setBoolean(4,active);
////         i++;
////     }
     pst.setString(5,itemList.toString());
     pst.setInt(6,amountVal);
     pst.setInt(7,amountVal);
     pst.setInt(8,capacity);
     pst.setInt(9, id);
      pst.executeUpdate();
//     pst.executeUpdate(query);
//     System.out.println(query);
         System.out.println("name:"+name+" location:"+location+" active:"+active+" item_list:"+itemList.toString()+", cap:"+capacity+", amt:"+amountVal);
     }
     catch (Exception e)
     {
         JOptionPane.showMessageDialog(null, e);
     }
}

public ArrayList<String>  getItemIdsFromRcmTable(String  name) throws SQLException
{
    
     String query = "Select * from rcm where name ='"+name+"'";
    
    ArrayList<String> ids = new ArrayList<String>();
    PreparedStatement pst = conn.prepareStatement(query);
   // pst.setString(1, name);
     System.out.println(query);
    //stmt = conn.createStatement();
    resultSet = pst.executeQuery();
    String i;
    while(resultSet.next())
    {   System.out.println("Here");
         i = resultSet.getString("item_list");
        ids.add(i);
        System.out.println(i);
        
    }
        System.out.println(ids);
    return ids;
}
public int getRcmID(String name) throws SQLException
{
    String query = "Select * from rcm where name='"+name+"'";
    int id =0;

    stmt = conn.createStatement();
    resultSet = stmt.executeQuery(query);
    while(resultSet.next())
    {
        id =resultSet.getInt("rcm_id");
    }
        System.out.println(id);
    return id;

}


/**
 * fetches the value of the item type selected
 * @param itemName
 * @return
 * @throws SQLException
 */
public float getItemPrice(String itemName) throws SQLException
{
//    System.out.println(itemName+ "WE are here now");
    String query = "Select * from items where type='"+itemName+"';";
    float price =0;
    System.out.println(query);
    stmt = conn.createStatement();
    resultSet = stmt.executeQuery(query);
    while(resultSet.next())
    {
        price =resultSet.getInt("price_per_weight");
//        System.out.println("what can we get "+resultSet.getInt("price_per_weight"));
    }
//        System.out.println(price);
    System.out.println("Before Return "+price);
    return price;
}
/**
 * Update the price value of a particular RCM
 * @param itemType
 * @param pricePerWt
 * @throws SQLException
 */
public void updateItemType(String itemType, int pricePerWt) throws SQLException
{
    String query = "UPDATE items SET price_per_weight = ? WHERE type = ?;";
   pst = conn.prepareStatement(query);

    // set the preparedstatement parameters
    pst.setInt(1,pricePerWt);
    pst.setString(2,itemType);

    pst.executeUpdate();
//    System.out.println("Boo "+itemType+" "+pricePerWt );
//    pst.execute();
//    pst.close();
//    conn.close();
}
/**
 * Insert new recyclable item type
 * @param itemType
 * @param pricePerWt
 */
public void insertItemType(String itemType, int pricePerWt)
{
    try
    {

    String query = "Insert into items (type, price_per_weight) values (?,?);";
    pst = conn.prepareStatement(query);
//     pst = conn.prepareStatement(query);
     System.out.println(query);
     pst.setString(1,itemType);
     pst.setInt(2,pricePerWt);
     pst.executeUpdate();
//     System.out.println(query+ " "+ itemType+" "+pricePerWt);
    }
    catch (Exception e)
    {
        JOptionPane.showMessageDialog(null, e);
    }
}

// for trial of combobox

//Method to fetch the available RCM names from the Db to populate the ComboBox
/**
 * Fetch the RCM names that are connected to the RMOS
 * @return ArrayList
 * @throws SQLException
 */
ArrayList<String> nameValueForFillCombo() throws  SQLException
{
//    ArrayList<String> strVal = new ArrayList<String>(5);
    String query = "Select * from rcm where active=1";
    int i=0;
    stmt = conn.createStatement();
    resultSet = stmt.executeQuery(query);
    while(resultSet.next())
    {
        String name= resultSet.getString("name");
        strVal.add(name);

    }
    return strVal;

}

/**
 * To fill the type values in combo box
 * @return
 * @throws SQLException
 */
ArrayList<String> typeValueForFillCombo() throws  SQLException
{
//    ArrayList<String> strVal = new ArrayList<String>(5);
    String query = "Select * from items";
    int i=0;
    stmt = conn.createStatement();
    resultSet = stmt.executeQuery(query);
    while(resultSet.next())
    {
        String type= resultSet.getString("type");
        strVal.add(type);
        System.out.println(type);

    }
    return strVal;

}

String fetchItemTypeAndName(String id) throws SQLException
{
    String typeNamePriceString = "";
    String query = "Select * from items where item_id="+id+" ";
    
    stmt = conn.createStatement();
    resultSet = stmt.executeQuery(query);

    while(resultSet.next())
    {

        String name= resultSet.getString("type");
        String price =resultSet.getString("price_per_weight");
        typeNamePriceString = name+" ( $"+price+" per lb )";


    }
    return typeNamePriceString;
    
    
}
    

    int fetchRcmIdfromRcmTable(String rcmName) throws SQLException
    {
        
        String query = "Select * from rcm WHERE name='"+rcmName+"'";
        
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        int rcmId = 0;
        System.out.println("");
        while(resultSet.next()) 
        {
            rcmId= resultSet.getInt("rcm_id");
        }
        System.out.println("Dbconn"+rcmId);
        return rcmId;

    }
    
    void insertIntoTransactionTable(int rcmId, int itemId,int weight, int price) throws SQLException 
    {
        Date date = new Date();
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateVal= dateFormatter.format(date);
        String query = "Insert into transaction (rcm_id,item_id,weight,price,date) values (?,?,?,?,?);";
        pst = conn.prepareStatement(query);
//     pst = conn.prepareStatement(query);
        System.out.println(query);
        pst.setInt(1,rcmId);
        pst.setInt(2,itemId);
        pst.setInt(3,weight);
        pst.setInt(4,price);
        pst.setString(5,dateVal);
        pst.executeUpdate();
        
    }
    
    Boolean adminValidation(String name, String password) throws SQLException
    {
         String query = "Select * from user WHERE user_name='"+name+"' AND password='"+password+"'";
        
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        Boolean valid=false;
        while (resultSet.next())
        {
            String userName = resultSet.getString("user_name");
            String pwd = resultSet.getString("password");
            System.out.println(" name: "+userName+" password:"+password );
            valid = true;
        }
        
        return valid;
    }
    
    int findTotalWeight(String name) throws SQLException
    {
        String query = "Select * from rcm WHERE name='"+name+"'";
        int weight = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
                 System.out.println("here");
            weight=resultSet.getInt("total_capacity");
            
        }
//        System.out.println("cap: "+weight);
        return weight;
    }
    int findTotalAmount(String name) throws SQLException
    {
        String query = "Select * from rcm WHERE name='"+name+"'";
        int amount = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
//            System.out.println("here");
            amount=resultSet.getInt("total_amount");
            
        }
//        System.out.println("cap: "+weight);
        return amount;
    }
    
    int getRcmRemainingWeightVal(String name) throws SQLException
    {
//        rcmDbconn = new DbConnect();
        
        String query = "Select * from rcm WHERE name='"+name+"'";
        int rem_weight = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            rem_weight= resultSet.getInt("remaining_capacity");
            System.out.println("Rem cap:"+rem_weight);
        }
        return rem_weight;
    }
     int getRcmRemainingAmountVal(String name) throws SQLException
    {
//        rcmDbconn = new DbConnect();
        
        String query = "Select * from rcm WHERE name='"+name+"'";
        int rem_amount = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            rem_amount= resultSet.getInt("remaining_amount");
            System.out.println("Rem cap:"+rem_amount);
        }
        return rem_amount;
    }
    
    Boolean activationInfo(String name) throws SQLException
    {
        String query = "Select * from rcm WHERE name='"+name+"'";
        Boolean active=null ;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            active= resultSet.getBoolean("active");
            System.out.println("Rem cap:"+active);
        }
        return active;
        
    }
    String findLocation(String name) throws SQLException
    {
        String query = "Select * from rcm WHERE name='"+name+"'";
        String location="";
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            location= resultSet.getString("location");
            System.out.println("Rem cap:"+location);
        }
        return location;
    }
    
    void setRemainingCapacity(int remCapacity,String rcmName) throws SQLException
    {
        String query = "update rcm set remaining_capacity=? where name = ?";
         pst = conn.prepareStatement(query);
//         pst.setInt(1,remAmount);
         pst.setInt(1, remCapacity);
         pst.setString(2, rcmName);
        
         pst.executeUpdate();
    }
    
    void setRemainingAmount(int remAmount, String rcmName) throws SQLException
    {
       String query = "update rcm set remaining_amount=? where name = ?";
        pst = conn.prepareStatement(query);
       pst.setInt(1, remAmount);
       pst.setString(2, rcmName);
       pst.executeUpdate();
    }
    
    void emptyCapacityAction(String name) throws SQLException
    {
        String query = "update rcm set remaining_capacity=? where name = ?";
        pst = conn.prepareStatement(query);
        int total_weight =findTotalWeight(name);
       pst.setInt(1, total_weight);
       pst.setString(2, name);
       pst.executeUpdate();
    }
    
    void refillAmountAction(String name) throws SQLException
    {
        String query = "update rcm set remaining_amount=? where name = ?";
        pst = conn.prepareStatement(query);
        int total_amount =findTotalAmount(name);
       pst.setInt(1, total_amount);
       pst.setString(2, name);
       pst.executeUpdate();
    }
    
    void deleteRcmAction(String name) throws SQLException
    {
        String query = "delete from rcm where name =?";
        pst = conn.prepareStatement(query);
        pst.setString(1,name);
        pst.execute();
        int id=findRcm(name);
        String query1 = "delete from transaction where rcm_id =?";
        pst = conn.prepareStatement(query1);
        pst.setInt(1,id);
        pst.execute();
    }
    int findRcm(String name) throws SQLException
    {
         String query = "Select * from rcm WHERE name='"+name+"'";
        int rcmId=0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            rcmId= resultSet.getInt("rcm_id");
            System.out.println("Rem cap:"+rcmId);
        }
        return rcmId;
    }
    
    void setLastServiceDate(int id,String date) throws SQLException
    {   
        String query = "Select * from service WHERE rcm_id="+id+"";
        Boolean idPresent = false;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        int idValue=0;
        System.out.println(" uber");
        while(resultSet.next())
        {
           idValue= resultSet.getInt("rcm_id");
            System.out.println(idValue+": id value");
//            rcmId= resultSet.getInt("rcm_id");
//            System.out.println("Rem cap:"+rcmId);
//            idPresent = true;
            System.out.println(" while stmt");
        }
        if(idValue==id)
        {
            String query1 = "update service set last_service_date=? where id = ?";
            pst = conn.prepareStatement(query1);
//            int total_amount =findTotalAmount(name);
             pst.setString(1,date);
            pst.setInt(2,id);
        
           pst.executeUpdate();
           System.out.println(" if");
        }
        else
        {
            String query2 = "Insert into service (rcm_id, last_service_date) values (?,?);";
            pst = conn.prepareStatement(query2);
    //     pst = conn.prepareStatement(query);
            System.out.println(query2);
            pst.setInt(1,id);
            pst.setString(2,date);
            pst.executeUpdate();
            System.out.println(" else stmt");
        }
        
        
    }
    
    String getServiceDate(int id) throws SQLException
    {
        String query = "Select * from service WHERE rcm_id="+id+"";
//        Boolean idPresent = false;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        String date="";
        System.out.println(" uber");
        while(resultSet.next())
        {
            date = resultSet.getString("last_service_date");
        }
        return date;
        
    }
    
}


