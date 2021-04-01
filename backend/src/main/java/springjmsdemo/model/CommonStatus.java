package springjmsdemo.model;

import java.io.Serializable;
import java.util.Map;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class CommonStatus implements Serializable {
    private int senderId;
    private Map<String, String> statusValues;

    public CommonStatus() {
    }

    public CommonStatus(int senderId, Map<String, String> statusValues) {
        this.senderId = senderId;
        this.statusValues = statusValues;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public Map<String, String> getStatusValues() {
        return statusValues;
    }

    public void setStatusValues(Map<String, String> statusValues) {
        this.statusValues = statusValues;
    }
}

