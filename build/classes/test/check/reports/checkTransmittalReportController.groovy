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

   
   String title = 'Transmittal';
   String reportPath = 'test/check/reports/';
   String reportName = reportPath + 'checktransmittalreportnew.jasper';
   
   def entity;
   
   public def getReportData(){
       
       //return svc.getCheckReportData(entity);
       def a = svc.getCheckTransmittalData(entity)
 
       return a;
       
       
       
   }
   
//    Map getParameters (){
//        def params = paramSvc.getStandardParameter()
//       
//        params.signature = EtracsReportUtil.getInputStream("sirJoelSignature.png")
//        return params 
//   }
   
   //SubReport[] getSubReports(){
    //   return[
     //      new SubReport('CHECKITEMS', reportPath+'checktransmittalreportitems.jasper'), 
     //  ]
  // }
   
    
}

