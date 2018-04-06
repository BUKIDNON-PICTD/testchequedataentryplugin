package test.check.reports;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.gov.etracs.rpt.common.*;
import com.rameses.etracs.shared.*;

public class checkTransmittalReportController extends ReportController
{
   @Service('testcheckService')
   def svc;
//   
//   @Service("ReportParameterService")
//   def paramSvc;
//   
//   def query = [:];
//   @Caller
//   def caller;
   
   String title = 'Transmittal';
   String reportPath = 'test/check/reports/';
   String reportName = reportPath + 'checktransmittalreport.jasper';
   
   def entity;
   
   public def getReportData(){
       //entity is personnel info akin to the platform
//       MsgBox.alert(svc.getReportData(entity))
       return svc.getCheckReportData(entity);
       
   }
   
//    Map getParameters (){
//        def params = paramSvc.getStandardParameter()
//       
//        params.signature = EtracsReportUtil.getInputStream("sirJoelSignature.png")
//        return params 
//   }
   
   SubReport[] getSubReports(){
       return[
           new SubReport('CHECKITEMS', reportPath+'checktransmittalreportitems.jasper'), 
       ]
   }
   
     //void view() { 
     //   viewReport(); 
    //} 
}

