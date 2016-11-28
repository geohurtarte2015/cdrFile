package controlador;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static java.lang.System.err;
import static java.lang.System.out;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import modelo.ConexionBD;
import pojo.Cdr;
import pojo.CdrJson;
import pojo.CdrSms;
import pojo.EstructureObjectJson;
import pojo.FileCdr;



public class Main extends FunctionProperties {
    
    

    public static void main(String[] args) throws IOException 
            
    {  
        ConexionBD bd = new ConexionBD();
        
        ReadCDR readCdr = new ReadCDR();  
        
        String webservicepath = readCdr.getProperties("wsdguatemala");   
        
        ArrayList<FileCdr> fileCdr = readCdr.findFilesCdr();
        
        ArrayList<CdrSms> arrayCdr = readCdr.getAllCdrs(fileCdr);  
        
        
        //Calculo de paquetes enviados de lotes en tamaño de 10
        int size = arrayCdr.size();        
        int send = size/10; 
        int position = size;
        int res = size % 10;
        if (res!=0){
            send = send + 1;
        }        
        
        
         for (int i = 0; i <= send; i++) {
         ArrayList<CdrSms> lotesTen = new ArrayList<CdrSms>();
             for (int j = 0; j <= 9; j++) {                 
                 if((position-1)>=0){
                 lotesTen.add(arrayCdr.get(position-1));                
                 position=position-1;
                 }
             }
             
                  String json = readCdr.cdrSmsToJson(lotesTen);      
                  System.out.println(json);


                  //System.out.println("Envio de lote exitoso! a Merka, esperando registro a base de datos.....");
                  //solo se pueden enviar 10 sms en el lote
                  //envio a endpoint Guatemala
                  readCdr.sendToWebService(json,webservicepath);
                  
                  

         }
        
                bd.insertDataFile(fileCdr);
                bd.insertDataCdr(arrayCdr);
        
                System.out.println("Finalizado registro a base de datos, de archivos leidos");
    }
    

    
}
