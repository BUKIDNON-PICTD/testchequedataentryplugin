//package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class EstRunningBalanceController extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('NumberService')
    def numSvc
    
    @Service('TestchequedataentryReportService')
    def checkService
    
     public void afterCreate(){
        entity.createdby_date = dtSvc.getBasicServerDate();
        //entity.state = 'DRAFT';
    }
    
    public void beforeSave(o){
        entity.currentlineno = 1;
        entity.totaldebit = 0.00;
        entity.totalcredit = 0.00;
        entity.endbalance = entity.beginningbalance;
        entity.state = "DRAFT";
    }
    
    /* ========== Lookup Account ========= */
    def getLookupAccount(){
       return Inv.lookupOpener('checkaccount:lookup',[
               onselect :{
                   entity.accountname = it.accountname;
                   entity.accountid = it.objid;
                   binding.refresh(); 
               },
           ])
   }
   
    
}