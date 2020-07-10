package com.cskaoyan.mall.vo;

public class StatesVO {
        public int id;
        public String name;

        public StatesVO() {
            this.id=0;
            this.name=null;
        }

        public StatesVO(int id, String name){
            this.id=id;
            this.name=name;
        }
        @Override
        public String toString() {
            return "StatesVO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
}
