package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class CheckMainModel extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('NumberService')
    def numSvc
    
    def checktypes = ['LandBank', 'LandBank-NTP',
                      'DBP-NEW', 'DBP-NEW-NTP',
                      'DBP-OLD', 'DBP-OLD-NTP', 
                      'DBPyellow', 'DBPyellow-NTP',
                      'PostalBank', 'PostalBank-NTP'];
  
   
    public void afterCreate(){
        entity.checkdate = dtSvc.getBasicServerDate();
        
        
    }
    
    public void beforeSave(o){
        entity.checkamtwords = numSvc.doubleToWords(entity.checkamt).toUpperCase() + " PESOS ONLY";
        entity.state = "DRAFT"
    }

    /* ========== Lookup Payee ========= */
    def getLookupPayee(){
       return Inv.lookupOpener('checkpayee:lookup',[
               onselect :{
                   entity.payee = it.payeename;
                   binding.refresh(); 
               },
           ])
   }
   
     /* ========== Lookup Account ========= */
    def getLookupAccount(){
       return Inv.lookupOpener('checkaccount:lookup',[
               onselect :{
                   entity.checkaccount = it.accountname;
                   binding.refresh(); 
               },
           ])
   }
   
     /* ========== Lookup Offices ========= */
     def getLookupOffices(){       
        return Inv.lookupOpener('hrisorg:lookup',[
                onselect :{   
                    entity.officeorigin = it.Entity.Name;
                                                            
                            
                    //binding.refresh('entity.officeorigin.*')
                    //binding.refresh('entity.paidby.*')
                },
                
                onempty: {
                    //
                }
        ])
    }
    
}