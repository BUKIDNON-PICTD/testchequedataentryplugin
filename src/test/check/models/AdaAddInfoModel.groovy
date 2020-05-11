package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class AdaAddInfoModel extends CrudFormModel{

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
    
   // boolean editAllowed = false;
    
//    boolean isAllowApprove() {
//         return ( entity.state.toString().matches('DRAFT|CLOSED') ); 
//    }
  
//    boolean isAllowApprove() {
//         return ( mode=='read' && entity.state.toString().matches('DRAFT|ACTIVE') ); 
//    }
   
//    public void afterCreate(){
//        //entity.txndate = dtSvc.getBasicServerDate();
//        entity.items = [];
//    }
    
//    public void beforeSave(o){
//        //entity.controlno = dtSvc.getServerYear() +"-"+dtSvc.getServerMonth() +"-"+ seqSvc.getNextFormattedSeries('ada' + dtSvc.getServerYear()) ;
//        entity.adaamtwords = numSvc.doubleToWords(entity.adaamt).toUpperCase() + " PESOS ONLY";
//        entity.state = "DRAFT"
//    }
   
     /* ========== Lookup Account ========= */
//    def getLookupAccount(){
//       return Inv.lookupOpener('checkaccount:lookup',[
//               onselect :{
//                   entity.checkaccount = it.accountname;
//                   entity.checkaccountid = it.objid;
//                    entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
//                   binding.refresh(); 
//               },
//           ])
//   }
    
//    void cancel() { 
//        if ( MsgBox.confirm('You are about to cancel this check. Proceed?')) { 
//            getPersistenceService().update([ 
//               _schemaname: 'ada', 
//               objid : entity.objid, 
//               state : 'CANCELLED' 
//            ]); 
//            loadData();
//            entity.reason = (MsgBox.prompt('Reason for cancelling?'))
//            rbService.postCancelledAda(entity);
//        }
//    }

    public void afterEdit(){
        entity.recordlog_dateupdated = dtSvc.getServerDate();
        entity.recordlog_lastupdatedbyuser = OsirisContext.env.FULLNAME;
        entity.recordlog_lastupdatedbyuserid = OsirisContext.env.USERID;
    }

    def expenseclasses = ['100','200','300'];
    
    def provfunds = ['GF', 'SEF', 'TF'];
    
    def selectedItem;
    
    def itemHandler = [
        
        
        fetchList: { 
            

            
                def p = [_schemaname: 'adaitems'];
                p.findBy = [ 'parentid': entity.objid];
                p.select = "adacontrolno,accountno,payeename,particulars,amount,respcentercode,fundcode,expenseclass";
                if(!entity.items){
                entity.items = queryService.getList( p );
            }
                //return queryService.getList( p );
                return entity.items;
            
        },
        
        createItem : {
           return[
               objid : 'AI' + new java.rmi.server.UID(),
               parentid : entity.objid,
               isnew : false
           ]
       },
       
        onRemoveItem : {
                
                if (MsgBox.confirm('Delete item?')) {
                //service.deleteFarmerItems(it)
                entity.items.remove(it)
                persistenceSvc.removeEntity([_schemaname:'adaitems',objid:it.objid])
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
    
    void refreshItem() {
        itemHandler.reload();
    }
    
    def getLookupRespcentercode(){
       return Inv.lookupOpener('respcentercode:lookup',[
               onselect :{
                   selectedItem.respcentercode = it.respcentercode;
                   selectedItem.respcentercodeid = it.objid;
                   //entity.balance = new java.text.DecimalFormat("#,##0.00").format(checkrptService.getAmountPerAccount(it.objid).endbalance);
                   binding.refresh('selectedItem.respcentercode.*'); 
                   //binding.refresh('selectedPayorderitem.item_code.*')
               },
           ])
   }
   
    
}