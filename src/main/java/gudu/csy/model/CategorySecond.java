package gudu.csy.model;

import java.io.Serializable;

/**
 * 二级分类表
 */

public class CategorySecond implements Serializable{
    private static final long serialVersionUID = 1L;
    private Category category;//所属一级分类，存放一级分类对象
    private Integer csid;//二级分类编号
    private String csname;//二级分类名

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getCsid() {
        return csid;
    }

    public void setCsid(Integer csid) {
        this.csid = csid;
    }

    public String getCsname() {
        return csname;
    }

    public void setCsname(String csname) {
        this.csname = csname;
    }
}
