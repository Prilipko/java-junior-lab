/*
 * Copyright (c) 2017 Tideworks Technology, Inc.
 */

package customLayout;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE_TIME;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Node;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.slf4j.MDC;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.util.Map;

/**
 * TODO: Change class description
 *
 * @author oleksandr.prylypko (oprylypk)
 * @since 0.10
 */
@Plugin(name = "NewCustLayout", category = Node.CATEGORY, elementType = Layout.ELEMENT_TYPE, printObject = true)
public class NewCustLayout extends AbstractStringLayout {
    final private Gson gson = new GsonBuilder().disableHtmlEscaping().serializeNulls().create();

    protected NewCustLayout(Charset charset) {
        super(charset);
    }

    @PluginFactory
    public static NewCustLayout createLayout() {
        return new NewCustLayout(Charset.forName("UTF-8"));
    }

    @Override
    public String toSerializable(LogEvent event) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("message", gson.toJsonTree(event.getMessage().getFormattedMessage()));

        if (event.getThrown() != null) {
            jsonObject.add("thrown", gson.toJsonTree(ExceptionUtils.getStackTrace(event.getThrown())));
        }

        jsonObject.add("level", gson.toJsonTree(event.getLevel().name()));
        jsonObject.add("thread_name", gson.toJsonTree(Thread.currentThread().getName()));
        jsonObject.add("class", gson.toJsonTree(event.getLoggerFqcn()));
        jsonObject.add("@timestamp", gson.toJsonTree(ZonedDateTime.now().format(ISO_OFFSET_DATE_TIME)));

        jsonObject.add("parameters", gson.toJsonTree(event.getMessage().getParameters()));

        Map mdc = MDC.getCopyOfContextMap();
        if (mdc != null && !mdc.isEmpty()) {
            jsonObject.add("mdc", gson.toJsonTree(mdc));
        }

        return gson.toJson(jsonObject) + '\n';
    }

}