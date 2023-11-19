package com.levkip.fibpet.api.model;

public class Response {

    private boolean success = true;
    private Object data;

    public Response(boolean status, Object data) {
        this.success = status;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
