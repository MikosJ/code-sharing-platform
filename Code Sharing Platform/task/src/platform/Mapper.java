package platform;

public class Mapper {
    public static CodeUserRepresentation map(Code code) {
        return new CodeUserRepresentation(code.getCode(),code.getDateLDT(), code.getTimeRestriction(), code.getViewRestriction());
    }
}
