/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataServices.util;

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
public class OrdenPdf {

    public static Boolean geratedPdf(String DestinationPath, Integer ORD_NUM_SIS) {
        Boolean exito = false;
        String path = OrdenPdf.class.getResource("/DataServices/rptm/rptmOrden.jrxml").getPath();
        try {
            ConnectionMysql connection = new ConnectionMysql();

            /*JasperReport jasperReport
                    = */
            String jasperReport
                    = JasperCompileManager.compileReportToFile(path);
            Map<String, Object> parameters = new HashMap<String, Object>();
            parameters.put("ORD_NUM_SIS", ORD_NUM_SIS);

            JasperPrint print = JasperFillManager.fillReport(jasperReport,
                    parameters, connection.conectar());
//            JasperPrint print = JasperFillManager.fillReport(jasperReport.replaceAll("jrxml", "jasper"),
//                    parameters, connection.conectar());

            // PDF Exportor.
            JRPdfExporter exporter = new JRPdfExporter();

            ExporterInput exporterInput = new SimpleExporterInput(print);
            // ExporterInput
            exporter.setExporterInput(exporterInput);

            // ExporterOutput
            OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
                    DestinationPath + "" + ORD_NUM_SIS + ".pdf");
            // Output
            exporter.setExporterOutput(exporterOutput);

            //
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            exito = true;
        } catch (JRException ex) {
            System.out.println(">> Ex JasperReports " + ex.getMessage());
        }

        return exito;
    }

}
