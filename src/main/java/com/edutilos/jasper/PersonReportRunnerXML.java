package com.edutilos.jasper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.data.TextDataSourceAttributes;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PersonReportRunnerXML {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            final ClassLoader classLoader = PersonReportRunnerXML.class.getClassLoader();
            String srcFileName = classLoader.getResource("jasper/PersonXPath.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            Map<String,Object> params = new HashMap<>();
            JRXmlDataSource ds = new JRXmlDataSource(classLoader.getResource("person.xml").getFile(), "/People/Person");
            TextDataSourceAttributes attrs= ds.getTextAttributes();
            return JasperFillManager.fillReport(report, params, ds);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    private static JFrame generateWindow() {
        JFrame mainWindow = new JFrame("PersonReportRunner");
        mainWindow.add(new JRViewer(generateJasperPrint()));
        mainWindow.setSize(new Dimension(500, 500));
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return mainWindow;
    }
}
