package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class CheckMainAddInfoModel extends CrudFormModel{

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
    
    //boolean editAllowed = false;
    
   // boolean isAllowApprove() {
    //     return ( entity.state.toString().matches('DRAFT|CLOSED') ); 
   // }
    
    
//    def checktypes = ['LandBank', 'LandBank-NTP',
//                      'DBP-NEW', 'DBP-NEW-NTP',
//                      'DBP-OLD', 'DBP-OLD-NTP', 
//                      'DBPyellow', 'DBPyellow-NTP',
//                      'PostalBank', 'PostalBank-NTP'];
    
    def expenseclasses = ['100','200','300'];
    
    def provfunds = ['GF', 'SEF', 'TF'];
  
   
//    public void afterCreate(){
//        entity.checkdate = dtSvc.getBasicServerDate();
//        //entity.state = "DRAFT"
//        
//    }
    
//    public void beforeSave(o){
//        entity.checkamtwords = numSvc.doubleToWords(entity.checkamt).toUpperCase() + " PESOS ONLY";
//       // entity.state = "DRAFT"
//       
//       //println entity
//    }

//    /* ========== Lookup Payee ========= */
//    def getLookupPayee(){
//       return Inv.lookupOpener('checkpayee:lookup',[
//               onselect :{
//                   entity.payee = it.payeename;
//                   entity.payeeid = it.objid;
//                   binding.refresh(); 
//               },
//           ])
//   }
   
//     /* ========== Lookup Account ========= */
//    def getLookupAccount(){
//       return Inv.lookupOpener('checkaccount:lookup',[
//               onselect :{
//                   entity.checkaccount = it.accountname;
//                   entity.checkaccountid = it.objid;
//                   entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
//                   binding.refresh(); 
//               },
//           ])
//   }

    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
    }
   
         /* ========== Lookup Responsibility Center Codes ========= */
    def getLookupRespcentercode(){
       return Inv.lookupOpener('respcentercode:lookup',[
               onselect :{
                   entity.respcentercode = it.respcentercode;
                   entity.respcentercodeid = it.objid;
                   //entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
                   binding.refresh(); 
               },
           ])
   }
   
//     /* ========== Lookup Offices ========= */
//     def getLookupOffices(){       
//        return Inv.lookupOpener('hrisorg:lookup',[
//                onselect :{   
//                    entity.officeorigin = it.Entity.Name;
//                                                            
//                            
//                    //binding.refresh('entity.officeorigin.*')
//                    //binding.refresh('entity.paidby.*')
//                },
//                
//                onempty: {
//                    //
//                }
//        ])
//    }
    
//    void cancel() { 
//        if ( MsgBox.confirm('You are about to cancel this check. Proceed?')) { 
//            getPersistenceService().update([ 
//               _schemaname: 'checkmain', 
//               objid : entity.objid, 
//               state : 'CANCELLED' 
//            ]); 
//            loadData();
//            entity.reason = (MsgBox.prompt('Reason for cancelling?'))
//            rbService.postCancelledCheck(entity);
//        }
//    }

      
    def selectedItem;
    
    def itemHandler = [
        
        
        fetchList: { 
            

            
                def p = [_schemaname: 'checkmainexpenseclassitems'];
                p.findBy = [ 'parentid': entity.objid];
                p.select = "duesname,amount";
                if(!entity.items){
                entity.items = queryService.getList( p );
            }
                //return queryService.getList( p );
                return entity.items;
            
        },
        
        createItem : {
           return[
               objid : 'CECI' + new java.rmi.server.UID(),
               parentid : entity.objid,
               isnew : false
           ]
       },
       
        onRemoveItem : {
                
                if (MsgBox.confirm('Delete item?')) {
                //service.deleteFarmerItems(it)
                entity.items.remove(it)
                persistenceSvc.removeEntity([_schemaname:'checkmainexpenseclassitems',objid:it.objid])
                itemHandler.reload();
                return true;
                
            }
            return false;
        },
        
        onAddItem : {
            entity.items << it; /* add to list syntax */
            //calculatetotal()
     }
     
       
      
     
    ] as EditorListModel;
    
  
  def getLookupExpenseclassitems(){
       return Inv.lookupOpener('expenseclassitems:lookup',[
               onselect :{
                   selectedItem.duesname = it.duesname;
                   selectedItem.duesnameid = it.objid;
                   //entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
                   binding.refresh('selectedItem.duesname.*'); 
                   //binding.refresh('selectedPayorderitem.item_code.*')
               },
           ])
   }
    
  
}