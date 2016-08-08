
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





public class Main {
    
    

    public static void main(String[] args) throws IOException 
    {   
         ConexionBD bd = new ConexionBD();

        
        ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");     

        ReadCDR readCdr = new ReadCDR();  
        
        ArrayList<FileCdr> fileCdr = readCdr.findFilesCdr();
        
        ArrayList<CdrSms> arrayCdr = readCdr.getAllCdrs(fileCdr);  
        
        String json = readCdr.cdrSmsToJson(arrayCdr); 
                
        System.out.println(json);
        
        System.out.println("Envio de lote exitoso! a Merka, esperando registro a base de datos.....");
        //solo se pueden enviar 10 sms en el lote
        //envio a endpoint Guatemala
        //readCdr.sendToWebService(json, wsdguatemala);
        
        bd.insertDataFile(fileCdr);
        bd.insertDataCdr(arrayCdr);
        
          
          
//        readCdr.createData(arrayCdr);
//        
//        for(FileCdr file : fileCdr){            
//            bd.insertFile(file.getNameFile(), file.getDateRead());            
//        }
        
        System.out.println("Finalizado registro a base de datos, de archivos leidos");
    }
    

    
}
