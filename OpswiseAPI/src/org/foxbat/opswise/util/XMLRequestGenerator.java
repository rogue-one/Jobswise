package org.foxbat.opswise.util;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.foxbat.opswise.core.OpswiseAPIManager;
import org.json.JSONObject;
import java.io.File;

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
        try { cfg.setDirectoryForTemplateLoading(new File("/")); } catch (IOException e) { e.printStackTrace();}
	}

	public static String generateRequestXML(JsonX json, String template_path) {

		try {
			Map<String, Object> map = new HashMap<>();
			String config = json.toString();
			map.put("config", config);
            System.out.println(template_path);
			Template template = cfg.getTemplate(template_path);
			Writer writer = new StringWriter();
			template.process(map, writer);
			return writer.toString();
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void main(String args[]) {
		try
		{
		OpswiseAPIManager opswise = new OpswiseAPIManager("/Users/chlr/dev_root/intellij/Jobswise/OpswiseAPI/config","na_opswise.json");
		JSONObject json = new JSONObject();
		json.put("name", "tgr_autobot_job2");
		json.put("task_name", "autobot_job2");
		json.put("user_name", "chlr");
		json.put("task_id", "");
		json.put("sys_id", "");
		JSONObject cron = new JSONObject();
		cron.put("minutes", "34");
		cron.put("hours", "*");
		cron.put("day_of_week", "*");
		cron.put("day_of_month", "*");
		cron.put("month", "*");
		json.put("cron", cron);
		opswise.getTriggerHandler().create(json);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


//        try
//        {
//    		OpswiseAPIManager opswise = new OpswiseAPIManager("/Users/chlr/dev_root/intellij/Jobswise/OpswiseAPI/config","na_opswise.json");
//            JSONObject json = new JSONObject();
//            json.put("name", "autobot_job2");
//            json.put("command", "echo All Hail Megatron");
//            json.put("agent", "pit-dev-owagent1 - AGNT0007");
//            JSONObject email = new JSONObject();
//            email.put("address", "mig.flanker@gmail.com");
//            email.put("connection", "Gmail - dw_etl");
//            json.put("email", email);
//            opswise.getTaskHandler().create(json);
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
	}

}