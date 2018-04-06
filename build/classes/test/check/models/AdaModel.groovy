package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class AdaModel extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('NumberService')
    def numSvc
    
    @Service('testcheckService')
    def checkService
    
    @Service("ListService")
    def service;
    
    boolean editAllowed = false;
    
    boolean isAllowApprove() {
         return ( entity.state.toString().matches('DRAFT|CLOSED') ); 
    }
  
   
    public void afterCreate(){
        entity.txndate = dtSvc.getBasicServerDate();
     
    }
    
    public void beforeSave(o){
        entity.adaamtwords = numSvc.doubleToWords(entity.adaamt).toUpperCase() + " PESOS ONLY";
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
    
    void cancel() { 
        if ( MsgBox.confirm('You are about to cancel this check. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'checkmain', 
               objid : entity.objid, 
               state : 'CANCELLED' 
            ]); 
            loadData(); 
        }
    }
    
   
    
}