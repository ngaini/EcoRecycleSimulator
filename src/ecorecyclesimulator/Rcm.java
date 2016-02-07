/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ecorecyclesimulator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Nathan
 */
public class Rcm {
    int price=0;
    int rcmID;
    String rcmName;
    String rcmLocation;
    Boolean active;
    ArrayList<Integer> rcmItemList;
    float rcmTotalWeightCapacity;
    float rcmTotalAmount;
    float rcmCurrentWeightVal;
    float rcmCurrentAmountVal;
    double remaining;
    
    
    //variables for database
    private Connection conn;
    private Statement stmt;
    private ResultSet resultSet;
    private PreparedStatement pst;
    private DbConnect rcmDbconn;
    /**
     * getter for rcmID
     * @return 
     */
    public int getRcmID()
    {
        return rcmID;
    }
    
    /**
     * setter for rcmID
     * @param newRcmID 
     */
    public void setRcmID(int newRcmID)
    {
        rcmID = newRcmID;
    }
    
    /**
     * setter for rcmName
     * @param newRcmName 
     */
    public void setRcmName(String newRcmName)
    {
        rcmName = newRcmName;
    }
    
    /**
     * getter for rcmName
     * @return 
     */
    public String getRcmName()
    {
        return rcmName;
    }
    
    /**
     * setter for rcmLocation
     * @param newLocation 
     */
    public void setRcmLocation(String newLocation)
    {
        rcmLocation = newLocation;
    }
    
    /**
     * getter for rcmLocation
     * @return 
     */
    public String getRcmLocation()
    {
        return rcmLocation;
    }
    
    /**
     * setter for active
     * @param newActive 
     */
    public void setActive(Boolean newActive)
    {
        active = newActive;
    }
    
    /**
     * getter for active
     * @return 
     */
    public Boolean getActive()
    {
        return active;
    }
    
    /**
     * setter for rcmItemList
     * @param newRcmItemList 
     */
    public void setRcmItemList(ArrayList<Integer> newRcmItemList)
    {
        rcmItemList = newRcmItemList;
    }
    
    
    /**
     * getter for rcmItemList
     * @return 
     */
    public ArrayList<Integer> getRcmItemList()
    {
        return rcmItemList;
    }
    
    /**
     * setter for rcmTotalWeightCapacity
     * @param newRcmTotalWeightCapacity 
     */
    public void setRcmTotalWeightCapacity(int newRcmTotalWeightCapacity)
    {
        rcmTotalWeightCapacity= newRcmTotalWeightCapacity;
    }
    
    /**
     * getter for rcmTotalWeightCapacity
     * @return 
     */
    public int getRcmTotalWeightCapacity(String name) throws SQLException
    {
        rcmDbconn = new DbConnect();
        String query = "Select * from rcm WHERE name='"+name+"'";
        int weight = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
        System.out.println(query);
   
        while(resultSet.next())
        {
                 System.out.println("here");
            weight=resultSet.getInt("total_capacity");
            
        }
        System.out.println("cap: "+weight);
        return weight;
//        return rcmTotalWeightCapacity;
    }
    
    /**
     * setter for rcmTotalAmount
     * @param newRcmTotalAmount 
     */
    public void setRcmTotalAmount(int newRcmTotalAmount)
    {
        rcmTotalAmount = newRcmTotalAmount;
    }
    
    /**
     * getter for rcmTotalAmount
     * @return 
     */
    public int getRcmTotalAmount(String name) throws SQLException
    {
        rcmDbconn = new DbConnect();
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
//        return rcmTotalAmount;
    }
    
    /**
     * getter for rcmCurrentWeightVal
     * @return 
     */
    public int getRcmRemainingWeightVal(String name) throws SQLException
    {
        rcmDbconn = new DbConnect();
        
        String query = "Select * from rcm WHERE name='"+name+"'";
        int rem_weight = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            rem_weight= resultSet.getInt("remaining_capacity");
        }
        return rem_weight;
    }
    
    /**
     * setter for rcmCurrentWeightVal
     * @param newRcmCurrentWeightVal 
     */
    public void setRcmRemainingWeightVal(int newRcmCurrentWeightVal)
    {
        rcmCurrentWeightVal = newRcmCurrentWeightVal;
    }
    
    /**
     * setter for remCurrentAmountVal
     * @param newRemCurrentAmountVal 
     */
    public void setRcmRemainingAmountVal(int newRemCurrentAmountVal)
    {
        
        rcmCurrentAmountVal = newRemCurrentAmountVal;
    }
    
    /**
     * getter for remCurrentAmountVal
     * @return 
     */
    public int getRcmRemainingAmountVal(String name) throws SQLException
    {
         rcmDbconn = new DbConnect();
        
        String query = "Select * from rcm WHERE name='"+name+"'";
        int rem_amount = 0;
        stmt = conn.createStatement();
        resultSet = stmt.executeQuery(query);
   
        while(resultSet.next())
        {
            rem_amount= resultSet.getInt("remaining_amount");
        }
        return rem_amount;
//        return rcmCurrentAmountVal;
    }
    
    /**
     * calculates the remaining of either amount or w
     * @param total
     * @param current 
     */
    public void calculateRemaining(float total , float current)
    {
        remaining = total - current;
    }
    
    private void setPrice(int newPrice)
    {
        int price = newPrice;
    }
    
    private int getPrice()
    {
        return price;
    }
    /**
     * getter for the remaining value
     * @return 
     */
    public double getRemaining()
    {
        return remaining;
    }
    
    /**
     * setter for remaining
     * @param newRemining 
     */
    public void setRemaining(double newRemining)
    {
        remaining = newRemining;
    }
}
