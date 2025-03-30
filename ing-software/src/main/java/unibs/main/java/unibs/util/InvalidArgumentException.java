package unibs.util;

public class InvalidArgumentException extends Exception{
    private String msg;
    public InvalidArgumentException(){}
    public InvalidArgumentException(String arg){
        msg=arg;
    }
    public String getMsg(){
        return msg;
    }
}