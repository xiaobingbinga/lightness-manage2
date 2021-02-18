package com.xuetang9.qingying.util;

/**
 * @author 洋葱
 * @version 1.0.0
 * @date 2020/6/9 16:39
 * @copyright 老九学堂
 */
public interface NodeMapper<T> {

    /**
     * 把一个对象转换为节点的数据对象
     * @param object
     * @return
     */
    TreeNode<T> objectMapper(T object);
}
