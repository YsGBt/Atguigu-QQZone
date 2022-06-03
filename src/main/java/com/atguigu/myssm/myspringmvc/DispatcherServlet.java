package com.atguigu.myssm.myspringmvc;

import com.atguigu.myssm.ioc.BeanFactory;
import com.atguigu.myssm.util.StringUtil;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截所有以.do结尾的请求
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

  private BeanFactory beanFactory;

  @Override
  public void init() throws ServletException {
    super.init();
    // 之前是在此处主动创建IOC容器的
    // 现在优化为从application作用域去获取
    // beanFactory = new ClassPathXmlApplicationContext();
    // 这里beanFactory的创建在Listener中完成了
    ServletContext application = getServletContext();
    Object beanFactoryObj = application.getAttribute("beanFactory");
    if (beanFactoryObj != null) {
      beanFactory = (BeanFactory) beanFactoryObj;
    } else {
      throw new DispatcherServletException("IOC容器获取失败");
    }
  }

  @Override
  protected void service(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // 假设url是: http://localhost:8080/Atguigu_JavaTomcatLearning_Web_exploded/hello.do
    // 那么Servlet是: /hello.do
    // 思路:
    // 1. /hello.do -> hello 或者 /fruit.do -> fruit
    // 2. hello -> HelloController 或者 fruit -> FruitController
    String servletPath = req.getServletPath();
    int lastDotIndex = servletPath.lastIndexOf(".do");
    servletPath = servletPath.substring(1, lastDotIndex);

    Object controllerBeanObj = beanFactory.getBean(servletPath);

    String operate = req.getParameter("operate");
    if (StringUtil.isEmpty(operate)) {
      operate = "index";
    }

    try {
      Method[] methods = controllerBeanObj.getClass().getDeclaredMethods();
      for (Method method : methods) {
        if (operate.equals(method.getName())) {
          // 1. 统一获取请求参数
          // 获取当前方法的参数数组，返回参数数组
          Parameter[] parameters = method.getParameters();
          // parameterValues 用来承载参数的值
          Object[] parameterValues = new Object[parameters.length];
          for (int i = 0; i < parameters.length; ++i) {
            String parameterName = parameters[i].getName();
            // 如果参数名是req, resp, session 那么就不是通过请求中获取参数的方式了
            if ("req".equals(parameterName)) {
              parameterValues[i] = req;
            } else if ("resp".equals(parameterName)) {
              parameterValues[i] = resp;
            } else if ("session".equals(parameterName)) {
              parameterValues[i] = req.getSession();
            } else {
              // 从请求中获取参数值
              Object parameterValue = req.getParameter(parameterName);
              String typeName = parameters[i].getType().getName();

              if (parameterValue != null && "java.lang.Integer".equals(typeName)) {
                parameterValue = Integer.parseInt((String) parameterValue);
              }
              parameterValues[i] = parameterValue;
            }
          }

          // 2. controller组件中的方法调用
          method.setAccessible(true);
          Object methodReturnObj = method.invoke(controllerBeanObj, parameterValues);

          // 3. 视图处理
          String methodReturnStr = "";
          if (methodReturnObj != null) {
            methodReturnStr = (String) methodReturnObj;
          }
          if (methodReturnStr.startsWith("redirect:")) { // 比如: redirect:fruit.do
            String redirectStr = methodReturnStr.substring("redirect:".length());
            resp.sendRedirect(redirectStr);
          } else {
            super.processTemplate(methodReturnStr, req, resp); // 比如: edit
          }
        }
      }

    } catch (IllegalAccessException e) {
      throw new DispatcherServletException("DispatcherServlet error");
    } catch (InvocationTargetException e) {
      throw new DispatcherServletException("DispatcherServlet error");
    }
  }

  @Override
  public void destroy() {

  }
}
