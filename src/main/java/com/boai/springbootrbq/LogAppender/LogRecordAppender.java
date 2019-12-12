package com.boai.springbootrbq.LogAppender;

import com.boai.springbootrbq.Util.LogUtil;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Core;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

@Plugin(name = "LogRecordAppender", category = Core.CATEGORY_NAME, elementType = Appender.ELEMENT_TYPE)
public class LogRecordAppender extends AbstractAppender {

    private static final Logger logger = LoggerFactory.getLogger(LogRecordAppender.class);

    private ConcurrentHashMap<String, LogEvent> eventMap = new ConcurrentHashMap<>();

    public LogRecordAppender(String name, Filter filter) {
        super(name, filter, null);
    }

    @PluginFactory
    public static LogRecordAppender createApppender(@PluginAttribute("name") String name,
                                                    @PluginElement("filter") Filter filter) {
        return new LogRecordAppender(name, filter);
    }

    @Override
    public void append(LogEvent logEvent) {
        if(logEvent.getLevel().equals(Level.ERROR)) {
            LogUtil.info(logEvent.getMessage().toString());
        }
    }
}
