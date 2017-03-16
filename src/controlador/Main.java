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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import modelo.ConexionBD;
import pojo.Cdr;
import pojo.CdrJson;
import pojo.CdrSms;
import pojo.EstructureObjectJson;
import pojo.FileCdr;



public class Main extends FunctionProperties {

    public static void main(String[] args) throws IOException, ParseException 
            
    {  
        int resp=0;
        boolean switchWs=true;
        
        ConexionBD bd = new ConexionBD();
        
        ReadCDR readCdr = new ReadCDR();  
        ReadCDR readCdr2 = new ReadCDR(); 
        
        String webservicepath = readCdr.getProperties("webservice1");   
        String webservicepath2 = readCdr.getProperties("webservice2");  
        
        //comienza a medir tiempo
        long startTimeRead = System.currentTimeMillis();
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date dateInit =  Calendar.getInstance().getTime();
        String dateInitString = dateFormat.format(dateInit);        
        
        ArrayList<FileCdr> fileCdr = readCdr.findFilesCdr();
        
        ArrayList<CdrSms> arrayCdr = readCdr.getAllCdrs(fileCdr);  
        
        
        //Finaliza tiempo de lectura de CDR's
        //------------------------------------------------
        long endTimeRead   = System.currentTimeMillis();
        long totalTimeRead = endTimeRead - startTimeRead;
        long timeRead = (totalTimeRead/1000);
        double timeRealRead = timeRead/60;
        
        //-------------------------------------------------
        
        //Calculo de paquetes enviados de lotes en tamaño de 10
        int size = arrayCdr.size();        
        int send = size/10; 
        int position = size;
        int res = size % 10;
        if (res!=0){
            send = send + 1;
        }        
        
        long startTimeSend = System.currentTimeMillis();
        int doneWs=0;
        int errorWs=0;
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
                  
         
                 
                 if(switchWs){
                    //Envio al webservice 
                    resp = readCdr.sendToWebService(json,webservicepath,1);
                    switchWs=false;
                 }else{
                    //Envio al webservice 2
                    resp = readCdr2.sendToWebService(json,webservicepath2,2);
                    switchWs=true;
                 }
            
                 
                
                 
                 if(resp==201){
                     doneWs++;
                 }else{
                     errorWs++;
                 }
                 

         }
        
                bd.insertDataFile(fileCdr);
                bd.insertDataCdr(arrayCdr);
        
                System.out.println("Finalizado registro a base de datos, de archivos leidos");
                
                //Finaliza timepo de lectura de CDR's
                long endTimeSend   = System.currentTimeMillis();
                long totalTimeSend = endTimeSend - startTimeSend;
                long timeSend = (totalTimeSend/1000);
                
                long totalTime = endTimeSend - startTimeRead;
                long time = (totalTime/1000);
                
                double timeRealSend = timeSend/60;
                Date dateFinish =  Calendar.getInstance().getTime();
                String dateFinishString = dateFormat.format(dateFinish);
                
                
                
                ConexionBD conexion = new ConexionBD();
                
                
                conexion.insertTime(dateInitString, dateFinishString, timeSend, timeRead, time,errorWs,doneWs);
                
                System.out.println("Tiempo de envio a webservice "+String.valueOf(timeSend)+" seconds");
    }
    

    
}
