package com.imooc.user.client;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.imooc.thrift.user.dto.UserDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public abstract class LoginFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    private static Cache<String, UserDTO> cache = CacheBuilder.newBuilder().maximumSize(10000)
            .expireAfterWrite(3, TimeUnit.MINUTES).build();

    public void init(FilterConfig filterConfig) throws ServletException{

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String token = request.getParameter("token");
        logger.error("===LoginFilter===doFilter()===token: " + token);
        if(StringUtils.isBlank(token)){
            logger.error("===LoginFilter===doFilter()===1===");
            Cookie[] cookies = request.getCookies();
            if(cookies != null){
                logger.error("===LoginFilter===doFilter()===2===");
                for (Cookie c: cookies) {
                    if(c.getName().equals("token")){
                        token = c.getValue();
                    }
                }
            }
        }

        logger.error("===LoginFilter===doFilter()===3===token: " + token);
        UserDTO userDTO = null;
        if(StringUtils.isNotBlank(token)){
            logger.error("===LoginFilter===doFilter()===4===");
            userDTO = cache.getIfPresent(token);
            if(userDTO == null){
                logger.error("===LoginFilter===doFilter()===5===");
                userDTO = reqestUserInfo(token);
                if (userDTO != null){
                    logger.error("===LoginFilter===doFilter()===6===");
                    cache.put(token,userDTO);
                }
            }
        }

        if (userDTO == null){
            logger.error("===LoginFilter===doFilter()===userDTO is null.");
            logger.error("===response.sendRedirect: http://user-edge-service:8082/user/login");
            response.sendRedirect("http://user-edge-service:8082/user/login");
            return;
        }
        login(request,response,userDTO);
        filterChain.doFilter(request,response);

    }

    protected abstract void login(HttpServletRequest request, HttpServletResponse response, UserDTO userDTO);

    private UserDTO reqestUserInfo(String token) {
        String url = "http://user-edge-service:8082/user/authentication";
        logger.error("===LoginFilter===reqestUserInfo()===token: "+ token);
        HttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        post.addHeader("token", token);
        InputStream inputStream = null;
        try {
            HttpResponse response = client.execute(post);
            if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK) {
                throw new RuntimeException("request user info failed! StatusLine:"+response.getStatusLine());
            }
            inputStream = response.getEntity().getContent();
            byte[] temp = new byte[1024];
            StringBuilder sb = new StringBuilder();
            int len = 0;
            while((len = inputStream.read(temp))>0) {
                sb.append(new String(temp,0,len));
            }

            UserDTO userDTO = new ObjectMapper().readValue(sb.toString(), UserDTO.class);
            return userDTO;
        } catch (IOException e) {
            logger.error("===LoginFilter===reqestUserInfo()===exception: ");
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if(inputStream!=null) {
                try{
                    inputStream.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void destroy() {

    }
}
