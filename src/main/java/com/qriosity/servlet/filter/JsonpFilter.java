package com.qriosity.servlet.filter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yoandyo
 * @since 10/5/13
 */
public class JsonpFilter  implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        @SuppressWarnings("unchecked")
        Map<String, String[]> params = httpRequest.getParameterMap();

        if(params.containsKey("callback")) {
            OutputStream out = httpResponse.getOutputStream();

            GenericResponseWrapper wrapper = new GenericResponseWrapper(httpResponse);

            chain.doFilter(request, wrapper);

            out.write((params.get("callback")[0] + "(").getBytes());
            out.write(wrapper.getData());
            out.write(");".getBytes());

            wrapper.setContentType("application/json;charset=UTF-8");

            out.close();
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {}
}
