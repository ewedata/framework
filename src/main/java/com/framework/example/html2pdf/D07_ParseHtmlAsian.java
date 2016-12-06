package com.framework.example.html2pdf;


import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.ElementList;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.css.StyleAttrCSSResolver;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.ElementHandlerPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


public class D07_ParseHtmlAsian {


    private final static String HTML = "e:\\test.html";

    private final static String PDF = "e:\\out.pdf";

    private final static String CSS = "e:\\en_css.css";

    /**
     * Creates a PDF with the words "Hello World"
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf() throws IOException, DocumentException {
        // step 1
        Document document = new Document();
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
        // step 3
        document.open();

        D07_ParseHtmlAsian.MyFontsProvider fontProvider = new D07_ParseHtmlAsian.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);

        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(HTML),
                new FileInputStream(CSS), Charset.forName("UTF-8"), fontProvider);
        // step 5
        document.close();
    }

    /**
     * 支持中文
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf2() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
        document.open();
        D07_ParseHtmlAsian.MyFontsProvider fontProvider = new D07_ParseHtmlAsian.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        // 使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);

        // pipelines
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
                new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer)));

        // XML worker
        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);
        File input = new File(HTML);
        p.parse(new InputStreamReader(new FileInputStream(input), "UTF-8"));
        document.close();
    }

    /**
     * 支持中文4
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf4() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
        document.open();
        D07_ParseHtmlAsian.MyFontsProvider fontProvider = new D07_ParseHtmlAsian.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        // 使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(new File(HTML)), new FileInputStream(new File(CSS)),
                Charset.forName("UTF-8"), fontProvider);
        document.close();
    }

    /**
     * 支持中文3
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf3() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
        document.open();
        D07_ParseHtmlAsian.MyFontsProvider fontProvider = new D07_ParseHtmlAsian.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        // 使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);


        CSSResolver cssResolver = new StyleAttrCSSResolver();
        CssFile cssFile = XMLWorkerHelper.getCSS(new ByteArrayInputStream(toByteArray(CSS)));
        cssResolver.addCss(cssFile);

        // HTML
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        // pipelines
        ElementList elements = new ElementList();
        ElementHandlerPipeline pdf = new ElementHandlerPipeline(elements, null);
        HtmlPipeline html = new HtmlPipeline(htmlContext, pdf);
        CssResolverPipeline css = new CssResolverPipeline(cssResolver, html);


        // XML worker
        XMLWorker worker = new XMLWorker(css, true);
        XMLParser p = new XMLParser(worker);
        p.parse(new InputStreamReader(new FileInputStream(new File(HTML)), "UTF-8"));

        document.close();
    }


    /**
     * Creates a PDF with the words "Hello World"
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf6() throws IOException, DocumentException {

        String remote_img_url = "https://www.baidu.com/";

        URL url = null;
        URLConnection urlconn = null;
        HttpURLConnection httpconn = null;
        BufferedInputStream bis = null;
        try {
            url = new URL(remote_img_url);
            urlconn = url.openConnection();
            // 1.5以后URLConnection设置连接超时，从主机读取数据超时 单位：毫秒
            /*
             * urlconn.setConnectTimeout(10000); urlconn.setReadTimeout(20000);
             */
            httpconn = (HttpURLConnection) urlconn;
            int httpResult = httpconn.getResponseCode();
            System.out.println("httpResult::" + httpResult);
            if (httpResult == HttpURLConnection.HTTP_OK) {
                int filesize = urlconn.getContentLength(); // 取数据长度
                byte[] b = new byte[filesize];
                bis = new BufferedInputStream(httpconn.getInputStream());
                
                // step 1
                Document document = new Document();
                // step 2
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
                // step 3
                document.open();

                D07_ParseHtmlAsian.MyFontsProvider fontProvider =
                        new D07_ParseHtmlAsian.MyFontsProvider();
                fontProvider.addFontSubstitute("lowagie", "garamond");
                fontProvider.setUseUnicode(true);

                // step 4
                XMLWorkerHelper.getInstance().parseXHtml(writer, document, bis,
                        new FileInputStream(CSS), Charset.forName("UTF-8"), fontProvider);
                // step 5
                document.close();
                
                int r = 0;
                while ((r = bis.read(b)) > 0);
                System.out.println("length::" + filesize);
                // System.out.println("string:"+new String(b,"UTF-8"));

              
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpconn != null) httpconn.disconnect();
            if (bis != null) {
                bis.close();
            }
        }



    }


    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    /**
     * Main method
     */
    public static void main(String[] args) throws IOException, DocumentException {
        createPdf();
    }

    public static class MyFontsProvider extends XMLWorkerFontProvider {
        public MyFontsProvider() {
            super(null, null);
        }

        @Override
        public Font getFont(final String fontname, String encoding, float size, final int style) {

            String fntname = fontname;
            if (fntname == null) {
                fntname = "宋体";
            }
            return super.getFont(fntname, encoding, size, style);
        }
    }
}
