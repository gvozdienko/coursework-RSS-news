package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws IOException {
        window(readRSS("https://www.0629.com.ua/rss"));
    }
    public static void window(String string) {
        JFrame window = new JFrame("RSS-news");
        window.setVisible(true);
        JLabel label = new JLabel(string, JLabel.CENTER);
        JTextArea area = new JTextArea(string);
        JScrollPane scroll = new JScrollPane(area,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        window.setSize(1200, 800);
        window.setLocationRelativeTo(null);
        window.add(scroll);

    }
    public static String readRSS(String urlAddress) throws IOException {
        try {
            URL rssUr1 = new URL(urlAddress);
            BufferedReader in = new BufferedReader(new InputStreamReader(rssUr1.openStream()));
            String sourceCode = "";
            String line;

            while ((line = in.readLine()) != null) {
                if (line.contains("<title>")) {
                    int firstPos = line.indexOf("<title>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<title>", "");
                    temp = temp.replace("<![CDATA[","");
                    temp = temp.replace("]]>","");
                    temp = temp.replace("&amp;laquo;","«");
                    temp = temp.replace("&amp;raquo;","»");
                    temp = temp.replace("&amp;nbsp;"," ");
                    temp = temp.replace("&amp;ndash;","–");
                    temp = temp.replace("&quot;","\"" );
                    int lastPos = temp.indexOf("</title>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";


                }
                if (line.contains("<description>")) {
                    int firstPos = line.indexOf("<description>");
                    String temp = line.substring(firstPos);
                    temp = temp.replace("<description>", "");
                    temp = temp.replace("<![CDATA[","");
                    temp = temp.replace("]]>","");
                    temp = temp.replace("&amp;laquo;","«");
                    temp = temp.replace("&amp;raquo;","»");
                    temp = temp.replace("&amp;nbsp;"," ");
                    temp = temp.replace("&amp;ndash;","–");
                    temp = temp.replace("&quot;","\"" );
                    int lastPos = temp.indexOf("</description>");
                    temp = temp.substring(0, lastPos);
                    sourceCode += temp + "\n";

                }
                if (line.contains("<content:encoded>")) {

                    int firstPos = line.indexOf("<content:encoded>");

                    String temp = line.substring(firstPos);
                    while(!line.contains("</content:encoded>")){
                        line = in.readLine();
                        temp+="\n"+line;

                    }
                    if(line.contains("</content:encoded>")){
                        temp+=line;
                    }
                    temp = temp.replace("<content:encoded>", "");
                    temp = temp.replace("<![CDATA[", "");
                    temp = temp.replace("]]>", "");
                    temp = temp.replace("&amp;laquo;","«");
                    temp = temp.replace("&amp;raquo;","»");
                    temp = temp.replace("&amp;nbsp;"," ");
                    temp = temp.replace("&amp;ndash;","–");
                    temp = temp.replace("&quot;","\"" );
                    int lastPos = temp.indexOf("</content:encoded>");
                    temp = temp.substring(0, lastPos);
                    temp = temp.replace("</content:encoded>", "");
                    sourceCode += temp + "\n_______________________________" + "\n";


                }

            }
            in.close();
            return sourceCode;
        } catch (MalformedURLException ue) {
            System.out.println("Malformed URL");
        } catch (IOException ioe) {
            System.out.println("Something went wrong reading the contents");
        }
        return null;
    }
}