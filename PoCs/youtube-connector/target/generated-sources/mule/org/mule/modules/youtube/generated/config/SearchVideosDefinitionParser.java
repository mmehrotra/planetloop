
package org.mule.modules.youtube.generated.config;

import javax.annotation.Generated;
import org.mule.common.MuleVersion;
import org.mule.config.MuleManifest;
import org.mule.modules.youtube.generated.processors.SearchVideosMessageProcessor;
import org.mule.modules.youtube.generated.processors.SearchVideosMessageProcessorDebuggable;
import org.mule.security.oauth.config.AbstractDevkitBasedDefinitionParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.parsing.BeanDefinitionParsingException;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.beans.factory.parsing.Problem;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-01-19T08:25:21-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class SearchVideosDefinitionParser
    extends AbstractDevkitBasedDefinitionParser
{

    private static Logger logger = LoggerFactory.getLogger(SearchVideosDefinitionParser.class);

    private BeanDefinitionBuilder getBeanDefinitionBuilder(ParserContext parserContext) {
        try {
            MuleVersion muleVersion = new MuleVersion(MuleManifest.getProductVersion());
            BeanDefinitionBuilder beanDefinitionBuilder;
            if (muleVersion.atLeastBase("3.8.0")) {
                beanDefinitionBuilder = beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SearchVideosMessageProcessorDebuggable.class.getName());
            } else {
                beanDefinitionBuilder = beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(SearchVideosMessageProcessor.class.getName());
            }
            return beanDefinitionBuilder;
        } catch (NoClassDefFoundError noClassDefFoundError) {
            String muleVersion = "";
            try {
                muleVersion = MuleManifest.getProductVersion();
            } catch (Exception _x) {
                logger.error("Problem while reading mule version");
            }
            logger.error(("Cannot launch the mule app, the @Processor [search-videos] within the connector [youtube] is not supported in mule "+ muleVersion));
            throw new BeanDefinitionParsingException(new Problem(("Cannot launch the mule app, the @Processor [search-videos] within the connector [youtube] is not supported in mule "+ muleVersion), new Location(parserContext.getReaderContext().getResource()), null, noClassDefFoundError));
        }
    }

    public BeanDefinition parse(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder builder = getBeanDefinitionBuilder(parserContext);
        builder.addConstructorArgValue("searchVideos");
        builder.setScope(BeanDefinition.SCOPE_PROTOTYPE);
        if (!hasAttribute(element, "config-ref")) {
            throw new BeanDefinitionParsingException(new Problem("It seems that the config-ref for @Processor [search-videos] within the connector [youtube] is null or missing. Please, fill the value with the correct global element.", new Location(parserContext.getReaderContext().getResource()), null));
        }
        parseConfigRef(element, builder);
        parseProperty(builder, element, "key", "key");
        parseProperty(builder, element, "query", "query");
        parseProperty(builder, element, "regionCode", "regionCode");
        parseProperty(builder, element, "order", "order");
        parseProperty(builder, element, "maxResults", "maxResults");
        parseProperty(builder, element, "videoDuration", "videoDuration");
        BeanDefinition definition = builder.getBeanDefinition();
        setNoRecurseOnDefinition(definition);
        attachProcessorDefinition(parserContext, definition);
        return definition;
    }

}
