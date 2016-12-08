package com.framework.example.html2pdf;


import java.io.BufferedInputStream;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.log.Level;
import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.itextpdf.tool.xml.NoCustomContextException;
import com.itextpdf.tool.xml.Pipeline;
import com.itextpdf.tool.xml.Tag;
import com.itextpdf.tool.xml.WorkerContext;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerFontProvider;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.css.CssFile;
import com.itextpdf.tool.xml.exceptions.LocaleMessages;
import com.itextpdf.tool.xml.exceptions.RuntimeWorkerException;
import com.itextpdf.tool.xml.html.CssAppliers;
import com.itextpdf.tool.xml.html.CssAppliersImpl;
import com.itextpdf.tool.xml.html.HTML;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.AbstractImageProvider;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;


public class HtmlPdfConvert {


    private final static String S_HTML = "e:\\in.html";

    private final static String PDF = "e:\\out.pdf";

    private final static String CSS = "e:\\texthtml.css";

    /**
     * Main method
     */
    public static void main(String[] args) throws IOException, DocumentException {
        createPdf2();
    }

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

        HtmlPdfConvert.MyFontsProvider fontProvider = new HtmlPdfConvert.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);

        // step 4
        XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream(S_HTML),
                new FileInputStream(CSS), Charset.forName("UTF-8"), fontProvider);
        // step 5
        document.close();
    }

    /**
     * 图片内容在html页面中时，使用以下方法
     * 
     * @param file
     * @throws IOException
     * @throws DocumentException
     */
    public static void createPdf2() throws IOException, DocumentException {

        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(PDF));
        document.open();
        HtmlPdfConvert.MyFontsProvider fontProvider = new HtmlPdfConvert.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        // 使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);

        // css
        CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(true);
        CssFile cssFile = XMLWorkerHelper.getCSS(new FileInputStream(CSS));
        cssResolver.addCss(cssFile);


        // html
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());
        htmlContext.setImageProvider(new Base64ImageProvider());


        // pipelines
        Pipeline<?> pipeline = new CssResolverPipeline(cssResolver,
                new HtmlPipeline(htmlContext, new PdfWriterPipeline(document, writer)));

        // XML worker
        XMLWorker worker = new XMLWorker(pipeline, true);
        XMLParser p = new XMLParser(worker);
        File input = new File(S_HTML);
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
        HtmlPdfConvert.MyFontsProvider fontProvider = new HtmlPdfConvert.MyFontsProvider();
        fontProvider.addFontSubstitute("lowagie", "garamond");
        fontProvider.setUseUnicode(true);
        // 使用我们的字体提供器，并将其设置为unicode字体样式
        CssAppliers cssAppliers = new CssAppliersImpl(fontProvider);
        HtmlPipelineContext htmlContext = new HtmlPipelineContext(cssAppliers);
        htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

        XMLWorkerHelper.getInstance().parseXHtml(writer, document,
                new FileInputStream(new File(S_HTML)), new FileInputStream(new File(CSS)),
                Charset.forName("UTF-8"), fontProvider);
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

                HtmlPdfConvert.MyFontsProvider fontProvider =
                        new HtmlPdfConvert.MyFontsProvider();
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

    private static class Base64ImageProvider extends AbstractImageProvider {

        @Override
        public Image retrieve(String src) {
            int pos = src.indexOf("base64,");
            try {
                if (src.startsWith("data") && pos > 0) {
                    byte[] img = Base64.decode(src.substring(pos + 7));
                    return Image.getInstance(img);
                } else {
                    return Image.getInstance(src);
                }
            } catch (BadElementException ex) {
                return null;
            } catch (IOException ex) {
                return null;
            }
        }

        @Override
        public String getImageRootPath() {
            return null;
        }
    }

    private class ImageTagProcessor extends com.itextpdf.tool.xml.html.Image {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        @Override
        public List<Element> end(WorkerContext ctx, Tag tag, List<Element> currentContent) {

            final Map<String, String> attributes = tag.getAttributes();
            String src = attributes.get(HTML.Attribute.SRC);
            List<Element> elements = new ArrayList<Element>(1);
            if (null != src && src.length() > 0) {
                Image img = null;
                if (src.startsWith("data:image/")) {
                    final String base64Data = src.substring(src.indexOf(",") + 1);
                    try {
                        img = Image.getInstance(Base64.decode(base64Data));
                    } catch (Exception e) {
                        if (logger.isLogging(Level.ERROR)) {
                            logger.error(String.format(LocaleMessages.getInstance()
                                    .getMessage(LocaleMessages.HTML_IMG_RETRIEVE_FAIL), src), e);
                        }
                    }
                    if (img != null) {
                        try {
                            final HtmlPipelineContext htmlPipelineContext =
                                    getHtmlPipelineContext(ctx);
                            elements.add(getCssAppliers().apply(
                                    new Chunk((com.itextpdf.text.Image) getCssAppliers().apply(img,
                                            tag, htmlPipelineContext), 0, 0, true),
                                    tag, htmlPipelineContext));
                        } catch (NoCustomContextException e) {
                            throw new RuntimeWorkerException(e);
                        }
                    }
                }

                if (img == null) {
                    elements = super.end(ctx, tag, currentContent);
                }
            }
            return elements;

        }

    }
}


