
package org.mule.modules.youtube.generated.adapters;

import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleMessage;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.routing.filter.Filter;
import org.mule.modules.youtube.YoutubeConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * A <code>YoutubeConnectorProcessAdapter</code> is a wrapper around {@link YoutubeConnector } that enables custom processing strategies.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-01-19T08:25:21-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class YoutubeConnectorProcessAdapter
    extends YoutubeConnectorLifecycleInjectionAdapter
    implements ProcessAdapter<YoutubeConnectorCapabilitiesAdapter>
{


    public<P >ProcessTemplate<P, YoutubeConnectorCapabilitiesAdapter> getProcessTemplate() {
        final YoutubeConnectorCapabilitiesAdapter object = this;
        return new ProcessTemplate<P,YoutubeConnectorCapabilitiesAdapter>() {


            @Override
            public P execute(ProcessCallback<P, YoutubeConnectorCapabilitiesAdapter> processCallback, MessageProcessor messageProcessor, MuleEvent event)
                throws Exception
            {
                return processCallback.process(object);
            }

            @Override
            public P execute(ProcessCallback<P, YoutubeConnectorCapabilitiesAdapter> processCallback, Filter filter, MuleMessage message)
                throws Exception
            {
                return processCallback.process(object);
            }

        }
        ;
    }

}
