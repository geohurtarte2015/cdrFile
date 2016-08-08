package controlador;



import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;
import modelo.ConexionBD;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import pojo.Cdr;
import pojo.CdrJson;
import pojo.CdrSms;
import pojo.EstructureObjectJson;
import pojo.FileCdr;


public class ReadCDR {
    
    public static String ANSI_RED = "\u001B[31m";
    
    //private final ResourceBundle rb = ResourceBundle.getBundle("properties.configuration"); 
    //private final String path = rb.getString("data");
  
    public static void main(String[] args) throws IOException {
        ConexionBD bd = new ConexionBD();
   
        ReadCDR readCdr = new ReadCDR(); 
        
        readCdr.validateNumber("57856308");
 
    }
    
    
    //Obtiene todos los Cdr's del lote de archivos "no leidos" en
    //la carpeta y filtra los que no tienen saldo para mensajitos
    public ArrayList<CdrSms> getAllCdrs(ArrayList<FileCdr> arrayFileCdr) throws IOException{   
        ConexionBD saveCdrBd = new ConexionBD();
        ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");     
        String path = rb.getString("path");
        String data = rb.getString("data");
        String pathLogs = rb.getString("pathLogs");
        String activeLogs = rb.getString("logs");
        String split = rb.getString("split");
        String head = rb.getString("head");
        String txtlogs=""; 
        
        
        ArrayList<CdrSms> arrayCdr = new ArrayList<CdrSms>();
        
        
           
        ReadCDR readCdr = new ReadCDR();
       
        //Fecha de Hoy //Date Today
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  //Formato 06/05/2014 16:00:22
        Calendar calendar = Calendar.getInstance();
        String dateTimeToday = dateFormat.format(calendar.getTime());

        String files;

        for (FileCdr fileCdr : arrayFileCdr) {
            
                    files = fileCdr.getNameFile();
             
                    List<String> lines;
                    String wiki_path = path + files;
                    
                    Charset charset = Charset.forName("ISO-8859-1");//Formato ISO para reconcimiento de caracteres Latinos  
                   
                    lines = Files.readLines(new File(wiki_path), charset);
                    
                    Integer expectionLine=Integer.parseInt(head);
                    int sizeLine = (lines.size()-expectionLine);
                    String arrayLogs[]=new String[sizeLine];
                    int index = 0;
                    int indexLog=0;
                    for (String line : lines) {
                     
                        CdrSms cdr = new CdrSms();
                        
                        if (index >= expectionLine) {
                            int x = 0;
                            String readSplit[] = line.split(split);
                            int size = readSplit.length;
                            
                            String[] fieldCdr = new String[size];
                         
                            for (String val : readSplit) { //Filtra espacios en blanco o nulos
                                if (val.equals(" ") || val.equals("")) {
                                    fieldCdr[x] = null;
                                } else {
                                    fieldCdr[x] = val;
                                }
                                x++;
                            }
                      
                            
                            String arrayDestinationsNumbers = fieldCdr[50];
                            String destinationNumber = arrayDestinationsNumbers;//Obtiene el numero destino para envio del sms filtrado
                            
                            String txtMsgDestinationNumber="";
                            String txtMsgTransactionType="";
                            String txtMsgBillingEventId="";
                            
                            
                            if(destinationNumber==null){
                            destinationNumber="";
                            }
                            
                            
                            boolean valDestinationNumber = readCdr.validateNumber(destinationNumber);
                            
                            
                            if(valDestinationNumber){txtMsgDestinationNumber="VALID";}
                            else{txtMsgDestinationNumber="INVALID";
                            arrayDestinationsNumbers="INVALID";}
                            
                            boolean nullTransactionType= fieldCdr[19]!=null;
                            if(nullTransactionType){txtMsgTransactionType="VALID";}
                            else{txtMsgTransactionType="INVALID";
                            fieldCdr[19]="INVALID";}
                            
                            boolean nullBillingEventId = fieldCdr[7]!=null;
                            if(nullBillingEventId){txtMsgBillingEventId="VALID";}
                            else{txtMsgBillingEventId="INVALID";
                            fieldCdr[7]="INVALID";}
                            
                            String status="INVALID";
                            if (fieldCdr[19] != null && fieldCdr[7] != null) {
                                
                                boolean valTransactionType = (fieldCdr[19].equals("700"));
                                if (valTransactionType) {
                                    txtMsgTransactionType = "VALID";
                                } else {
                                    txtMsgTransactionType = "NOT 700";
                                }  
                                
                                boolean valBillingEventId = (fieldCdr[7].equals("358"));
                                if (valBillingEventId) {
                                    txtMsgBillingEventId = "VALID";
                                } else {
                                    txtMsgBillingEventId = "NOT 358";
                                }    
                                
                                if (valTransactionType && valBillingEventId && valDestinationNumber) {
                                    status = "READ";
                                    
                                    
                                    cdr.setStatus(status);
                                    
                                }
                            }
                            
                            //creacion de objeto
                            cdr.setSequenceNumber(fieldCdr[0]);
                            cdr.setSubscriberId(fieldCdr[1]);
                            cdr.setCdrDate(fieldCdr[2]);
                            cdr.setTransactionType(fieldCdr[7]);
                            cdr.setBillingEventId(fieldCdr[19]);
                            cdr.setFileName(files);
                            cdr.setDateReadCdr(dateTimeToday);
                            cdr.setDestinationNumber(destinationNumber);
                            cdr.setStatus(status);
                         
                            
                            if(cdr.getStatus().equals("READ")){
                            
                             //Guardar ID
                             int id =Integer.parseInt(fieldCdr[0]);
                         
                             
                             cdr.setIdSave(String.valueOf(id));
                             
                             arrayCdr.add(cdr);
                           
                             
                            }
                            
                            if(fieldCdr[0].equals("24596")){
                            System.out.println("degug");
                            };
                            
                            txtlogs=files+" [Subscriber_Id:"+fieldCdr[1]+"]"+
                                    " [Valid_Destination_Number:"+txtMsgDestinationNumber+"]"+" [Transaction_Type:"+txtMsgTransactionType+"]"+
                                    " [Billing_Event_Id:"+txtMsgBillingEventId+"]"+"[STATUS:"+status+"]";
                            
                            if(activeLogs.equals("TRUE")){
                                arrayLogs[indexLog]=txtlogs;
                                indexLog++;
                            }
                            
                            System.out.println(txtlogs);
                            
                        }
                        index++;
                        
                        
                    }
                    if(activeLogs.equals("TRUE")){
                        this.createFileLogs(pathLogs, arrayLogs, indexLog-1);
                    }
                    System.out.println("Procesado archivo CDR: " + files);

              
        }
    
    System.out.println(ANSI_RED + "Lote de CDR's de SMS sin Saldo procesados");

        for (CdrSms temp : arrayCdr) {
            System.out.println("SEGUENCE_NUMBER:" + temp.getSequenceNumber()
                    + " SUBSCRIBER_ID:" + temp.getSubscriberId()
                    + " DESTINATION_NUMBER:" + temp.getDestinationNumber()                  
                    + " FILE_NAME:" + temp.getFileName()
                    + " DATE_READ_CDR:" + temp.getDateReadCdr()
            );
        }
     
    return arrayCdr;
    }        
    
        
    public void createFileLogs(String pathLogs,String arrayLogs[],int sizeArrayLogs){
      
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(pathLogs,true);
            pw = new PrintWriter(fichero);
            
            
            for(String write : arrayLogs){
                pw.println(write);
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {     
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

    }  
        
    
    public void createData(ArrayList<CdrSms> cdrArray){
        ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");  
        String data = rb.getString("data");        
        
        FormatDate formatDate = new FormatDate();
        
        String date =  formatDate.getFormat(formatDate.getDateToday());
        
        String pathDate=data+date+".txt";
        
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
            fichero = new FileWriter(pathDate,true);
            pw = new PrintWriter(fichero);
            
            
            for(CdrSms cdr : cdrArray){
                pw.println(cdr.getIdSave()+"|"+cdr.getSequenceNumber()+"|"+cdr.getDateReadCdr()+"|"+cdr.getCdrDate()+"|"+cdr.getDestinationNumber()+"|"+cdr.getSubscriberId()+"|"+cdr.getStatus()+"|"+cdr.getFileName());
            }    
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {     
           if (null != fichero)
              fichero.close();
           } catch (Exception e2) {
              e2.printStackTrace();
           }
        }

    }  
    
    
    //Obtiene los archivos Cdr's nuevos de la carpeta configurada en properties 
    public ArrayList<FileCdr> findFilesCdr(){
        
        ArrayList<FileCdr> arrayFileCdr = new ArrayList<FileCdr>();
        ArrayList<FileCdr> newFileCdr = new ArrayList<FileCdr>();        

        ConexionBD bd = new ConexionBD();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  //Formato 06/05/2014 16:00:22
        Calendar calendar = Calendar.getInstance();
        String dateTimeToday = dateFormat.format(calendar.getTime());

        ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");
        String path = rb.getString("path");
        String type = rb.getString("type");
        String files;
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        arrayFileCdr = bd.findFiles();
        if (listOfFiles != null) {
            for (int i = 0; i < listOfFiles.length; i++) {
                boolean validate=false;
                FileCdr fileCdr = new FileCdr();
                if (listOfFiles[i].isFile()) {
                    files = listOfFiles[i].getName();
                    if (files.endsWith(type) || files.endsWith(type.toUpperCase())) {
                        
                        if (arrayFileCdr.isEmpty()) {
                            //bd.insertFile(files, dateTimeToday);
                            
                            
                            fileCdr.setNameFile(files);
                            fileCdr.setDateRead(dateTimeToday);
                            newFileCdr.add(fileCdr);
                        } else {
                            
                            for (FileCdr nameFile : arrayFileCdr) {
                                if (files.equals(nameFile.getNameFile())) {
                                    validate=true;
                                }else{
                                   
                                }
                            }
                            if(validate==false){
                                
                            //bd.insertFile(files, dateTimeToday);                            
                            
                            fileCdr.setNameFile(files);
                            fileCdr.setDateRead(dateTimeToday);
                            newFileCdr.add(fileCdr);
                            
                            }
                        }
                    }
                }

            }
        }
        return newFileCdr;
    }
    
    
    //valida formato del prefijo del numero destino
    public boolean validateNumber(String number){
       
        boolean validate = false;
        int size = number.length();
        if (size >= 8) {
            // Aquí la carpeta que queremos explorar //
            ResourceBundle rb = ResourceBundle.getBundle("properties.configuration");
            String format = rb.getString("format");
            String exception = rb.getString("initialcel");
            
            String formatString[] = format.split("[|]");
            String exceptionString[] = exception.split("[|]");
            
            String prefix = number.substring(0, number.length() - 8);//prefix obtiene el prefijo Ej: +502,00502
            int sizeNumber = prefix.length();
            String newNumber = number.substring(sizeNumber, number.length()); //newNumber obtiene el numero de 8 digitos
            
            
        if(sizeNumber==0){
                String prefixEight = newNumber.substring(0, newNumber.length() - 7);//prefix obtiene el prefijo Ej: +502,00502
                for (String validateString : exceptionString) {
                    if (validateString.trim().equals(prefixEight)) {
                        validate = true;
                    }
                }
        }else{
            
            for (String validateString : formatString) {
                if (validateString.trim().equals(prefix)) {
                    validate = true;
                }
            }
        }
            if (validate == true) {
                //System.out.println("Number destination " + number + " Valid");
                return validate;
            } else {
               // System.out.println("Number destination " + number + " Invalid");
                return validate;
            }

        } else {
           // System.out.println("Number destination " + number + " Invalid");
            return validate;
        }

    } 
    
    
    //obtiene string Json a partir de un arreglo de objetos Cdr
    public String cdrSmsToJson(ArrayList<CdrSms> arrayCdrSms) {
    EstructureObjectJson dataJson = new EstructureObjectJson();
    List<CdrJson> objectCdrs = new ArrayList();
    ConexionBD bd = new ConexionBD();
        
        for (CdrSms cdrSms : arrayCdrSms){
            CdrJson cdrJson = new CdrJson();
            
            DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  //Formato 06/05/2014 16:00:22
            Calendar calendar = Calendar.getInstance();            
            String dateTimeToday = dateFormat.format(calendar.getTime());
            
            cdrJson.setDnA(cdrSms.getSubscriberId());
            cdrJson.setDnB(cdrSms.getDestinationNumber());
            cdrJson.setRequestId(cdrSms.getIdSave());
            cdrJson.setTimeStamp(dateTimeToday);
            objectCdrs.add(cdrJson);
          
        }
            
            dataJson.setInboundSMSMessage(objectCdrs);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(dataJson);
            
            
    
     return json;
    }
    
    
    public  void sendToWebService(String json, String url) {
            
            try {
               
                String       postUrl       = url;// put in your url       
                HttpClient   httpClient    = HttpClientBuilder.create().build();
                HttpPost     post          = new HttpPost(postUrl);
                StringEntity postingString = new StringEntity(json);//gson.tojson() converts your pojo to json
                post.setEntity(postingString);
                post.setHeader("Content-type", "application/json");
                try {
                    HttpResponse  response = httpClient.execute(post);
                    System.out.println(response.getStatusLine().getStatusCode());
                } catch (IOException ex) {
                    System.out.println("Error format Json: "+ ex);
                }
                
                
            } catch (UnsupportedEncodingException ex) {
                 System.out.println("Error read URL: "+ ex);
            }
	
		
	}
    

}
