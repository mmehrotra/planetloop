
package com.planetloop.integration.youtube.common.generated.transformers;

import com.planetloop.integration.youtube.common.CommonConstants.VIDEO_DURATION;
import javax.annotation.Generated;
import org.mule.api.transformer.DiscoverableTransformer;
import org.mule.api.transformer.TransformerException;
import org.mule.transformer.AbstractTransformer;
import org.mule.transformer.types.DataTypeFactory;

@SuppressWarnings("all")
@Generated(value = "Mule DevKit Version 3.9.0", date = "2017-01-29T01:26:03-05:00", comments = "Build UNNAMED.2793.f49b6c7")
public class CommonConstantsVIDEO_DURATIONEnumTransformer
    extends AbstractTransformer
    implements DiscoverableTransformer
{

    private int weighting = DiscoverableTransformer.DEFAULT_PRIORITY_WEIGHTING;

    public CommonConstantsVIDEO_DURATIONEnumTransformer() {
        registerSourceType(DataTypeFactory.create(String.class));
        setReturnClass(VIDEO_DURATION.class);
        setName("CommonConstantsVIDEO_DURATIONEnumTransformer");
    }

    protected Object doTransform(Object src, String encoding)
        throws TransformerException
    {
        VIDEO_DURATION result = null;
        result = Enum.valueOf(VIDEO_DURATION.class, ((String) src));
        return result;
    }

    public int getPriorityWeighting() {
        return weighting;
    }

    public void setPriorityWeighting(int weighting) {
        this.weighting = weighting;
    }

}
