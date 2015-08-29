package eu.m0k.lol.api;

/**
 * Created by Don on 19/08/2015.
 */
public class Response<T> {
    private int mCode;
    private T mData;

    public Response(T data, int code) {
        mData = data;
        mCode = code;
    }

    public int getCode() {
        return mCode;
    }

    public T getData() {
        return mData;
    }

    public boolean isSuccess() {
        return this.mCode == 200;
    }
}
