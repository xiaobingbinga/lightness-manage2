package com.xuetang9.qingying.web;

import com.xuetang9.qingying.domain.Category;
import com.xuetang9.qingying.service.CategoryService;
import com.xuetang9.qingying.util.JsonResult;
import com.xuetang9.qingying.util.NodeMapper;
import com.xuetang9.qingying.util.TreeNode;
import com.xuetang9.qingying.util.TreeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lucky
 * @version 1.0.0
 * @date 2020/7/15 19:28
 * @copyright 老九学堂
 */
@RestController
@RequestMapping("/api/goods")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/category")
    public JsonResult addCategory(@Validated Category category){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setAutoShowMessage(true);
        try {
            categoryService.save(category);
            jsonResult.setCode(200);
        } catch (Exception e) {
            jsonResult.setCode(2000);
            jsonResult.setMessage("商品类目新增失败！");
            e.printStackTrace();
        }
        return jsonResult;
    }

    @GetMapping("/categories")
    public JsonResult<List<TreeNode<Category>>> queryCategories(){
        JsonResult<List<TreeNode<Category>>> jsonResult = new JsonResult<>();
        List<Category> categories = categoryService.listAll();

        NodeMapper<Category> nodeMapper = category -> {
            TreeNode<Category> treeNode = new TreeNode<>();
            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            treeNode.setPid(category.getParentId());
            treeNode.setRaw(category);
            return treeNode;
        };

        List<TreeNode<Category>> tree = TreeUtils.listToTree(categories,nodeMapper);

        jsonResult.setCode(200);
        jsonResult.setData(tree);

        return jsonResult;
    }

    @GetMapping("/parentCategories")
    public JsonResult<List<TreeNode<Category>>> queryParentCategories(){
        JsonResult<List<TreeNode<Category>>> jsonResult = new JsonResult<>();
        jsonResult.setAutoShowMessage(false);
        List<Category> categories = categoryService.listAllParent();

        NodeMapper<Category> nodeMapper = category -> {
            TreeNode<Category> treeNode = new TreeNode<>();
            treeNode.setId(category.getId());
            treeNode.setText(category.getName());
            treeNode.setPid(category.getParentId());
            treeNode.setRaw(category);
            return treeNode;
        };

        List<TreeNode<Category>> tree = TreeUtils.listToTree(categories,nodeMapper);

        jsonResult.setCode(200);
        jsonResult.setData(tree);

        return jsonResult;
    }

    @GetMapping("/queryById")
    public JsonResult queryById(Integer id){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setAutoShowMessage(false);
        Category category = categoryService.searchById(id);

        if (category == null){
            jsonResult.setCode(2002);
            jsonResult.setMessage("没有该类目数据");
        }else{
            jsonResult.setCode(200);
            jsonResult.setData(category);
        }

        return jsonResult;
    }

    @PostMapping("/update")
    public JsonResult updateCategory(@Validated Category category){
        JsonResult jsonResult = new JsonResult();
        jsonResult.setAutoShowMessage(true);
        try {
            categoryService.update(category);
            jsonResult.setCode(200);
        } catch (Exception e) {
            jsonResult.setCode(2004);
            jsonResult.setMessage("商品类目更新失败！");
            e.printStackTrace();
        }
        return jsonResult;
    }

}
