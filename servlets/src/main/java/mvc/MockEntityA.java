package mvc;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MockEntityA {
    private String str;
    private String[] array;
    private List<Integer> list;
    private Map<String,String> map;
    private MockEntityB mockEntityB;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public MockEntityA() {
        str = "str";
        array=new String[]{"AAA","BBB","CCC"};
        list = Arrays.asList(123,456,789);
        map = new HashMap<String,String>(){{put("key-0","value-0");}};
        mockEntityB = new MockEntityB();

    }

    public String[] getArray() {
        return array;
    }

    public void setArray(String[] array) {
        this.array = array;
    }

    public List<Integer> getList() {
        return list;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }

    public MockEntityB getMockEntityB() {
        return mockEntityB;
    }

    public void setMockEntityB(MockEntityB mockEntityB) {
        this.mockEntityB = mockEntityB;
    }

    public String getStr() {

        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}
