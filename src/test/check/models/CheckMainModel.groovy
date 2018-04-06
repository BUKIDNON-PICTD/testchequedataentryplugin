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
    
    @Service("ListService")
    def service;
    
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
        }
    }
    
    /* ========== Suggest Payee ========= */
   // def payeeLookup = [
             //   fetchList: { o->
                    //  o.pname = 'payeename';
              //      return checkService.getPayeeList(o);
                    
             //   }
          //  ] as SuggestModel; 
            
    //def payeeLookup = [
      //  fetchList: { o->
        //    def p = [_schemaname: 'checkpayee'];
          //  p.where = [ 'payeename like :pname', [pname: params.searchtext+'%'] ];
           // p.orderBy = 'payeename';
           // p.select = "payeename";
           // return queryService.getList( p );
        //}
    //] as SuggestModel;
    
    //def payeeLookup = [
      //          fetchList: { o->
        //            o.name = 'payeename';
          //          return service.getList(o);
            //    }
            //] as SuggestModel; 
    
}