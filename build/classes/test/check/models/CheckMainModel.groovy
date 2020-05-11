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
    
    @Service('testcheckService')
    def checkService
    
    @Service('TestchequedataentryReportService')
    def checkrptService
    
    @Service("ListService")
    def service;
    
    @Service("EstRunningBalancePostService")
    def rbService;
    
    boolean editAllowed = false;
    
    boolean isAllowApprove() {
         return ( entity.state.toString().matches('DRAFT|CLOSED') ); 
    }
    
    
    def checktypes = ['LandBank', 'LandBank-NTP',
                      'DBP-NEW', 'DBP-NEW-NTP',
                      'DBP-OLD', 'DBP-OLD-NTP', 
                      'DBPyellow', 'DBPyellow-NTP',
                      'PostalBank', 'PostalBank-NTP'];
  
   
    public void afterCreate(){
        entity.checkdate = dtSvc.getBasicServerDate();
        entity.state = "DRAFT"
        
    }
    
    public void beforeSave(o){
        entity.checkamtwords = numSvc.doubleToWords(entity.checkamt).toUpperCase() + " PESOS ONLY";
       // entity.state = "DRAFT"
        entity.recordlog_datecreated = dtSvc.getServerDate();
        entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_createdbyuserid = OsirisContext.env.USERID;
    }
    
    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
    }

    /* ========== Lookup Payee ========= */
    def getLookupPayee(){
       return Inv.lookupOpener('checkpayee:lookup',[
               onselect :{
                   entity.payee = it.payeename;
                   entity.payeeid = it.objid;
                   binding.refresh(); 
               },
           ])
   }
   
     /* ========== Lookup Account ========= */
    def getLookupAccount(){
       return Inv.lookupOpener('checkaccount:lookup',[
               onselect :{
                   entity.checkaccount = it.accountname;
                   entity.checkaccountid = it.objid;
                   entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
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
    
    void cancel() { 
        if ( MsgBox.confirm('You are about to cancel this check. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'checkmain', 
               objid : entity.objid, 
               state : 'CANCELLED' 
            ]); 
            loadData();
            entity.reason = (MsgBox.prompt('Reason for cancelling?'))
            rbService.postCancelledCheck(entity);
        }
    }
    
//    def viewReport(){
//        return Inv.lookupOpener('checkmain:reports')
//    }

      def viewReport() {
        def op = new PopupMenuOpener();
        try {
            def list = Inv.lookupOpeners('checkmain:form:reports', [entity]);
            if(!list) throw new Exception("No reports are defined for " );
            op.addAll( list );
        } catch(Throwable ign){;}
        return op;
    }
    
  
}