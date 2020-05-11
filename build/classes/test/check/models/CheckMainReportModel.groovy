package test.check.models;

import com.rameses.rcp.annotations.*;
import com.rameses.rcp.common.*;
import com.rameses.osiris2.common.*;
import com.rameses.osiris2.client.*;
import com.rameses.osiris2.reports.*;
import com.rameses.gov.etracs.rpt.common.*;
import com.rameses.etracs.shared.*;

public class CheckMainReportModel extends ReportController
{
    @Service('testcheckService')
    def svc;
   
    @Service("ReportParameterService")
    def paramSvc;
    
   String title = 'Check Information';
   String reportPath = 'test/check/reports/';
   String reportName = reportPath + 'checkmainreport.jasper';
   def reportData;
   def query = [:];
   //def clientContext = com.rameses.rcp.framework.ClientContext.currentcontext;
   
//   def initPreview(def x){
//        reportData = x
//      
//    }
    
     def preview(def x){
        reportData = x
        report.viewReport();
        return 'preview'
    }
    
    def back(){
        return 'default'
    }
    
    
    
    def report = [
        getReportName : { return reportPath + 'checkmainreport.jasper'},
//        getSubReports : { 
//            return[
//                new SubReport('TTPAYORDERITEMS', reportPath+'TransferTaxPaymentOrderItems.jasper'), 
//            ] as SubReport[]
//        },
        getReportData : { return reportData },
        getParameters : {
            def params = paramSvc.getStandardParameter()
            //params.LOGOLGU = ReportUtil.getImageAsStream("lgu-logo.png")
            //params.signature = clientContext.classloader.getResourceAsStream('etracs/training/plugin/report/images/cajessignature.jpg')
//            params.BACKGROUND = ReportUtil.getInputStream("background.png")
//            params.LOGOASSESSOR = ReportUtil.getInputStream("lgu-assessor.png")
//            params.LOGOBLGF = ReportUtil.getInputStream("lgu-blgf.png")
            return params 
        },
    ] as ReportModel
//    
//   public def getReportData(){
//       //entity is personnel info akin to the platform
//       return reportData;
//       
//   }
//   SubReport[] getSubReports(){
//       return[
//           new SubReport('SGPAYORDERITEMS', reportPath+'PayOrderReportAdminItems.jasper'), 
//       ]
//   }

    public def getReportData(){
       //entity is personnel info akin to the platform
//       MsgBox.alert(svc.getReportData(entity))
       return svc.getFrontCheckMainReportData(entity);
       
   }
//   Map getParameters (){
//        def params = paramSvc.getStandardParameter()
//       
//        //params.signature = ReportUtil.getImageAsStream("albarecesignature.jpg")
//        return params 
//   }
}

