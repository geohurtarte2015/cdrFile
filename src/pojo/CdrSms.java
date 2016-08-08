/*
**********************************
Autor: Edgar Giovanni Hurtarte
Empresa: Claro Guatemala
Componente: Pojo CdrSms
Fecha creación: 15/06/2016
Descripcion: Lectura de CDR's para uso del sistema de Merka
**********************************
 */
package pojo;

import java.util.List;


public class CdrSms {

private String idSave;    
private String sequenceNumber;
private String subscriberId;
private String cdrDate;
private String transactionType;
private String billingEventId;
private String fileName;
private String dateReadCdr;
private String destinationNumber;
private String status;
private String dateSendCdr;
private List<Object> aaData;


    //Construction Cdr
    public CdrSms( 
                String sequenceNumber,
                String subscriberId,
                String destinationNumber,
                String cdrDate,      
                String transactionType,               
                String billingEventId,               
                String fileName,
                String dateReadCdr,
                String status,
                String idSave
            )
            {
            this.sequenceNumber=sequenceNumber;
            this.subscriberId=subscriberId;
            this.cdrDate=cdrDate;        
            this.transactionType=transactionType;
            this.billingEventId=billingEventId;
            
            this.fileName=fileName;
            this.dateReadCdr=dateReadCdr;
            this.dateSendCdr=dateSendCdr;
            this.destinationNumber=destinationNumber;
            this.status=status;
            this.idSave=idSave;
            
    }

    public CdrSms(){}    


    public String getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(String sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

  
    public String getSubscriberId() {
        return subscriberId;
    }

   
    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }


    public String getCdrDate() {
        return cdrDate;
    }

  
    public void setCdrDate(String cdrDate) {
        this.cdrDate = cdrDate;
    }

    
    public String getTransactionType() {
        return transactionType;
    }

  
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

   
    public String getBillingEventId() {
        return billingEventId;
    }

  
    public void setBillingEventId(String billingEventId) {
        this.billingEventId = billingEventId;
    }

   
    public String getFileName() {
        return fileName;
    }

  
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    public String getDateReadCdr() {
        return dateReadCdr;
    }

    
    public void setDateReadCdr(String dateReadCdr) {
        this.dateReadCdr = dateReadCdr;
    }

    
    public String getDestinationNumber() {
        return destinationNumber;
    }

    
    public void setDestinationNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    
    public String getStatus() {
        return status;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

   
    public String getIdSave() {
        return idSave;
    }


    public void setIdSave(String idSave) {
        this.idSave = idSave;
    }

    
    public String getDateSendCdr() {
        return dateSendCdr;
    }

  
    public void setDateSendCdr(String dateSendCdr) {
        this.dateSendCdr = dateSendCdr;
    }

    /**
     * @return the aaData
     */
    public List<Object> getAaData() {
        return aaData;
    }

    /**
     * @param aaData the aaData to set
     */
    public void setAaData(List<Object> aaData) {
        this.aaData = aaData;
    }
   


}
