package cn.fcrj.myapplication.global;

/**
 * Created by zht on 2017/04/07 9:51
 */

public class BaseEvent {

    public static final int LOG_IN_OUT = 100;



    public static BaseEvent event(int code){
        return new BaseEvent(code);
    }

    public static BaseEvent event(int code,Object assign){
        return new BaseEvent(code,assign);
    }
    
    private int code = 0;
    private Object assign;
    
    public BaseEvent(int code){
        this(code, null);
    }
    
    public BaseEvent(int code, Object assign) {
        this.code = code;
        this.assign = assign;
    }

    public int getCode() {
        return code;
    }

    public Object getAssign() {
        return assign;
    }
    
    
}
