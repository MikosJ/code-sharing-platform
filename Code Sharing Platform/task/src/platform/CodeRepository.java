package platform;

import lombok.Data;


import java.util.ArrayList;

@Data
public class CodeRepository {
    public ArrayList<Code> list;

    public CodeRepository() {
        this.list = new ArrayList<>();
    }

    public ArrayList<Code> getList() {
        return list;
    }

    public void appendCode(Code code) {
        this.list.add(code);
    }

    public Code getCode(int id) {
        return list.get(id);
    }
}
