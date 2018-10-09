package com.edutilos.jasper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.edutilos.model.Country;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class PrintFileGenerator {

    public static JasperPrint generate() throws JRException {

        String xmlFile = "src/main/resources/jasper/jasper-beans.xml";

        JasperReport jreport = JasperCompileManager.compileReport(xmlFile);

        JRBeanCollectionDataSource beanColDataSource
                = new JRBeanCollectionDataSource(getBeans());

        Map params = new HashMap();

        JasperPrint jrprint = JasperFillManager.fillReport(jreport,
                params, beanColDataSource);

        return jrprint;
    }

    private static ArrayList<Country> getBeans() {

        ArrayList<Country> beans = new ArrayList<>();

        beans.add(new Country("China", 1382050000));
        beans.add(new Country("India", 1313210000));
        beans.add(new Country("USA", 324666000));
        beans.add(new Country("Indonesia", 260581000));
        beans.add(new Country("Brazil", 207221000));
        beans.add(new Country("Pakistan", 196626000));
        beans.add(new Country("Nigeria", 186988000));

        return beans;
    }
}