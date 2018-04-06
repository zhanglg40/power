package com.sms.util.fetcher;


public class FetchEntity {

    private int status;
    private byte[] data;
    private String contentType;
    private String contentEncoding;
    private String location;
    private boolean success = false;
    private String message;
    
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getContent(String charsetName) {
        try {
            if(data == null)
                return null;
            if(charsetName == null)
                return new String(data);
            return new String(data, charsetName);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public String getContent() {
        
        try {
            if(data == null)
                return null;
            return new String(data, "utf-8");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    public byte[] getData() {
        return data;
    }
    public void setData(byte[] data) {
        this.data = data;
    }
    public String getContentType() {
        return contentType;
    }
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    public String getContentEncoding() {
        return contentEncoding;
    }
    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    
}
