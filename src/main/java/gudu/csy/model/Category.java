package gudu.csy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 一级分类表
 * cid	一级分类编号	INT(11)
 cname	一级分类名	VARCHAR(50)

 */
public class Category implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer cid;//一级分类编号
    private String cname;//一级分类名
    //二级分类集合
    private List<CategorySecond> categorySeconds = new ArrayList<CategorySecond>();

    public List<CategorySecond> getCategorySeconds() {
        return categorySeconds;
    }

    public void setCategorySeconds(List<CategorySecond> categorySeconds) {
        this.categorySeconds = categorySeconds;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
