package web;

import com.alibaba.fastjson.JSON;
import javaBean.Brand;
import javaBean.PageBean;
import service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet {
    private final BrandServiceImpl brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用service查询
        List<Brand> brands = brandService.selectAll();

        //2. 转为JSON
        String jsonString = JSON.toJSONString(brands);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        //转为 int[]
        System.out.println("删除单行数据：" + params);
        int id = JSON.parseObject(params, int.class);
        //2. 调用service添加
        brandService.deleteById(id);
        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2. 调用service添加
        brandService.add(brand);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1. 接收数据  [1,2,3]
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println("批量删除时的params:" + params);
        //转为 int[]
        int[] ids = JSON.parseObject(params, int[].class);
        System.out.println("欲删除的品牌为：" + Arrays.toString(ids));
        //2. 调用service删除
        brandService.deleteByIds(ids);

        //3. 响应成功的标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 分页条件查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 接收 当前页码 和 每页展示条数    url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        // 获取查询条件对象
        BufferedReader br = request.getReader();
        String params = br.readLine();//json字符串
        System.out.println("按条件查询 所得的params" + params);
        //转为 Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        System.out.println("brand: " + brand);
        //2. 调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize, brand);

        //2. 转为JSON
        String jsonString = JSON.toJSONString(pageBean);
        //3. 写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }


}
