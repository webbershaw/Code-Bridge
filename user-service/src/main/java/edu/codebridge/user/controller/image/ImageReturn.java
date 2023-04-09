package edu.codebridge.user.controller.image;

public class ImageReturn{

    int errno;

    String message;

    Data data;

    public ImageReturn(int errno, String message) {
        this.errno = errno;
        this.message = message;
    }

    public ImageReturn(int errno, Data data) {
        this.errno = errno;
        this.data = data;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
