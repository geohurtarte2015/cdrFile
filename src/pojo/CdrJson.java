
package pojo;


public class CdrJson {
  
    private String requestId;
    private String dnA; 
    private String dnB; 
    private String ServiceType; 
    private String TimeStamp;
    


    public String getRequestId() {
        return requestId;
    }


    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

   
    public String getDnA() {
        return dnA;
    }

   
    public void setDnA(String dnA) {
        this.dnA = dnA;
    }

  
    public String getDnB() {
        return dnB;
    }

   
    public void setDnB(String dnB) {
        this.dnB = dnB;
    }


    public String getServiceType() {
        return ServiceType;
    }

   
    public void setServiceType(String ServiceType) {
        this.ServiceType = ServiceType;
    }

   
    public String getTimeStamp() {
        return TimeStamp;
    }

   
    public void setTimeStamp(String TimeStamp) {        
        this.TimeStamp = TimeStamp;
    }

}
