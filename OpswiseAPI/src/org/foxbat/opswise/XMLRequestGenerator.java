package org.foxbat.opswise;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class XMLRequestGenerator {

	private static Configuration cfg;

	static {
		cfg = new Configuration();
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	public static String generateRequestXML(JsonX json, String template_path) {

		try {
			Map<String, Object> map = new HashMap<String, Object>();
			String config = json.toString();
			map.put("config", config);
			Template template = cfg.getTemplate(template_path);
			Writer writer = new StringWriter();
			template.process(map, writer);
			System.out.println(writer.toString());
			return writer.toString();
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[]) {
		try {
			OpswiseAPIManager opswise = new OpswiseAPIManager();
			JSONObject json = new JSONObject();
			json.put("name", "autobot_job1");
			json.put("command", "All Hail Megatron");
			json.put("agent", "pit-prod-scrs");
			JSONObject email = new JSONObject();
			email.put("address", "mig.flanker@gmail.com");
			email.put("connection", "localhost");
			json.put("email", email);
			System.out.println(opswise.getTaskHandler().create(json).toString());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
