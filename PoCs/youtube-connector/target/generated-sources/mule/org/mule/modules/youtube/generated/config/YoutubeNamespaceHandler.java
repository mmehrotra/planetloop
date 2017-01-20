
package org.mule.modules.youtube.generated.config;

import javax.annotation.Generated;
import org.mule.config.MuleManifest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * Registers bean definitions parsers for handling elements in <code>http://www.mulesoft.org/schema/mule/youtube</code>.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-01-19T08:25:21-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class YoutubeNamespaceHandler
    extends NamespaceHandlerSupport
{

    private static Logger logger = LoggerFactory.getLogger(YoutubeNamespaceHandler.class);

    private void handleException(String beanName, String beanScope, NoClassDefFoundError noClassDefFoundError) {
        String muleVersion = "";
        try {
            muleVersion = MuleManifest.getProductVersion();
        } catch (Exception _x) {
            logger.error("Problem while reading mule version");
        }
        logger.error(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [youtube] is not supported in mule ")+ muleVersion));
        throw new FatalBeanException(((((("Cannot launch the mule app, the  "+ beanScope)+" [")+ beanName)+"] within the connector [youtube] is not supported in mule ")+ muleVersion), noClassDefFoundError);
    }

    /**
     * Invoked by the {@link DefaultBeanDefinitionDocumentReader} after construction but before any custom elements are parsed. 
     * @see NamespaceHandlerSupport#registerBeanDefinitionParser(String, BeanDefinitionParser)
     * 
     */
    public void init() {
        try {
            this.registerBeanDefinitionParser("config", new YoutubeConnectorConnectorConfigConfigDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("config", "@Config", ex);
        }
        try {
            this.registerBeanDefinitionParser("search-videos", new SearchVideosDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("search-videos", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("search-most-popular", new SearchMostPopularDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("search-most-popular", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("search-most-recent-videos", new SearchMostRecentVideosDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("search-most-recent-videos", "@Processor", ex);
        }
        try {
            this.registerBeanDefinitionParser("search-videos-related-to-video-id", new SearchVideosRelatedToVideoIdDefinitionParser());
        } catch (NoClassDefFoundError ex) {
            handleException("search-videos-related-to-video-id", "@Processor", ex);
        }
    }

}
