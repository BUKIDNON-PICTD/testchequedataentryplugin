package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class DepositModel extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('NumberService')
    def numSvc
    
    @Service('testcheckService')
    def checkService
    
    @Service("ListService")
    def service;
    
    boolean editAllowed = false;
  
   
    public void afterCreate(){
        entity.txndate = dtSvc.getBasicServerDate();
     
    }
    
    public void beforeSave(o){
        entity.txnno = '';
        entity.state = "DRAFT"
    }
   
     /* ========== Lookup Account ========= */
    def getLookupAccount(){
       return Inv.lookupOpener('checkaccount:lookup',[
               onselect :{
                   entity.checkaccount = it.accountname;
                   entity.checkaccountid = it.objid;
                   binding.refresh(); 
               },
           ])
   }
        
}