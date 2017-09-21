import edu.tongji.comm.controller.SimpleController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * @author chenkangqiang
 * @date 2017/9/21
 *
 * SpringBoot单元测试
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {edu.tongji.comm.controller.SimpleController.class})
public class SimpleControllerTest {

    private MockMvc mvc;

    //初始化执行
    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new SimpleController()).build();
    }


    @Test
    public void home() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/home"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

}
