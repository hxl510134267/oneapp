package com.example.oneapp.gson;

import java.util.List;

public class Scan {

    public Attach attach;

    public class Attach {

        public String reason;

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

        public Result result;

        public class Result {

            public CodeInfo codeInfo;

            public class CodeInfo {

                public String bindFun;

                public String getBindFun() {
                    return bindFun;
                }

                public void setBindFun(String bindFun) {
                    this.bindFun = bindFun;
                }

                public String bindTime;

                public String getBindTime() {
                    return bindTime;
                }

                public void setBindTime(String bindTime) {
                    this.bindTime = bindTime;
                }

                public String hasBind;

                public String getHasBind() {
                    return hasBind;
                }

                public void setHasBind(String hasBind) {
                    this.hasBind = hasBind;
                }

                public String qrcodeKey;

                public String getQrcodeKey() {
                    return qrcodeKey;
                }

                public void setQrcodeKey(String qrcodeKey) {
                    this.qrcodeKey = qrcodeKey;
                }

                public String qrcodeUrl;

                public String getQrcodeUrl() {
                    return qrcodeUrl;
                }

                public void setQrcodeUrl(String qrcodeUrl) {
                    this.qrcodeUrl = qrcodeUrl;
                }

                public String shopName;

                public String getShopName() {
                    return shopName;
                }

                public void setShopName(String shopName) {
                    this.shopName = shopName;
                }

            }

            public List<WxAppPages> wxAppPages;

            public class WxAppPages {

                public String flag;

                public String getFlag() {
                    return flag;
                }

                public void setFlag(String flag) {
                    this.flag = flag;
                }

                public String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String page;

                public String getPage() {
                    return page;
                }

                public void setPage(String page) {
                    this.page = page;
                }

            }

        }

        public String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

    }

    public String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}