package com.xuetang9.qingying.util;

import java.util.List;

/**
 * 用来定义一个树形节点的数据
 * @author 洋葱
 * @version 1.0.0
 * @date 2020/6/9 16:19
 * @copyright 老九学堂
 */
public class TreeNode<T> {

    private Integer id;

    private String text;

    private String icon;

    private String href;

    private Integer pid;

    private Integer depth;

    private Boolean expanded;

    private Boolean checked;

    private List<TreeNode<T>> children;

    private T raw;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getDepth() {
        return depth;
    }

    public void setDepth(Integer depth) {
        this.depth = depth;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    public void setExpanded(Boolean expanded) {
        this.expanded = expanded;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode<T>> children) {
        this.children = children;
    }

    public T getRaw() {
        return raw;
    }

    public void setRaw(T raw) {
        this.raw = raw;
    }
}
