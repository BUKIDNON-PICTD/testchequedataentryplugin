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
    
    @Service('TestchequedataentryReportService')
    def checkrptService
    
    @Service("ListService")
    def service;
    
    @Service('SequenceService')
    def seqSvc
    
    @Service("EstRunningBalancePostService")
    def rbService;
    
    boolean editAllowed = false;
    
    boolean isAllowApprove() {
         return ( entity.state.toString().matches('DRAFT|CLOSED') ); 
    }
  
   
    public void afterCreate(){
        entity.txndate = dtSvc.getBasicServerDate();
        entity.items = [];
    }
    
    public void beforeSave(o){
        entity.controlno = dtSvc.getServerYear() +"-"+dtSvc.getServerMonth() +"-"+ seqSvc.getNextFormattedSeries('ada' + dtSvc.getServerYear()) ;
        entity.adaamtwords = numSvc.doubleToWords(entity.adaamt).toUpperCase() + " PESOS ONLY";
        entity.state = "DRAFT"
        entity.recordlog_datecreated = dtSvc.getServerDate();
        entity.recordlog_createdbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_createdbyuserid = OsirisContext.env.USERID;
    }
    
    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
        itemHandler.reload();
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
    
    void cancel() { 
        if ( MsgBox.confirm('You are about to cancel this check. Proceed?')) { 
            getPersistenceService().update([ 
               _schemaname: 'ada', 
               objid : entity.objid, 
               state : 'CANCELLED' 
            ]); 
            loadData();
            entity.reason = (MsgBox.prompt('Reason for cancelling?'))
            rbService.postCancelledAda(entity);
        }
    }
    
   
    
}