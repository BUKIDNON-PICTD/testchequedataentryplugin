package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

class CheckTransmittalModel extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('SequenceService')
    def seqSvc
    
     public void afterCreate(){
        entity.datecreated = dtSvc.getServerDate();
        //entity.createdby_name = OsirisContext.env.FULLNAME;
        //entity.createdby_id = OsirisContext.env.USERID;
        entity.transmittalnum = dtSvc.getServerYear() +"-"+ dtSvc.getServerMonth() + seqSvc.getNextFormattedSeries('check' + dtSvc.getServerYear() + dtSvc.getServerMonth()) ;
        entity.items = [];
    
    }
    
    public void beforeSave(o){
        if(!entity.items)throw new Exception("There must be checks")
        
        entity.transtate = "CLOSED";
    }
    
    
    def selectedItem;
    
    def checkHandler = [
        
        
        fetchList: { o->
            
            if (!entity.transtate)
            {
                def p = [_schemaname: 'checkmain'];
                p.findBy = [ 'state': 'DRAFT'];
                p.select = "objid,checknumber,payee,checkamt";
            
                entity.items = queryService.getList( p );
                entity.items.each{
                    it.transmittalid = entity.objid;
                    it.checkid = it.objid;
            
            }              
            
                return entity.items;
            }
            
            else
            {
                def p = [_schemaname: 'checktransmittalitems'];
                p.findBy = [ 'transmittalid': entity.objid];
                p.select = "checknumber,payee,checkamt";
                return queryService.getList( p );
            }
        }
       
      
     
    ] as EditorListModel;

//    def capturePayment() {
//        return Inv.lookupOpener("housing_ledger_capture_payment", [parent: entity ] );
//    }

    void refreshItem() {
        checkHandler.reload();
    }
}