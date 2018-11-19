package com.demo.wechatpay.entity.json;

import net.sf.json.JSONArray;

import java.util.Collection;

/**
 * JSONResponse
 *
 * @author WuYuxiang
 * @date 2015/4/24
 */
public class JSONResponse {

    private int total;

    private Collection rows;

    private Object object;

    private Result result;

    private String message;

    private JSONArray jsonArray;

    private String qrCode;

    public JSONArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public static enum Result {
        SUCCESS(0),
        FAILURE(1);
        private int value;

        private Result(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Collection getRows() {
        return rows;
    }

    public void setRows(Collection rows) {
        this.rows = rows;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}
