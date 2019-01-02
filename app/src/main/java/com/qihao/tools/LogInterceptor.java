package com.qihao.tools;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * (核心功能)：
 *
 * @author qihao
 * @date on 2019/1/2 14:51
 */
public class LogInterceptor implements Interceptor{

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());
        MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        LoggerUtil.d(request.toString());
        String method=request.method();
        if("POST".equals(method)){
            StringBuilder sb = new StringBuilder();
            if (request.body() instanceof FormBody) {
                FormBody body = (FormBody) request.body();
                for (int i = 0; i < body.size(); i++) {
                    sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                }
                sb.delete(sb.length() - 1, sb.length());
                LoggerUtil.d("RequestParams:{"+sb.toString()+"}");
            }
        }
        LoggerUtil.json(content);
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }
}
