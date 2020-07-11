package com.cskaoyan.mall.model;

import com.cskaoyan.mall.bo.MsgGoodsBO;
import com.cskaoyan.mall.bo.MsgUserBO;

import java.sql.Date;

public class Msg {
    private int id;
    private int userId;
    private int goodsId;
    private String content;//提问的内容
    private String replyContent;
    private byte state;//0代表已经回复，1代表未回复
    private Date createtime;
    private MsgGoodsBO goods;
    private MsgUserBO user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public byte getState() {
        return state;
    }

    public void setState(byte state) {
        this.state = state;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public MsgGoodsBO getGoods() {
        return goods;
    }

    public void setGoods(MsgGoodsBO goods) {
        this.goods = goods;
    }

    public MsgUserBO getUser() {
        return user;
    }

    public void setUser(MsgUserBO user) {
        this.user = user;
    }
}
