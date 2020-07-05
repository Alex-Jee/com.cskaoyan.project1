/**
 * User: zsquirrel
 * Date: 2020/4/28
 * Time: 5:29 下午
 */
package com.cskaoyan.mall.utils;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class HttpUtils {
    /**
     * 专门用来去读取请求体里面的参数（字符）数据
     * @param request
     * @return
     * @throws IOException
     */
    public static String getRequestBody(HttpServletRequest request) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(bytes)) != -1){
            byteArrayOutputStream.write(bytes, 0, length);
        }
        return byteArrayOutputStream.toString("utf-8");
    }
}
