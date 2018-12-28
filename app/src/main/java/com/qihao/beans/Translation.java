package com.qihao.beans;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2018/12/27 14:58
 */
public class Translation {
    /**
     *
     * {
     * 	"status": 1,
     * 	"content": {
     * 		"from": "en-EU",
     * 		"to": "zh-CN",
     * 		"out": "示例",
     * 		"vendor": "ciba",
     * 		"err_no": 0
     *        }
     * }
     *
     */
    private int status;
    private content content;

    private static class content {
        private String from;
        private String to;
        private String vendor;
        private String out;
        private int errNo;

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        public String getVendor() {
            return vendor;
        }

        public void setVendor(String vendor) {
            this.vendor = vendor;
        }

        public String getOut() {
            return out;
        }

        public void setOut(String out) {
            this.out = out;
        }

        public int getErrNo() {
            return errNo;
        }

        public void setErrNo(int errNo) {
            this.errNo = errNo;
        }

        @Override
        public String toString() {
            return "from="+from+",to="+to+",vendor="+vendor+",out="+out+",errNo="+errNo;
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Translation.content getContent() {
        return content;
    }

    public void setContent(Translation.content content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "status="+status+",content="+content.toString();
    }
}
