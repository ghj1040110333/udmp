package cn.com.git.udmp.test.common.gen;

import java.io.IOException;
import java.util.Map;

import cn.com.git.udmp.common.utils.FreeMarkers;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class GenTest {
    public static void main(String[] args) throws IOException {
        Map<String, String> model = com.google.common.collect.Maps.newHashMap();
      model.put("packageName", "BatchTask");
//      String result = FreeMarkers.renderString("hello ${userName}", model);
//      System.out.println(result);
//      // renderTemplate
        Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
        /**
        Template template = cfg.getTemplate("service.xml");
         */
        Template template = cfg.getTemplate("ucc.xml");
        String result2 = FreeMarkers.renderTemplate(template, model);
        System.out.println(result2);
    }
}
