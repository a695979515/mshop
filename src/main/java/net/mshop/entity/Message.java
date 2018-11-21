package net.mshop.entity;


/**
 * 消息实体类
 * Created by Panfuhao on 2016/10/19.
 */
public class Message {
    public enum Type {
        success,//成功
        warn,   //警告
        error   //错误
    }

    /**
     * 消息类型
     */
    private Message.Type type;
    /**
     * 消息内容
     */
    private String content;

    public Message() {
    }



    public Message(Message.Type type, String content) {
        this.type = type;
        this.content = content;
    }

    /**
     * 成功消息返回
     * @param content
     * @return
     */
    public static Message success(String content) {
        return new Message(Type.success, content);
    }

    /**
     * 警告消息返回
     * @param content
     * @return
     */
    public static Message warn(String content) {
        return new Message(Type.warn, content);
    }

    /**
     * 错误消息返回
     * @param content
     * @return
     */
    public static Message error(String content) {
        return new Message(Type.error, content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


}
