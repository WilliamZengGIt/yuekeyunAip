package org.ilong.yuekeyun.bean;

/**
 * 信息返回实体类
 *
 * @author long
 * @date 2020-10-10 16:05
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object obj;
    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object obj) {
        return new RespBean(200, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }
    public static RespBean error( Object obj) {
        return new RespBean(500, obj);
    }

    public static RespBean error(Integer status){
        return new RespBean(status);
    }

    public static RespBean UserIsEmpty(){
        return RespBean.error("当前用户已失效，请重新登录！",401);
    }

    private RespBean() {
    }
    private RespBean(Integer status) {
        this.status = status;
    }
    private RespBean(Object obj) {
        this.obj = obj;
    }

    private RespBean(Integer status,Object obj) {
        this.status = status;
        this.obj = obj;
    }
    private RespBean(Integer status, String msg, Object obj) {
        this.status = status;
        this.msg = msg;
        this.obj = obj;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObj() {
        return obj;
    }

    public void setObj(Object obj) {
        this.obj = obj;
    }
}
