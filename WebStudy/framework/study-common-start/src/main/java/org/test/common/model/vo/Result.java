package org.test.common.model.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.test.common.enums.CodeEnum;
import org.test.common.util.LanguageUtil;

import java.io.Serializable;


public class Result<T> implements Serializable {
    private T data;
    private Integer code;
    private String msg;

    public static <T> Result<T> succeed() {
        return of(null, CodeEnum.SUCCESS.getCode(), "success");
    }

    public static <T> Result<T> succeed(String msg) {
        return of(null, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model, String msg) {
        return of(model, CodeEnum.SUCCESS.getCode(), msg);
    }

    public static <T> Result<T> succeed(T model) {
        return of(model, CodeEnum.SUCCESS.getCode(), "Success ");
    }

    public static <T> Result<T> of(T datas, Integer code, String msg) {
        return new Result(datas, code, msg);
    }

    public static <T> Result<T> failed(String msg) {
        return of(null, CodeEnum.ERROR.getCode(), msg);
    }

    public static <T> Result<T> failed() {
        return of(null, CodeEnum.ERROR.getCode(), LanguageUtil.get("System error "));
    }

    public static <T> Result<T> failed(T model, String msg) {
        return of(model, CodeEnum.ERROR.getCode(), msg);
    }

    public T getData() {
        return this.data;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label47: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label47;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label47;
                    }

                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof Result;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $code = this.getCode();
        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        Object $data = this.getData();
        result = result * 59 + ($data == null ? 43 : $data.hashCode());
        Object $msg = this.getMsg();
        result = result * 59 + ($msg == null ? 43 : $msg.hashCode());
        return result;
    }

    public String toString() {
        return "Result(data=" + this.getData() + ", code=" + this.getCode() + ", msg=" + this.getMsg() + ")";
    }

    public Result() {
    }

    public Result(T data, Integer code, String msg) {
        this.data = data;
        this.code = code;
        this.msg = msg;
    }
}
