package com.edutilos.jasper;

import com.edutilos.model.Person;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonReportRunnerCSV {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            final ClassLoader classLoader = PersonReportRunnerCSV.class.getClassLoader();
            String srcFileName = classLoader.getResource("jasper/Person.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            Map<String,Object> params = new HashMap<>();
            JRCsvDataSource ds = new JRCsvDataSource(classLoader.getResource("people.csv").getFile());
            ds.setColumnNames(new String[]{"id","name","age","wage","active"});
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
