package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.seti2.models.*;
import com.rameses.osiris2.client.*
import com.rameses.osiris2.common.*;
import com.rameses.util.*;

import com.rameses.rcp.framework.ValidatorException;
import com.rameses.util.BreakException;

class CheckTransmittalModel extends CrudFormModel{

    @Service('DateService')
    def dtSvc
    
    @Service('SequenceService')
    def seqSvc
    
    @Script("User")
    def userInfo
    
    boolean editAllowed = false;
    
    def app
    def user
    
    public void afterCreate(){
        entity.datecreated = dtSvc.getServerDate();
        //entity.createdby_name = OsirisContext.env.FULLNAME;
        //entity.createdby_id = OsirisContext.env.USERID;
        //entity.transmittalnum = dtSvc.getServerYear() +"-"+ dtSvc.getServerMonth() + seqSvc.getNextFormattedSeries('check' + dtSvc.getServerYear() + dtSvc.getServerMonth()) ;
        entity.items = [];
        
        app = userInfo.env;
        user = [objid: app.USERID, name: app.NAME, fullname: app.FULLNAME, username: app.USER ];
        //MsgBox.alert(user)
    }
    
    public void beforeSave(o){
        if(!entity.items)throw new Exception("There must be checks")
        
        entity.transtate = "CLOSED";
        
        entity.transmittalnum = dtSvc.getServerYear() +"-"+ dtSvc.getServerMonth() + seqSvc.getNextFormattedSeries('check' + dtSvc.getServerYear() + dtSvc.getServerMonth()) ;
        
    }
    
    
    def selectedItem;
    
    def checkHandler = [
        
        
        fetchList: { 
            
            if (!entity.transtate)
            {
               
                def p = [_schemaname: 'checkmain'];
                p.findBy = [ 'state': 'DRAFT', 'recordlog_createdbyuserid': user.objid];
                p.select = "objid,checknumber,payee,checkamt";
                //p.where = [ 'recordlog_createdbyuserid': user.objid];
            
                entity.items = queryService.getList( p );
                entity.items.each{
                    it.transmittalid = entity.objid;
                    it.checkid = it.objid;
            
                }              
            
                return entity.items;
            }else
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