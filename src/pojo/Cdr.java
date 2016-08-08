/*
**********************************
Autor: Edgar Giovanni Hurtarte
Empresa: Claro Guatemala
Componente: Pojo Cdr 
Fecha creación: 15/06/2016
Descripcion: Lectura de CDR's para uso del sistema de Merka
**********************************
 */
package pojo;


public class Cdr {

    
private String sequenceNumber;
private String subscriberId;
private String cdrDate;
private String cdrSeconds;
private String sourceDate;
private String sourceTime;
private String serviceType;
private String transactionType;
private String correlationId;
private String transactionMajorNumber;
private String transactionMinorNumber;
private String sourceType;
private String sourceInfo1;
private String sourceInfo2;
private String rateName;
private String tariffPlanId;
private String systemTax;
private String locationTax;
private String serviceTax;
private String billingEventId;
private String timeBandGroupId;
private String serviceCategoryId;
private String operatorId;
private String operatorTransactionType;
private String accountNumber;
private String subscriberClass;
private String profileId;
private String optionsArray;
private String serviceFeeDate;
private String suspendedDate;
private String frozenDate; 
private String groupType;
private String groupId;
private String ruleDiscountAmount;
private String ruledIsCountUnits;
private String ruledIsCountName;
private String discountAmount0;
private String discountUnits0;
private String discountAmount1;
private String discountUnits1;
private String discountAmount2;
private String discountUnits2;
private String accountType;
private String accountStatus;
private String balanceType;
private String balancedEfid;
private String balanceAmount;
private String balanceUnits;
private String balanceDelta;
private String reservationCount;
private String combinedField;
private String locationId;
private String imei;
private String fileName;
private String dateReadCdr;
private String destinationNumber;
private String status;

    //Construction Cdr
    public Cdr( 
                String sequenceNumber,
                String subscriberId,
                String destinationNumber,
                String cdrDate,
                String cdrSeconds,
                String sourceDate,
                String sourceTime,
                String serviceType,
                String transactionType,
                String correlationId,
                String transactionMajorNumber,
                String transactionMinorNumber,
                String sourceType,
                String sourceInfo1,
                String sourceInfo2,
                String rateName,
                String tariffPlanId,
                String systemTax,
                String locationTax,
                String serviceTax,
                String billingEventId,
                String timeBandGroupId,
                String serviceCategoryId,
                String operatorId,
                String operatorTransactionType,
                String accountNumber,
                String subscriberClass,
                String profileId,
                String optionsArray,
                String serviceFeeDate,
                String suspendedDate,
                String frozenDate,
                String groupType,
                String groupId,
                String ruleDiscountAmount,
                String ruledIsCountUnits,
                String ruledIsCountName,
                String discountAmount0,
                String discountUnits0,
                String discountAmount1,
                String discountUnits1,
                String discountAmount2,
                String discountUnits2,
                String accountType,
                String accountStatus,
                String balanceType,
                String balancedEfid,
                String balanceAmount,
                String balanceUnits,
                String balanceDelta,
                String reservationCount,
                String combinedField,
                String locationId,
                String imei,
                String fileName,
                String dateReadCdr,
                String status
            )
            {
            this.sequenceNumber=sequenceNumber;
            this.subscriberId=subscriberId;
            this.cdrDate=cdrDate;
            this.cdrSeconds=cdrSeconds;
            this.sourceDate=sourceDate;
            this.sourceTime=sourceTime;
            this.serviceType=serviceType;
            this.transactionType=transactionType;            
            this.correlationId=correlationId;            
            this.transactionMajorNumber=transactionMajorNumber;
            this.transactionMinorNumber=transactionMinorNumber;
            this.sourceType=sourceType;
            this.sourceInfo1=sourceInfo1;
            this.sourceInfo2=sourceInfo2;
            this.rateName=rateName;
            this.tariffPlanId=tariffPlanId;
            this.systemTax=systemTax;
            this.locationTax=locationTax;
            this.serviceTax=serviceTax;
            this.billingEventId=billingEventId;
            this.timeBandGroupId=timeBandGroupId;
            this.serviceCategoryId=serviceCategoryId;
            this.operatorId=operatorId;
            this.operatorTransactionType=operatorTransactionType;
            this.accountNumber=accountNumber;
            this.subscriberClass=subscriberClass;
            this.profileId=profileId;
            this.optionsArray=optionsArray;
            this.serviceFeeDate=serviceFeeDate;
            this.suspendedDate=suspendedDate;
            this.frozenDate=frozenDate;
            this.groupType=groupType;
            this.groupId=groupType;
            this.ruleDiscountAmount=ruleDiscountAmount;
            this.ruledIsCountUnits=ruledIsCountUnits;
            this.ruledIsCountName=ruledIsCountName;
            this.discountAmount0=discountAmount0;
            this.discountUnits0=discountUnits0;
            this.discountAmount1=discountAmount1;
            this.discountUnits1=discountUnits1;
            this.discountAmount2=discountAmount2;
            this.discountUnits2=discountUnits2;
            this.accountType=accountType;
            this.accountStatus=accountStatus;
            this.balanceType=balanceType;
            this.balancedEfid=balancedEfid;
            this.balanceAmount=balanceAmount;
            this.balanceUnits=balanceUnits;
            this.balanceDelta=balanceDelta;
            this.reservationCount=reservationCount;
            this.combinedField=combinedField;
            this.locationId=locationId;
            this.imei=imei;
            this.fileName=fileName;
            this.dateReadCdr=dateReadCdr;
            this.destinationNumber=destinationNumber;
            this.status=status;
            
    }

