package com.mk.taskfactory.biz.utils;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Map;

 /**
 * Created by kangxiaolong.
 */
public class FreeMarkerTemplateUtils {
    private final static String Template_Dir = "/templates";
    private final static Configuration config = new Configuration();

    static{
        TemplateLoader templateLoader = new ClassTemplateLoader(FreeMarkerTemplateUtils.class, Template_Dir);
        config.setTemplateLoader(templateLoader);
        config.setObjectWrapper(new DefaultObjectWrapper());
        config.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
        config.setDefaultEncoding("utf-8");
    }

    public static String process(Map map, String templateName) throws IOException, TemplateException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Template template = config.getTemplate(templateName);
        template.process(map, new OutputStreamWriter(outputStream));
        String content = outputStream.toString();
        content = content.substring(content.indexOf("<"));
        return content;
    }

}
