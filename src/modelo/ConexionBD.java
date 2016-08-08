/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import pojo.CdrSms;
import pojo.FileCdr;

/**
 *
 * @author edgar.hurtarte
 */
public class ConexionBD {
    
    
    ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");     
    String host = rb.getString("host");
    String userdb = rb.getString("userdb");
    String passdb = rb.getString("passdb");
    String port = rb.getString("port");
    String valconnect = rb.getString("valconnect");
    String connect = rb.getString("connect");
    
    

    private String classfor="oracle.jdbc.OracleDriver";
    private String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
    private String usuario=userdb;
    private String clave=passdb;
    
    private Connection con=null;
    private PreparedStatement pr=null;
    private Statement ps=null;
    private ResultSet rs=null;
    
    
    CdrSms cdrSms = new CdrSms();
    
   
    public CdrSms findCdr(String id){
        
      try{
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            String sql="SELECT * FROM TB_FILE_CDR_MERKA WHERE ID=?";       
            pr=con.prepareStatement(sql);
            pr.setString(1, id);   
            rs = pr.executeQuery();  
            
            while(rs.next()){ 
               cdrSms.setFileName(rs.getString("NAME_FILE"));
               cdrSms.setCdrDate(rs.getString("CDR_DATE_CREATE"));
               cdrSms.setDateReadCdr(rs.getString("CDR_DATE_PROCESSED"));
               cdrSms.setStatus(rs.getString("STATUS_CDR"));
            }      
    
      }catch(Exception exception){
          
            System.out.println("Problema en la consulta con BD"+" "+exception);
      }
      
      
           
      return cdrSms;
        
    }
    
    public CdrSms findCdrSended(String id){
         String sql="UPDATE TB_CDR_MERKA SET STATUS_CDR='SEND' WHERE ID_FILE=?";
         
    
      try{
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);            
            pr=con.prepareStatement(sql);   
            pr.setString(1,id); 
             
          
            if(pr.executeUpdate()==1){
     
            }else{
                System.out.println("No se pudo modificar");
            }  
            
            
    
      }catch(Exception exception){
          
            System.out.println("Problema en la consulta con BD"+" "+exception);
      }
      
      
           
