package service.impl;

import javaBean.User;
import mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import service.UserService;
import utils.sqlSessionFactoryUtils;

public class UserServiceImpl implements UserService {
    SqlSessionFactory factory = sqlSessionFactoryUtils.getSqlSessionFactory();

    public User login(String username, String password) {
        //2. 获取SqlSession对象
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        User user = mapper.login(username, password);
        sqlSession.close();
        return user;
    }
public User search(String username){
    //2. 获取SqlSession对象
    SqlSession sqlSession = factory.openSession(true);
    //3. 获取BrandMapper
    UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    //4. 调用方法
    User user = mapper.search(username);
    sqlSession.close();
    return user;
}
    public void register(String username, String password) {
        SqlSession sqlSession = factory.openSession(true);
        //3. 获取BrandMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //4. 调用方法
        mapper.register(username, password);
        sqlSession.close();
    }
}
