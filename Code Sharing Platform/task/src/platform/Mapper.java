package platform;

public class Mapper {
    public static CodeUserRepresentation mapAll(Code code) {
        CodeUserRepresentation response = null;
        if (code.isTimeRestricted() && !code.isViewRestricted()) {
            response = new CodeUserRepresentation(code.getCode(), code.getDateLDT(), code.getTimeRestriction());
        }
        if (code.isViewRestricted() && !code.isTimeRestricted()) {
            response = new CodeUserRepresentation(code.getCode(), code.getDateLDT(), code.getViewRestriction());
        }
        if (!code.isTimeRestricted() && !code.isViewRestricted()) {
            response = new CodeUserRepresentation(code.getCode(), code.getDateLDT(), code.getTimeRestriction(), code.getViewRestriction());
        }
        if (!code.isViewRestricted() && !code.isTimeRestricted()) {
            response = new CodeUserRepresentation(code.getCode(), code.getDateLDT());
        }
        return response;
    }

    public static CodeUserRepresentation mapTime(Code code) {
        return new CodeUserRepresentation(code.getCode(), code.getDateLDT(), code.getTimeRestriction());
    }

    public static CodeUserRepresentation mapView(Code code) {
        return new CodeUserRepresentation(code.getCode(), code.getDateLDT(), code.getViewRestriction());
    }

    public static CodeUserRepresentation map(Code code) {
        return new CodeUserRepresentation(code.getCode(), code.getDateLDT());
    }


}
