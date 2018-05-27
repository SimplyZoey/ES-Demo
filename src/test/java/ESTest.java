import com.rocky.es.biz.OperatorES;
import org.junit.Test;

/**
 * @Author: shtian
 * @Description:
 * @Date: Create in 2018/5/27 22:58
 */
public class ESTest {
    @Test
    public void testAddIndex(){
        OperatorES es = new OperatorES();
        try {
            String id = es.addIndex();
            System.out.println(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGetIndex(){
        OperatorES es = new OperatorES();
        try {
            String respose = es.getIndex("LfMgomMBX3ah06XLk2ab");
            System.out.println(respose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteByQueryIndex(){
        OperatorES es = new OperatorES();
        try {
            long respose = es.deleteByQuery();
            System.out.println(respose);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDeleteByQueryWithListener(){
        OperatorES es = new OperatorES();
        try {
            es.deleteByQueryWithListener();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testUpdateIndex(){
        OperatorES es = new OperatorES();
        try {
            String result = es.updateIndex("LfMgomMBX3ah06XLk2ab");
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
