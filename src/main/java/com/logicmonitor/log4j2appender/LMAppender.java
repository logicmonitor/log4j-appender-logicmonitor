package com.logicmonitor.log4j2appender;

import com.logicmonitor.sdk.data.Configuration;
import com.logicmonitor.sdk.data.api.Logs;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.status.StatusLogger;
import java.util.*;
import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;

/*
 * LogicMonitor Log4j2 log appender
 *
 */
@Plugin(name = "LMAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class LMAppender extends AbstractAppender {

	private static final Logger logger = StatusLogger.getLogger();

	private HashMap<String,String> metadata = new HashMap<>();
	private Map<String, String> resourceId= new HashMap<>();
	Logs lmLogs;

	public LMAppender(String name, Filter filter, Layout patternLayout) {
		super(name, filter, patternLayout);
		MyResponseHandler responseHandler = new MyResponseHandler();
		Configuration configuration=new Configuration();
		lmLogs= new Logs(configuration, 10, true, responseHandler);

	}

	@PluginFactory
	public static LMAppender createAppender(@PluginAttribute("name") String name,
												@PluginElement("Filter") final Filter filter, @PluginElement("pattern") final Layout patternLayout) {
		return new LMAppender(name, filter,patternLayout);
	}

	@Override
	public void append(LogEvent event) {
		HashMap<String,String> metadata = new HashMap<>();
		Map<String, String> resourceId= new HashMap<>();
		if(event.getLevel()!=null){
			metadata.put("LEVEL", String.valueOf(event.getLevel()));
		}

		if(event.getContextData()!=null) {
			Map<String, String> contextData = event.getContextData().toMap();
			for (Map.Entry<String, String> data : contextData.entrySet()) {
				if (data.getKey().equalsIgnoreCase("trace_id")) {
					metadata.put(data.getKey(), data.getValue());
				}
				if ( data.getKey().equalsIgnoreCase("span_id")) {
					metadata.put(data.getKey(), data.getValue());
				}
			}
		}
		String message = getLayout().toSerializable(event).toString();
		try {
			lmLogs.sendLogs(message, resourceId, metadata);
		} catch (Exception e) {
			logger.error("Error while sending logs ",e);
		}
	}

	static class MyResponseHandler implements ApiCallback<ApiResponse> {


		@Override
		public void onFailure(ApiException e, int i, Map<String, List<String>> map) {

		}

		@Override
		public void onSuccess(ApiResponse apiResponse, int i, Map<String, List<String>> map) {

		}

		@Override
		public void onUploadProgress(long l, long l1, boolean b) {

		}

		@Override
		public void onDownloadProgress(long l, long l1, boolean b) {

		}
	}

}
