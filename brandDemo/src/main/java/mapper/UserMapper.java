package mapper;
import javaBean.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
public interface UserMapper {
    @Select("select * from tb_user where username=#{username} and password=#{password}")
    User login(@Param("username")String username,@Param("password") String password);

    @Insert("insert into tb_user values(#{username},#{password})")
    void register(@Param("username")String username,@Param("password") String password);
   @Select("select * from tb_user where username=#{username}")
    User search(@Param("username")String username);
}
