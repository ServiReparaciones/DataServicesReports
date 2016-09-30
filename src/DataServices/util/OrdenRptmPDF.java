/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DataServices.util;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
/**
 *
 * @author luis
 */
public class OrdenRptmPDF {
        public static void main(String[] args) throws JRException,
            ClassNotFoundException, SQLException {
 
        String reportSrcFile = "F:/ECLIPSE_TUTORIAL/JASPERREPORT/HelloJasperReport/FirstJasperReport.jrxml";
         
        // First, compile jrxml file.
        JasperReport jasperReport =    JasperCompileManager.compileReport(reportSrcFile);
 
        ConnectionMysql connection=  new ConnectionMysql();
 
        // Parameters for report
        Map<String, Object> parameters = new HashMap<String, Object>();
 
        JasperPrint print = JasperFillManager.fillReport(jasperReport,
                parameters, connection.conectar());
 
        // Make sure the output directory exists.
        File outDir = new File("C:/jasperoutput");
        outDir.mkdirs();
 
        // PDF Exportor.
        JRPdfExporter exporter = new JRPdfExporter();
 
        ExporterInput exporterInput = new SimpleExporterInput(print);
        // ExporterInput
        exporter.setExporterInput(exporterInput);
 
        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                "C:/jasperoutput/FirstJasperReport.pdf");
        // Output
        exporter.setExporterOutput(exporterOutput);
 
        //
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
 
        System.out.print("Done!");
    }
    
    
    
}
