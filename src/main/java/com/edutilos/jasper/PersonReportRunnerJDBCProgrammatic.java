package com.edutilos.jasper;

import com.edutilos.model.Person;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.type.*;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PersonReportRunnerJDBCProgrammatic {
    public static void main(String[] args) {
        generateWindow().setVisible(true);
    }

    private static JasperPrint generateJasperPrint() {
        try {
            String srcFileName = PersonReportRunnerJDBCProgrammatic.class.getClassLoader().getResource("jasper/PersonJDBC.jrxml").getFile();
            JasperReport report = JasperCompileManager.compileReport(buildDesign());
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

    private static JasperDesign buildDesign() throws Exception {
        JasperDesign jasperDesign = new JasperDesign();
        jasperDesign.setName("PersonReportRunnerJDBCProgrammatic");
        jasperDesign.setPageWidth(595);
        jasperDesign.setPageHeight(842);
        jasperDesign.setTopMargin(20);
        jasperDesign.setRightMargin(20);
        jasperDesign.setBottomMargin(20);
        jasperDesign.setLeftMargin(20);
        jasperDesign.setColumnWidth(555);
        jasperDesign.setUUID(UUID.randomUUID());

        //Style
        JRDesignStyle style = new JRDesignStyle();
        style.setName("style1");
        style.setDefault(true);
        style.setFontName("DejaVu Sans");
        style.setFontSize(25f);
        style.setPdfFontName("Helvetica");
        style.setPdfEncoding("UTF-8");
        jasperDesign.addStyle(style);

        //Fields
        //id
        JRDesignField fieldId = new JRDesignField();
        fieldId.setName("id");
        fieldId.setValueClass(Long.class);
        jasperDesign.addField(fieldId);
        //name
        JRDesignField fieldName = new JRDesignField();
        fieldName.setName("name");
        fieldName.setValueClass(String.class);
        jasperDesign.addField(fieldName);
        //age
        JRDesignField fieldAge = new JRDesignField();
        fieldAge.setName("age");
        fieldAge.setValueClass(Integer.class);
        jasperDesign.addField(fieldAge);
        //wage
        JRDesignField fieldWage = new JRDesignField();
        fieldWage.setName("wage");
        fieldWage.setValueClass(Double.class);
        jasperDesign.addField(fieldWage);
        //active
        JRDesignField fieldActive = new JRDesignField();
        fieldActive.setName("active");
        fieldActive.setValueClass(Boolean.class);
        jasperDesign.addField(fieldActive);

        //Parameters
        //idMin and idMax
        JRDesignParameter paramIdMin = new JRDesignParameter();
        paramIdMin.setName("idMin");
        paramIdMin.setValueClass(Long.class);
        JRDesignParameter paramIdMax = new JRDesignParameter();
        paramIdMax.setName("idMax");
        paramIdMax.setValueClass(Long.class);
        jasperDesign.addParameter(paramIdMin);
        jasperDesign.addParameter(paramIdMax);
        //ageMin and ageMax
        JRDesignParameter paramAgeMin = new JRDesignParameter();
        paramAgeMin.setName("ageMin");
        paramAgeMin.setValueClass(Integer.class);
        JRDesignParameter paramAgeMax = new JRDesignParameter();
        paramAgeMax.setName("ageMax");
        paramAgeMax.setValueClass(Integer.class);
        jasperDesign.addParameter(paramAgeMin);
        jasperDesign.addParameter(paramAgeMax);
        //wageMin and wageMax
        JRDesignParameter paramWageMin = new JRDesignParameter();
        paramWageMin.setName("wageMin");
        paramWageMin.setValueClass(Double.class);
        JRDesignParameter paramWageMax = new JRDesignParameter();
        paramWageMax.setName("wageMax");
        paramWageMax.setValueClass(Double.class);
        jasperDesign.addParameter(paramWageMin);
        jasperDesign.addParameter(paramWageMax);

        //Query
        JRDesignQuery query = new JRDesignQuery();
        query.setText("select * from Person where id >= $P{idMin} and id <= $P{idMax} and age >= $P{ageMin} and age <= $P{ageMax} and wage >= $P{wageMin} and wage <= $P{wageMax}");
        jasperDesign.setQuery(query);


        //background
        JRDesignBand backgroundBand = new JRDesignBand();
        backgroundBand.setSplitType(SplitTypeEnum.STRETCH);
        jasperDesign.setBackground(backgroundBand);

        //title
        JRDesignBand titleBand = new JRDesignBand();
        titleBand.setHeight(80);
        titleBand.setSplitType(SplitTypeEnum.STRETCH);
        JRDesignStaticText titleText = new JRDesignStaticText();
        titleText.setPositionType(PositionTypeEnum.FIX_RELATIVE_TO_BOTTOM);
        titleText.setMode(ModeEnum.OPAQUE);
        titleText.setX(178);
        titleText.setY(30);
        titleText.setWidth(266);
        titleText.setHeight(30);
        titleText.setForecolor(Color.decode("#FFFFFF"));
        titleText.setBackcolor(Color.decode("#159BE8"));
        titleText.setUUID(UUID.randomUUID());
        titleText.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        titleText.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        titleText.setText("Person Details");
        titleBand.addElement(titleText);
        jasperDesign.setTitle(titleBand);

        //columnHeader
        JRDesignBand columnHeaderBand = new JRDesignBand();
        columnHeaderBand.setHeight(76);
        columnHeaderBand.setSplitType(SplitTypeEnum.STRETCH);
        JRDesignStaticText columnHeaderBackground = new JRDesignStaticText();
        columnHeaderBackground.setKey("");
        columnHeaderBackground.setPositionType(PositionTypeEnum.FIX_RELATIVE_TO_BOTTOM);
        columnHeaderBackground.setStretchType(StretchTypeEnum.ELEMENT_GROUP_HEIGHT);
        columnHeaderBackground.setMode(ModeEnum.OPAQUE);
        columnHeaderBackground.setX(-11);
        columnHeaderBackground.setY(29);
        columnHeaderBackground.setWidth(562);
        columnHeaderBackground.setHeight(41);
        columnHeaderBackground.setBackcolor(Color.decode("#24F2E8"));
        columnHeaderBackground.setUUID(UUID.randomUUID());
        columnHeaderBackground.setText("");
        columnHeaderBand.addElement(columnHeaderBackground);

        JRDesignStaticText columnnHeaderId = new JRDesignStaticText();
        columnnHeaderId.setX(0);
        columnnHeaderId.setY(40);
        columnnHeaderId.setWidth(80);
        columnnHeaderId.setHeight(30);
        columnnHeaderId.setUUID(UUID.randomUUID());
        columnnHeaderId.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        columnnHeaderId.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        columnnHeaderId.setFontSize(15f);
        columnnHeaderId.setBold(true);
        columnnHeaderId.getParagraph().setLineSpacing(LineSpacingEnum.SINGLE);
        columnnHeaderId.getParagraph().setLineSpacingSize(0f);
        columnnHeaderId.getParagraph().setSpacingBefore(2);
        columnnHeaderId.setText("Id");
        columnHeaderBand.addElement(columnnHeaderId);


        JRDesignStaticText columnnHeaderName = new JRDesignStaticText();
        columnnHeaderName.setX(100);
        columnnHeaderName.setY(40);
        columnnHeaderName.setWidth(100);
        columnnHeaderName.setHeight(30);
        columnnHeaderName.setUUID(UUID.randomUUID());
        columnnHeaderName.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        columnnHeaderName.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        columnnHeaderName.setFontSize(15f);
        columnnHeaderName.setBold(true);
        columnnHeaderName.getParagraph().setLineSpacing(LineSpacingEnum.SINGLE);
        columnnHeaderName.getParagraph().setLineSpacingSize(0f);
        columnnHeaderName.getParagraph().setSpacingBefore(2);
        columnnHeaderName.setText("Name");
        columnHeaderBand.addElement(columnnHeaderName);
        JRDesignStaticText columnnHeaderAge = new JRDesignStaticText();
        columnnHeaderAge.setX(217);
        columnnHeaderAge.setY(40);
        columnnHeaderAge.setWidth(100);
        columnnHeaderAge.setHeight(30);
        columnnHeaderAge.setUUID(UUID.randomUUID());
        columnnHeaderAge.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        columnnHeaderAge.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        columnnHeaderAge.setFontSize(15f);
        columnnHeaderAge.setBold(true);
        columnnHeaderAge.getParagraph().setLineSpacing(LineSpacingEnum.SINGLE);
        columnnHeaderAge.getParagraph().setLineSpacingSize(0f);
        columnnHeaderAge.getParagraph().setSpacingBefore(2);
        columnnHeaderAge.setText("Age");
        columnHeaderBand.addElement(columnnHeaderAge);


        JRDesignStaticText columnnHeaderWage = new JRDesignStaticText();
        columnnHeaderWage.setX(333);
        columnnHeaderWage.setY(40);
        columnnHeaderWage.setWidth(100);
        columnnHeaderWage.setHeight(30);
        columnnHeaderWage.setUUID(UUID.randomUUID());
        columnnHeaderWage.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        columnnHeaderWage.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        columnnHeaderWage.setFontSize(15f);
        columnnHeaderWage.setBold(true);
        columnnHeaderWage.getParagraph().setLineSpacing(LineSpacingEnum.SINGLE);
        columnnHeaderWage.getParagraph().setLineSpacingSize(0f);
        columnnHeaderWage.getParagraph().setSpacingBefore(2);
        columnnHeaderWage.setText("Wage");
        columnHeaderBand.addElement(columnnHeaderWage);

        JRDesignStaticText columnnHeaderActive = new JRDesignStaticText();
        columnnHeaderActive.setX(451);
        columnnHeaderActive.setY(40);
        columnnHeaderActive.setWidth(100);
        columnnHeaderActive.setHeight(30);
        columnnHeaderActive.setUUID(UUID.randomUUID());
        columnnHeaderActive.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        columnnHeaderActive.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        columnnHeaderActive.setFontSize(15f);
        columnnHeaderActive.setBold(true);
        columnnHeaderActive.getParagraph().setLineSpacing(LineSpacingEnum.SINGLE);
        columnnHeaderActive.getParagraph().setLineSpacingSize(0f);
        columnnHeaderActive.getParagraph().setSpacingBefore(2);
        columnnHeaderActive.setText("Active");
        columnHeaderBand.addElement(columnnHeaderActive);
        jasperDesign.setColumnHeader(columnHeaderBand);


        //Detail
        JRDesignBand detailBand = new JRDesignBand();
        detailBand.setHeight(40);
        detailBand.setSplitType(SplitTypeEnum.STRETCH);
        JRDesignStaticText detailBackground = new JRDesignStaticText();
        detailBackground.setMode(ModeEnum.OPAQUE);
        detailBackground.setX(-18);
        detailBackground.setY(-3);
        detailBackground.setWidth(561);
        detailBackground.setHeight(30);
        detailBackground.setBackcolor(Color.decode("#F7F431"));
        detailBackground.setUUID(UUID.randomUUID());
        detailBackground.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailBackground.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailBackground.setText("");
        detailBand.addElement(detailBackground);

        JRDesignTextField detailId = new JRDesignTextField();
        detailId.setX(0);
        detailId.setY(0);
        detailId.setWidth(88);
        detailId.setHeight(30);
        detailId.setUUID(UUID.randomUUID());
        detailId.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailId.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailId.setFontSize(15f);
        detailId.setItalic(true);
        detailId.setExpression(new JRDesignExpression("$F{id}"));
        detailBand.addElement(detailId);


        JRDesignTextField detailName = new JRDesignTextField();
        detailName.setX(102);
        detailName.setY(0);
        detailName.setWidth(88);
        detailName.setHeight(30);
        detailName.setUUID(UUID.randomUUID());
        detailName.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailName.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailName.setFontSize(15f);
        detailName.setItalic(true);
        detailName.setExpression(new JRDesignExpression("$F{name}"));
        detailBand.addElement(detailName);

        JRDesignTextField detailAge = new JRDesignTextField();
        detailAge.setX(221);
        detailAge.setY(0);
        detailAge.setWidth(88);
        detailAge.setHeight(30);
        detailAge.setUUID(UUID.randomUUID());
        detailAge.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailAge.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailAge.setFontSize(15f);
        detailAge.setItalic(true);
        detailAge.setExpression(new JRDesignExpression("$F{age}"));
        detailBand.addElement(detailAge);

        JRDesignTextField detailWage = new JRDesignTextField();
        detailWage.setX(338);
        detailWage.setY(0);
        detailWage.setWidth(88);
        detailWage.setHeight(30);
        detailWage.setUUID(UUID.randomUUID());
        detailWage.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailWage.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailWage.setFontSize(15f);
        detailWage.setItalic(true);
        detailWage.setUnderline(true);
        detailWage.setStrikeThrough(true);
        detailWage.setExpression(new JRDesignExpression("$F{wage}"));
        detailBand.addElement(detailWage);

        JRDesignTextField detailActive = new JRDesignTextField();
        detailActive.setX(457);
        detailActive.setY(0);
        detailActive.setWidth(88);
        detailActive.setHeight(30);
        detailActive.setUUID(UUID.randomUUID());
        detailActive.setHorizontalTextAlign(HorizontalTextAlignEnum.CENTER);
        detailActive.setVerticalTextAlign(VerticalTextAlignEnum.MIDDLE);
        detailActive.setFontSize(15f);
        detailActive.setBold(true);
        detailActive.setItalic(true);
        detailActive.setUnderline(true);
        detailActive.setStrikeThrough(true);
        detailActive.setExpression(new JRDesignExpression("$F{active}"));
        detailBand.addElement(detailActive);

        ((JRDesignSection)jasperDesign.getDetailSection()).addBand(detailBand);

        return jasperDesign;
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
