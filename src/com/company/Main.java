package com.company;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println(readRSS("https://www.0629.com.ua/rss"));

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

                       int lastPos = temp.indexOf("</content:encoded>");
                        temp = temp.substring(0, lastPos);
                    temp = temp.replace("</content:encoded>", "");
                        sourceCode += temp + "\n";


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