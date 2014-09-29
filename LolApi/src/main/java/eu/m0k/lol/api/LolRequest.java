package eu.m0k.lol.api;

import java.util.HashMap;
import java.util.Map;

import eu.m0k.lol.api.model.Region;

/**
 * Created by Don on 24.09.2014.
 */
public abstract class LolRequest {
    private final String mUrl;
    private final Region mRegion;
    private Map<String, String> mParameter = new HashMap<String, String>();

    public LolRequest(Region region, String url) {
        this.mRegion = region;
        this.mUrl = url;
    }

    public void addParameter(String key, String value) {
        this.mParameter.put(key, value);
    }

    public Map<String, String> getParameter() {
        return this.mParameter;
    }

    public String getUrl() {
        return this.mUrl.replaceAll("\\{region\\}", this.mRegion.toString()) + "?" + getParamString();
    }

    public String getParamString() {
        if (this.mParameter.size() == 0) {
            return "";
        }
        String ret = "";
        for (String key : this.mParameter.keySet()) {
            if (ret.length() != 0) {
                ret += "&";
            }
            ret += key + "=" + this.mParameter.get(key);
        }
        return ret;
    }
}
