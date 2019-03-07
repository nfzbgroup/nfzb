package com.wonders.fzb.platform.compose;

@SuppressWarnings("serial")
public class ComposeException extends Exception{

    private Exception _composeException;

    public ComposeException(Exception e) {
        _composeException = e;
    }

    public ComposeException() {
        super();
    }

    public ComposeException(String msg) {
        super(msg);
    }

    public String toString(){
        String exceptionMsg = "ComposeException with nested exception " + _composeException.toString();
        _composeException.printStackTrace();
        return exceptionMsg;
    }

}