      return cdrSms;
        
    }
      
    public ArrayList<FileCdr> findFiles(){
      ArrayList<FileCdr> arrayFiles = new ArrayList<FileCdr>();
      
      String sql="SELECT * FROM TB_FILE";
        try
        {
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);  
            pr=con.prepareStatement(sql);   
            rs=pr.executeQuery();
            
            //obtiene valores de columna         
            
       
                while(rs.next()){
                    FileCdr file = new FileCdr();
                    file.setNameFile(rs.getString("NAME_FILE"));
                    arrayFiles.add(file);           
                }
          
        }
        catch (Exception exception){
                        
             exception.printStackTrace();
        }
        finally{
		if(con != null)
		{
			try
			{
                                
				con.close();
                                rs.close();
                                pr.close();
                                
			}
			catch (Exception ignored)
			{
				// ignore
			}
				
		}
	}
          
      return arrayFiles;
      }
      
    public int insertCdr(CdrSms cdrSms){
      
        String sql="INSERT INTO TB_FILE_CDR_MERKA (ID,SEQUENCE_NUMBER,NAME_FILE, CDR_DATE_CREATE, CDR_DATE_PROCESSED,DESTINATION_NUMBER,SUBSCRIBER_ID,STATUS_CDR)" +
        "VALUES (?,?,?,?,?,?,?,?)";
        int id=-1;
        try
        {   
            id=Integer.parseInt(this.sequence("SEQ_FILE_CDR_MERKA"));
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            pr=con.prepareStatement(sql);
            pr.setString(1, String.valueOf(id));
            pr.setString(2, cdrSms.getSequenceNumber());
            pr.setString(3, cdrSms.getFileName());
            pr.setString(4,cdrSms.getCdrDate());            
            pr.setString(5,cdrSms.getDateReadCdr());            
            pr.setString(6,cdrSms.getDestinationNumber()); 
            pr.setString(7,cdrSms.getSubscriberId()); 
            pr.setString(8,cdrSms.getStatus()); 
            
            
            if(pr.executeUpdate()!=1){
              System.out.println("Error al generar consulta , error en parametros en sentencia SQL");
              id=-1;
            } 
        }
        catch (Exception exception){
            System.out.println("Exception : " + exception.getMessage() + "  "+"error en base de datos");
            id=-1;
        }
        finally
	{
		if(con != null)
		{
			try
			{
                                pr.close();
				con.close();
			}
			catch (Exception ignored)
			{
				// ignore
			}
				
		}
	}
        
        
      
          
      return id;
      }
      
    public String insertFile(String fileName,String date){
      
        String sql="INSERT INTO TB_FILE (ID,NAME_FILE,DATE_READ)" +
        "VALUES (?,?,?)";
        String id="-1";
        try
        {   
            id=this.sequence("SEQ_FILE");
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            pr=con.prepareStatement(sql);
            pr.setString(1, id);
            pr.setString(2, fileName);
            pr.setString(3, date);
            
            if(pr.executeUpdate()!=1){
              System.out.println("Error al generar consulta , error en parametros en sentencia SQL");
              id="-1";
            } 
        }
        catch (Exception exception){
            System.out.println("Exception : " + exception.getMessage() + "  "+"error en base de datos");
            id="-1";
        }
        finally
	{
		if(con != null)
		{
			try
			{
                                pr.close();
				con.close();
			}
			catch (Exception ignored)
			{
				// ignore
			}
				
		}
	}
        
        
      
          
      return id;
      }
    
    public int insertDataFile(ArrayList<FileCdr> fileCdr){
        String sql=null;
        int id=0;
        try
        {   
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            System.out.println("Connected database successfully...");
            
            ps=con.createStatement();
            
            for(FileCdr cdr : fileCdr){  
                
                sql="INSERT INTO TB_FILE (ID,NAME_FILE,DATE_READ)" +
                "VALUES (SEQ_FILE.nextval"+",'"+cdr.getNameFile()+"','"+cdr.getDateRead()+"')";
          
                System.out.println(sql);
                ps.executeUpdate(sql);
            }
            id=1;
            
        }
        catch (Exception exception){
            System.out.println("Exception : " + exception.getMessage() + "  "+"error en base de datos");      
        }
        finally
	{
		if(con != null)
		{
			try
			{
                                pr.close();
				con.close();
			}
			catch (Exception ignored)
			{
				// ignore
			}
				
		}
	}
             
          
      return id;
      }
    
    public int insertDataCdr(ArrayList<CdrSms> dataCdr){
        String sql=null;
        int id=0;
        try
        {   
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            System.out.println("Connected database successfully...");
            
            ps=con.createStatement();
            
            for(CdrSms cdr : dataCdr){            
          
               sql="INSERT INTO TB_FILE_CDR_MERKA (ID,SEQUENCE_NUMBER,NAME_FILE, CDR_DATE_CREATE, CDR_DATE_PROCESSED,DESTINATION_NUMBER,SUBSCRIBER_ID,STATUS_CDR)" +
    "values("+"SEQ_FILE_CDR_MERKA.nextval"+
                             
                       ",'"+
                  cdr.getSequenceNumber() +
                                   "','" + 
                       cdr.getFileName() +
                                   "','" + 
                  cdr.getCdrDate() +
                                   "','" + 
                cdr.getDateReadCdr()+
                                   "','" + 
               cdr.getDestinationNumber()+
                                   "','" + 
                    cdr.getSubscriberId()+
                                   "','" + 
                      cdr.getStatus() +
                                     "')";
                    System.out.println(sql);
                ps.executeUpdate(sql);
            }
            id=1;
            
        }
        catch (Exception exception){
            System.out.println("Exception : " + exception.getMessage() + "  "+"error en base de datos");      
        }
        finally
	{
		if(con != null)
		{
			try
			{
                                pr.close();
				con.close();
			}
			catch (Exception ignored)
			{
				// ignore
			}
				
		}
	}
             
          
      return id;
      }
      
    public String sequence(String sequence){
      String seq="";
        try{
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            String sql="SELECT "+sequence+".nextval FROM dual";       
            pr=con.prepareStatement(sql);       
            rs = pr.executeQuery();  
            
            while(rs.next()){ 
                
                seq=rs.getString(1);
            }
    
      }catch(Exception exception){
            
            seq="0";
          
            System.out.println("Problema en la consulta con BD"+" "+exception);
      }
          
      return seq;
      }    
 
    
}
