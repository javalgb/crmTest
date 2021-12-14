package com.yjxxt.crm.controller;


import com.yjxxt.crm.base.ResultInfo;
import com.yjxxt.crm.utils.UploadUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;


@Controller
@RequestMapping("upload")
public class UploadController {

    @RequestMapping("image")
    @ResponseBody
    public ResultInfo image(MultipartFile file){
        //调用工具类完成文件上传
        String imagePath = UploadUtils.upload(file);
        System.out.println(imagePath);
        ResultInfo dataJson = new ResultInfo();
        if (imagePath != null){
            //创建一个HashMap用来存放图片路径
            HashMap hashMap = new HashMap();
            hashMap.put("src",imagePath);
            dataJson.setCode(0);
            dataJson.setMsg("上传成功");
            dataJson.setResult(hashMap);
        }else{
            dataJson.setCode(0);
            dataJson.setMsg("上传失败");
        }
        return dataJson;
    }


    @RequestMapping("addImage")
    @ResponseBody
    public String addImage(String imageDescribe,String imagePath){
        //获得图片地址和图片描述
        /*
            这里我们只做打印的操作，实际上，我们应该在这里调用
            方法，把图片地址和图片的描述加入到数据库中，但是这些
            相信大家已经回了，所以，就不再写了。
         */

        System.out.println("图片描述："+imageDescribe);
        System.out.println("图片地址："+imagePath);

        return "1";
    }

    @RequestMapping("deleteImage")
    @ResponseBody
    public String deleteImage(){
        UploadUtils.delete("84e15dddb4284fc8a4877c93bcc9d81f-dl.jpg");
        return "1";
    }
}
