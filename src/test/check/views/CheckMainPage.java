/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test.check.views;

import com.rameses.rcp.ui.annotations.Template;
import com.rameses.seti2.views.CrudFormPage;

/**
 *
 * @author dell
 */
@Template(CrudFormPage.class)
public class CheckMainPage extends javax.swing.JPanel {

    /**
     * Creates new form HousingLedgerPage
     */
    public CheckMainPage() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        xTabbedPane1 = new com.rameses.rcp.control.XTabbedPane();
        xPanel1 = new com.rameses.rcp.control.XPanel();
        xFormPanel1 = new com.rameses.rcp.control.XFormPanel();
        xDateField1 = new com.rameses.rcp.control.XDateField();
        xLookupField1 = new com.rameses.rcp.control.XLookupField();
        xTextField1 = new com.rameses.rcp.control.XTextField();
        xIntegerField1 = new com.rameses.rcp.control.XIntegerField();
        xDecimalField1 = new com.rameses.rcp.control.XDecimalField();
        xLookupField3 = new com.rameses.rcp.control.XLookupField();
        xLookupField2 = new com.rameses.rcp.control.XLookupField();
        xComboBox1 = new com.rameses.rcp.control.XComboBox();
        xTextField2 = new com.rameses.rcp.control.XTextField();
        xTextField3 = new com.rameses.rcp.control.XTextField();
        xCheckBox1 = new com.rameses.rcp.control.XCheckBox();
        xLabel1 = new com.rameses.rcp.control.XLabel();
        xLabel2 = new com.rameses.rcp.control.XLabel();

        xFormPanel1.setCaptionWidth(120);

        xDateField1.setCaption("Date");
        xDateField1.setName("entity.checkdate"); // NOI18N
        xDateField1.setReadonly(true);
        xFormPanel1.add(xDateField1);

        xLookupField1.setCaption("Payee");
        xLookupField1.setExpression("#{entity.payee}");
        xLookupField1.setHandler("lookupPayee");
        xLookupField1.setName("entity.payee"); // NOI18N
        xLookupField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLookupField1);

        xTextField1.setCaption("Check Add");
        xTextField1.setName("entity.checkadd"); // NOI18N
        xTextField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField1);

        xIntegerField1.setCaption("Check Number");
        xIntegerField1.setName("entity.checknumber"); // NOI18N
        xIntegerField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xIntegerField1);

        xDecimalField1.setCaption("Amount");
        xDecimalField1.setName("entity.checkamt"); // NOI18N
        xDecimalField1.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xDecimalField1);

        xLookupField3.setCaption("Fund/Account");
        xLookupField3.setExpression("#{entity.checkaccount}");
        xLookupField3.setHandler("lookupAccount");
        xLookupField3.setName("entity.checkaccount"); // NOI18N
        xLookupField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLookupField3);

        xLookupField2.setCaption("Office Origin");
        xLookupField2.setExpression("#{entity.officeorigin}");
        xLookupField2.setHandler("lookupOffices");
        xLookupField2.setName("entity.officeorigin"); // NOI18N
        xLookupField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xLookupField2);

        xComboBox1.setCaption("Check Type");
        xComboBox1.setItems("checktypes");
        xComboBox1.setName("entity.checktype"); // NOI18N
        xComboBox1.setPreferredSize(new java.awt.Dimension(300, 20));
        xFormPanel1.add(xComboBox1);

        xTextField2.setCaption("Particulars");
        xTextField2.setName("entity.particulars"); // NOI18N
        xTextField2.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField2);

        xTextField3.setCaption("Voucher No.");
        xTextField3.setName("entity.voucherno"); // NOI18N
        xTextField3.setPreferredSize(new java.awt.Dimension(0, 20));
        xFormPanel1.add(xTextField3);

        xCheckBox1.setCaption("Cash Advance");
        xCheckBox1.setName("entity.iscashadvance"); // NOI18N
        xFormPanel1.add(xCheckBox1);

        javax.swing.GroupLayout xPanel1Layout = new javax.swing.GroupLayout(xPanel1);
        xPanel1.setLayout(xPanel1Layout);
        xPanel1Layout.setHorizontalGroup(
            xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(xPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 583, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );
        xPanel1Layout.setVerticalGroup(
            xPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(xPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xFormPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
        );

        xTabbedPane1.addTab("Cheque Info", xPanel1);

        xLabel1.setCaption("Remaining Balance for the Account");
        xLabel1.setExpression("Remaining Balance for the Account:");
        xLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        xLabel1.setVisibleWhen("mode=='create'");

        xLabel2.setCaption("Remaining Balance for the Account");
        xLabel2.setExpression("#{entity.balance}");
        xLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        xLabel2.setName("entity.balance"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(xLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(xLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 254, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(xTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(xLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(xLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.rameses.rcp.control.XCheckBox xCheckBox1;
    private com.rameses.rcp.control.XComboBox xComboBox1;
    private com.rameses.rcp.control.XDateField xDateField1;
    private com.rameses.rcp.control.XDecimalField xDecimalField1;
    private com.rameses.rcp.control.XFormPanel xFormPanel1;
    private com.rameses.rcp.control.XIntegerField xIntegerField1;
    private com.rameses.rcp.control.XLabel xLabel1;
    private com.rameses.rcp.control.XLabel xLabel2;
    private com.rameses.rcp.control.XLookupField xLookupField1;
    private com.rameses.rcp.control.XLookupField xLookupField2;
    private com.rameses.rcp.control.XLookupField xLookupField3;
    private com.rameses.rcp.control.XPanel xPanel1;
    private com.rameses.rcp.control.XTabbedPane xTabbedPane1;
    private com.rameses.rcp.control.XTextField xTextField1;
    private com.rameses.rcp.control.XTextField xTextField2;
    private com.rameses.rcp.control.XTextField xTextField3;
    // End of variables declaration//GEN-END:variables
}
