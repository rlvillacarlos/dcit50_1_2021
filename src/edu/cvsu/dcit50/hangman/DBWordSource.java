package edu.cvsu.dcit50.hangman;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author rlvillacarlos
 */
public class DBWordSource extends WordSource{
    private final Random rnd = new Random(System.nanoTime());
    private final Connection conn;
    private final String table; 
    
    public DBWordSource(String connection, String table) throws SQLException, ClassNotFoundException {
        this.conn = DriverManager.getConnection(connection);
        this.table = table;
    }

    @Override
    public void close() {
        super.close();
        
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBWordSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @Override
    public List<String> getTopics() {
        List<String> topics  = new ArrayList<>();
        
        String query = "SELECT DISTINCT topic FROM " + this.table;
        
        try {
            Statement stmt = conn.createStatement();
            ResultSet res = stmt.executeQuery(query);
            
            while(res.next()){
                topics.add(res.getString("topic"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBWordSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return topics;
    }
    
    
    
    @Override
    public Word getRandomWord() {
        String queryCount = "SELECT count(id) FROM " + table;
        
        try {
            ResultSet res;

            if(!this.topicFilter.equals("")){
                queryCount = queryCount + " WHERE topic = ?";
                PreparedStatement pstmt = conn.prepareStatement(queryCount);
                pstmt.setString(1, this.topicFilter);
                res = pstmt.executeQuery();
            }else{
                Statement stmt;
                stmt = conn.createStatement();
                res = stmt.executeQuery(queryCount);
            }

            if(res.next()){
                int resultCount = res.getInt(1);
                
                String query = "SELECT * FROM " + table;
                
                if (!this.topicFilter.equals("")) {
                    query = query + " WHERE topic = ?";
                    PreparedStatement pstmt = conn.prepareStatement(query);
                    pstmt.setString(1, this.topicFilter);
                    res = pstmt.executeQuery();
                } else {
                    Statement stmt;
                    stmt = conn.createStatement();
                    res = stmt.executeQuery(query);
                }
                
                int rndRow = rnd.nextInt(resultCount);
                
                while(res.next() && res.getRow() != rndRow);
                
                if(!res.isAfterLast()){
                    return new Word(res.getString("topic"), res.getString("value"));
                }
                
            }


        } catch (SQLException ex) {
            Logger.getLogger(DBWordSource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
