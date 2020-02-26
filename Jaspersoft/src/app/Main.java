package app;

import java.sql.*;
import java.util.Scanner;
import java.io.File;
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

public class Main{
    
    public static void main(String[] args) throws JRException, SQLException, ClassNotFoundException{
        Scanner sc = new Scanner(System.in);

        String IP;
        String DB;
        String UserName;
        String Pass;
        String rutaBase;
        boolean iterate = true;

        System.out.println("BEM VINDO AO PROGRAMA");

        System.out.println("Por favor introduzca los parametros para realizar la conexión con la base de datos");
        System.out.println("\n Dirección IP \n =>");
        IP = sc.nextLine();

        System.out.println("\n Nombre de la Base de datos \n =>");
        DB = sc.nextLine();

        System.out.println("\n Usuario con el que se realizará la conexión \n =>");
        UserName = sc.nextLine();

        System.out.println("\n Contraseña del usuario proporcionado \n =>");
        Pass = sc.nextLine();

        System.out.println("\n Introduzca la ruta del informe que va a usar como base \n =>");
        rutaBase = sc.nextLine();

        System.out.println("\n En que formato quiere exportar el informe \n" +
                            "1- PDF \n" +
                            "2- XML \n" +
                            "3- HTML\n" +
                            "=>");

        String opcion = sc.nextLine();
        while(iterate){
            switch(opcion){
                case"1":
                    JasperReport jasperReport = JasperCompileManager.compileReport(rutaBase);
                    Connection conn = MySQLConnUtils.getMySQLConnection(IP, DB, UserName, Pass);
                    Map<String, Object> parameters = new HashMap<String, Object>();
                    JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);

                    System.out.println("\n Introduzca la ruta en la que quiere que se exporte el fichero");
                    String rutaExportar = sc.nextLine();

                    File outDir = new File(rutaExportar);
                    outDir.mkdirs();
                    JRPdfExporter exporter = new JRPdfExporter();
                    ExporterInput exporterInput = new SimpleExporterInput(print);
                    exporter.setExporterInput(exporterInput);

                    System.out.println("\n Introduzca el nombre que quiere que tenga el fichero");
                    String nombreFichero = sc.nextLine();
                    OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput("D:\\2T\\Interfaces\\" + nombreFichero + ".pdf");
                    exporter.setExporterOutput(exporterOutput);
                    SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
                    exporter.setConfiguration(configuration);
                    exporter.exportReport();

                    System.out.print("\n Se ha creado el fichero " + nombreFichero+ " con exito");

                    iterate = false;
                break;

                case "2":
                break;

                case"3":
                break;

                default:
                System.out.println("La opción introducida es invalida, por favor vuelva a intentarlo");
            }
        }        
    }
}