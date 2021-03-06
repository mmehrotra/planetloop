
package org.mule.modules.youtube.generated.processors;

import com.google.api.services.youtube.model.VideoListResponse;
import com.planetloop.integration.youtube.common.CommonConstants.CHART;
import com.planetloop.integration.youtube.common.CommonConstants.RATINGS;
import java.util.List;
import javax.annotation.Generated;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.config.ConfigurationException;
import org.mule.api.devkit.ProcessAdapter;
import org.mule.api.devkit.ProcessTemplate;
import org.mule.api.lifecycle.InitialisationException;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.registry.RegistrationException;
import org.mule.common.DefaultResult;
import org.mule.common.FailureType;
import org.mule.common.Result;
import org.mule.common.metadata.ConnectorMetaDataEnabled;
import org.mule.common.metadata.DefaultMetaData;
import org.mule.common.metadata.DefaultMetaDataKey;
import org.mule.common.metadata.DefaultPojoMetaDataModel;
import org.mule.common.metadata.DefaultSimpleMetaDataModel;
import org.mule.common.metadata.MetaData;
import org.mule.common.metadata.MetaDataKey;
import org.mule.common.metadata.MetaDataModel;
import org.mule.common.metadata.OperationMetaDataEnabled;
import org.mule.common.metadata.datatype.DataType;
import org.mule.common.metadata.datatype.DataTypeFactory;
import org.mule.devkit.internal.metadata.fixes.STUDIO7157;
import org.mule.devkit.processor.DevkitBasedMessageProcessor;
import org.mule.modules.youtube.YoutubeConnector;
import org.mule.security.oauth.callback.ProcessCallback;


/**
 * GetVideoDetailsMessageProcessor invokes the {@link org.mule.modules.youtube.YoutubeConnector#getVideoDetails(java.lang.String, java.lang.String, java.lang.String, com.planetloop.integration.youtube.common.CommonConstants.CHART, com.planetloop.integration.youtube.common.CommonConstants.RATINGS, java.lang.String, java.lang.String, java.lang.String)} method in {@link YoutubeConnector }. For each argument there is a field in this processor to match it.  Before invoking the actual method the processor will evaluate and transform where possible to the expected argument type.
 * 
 */
