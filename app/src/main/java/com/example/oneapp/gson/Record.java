package com.example.oneapp.gson;

import java.util.List;

public class Record {

    public Attach attach;

    public String code;

    public String msg;

    public class Attach {

        public String hasNext;

        public List<LList> list;

        public String total;

        public String getHasNext() {
            return hasNext;
        }

        public void setHasNext(String hasNext) {
            this.hasNext = hasNext;
        }

        public class LList {

            public String bindFun;

            public String bindTime;

            public String hasBind;

            public String qrcodeKey;

            public String qrcodeUrl;

            public String shopName;

            public String getBindFun() {
                return bindFun;
            }

            public void setBindFun(String bindFun) {
                this.bindFun = bindFun;
            }

            public String getBindTime() {
                return bindTime;
            }

            public void setBindTime(String bindTime) {
                this.bindTime = bindTime;
            }

            public String getHasBind() {
                return hasBind;
            }

            public void setHasBind(String hasBind) {
                this.hasBind = hasBind;
            }

            public String getQrcodeKey() {
                return qrcodeKey;
            }

            public void setQrcodeKey(String qrcodeKey) {
                this.qrcodeKey = qrcodeKey;
            }

            public String getQrcodeUrl() {
                return qrcodeUrl;
            }

            public void setQrcodeUrl(String qrcodeUrl) {
                this.qrcodeUrl = qrcodeUrl;
            }

            public String getShopName() {
                return shopName;
            }

            public void setShopName(String shopName) {
                this.shopName = shopName;
            }

        }

        public String getTotal() {
            return total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
