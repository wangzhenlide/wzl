package domain;

/**
 * @Description
 * @Author wzl
 * @Date 2020/7/6 17:41
 */
public class CommonResult<T> {

    private String messgae;

    private Integer code;

    private T data;

    public CommonResult(String messgae, Integer code, T data) {
        this.messgae = messgae;
        this.code = code;
        this.data = data;
    }

    public CommonResult() {
    }

    public CommonResult(String messgae, Integer code) {
        this(messgae, 200, null);
    }

    public CommonResult(T data) {
        this("操作成功", 200, data);
    }

    public String getMessgae() {
        return messgae;
    }

    public void setMessgae(String messgae) {
        this.messgae = messgae;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