    public Cdr(){}
    
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

  
    public String getCdrSeconds() {
        return cdrSeconds;
    }

  
    public void setCdrSeconds(String cdrSeconds) {
        this.cdrSeconds = cdrSeconds;
    }

    
    public String getSourceDate() {
        return sourceDate;
    }

    
    public void setSourceDate(String sourceDate) {
        this.sourceDate = sourceDate;
    }

 
    public String getSourceTime() {
        return sourceTime;
    }

   
    public void setSourceTime(String sourceTime) {
        this.sourceTime = sourceTime;
    }

  
    public String getServiceType() {
        return serviceType;
    }

  
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    
    public String getTransactionType() {
        return transactionType;
    }

  
    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    
    public String getCorrelationId() {
        return correlationId;
    }

    
    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }

    
    public String getTransactionMajorNumber() {
        return transactionMajorNumber;
    }

    
    public void setTransactionMajorNumber(String transactionMajorNumber) {
        this.transactionMajorNumber = transactionMajorNumber;
    }

    
    public String getTransactionMinorNumber() {
        return transactionMinorNumber;
    }

    
    public void setTransactionMinorNumber(String transactionMinorNumber) {
        this.transactionMinorNumber = transactionMinorNumber;
    }

    
    public String getSourceType() {
        return sourceType;
    }

    
    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

   
    public String getSourceInfo1() {
        return sourceInfo1;
    }

    
    public void setSourceInfo1(String sourceInfo1) {
        this.sourceInfo1 = sourceInfo1;
    }

    
    public String getSourceInfo2() {
        return sourceInfo2;
    }

    
    public void setSourceInfo2(String sourceInfo2) {
        this.sourceInfo2 = sourceInfo2;
    }

    
    public String getRateName() {
        return rateName;
    }

   
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

   
    public String getTariffPlanId() {
        return tariffPlanId;
    }

    
    public void setTariffPlanId(String tariffPlanId) {
        this.tariffPlanId = tariffPlanId;
    }

   
    public String getSystemTax() {
        return systemTax;
    }

   
    public void setSystemTax(String systemTax) {
        this.systemTax = systemTax;
    }

    
    public String getLocationTax() {
        return locationTax;
    }

    
    public void setLocationTax(String locationTax) {
        this.locationTax = locationTax;
    }

    
    public String getServiceTax() {
        return serviceTax;
    }

   
    public void setServiceTax(String serviceTax) {
        this.serviceTax = serviceTax;
    }

    
    public String getBillingEventId() {
        return billingEventId;
    }

    
    public void setBillingEventId(String billingEventId) {
        this.billingEventId = billingEventId;
    }

    
    public String getTimeBandGroupId() {
        return timeBandGroupId;
    }

   
    public void setTimeBandGroupId(String timeBandGroupId) {
        this.timeBandGroupId = timeBandGroupId;
    }

    
    public String getServiceCategoryId() {
        return serviceCategoryId;
    }

    
    public void setServiceCategoryId(String serviceCategoryId) {
        this.serviceCategoryId = serviceCategoryId;
    }

    
    public String getOperatorId() {
        return operatorId;
    }

    
    public void setOperatorId(String operatorId) {
        this.operatorId = operatorId;
    }

    
    public String getOperatorTransactionType() {
        return operatorTransactionType;
    }

    
    public void setOperatorTransactionType(String operatorTransactionType) {
        this.operatorTransactionType = operatorTransactionType;
    }

    
    public String getAccountNumber() {
        return accountNumber;
    }

    
    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

   
    public String getSubscriberClass() {
        return subscriberClass;
    }

    
    public void setSubscriberClass(String subscriberClass) {
        this.subscriberClass = subscriberClass;
    }

    
    public String getProfileId() {
        return profileId;
    }

    
    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    
    public String getOptionsArray() {
        return optionsArray;
    }

    
    public void setOptionsArray(String optionsArray) {
        this.optionsArray = optionsArray;
    }

   
    public String getServiceFeeDate() {
        return serviceFeeDate;
    }

    
    public void setServiceFeeDate(String serviceFeeDate) {
        this.serviceFeeDate = serviceFeeDate;
    }

   
    public String getSuspendedDate() {
        return suspendedDate;
    }

   
    public void setSuspendedDate(String suspendedDate) {
        this.suspendedDate = suspendedDate;
    }

   
    public String getFrozenDate() {
        return frozenDate;
    }

   
    public void setFrozenDate(String frozenDate) {
        this.frozenDate = frozenDate;
    }

    
    public String getGroupType() {
        return groupType;
    }

    
    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    
    public String getGroupId() {
        return groupId;
    }

    
    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    
    public String getRuleDiscountAmount() {
        return ruleDiscountAmount;
    }

    
    public void setRuleDiscountAmount(String ruleDiscountAmount) {
        this.ruleDiscountAmount = ruleDiscountAmount;
    }

   
    public String getRuledIsCountUnits() {
        return ruledIsCountUnits;
    }

    
    public void setRuledIsCountUnits(String ruledIsCountUnits) {
        this.ruledIsCountUnits = ruledIsCountUnits;
    }

    
    public String getRuledIsCountName() {
        return ruledIsCountName;
    }

    
    public void setRuledIsCountName(String ruledIsCountName) {
        this.ruledIsCountName = ruledIsCountName;
    }

    
    public String getDiscountAmount0() {
        return discountAmount0;
    }

   
    public void setDiscountAmount0(String discountAmount0) {
        this.discountAmount0 = discountAmount0;
    }

    
    public String getDiscountUnits0() {
        return discountUnits0;
    }

    
    public void setDiscountUnits0(String discountUnits0) {
        this.discountUnits0 = discountUnits0;
    }

   
    public String getDiscountAmount1() {
        return discountAmount1;
    }

    
    public void setDiscountAmount1(String discountAmount1) {
        this.discountAmount1 = discountAmount1;
    }

    
    public String getDiscountUnits1() {
        return discountUnits1;
    }

    
    public void setDiscountUnits1(String discountUnits1) {
        this.discountUnits1 = discountUnits1;
    }

    
    public String getDiscountAmount2() {
        return discountAmount2;
    }

    
    public void setDiscountAmount2(String discountAmount2) {
        this.discountAmount2 = discountAmount2;
    }

   
    public String getDiscountUnits2() {
        return discountUnits2;
    }

    
    public void setDiscountUnits2(String discountUnits2) {
        this.discountUnits2 = discountUnits2;
    }

    
    public String getAccountType() {
        return accountType;
    }

   
    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    
    public String getAccountStatus() {
        return accountStatus;
    }

    
    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    
    public String getBalanceType() {
        return balanceType;
    }

   
    public void setBalanceType(String balanceType) {
        this.balanceType = balanceType;
    }

    
    public String getBalancedEfid() {
        return balancedEfid;
    }

   
    public void setBalancedEfid(String balancedEfid) {
        this.balancedEfid = balancedEfid;
    }

   
    public String getBalanceAmount() {
        return balanceAmount;
    }

   
    public void setBalanceAmount(String balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    
    public String getBalanceUnits() {
        return balanceUnits;
    }

   
    public void setBalanceUnits(String balanceUnits) {
        this.balanceUnits = balanceUnits;
    }

    
    public String getBalanceDelta() {
        return balanceDelta;
    }

   
    public void setBalanceDelta(String balanceDelta) {
        this.balanceDelta = balanceDelta;
    }

    
    public String getReservationCount() {
        return reservationCount;
    }

    
    public void setReservationCount(String reservationCount) {
        this.reservationCount = reservationCount;
    }

    
    public String getCombinedField() {
        return combinedField;
    }

   
    public void setCombinedField(String combinedField) {
        this.combinedField = combinedField;
    }

    
    public String getLocationId() {
        return locationId;
    }

    
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    
    public String getImei() {
        return imei;
    }

    
    public void setImei(String imei) {
        this.imei = imei;
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


}
