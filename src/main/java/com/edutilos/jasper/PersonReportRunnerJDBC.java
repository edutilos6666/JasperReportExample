package com.edutilos.jasper;

import com.edutilos.model.Person;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonReportRunnerJDBC {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            String srcFileName = PersonReportRunnerJDBC.class.getClassLoader().getResource("jasper/PersonJDBC.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            Map<String,Object> params = new HashMap<>();
            params.put("idMin", 1L);
            params.put("idMax", 7L);
            params.put("ageMin", 10);
            params.put("ageMax", 70);
            params.put("wageMin", 100.0);
            params.put("wageMax", 700.0);
            return JasperFillManager.fillReport(report, params, getConnection());
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static Connection getConnection() throws Exception {
        final String username = "root";
        final String password = "root";
        final String url = "jdbc:mysql://localhost/test2?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        return DriverManager.getConnection(url, username, password);
    }


    private static JFrame generateWindow() {
        JFrame mainWindow = new JFrame("PersonReportRunner");
        mainWindow.add(new JRViewer(generateJasperPrint()));
        mainWindow.setSize(new Dimension(500, 500));
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return mainWindow;
    }
}
