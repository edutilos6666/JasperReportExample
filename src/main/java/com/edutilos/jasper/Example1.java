package com.edutilos.jasper;

import com.edutilos.model.CountryName;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example1 {
    public static void main(String[] args) {
        doCompile();
    }

    private static void doCompile() {
        try {
            String srcFileName = Example1.class.getClassLoader().getResource("jasper/Example1.jrxml").getFile();
//            System.out.println(srcFileName);
//            JasperCompileManager.compileReportToFile(srcFileName);
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getBeans());
            JasperPrint jasperPrint = JasperFillManager.fillReport(report , new HashMap<>(), dataSource);
            JRViewer viewer = new JRViewer(jasperPrint);
            JFrame frame = new JFrame("Jasper report");
            frame.add(viewer);
            frame.setSize(new Dimension(500, 400));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    private static List<CountryName> getBeans() {
        return Stream.of(
                new CountryName("USA", "foo"),
                new CountryName("Germany", "bar"),
                new CountryName("Turkey", "deko"),
                new CountryName("Ireland", "pako"),
                new CountryName("France", "ceko"),
                new CountryName("Spain", "messi")
        ).collect(Collectors.toList());
    }
}
