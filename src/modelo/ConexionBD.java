package modelo;

import controlador.FunctionProperties;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.ResourceBundle;
import pojo.CdrSms;
import pojo.FileCdr;


public class ConexionBD extends FunctionProperties {

    private String classfor="oracle.jdbc.OracleDriver";
    
    private Connection con=null;
    private PreparedStatement pr=null;
    private Statement ps=null;
    private ResultSet rs=null;
    
    
    CdrSms cdrSms = new CdrSms();
    
   
    public CdrSms findCdr(String id) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
        
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
    
    public CdrSms findCdrSended(String id) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
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
    
    //busca nuevos cdr's 
    public ArrayList<FileCdr> findFiles() throws IOException, ParseException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
      ArrayList<FileCdr> arrayFiles = new ArrayList<FileCdr>();
      

      
     //String sql="select * from TB_FILE where date_read < trunc(SYSDATE)";
      
      String sql="select * from TB_FILE where date_read > trunc(SYSDATE)";
      
        try
        {
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);  
            pr=con.prepareStatement(sql);
            rs=pr.executeQuery(sql);
            
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
      
    public int insertCdr(CdrSms cdrSms) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";    
      
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
      
    public String insertFile(String fileName,String date) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";    
      
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
            pr.setString(3, "SYSDATE");
            
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
    
    public int insertDataFile(ArrayList<FileCdr> fileCdr) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
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
                "VALUES (SEQ_FILE.nextval"+",'"+cdr.getNameFile()+"',"+"SYSDATE"+")";
          
                //System.out.println(sql);
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
    
    public int insertDataCdr(ArrayList<CdrSms> dataCdr) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
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
                    //System.out.println(sql);
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
      
    public String sequence(String sequence) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";    
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
    
    public String inserTimeAltern(Date dateInit,Date dateFinish, long timeSend,long timeRead, long time) throws IOException{
    String host = this.getProperties("host");
    String userdb = this.getProperties("userdb");
    String passdb = this.getProperties("passdb");
    String port = this.getProperties("port");
    String valconnect = this.getProperties("valconnect");
    String connect = this.getProperties("connect");
    String usuario=userdb;
    String clave=passdb;
    String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";    
      
        String sql="INSERT INTO TB_FILE (ID,INIT_DATE,TIME_READ_CDR,TIME_SEND_MERKA,FINISH_TOTAL_TIME)" +
        "VALUES (?,?,?,?)";
        String id="-1";
        try
        {   
            id=this.sequence("SEQ_TIME_MERKA");
            int idVal = Integer.parseInt(id);
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            pr=con.prepareStatement(sql);
            pr.setInt(1, idVal);
            pr.setDate(2, new java.sql.Date(dateInit.getTime()));
            pr.setLong(3, timeRead);
            pr.setLong(4, timeSend);
            pr.setDate(5, new java.sql.Date(dateFinish.getTime()));
            
            
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
 
    public int insertTime (String dateInit,String dateFinish, long timeSend,long timeRead, long time,int errorWs,int doneWs) throws IOException{
        
        String host = this.getProperties("host");
        String userdb = this.getProperties("userdb");
        String passdb = this.getProperties("passdb");
        String port = this.getProperties("port");
        String valconnect = this.getProperties("valconnect");
        String connect = this.getProperties("connect");
        String usuario=userdb;
        String clave=passdb;
        String url="jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST="+host+")(PORT="+port+"))(CONNECT_DATA=("+connect+"="+valconnect+")))";
        String sql=null;
        int id=0;
        try
        {   
            Class.forName(classfor);
            con=DriverManager.getConnection(url, usuario,clave);
            System.out.println("Connected database successfully...");
            
            ps=con.createStatement();
            
                sql="INSERT INTO TB_TIME_MERKA (ID,INIT_DATE,TIME_READ_CDR,TIME_SEND_MERKA,FINISH_DATE,DIFFERENCE_TOTAL,ERROR_WS,DONE_WS) " +
                "VALUES (SEQ_TIME_MERKA.nextval"+","+"TO_DATE('"+dateInit+"', 'dd/MM/yyyy hh24:mi:ss')"+","+timeRead+","+timeSend+","+"TO_DATE('"+dateFinish+"', 'dd/MM/yyyy hh24:mi:ss')"+","+time+","+errorWs+","+doneWs+")";
          
                //System.out.println(sql);
                ps.executeUpdate(sql);
           
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
    
    
    }

