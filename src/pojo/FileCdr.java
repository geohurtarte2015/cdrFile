/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;


public class FileCdr {

    private String id;
    private String nameFile;
    private String dateRead;

    
    public FileCdr(){
    
    }
    
    public String getId() {
        return id;
    }
   
  
    public String getNameFile() {
        return nameFile;
    }
  
    public String getDateRead() {
        return dateRead;
    }

 
    public void setId(String id) {
        this.id = id;
    }


    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    
    public void setDateRead(String dateRead) {
        this.dateRead = dateRead;
    }
    
}
