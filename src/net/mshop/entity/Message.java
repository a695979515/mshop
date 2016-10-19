package net.mshop.entity;

import net.mshop.util.SpringUtils;

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

    public Message(String content, Type type) {
        this.content = content;
        this.type = type;
    }

    public Message(Message.Type type, String content, Object... args) {
        this.type = type;
        this.content = SpringUtils.getMessage(content, args);
    }

    /**
     * 成功消息返回
     * @param content
     * @param args
     * @return
     */
    public static Message success(String content, Object... args) {
        return new Message(Type.success, content, args);
    }

    /**
     * 警告消息返回
     * @param content
     * @param args
     * @return
     */
    public static Message warn(String content, Object... args) {
        return new Message(Type.warn, content, args);
    }

    /**
     * 错误消息返回
     * @param content
     * @param args
     * @return
     */
    public static Message error(String content, Object... args) {
        return new Message(Type.error, content, args);
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

    @Override
    public String toString() {
        return SpringUtils.getMessage(content);
    }
}
