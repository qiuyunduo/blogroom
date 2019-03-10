import cn.qyd.blogroom.blog.BlogServiceProvider;
import cn.qyd.blogroom.blog.entity.Blog;
import cn.qyd.blogroom.blog.service.BlogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author qyd
 * @Date 19-3-10 下午8:02
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {BlogServiceProvider.class})
public class BlogServideTest {
    @Autowired(required = false)
    private BlogService blogService;

    @Test
    public void getBlogByUserId() {
        Blog byUserId = blogService.findByUserId(19l);
        System.out.println(byUserId);
    }
}
