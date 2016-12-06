package com.framework.example.html2pdf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.html.simpleparser.StyleSheet;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class ConvertDemo {

    private final static String HTML = "e:\\aaa.html";

    private final static String PDF = "e:\\out.pdf";

    public static void main(String[] args) throws Exception {

        // ConvertDemo ih = new ConvertDemo();
        htmlCodeComeFromFile(HTML, PDF);
        // ih.htmlCodeComeString("e:\\aaa.html", "e:\\test.pdf");
        // htmlToPdf();
        createPdf(HTML);
    }

    public static void createPdf(String file) throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
        // step 3
        document.open();
        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML),
                Charset.forName("UTF-8"));
        // step 5
        document.close();
    }

    public static void htmlCodeComeFromFile(String filePath, String pdfPath) {
        Document document = new Document();
        try {
            StyleSheet st = new StyleSheet();
            st.loadTagStyle("body", "leading", "16,0");
            PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
            document.open();
            ArrayList p = (ArrayList) HTMLWorker.parseToList(new FileReader(filePath), st);
            for (int k = 0; k < p.size(); ++k) {
                document.add((Element) p.get(k));
            }
            document.close();
            System.out.println("文档创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void htmlCodeComeString(String htmlCode, String pdfPath) {
        Document doc = new Document(PageSize.A4);
        try {
            PdfWriter.getInstance(doc, new FileOutputStream(pdfPath));
            doc.open();
            // 解决中文问题
            // BaseFont bfChinese =
            // BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            // Font FontChinese = new Font(bfChinese, 12, Font.BOLD);
            Paragraph t = new Paragraph(htmlCode);
            doc.add(t);
            doc.close();
            System.out.println("文档创建成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void htmlToPdf() throws Exception {
        HttpURLConnection urlConnection = null;
        try {
            String apiEndpoint = "http://selectpdf.com/api2/convert/";
            String key = "your license key here";
            String testUrl = "http://www.17zhiliao.com";
            File localFile = new File("d:\\test.pdf");

            Map<String, Object> parameters = new LinkedHashMap<>();
            parameters.put("key", key);
            parameters.put("url", testUrl);

            String encodedParameters = encodeParameters(parameters);

            URL apiUrl = new URL(apiEndpoint + "?" + encodedParameters);
            urlConnection = (HttpURLConnection) apiUrl.openConnection();
            BufferedInputStream inputStream =
                    new BufferedInputStream(urlConnection.getInputStream());
            BufferedOutputStream outputStream =
                    new BufferedOutputStream(new FileOutputStream(localFile));

            byte[] b = new byte[8 * 1024];
            int read = 0;
            while ((read = inputStream.read(b)) > -1) {
                outputStream.write(b, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();

            System.out.println("Test pdf document generated successfully!");
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            if (urlConnection != null) {
                if (urlConnection.getResponseCode() != 200) {
                    System.out.println("HTTP Response Code: " + urlConnection.getResponseCode());
                    System.out.println(
                            "HTTP Response Message: " + urlConnection.getResponseMessage());
                }
            }
        }
    }


    private static String encodeParameters(Map<String, Object> params) throws Exception {
        try {
            StringBuilder data = new StringBuilder();
            for (Map.Entry<String, Object> param : params.entrySet()) {
                if (data.length() != 0) data.append('&');
                data.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                data.append('=');
                data.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }

            return data.toString();
        } catch (Exception ex) {
            throw (ex);
        }
    }


}
