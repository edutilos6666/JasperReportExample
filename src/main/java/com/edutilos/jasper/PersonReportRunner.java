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
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonReportRunner {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            String srcFileName = PersonReportRunner.class.getClassLoader().getResource("jasper/Person.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            return JasperFillManager.fillReport(report, new HashMap<>(), new JRBeanCollectionDataSource(getBeans()));
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static List<Person> getBeans() {
        return Stream.of(
                new Person(1, "foo", 10, 100.0, true),
                new Person(2, "bar", 20, 200.0, false),
                new Person(3, "bim", 30, 300.0, true),
                new Person(4, "edu", 40, 400.0, false),
                new Person(5, "tilos", 50, 500.0, true),
                new Person(6, "leo", 60, 600.0, false),
                new Person(7, "messi", 70, 700.0, true)
        ).collect(Collectors.toList());
    }

    private static JFrame generateWindow() {
        JFrame mainWindow = new JFrame("PersonReportRunner");
        mainWindow.add(new JRViewer(generateJasperPrint()));
        mainWindow.setSize(new Dimension(500, 500));
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return mainWindow;
    }
}
