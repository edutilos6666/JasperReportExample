package com.edutilos.jasper;
import java.awt.Dimension;
import javax.swing.JFrame;
import net.sf.jasperreports.swing.JRViewer;

public class GuiRunner {

    public static void main(String[] args) throws Exception {

        JFrame frame = new JFrame("Jasper report");

        JRViewer viewer = new JRViewer(PrintFileGenerator.generate());

        frame.add(viewer);
        frame.setSize(new Dimension(500, 400));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
