package com.levkip.fibpet.api.model;

public class Response<T> {

    private boolean success = true;
    private T data;

    public Response(boolean status, T data) {
        this.success = status;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
