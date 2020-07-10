package com.cskaoyan.mall.bo;

import com.cskaoyan.mall.vo.SpecVO;
import com.cskaoyan.mall.vo.StatesVO;

import java.util.Arrays;

public class OrderBO {
    private Integer id;
    private Double amount;
    private Integer num;//goodsNum
    private int goodsDetailId;
    private int state;//stateId
    private String goods;
    private int goodsId;
    private SpecVO[] spec;

    private StatesVO[] states;
    private CurState curState;
    private CurSpec curSpec;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public void setGoodsDetailId(int goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getGoodsDetailId() {
        return goodsDetailId;
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setGoodsDetailId(Integer goodsDetailId) {
        this.goodsDetailId = goodsDetailId;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public SpecVO[] getSpec() {
        return spec;
    }

    public void setSpec(SpecVO[] spec) {
        this.spec = spec;
    }

    public StatesVO[] getStates() {
        return states;
    }

    public void setStates(StatesVO[] statesVOS) {
        states=statesVOS;
    }
    public void setStatesArray(){
        states[0]=new StatesVO(0,"未付款");
        states[1]=new StatesVO(1,"已付款");
        states[2]=new StatesVO(2,"已发货");
        states[3]=new StatesVO(3,"已到货");
    }

    public CurState getCurSate() {
        return curState;
    }

    public CurSpec getCurSpec() {
        return curSpec;
    }

    public void setCurState() {
        this.curState=new CurState(this.state);
    }

    public void setCurSpec() {
        this.curSpec=new CurSpec(this.goodsDetailId);
    }


    public class CurState{
        public int id;

        public CurState(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "CurState{" +
                    "id=" + id +
                    '}';
        }
    }
    public class CurSpec{
        public int id;

        public CurSpec(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "CurSpec{" +
                    "id=" + id +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "OrderBO{" +
                "id=" + id +
                ", amount=" + amount +
                ", num=" + num +
                ", goodsDetailId=" + goodsDetailId +
                ", state=" + state +
                ", goods='" + goods + '\'' +
                ", goodsId=" + goodsId +
                ", spec=" + Arrays.toString(spec) +
                ", states=" + Arrays.toString(states) +
                ", curState=" + curState +
                ", curSpec=" + curSpec +
                '}';
    }
}
