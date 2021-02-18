package com.xuetang9.qingying.config;

import com.xuetang9.qingying.domain.Resource;
import com.xuetang9.qingying.util.NodeMapper;
import com.xuetang9.qingying.util.TreeNode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/21 11:30
 * @copyright 老九学堂
 */
@Configuration
public class TreeConfig {

    @Bean
    public NodeMapper<Resource> createResourceNodeMapper(){
        return new NodeMapper<Resource>() {
            @Override
            public TreeNode<Resource> objectMapper(Resource resource) {
                TreeNode<Resource> treeNode = new TreeNode<>();
                treeNode.setId(resource.getId());
                treeNode.setText(resource.getName());
                treeNode.setPid(resource.getParentId());
                treeNode.setRaw(resource);
                treeNode.setHref(resource.getUrl());
                treeNode.setIcon(resource.getIcon());
                return treeNode;
            }
        };
    }

}
