package gudu.csy.model;

import java.io.Serializable;

/**
 * 用户
 * 注：Serializable(标识接口)：一个对象序列化的接口，一个类只有实现了Serializable接口，它的对象才能被序列化。
 * 扩展：序列化是将对象状态转换为可保持或传输的格式的过程。与序列化相对的是反序列化，它将流转换为对象。
 * 这两个过程结合起来，可以轻松地存储和传输数据。
 * 详细可查;https://baijiahao.baidu.com/s?id=1633305649182361563&wfr=spider&for=pc
 */

public class User implements Serializable{
    private static final long serialVersionUID = 1L;//辅助对象的序列化与反序列化
    private Integer uid;//用户id
    private String username;//用户昵称
    private String password;//密码
    private String name;//真名
    private String email;//邮箱
    private String phone;//电话
    private String addr;//地址
    private Integer state;//状态
    private String code;//激活码
    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
