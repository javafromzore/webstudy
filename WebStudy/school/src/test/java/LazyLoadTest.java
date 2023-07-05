import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.test.school.dao.StudentMapper;

import java.io.InputStream;

public class LazyLoadTest {
    @Test
    void lazyLoadTest(){
        InputStream inputStream = Test.class.getClassLoader().getResourceAsStream("config.xml");
        //构建SQLSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new
                SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = factory.openSession();
        //获取实现接口的代理对象
        StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
        System.out.println(mapper.findById(1));
    }
}