@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-01-29T01:26:03-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class GetVideoDetailsMessageProcessor
    extends DevkitBasedMessageProcessor
    implements MessageProcessor, OperationMetaDataEnabled
{

    protected Object key;
    protected String _keyType;
    protected Object access_token;
    protected String _access_tokenType;
    protected Object id;
    protected String _idType;
    protected Object chart;
    protected CHART _chartType;
    protected Object ratings;
    protected RATINGS _ratingsType;
    protected Object videoCategoryId;
    protected String _videoCategoryIdType;
    protected Object maxResults;
    protected String _maxResultsType;
    protected Object regionCode;
    protected String _regionCodeType;

    public GetVideoDetailsMessageProcessor(String operationName) {
        super(operationName);
    }

    /**
     * Obtains the expression manager from the Mule context and initialises the connector. If a target object  has not been set already it will search the Mule registry for a default one.
     * 
     * @throws InitialisationException
     */
    public void initialise()
        throws InitialisationException
    {
    }

    @Override
    public void start()
        throws MuleException
    {
        super.start();
    }

    @Override
    public void stop()
        throws MuleException
    {
        super.stop();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    /**
     * Sets access_token
     * 
     * @param value Value to set
     */
    public void setAccess_token(Object value) {
        this.access_token = value;
    }

    /**
     * Sets regionCode
     * 
     * @param value Value to set
     */
    public void setRegionCode(Object value) {
        this.regionCode = value;
    }

    /**
     * Sets ratings
     * 
     * @param value Value to set
     */
    public void setRatings(Object value) {
        this.ratings = value;
    }

    /**
     * Sets videoCategoryId
     * 
     * @param value Value to set
     */
    public void setVideoCategoryId(Object value) {
        this.videoCategoryId = value;
    }

    /**
     * Sets maxResults
     * 
     * @param value Value to set
     */
    public void setMaxResults(Object value) {
        this.maxResults = value;
    }

    /**
     * Sets id
     * 
     * @param value Value to set
     */
    public void setId(Object value) {
        this.id = value;
    }

    /**
     * Sets chart
     * 
     * @param value Value to set
     */
    public void setChart(Object value) {
        this.chart = value;
    }

    /**
     * Sets key
     * 
     * @param value Value to set
     */
    public void setKey(Object value) {
        this.key = value;
    }

    /**
     * Invokes the MessageProcessor.
     * 
     * @param event MuleEvent to be processed
     * @throws Exception
     */
    public MuleEvent doProcess(final MuleEvent event)
        throws Exception
    {
        Object moduleObject = null;
        try {
            moduleObject = findOrCreate(null, false, event);
            final String _transformedKey = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_keyType").getGenericType(), null, key));
            final String _transformedAccess_token = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_access_tokenType").getGenericType(), null, access_token));
            final String _transformedId = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_idType").getGenericType(), null, id));
            final CHART _transformedChart = ((CHART) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_chartType").getGenericType(), null, chart));
            final RATINGS _transformedRatings = ((RATINGS) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_ratingsType").getGenericType(), null, ratings));
            final String _transformedVideoCategoryId = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_videoCategoryIdType").getGenericType(), null, videoCategoryId));
            final String _transformedMaxResults = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_maxResultsType").getGenericType(), null, maxResults));
            final String _transformedRegionCode = ((String) evaluateAndTransform(getMuleContext(), event, GetVideoDetailsMessageProcessor.class.getDeclaredField("_regionCodeType").getGenericType(), null, regionCode));
            Object resultPayload;
            final ProcessTemplate<Object, Object> processTemplate = ((ProcessAdapter<Object> ) moduleObject).getProcessTemplate();
            resultPayload = processTemplate.execute(new ProcessCallback<Object,Object>() {


                public List<Class<? extends Exception>> getManagedExceptions() {
                    return null;
                }

                public boolean isProtected() {
                    return false;
                }

                public Object process(Object object)
                    throws Exception
                {
                    return ((YoutubeConnector) object).getVideoDetails(_transformedKey, _transformedAccess_token, _transformedId, _transformedChart, _transformedRatings, _transformedVideoCategoryId, _transformedMaxResults, _transformedRegionCode);
                }

            }
            , this, event);
            event.getMessage().setPayload(resultPayload);
            return event;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Result<MetaData> getInputMetaData() {
        return new DefaultResult<MetaData>(null, (Result.Status.SUCCESS));
    }

    @Override
    public Result<MetaData> getOutputMetaData(MetaData inputMetadata) {
        MetaDataModel metaDataPayload = getPojoOrSimpleModel(VideoListResponse.class);
        DefaultMetaDataKey keyForStudio = new DefaultMetaDataKey("OUTPUT_METADATA", null);
        metaDataPayload.addProperty(STUDIO7157 .getStructureIdentifierMetaDataModelProperty(keyForStudio, false, false));
        return new DefaultResult<MetaData>(new DefaultMetaData(metaDataPayload));
    }

    private MetaDataModel getPojoOrSimpleModel(Class clazz) {
        DataType dataType = DataTypeFactory.getInstance().getDataType(clazz);
        if (DataType.POJO.equals(dataType)) {
            return new DefaultPojoMetaDataModel(clazz);
        } else {
            return new DefaultSimpleMetaDataModel(dataType);
        }
    }

    public Result<MetaData> getGenericMetaData(MetaDataKey metaDataKey) {
        ConnectorMetaDataEnabled connector;
        try {
            connector = ((ConnectorMetaDataEnabled) findOrCreate(null, false, null));
            try {
                Result<MetaData> metadata = connector.getMetaData(metaDataKey);
                if ((Result.Status.FAILURE).equals(metadata.getStatus())) {
                    return metadata;
                }
                if (metadata.get() == null) {
                    return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error processing metadata at YoutubeConnector at getVideoDetails retrieving was successful but result is null");
                }
                return metadata;
            } catch (Exception e) {
                return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
            }
        } catch (ClassCastException cast) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), "There was an error getting metadata, there was no connection manager available. Maybe you're trying to use metadata from an Oauth connector");
        } catch (ConfigurationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (RegistrationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (IllegalAccessException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (InstantiationException e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        } catch (Exception e) {
            return new DefaultResult<MetaData>(null, (Result.Status.FAILURE), e.getMessage(), FailureType.UNSPECIFIED, e);
        }
    }

}
