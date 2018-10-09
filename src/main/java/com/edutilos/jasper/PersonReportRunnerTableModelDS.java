package com.edutilos.jasper;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.data.JRXmlDataSource;
import net.sf.jasperreports.engine.data.TextDataSourceAttributes;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class PersonReportRunnerTableModelDS {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            final ClassLoader classLoader = PersonReportRunnerTableModelDS.class.getClassLoader();
            String srcFileName = classLoader.getResource("jasper/Person.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(srcFileName);
            Map<String,Object> params = new HashMap<>();
            JRTableModelDataSource ds = new JRTableModelDataSource(getBeans());
            return JasperFillManager.fillReport(report, params, ds);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static TableModel getBeans() {
        DefaultTableModel ret = new DefaultTableModel();
        ret.setColumnIdentifiers(new Object[]{"id","name","age","wage","active"});
        ret.addRow(new Object[]{1L, "foo", 10, 100.0, true});
        ret.addRow(new Object[]{2L, "bar", 20, 200.0, false});
        ret.addRow(new Object[]{3L, "bim", 30, 300.0, true});
        ret.addRow(new Object[]{4L, "pako", 40, 300.0, false});
        ret.addRow(new Object[]{5L, "deko", 50, 500.0, true});
        ret.addRow(new Object[]{6L, "ceko", 60, 600.0, false});
        ret.addRow(new Object[]{7L, "leo", 70, 700.0, true});
        ret.addRow(new Object[]{8L, "messi", 80, 700.0, false});
        return ret;
    }


    private static JFrame generateWindow() {
        JFrame mainWindow = new JFrame("PersonReportRunner");
        mainWindow.add(new JRViewer(generateJasperPrint()));
        mainWindow.setSize(new Dimension(500, 500));
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        return mainWindow;
    }
}
