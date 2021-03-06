package by.pwt.pilipenko.jd2final.dao.VO;

/**
 * Created by apilipenka on 8/5/2016.
 */
public class CurrencyVO extends EntityVO {

    private static final long serialVersionUID = 3561250473466586712L;
    private String mnemoCode;
    private String code;
    private String name;

    public CurrencyVO() {
        super();
    }

    public CurrencyVO(int id, String mnemoCode, String code, String name) {
        super(id);
        this.mnemoCode = mnemoCode;
        this.code = code;
        this.name = name;
    }

    public String getMnemoCode() {
        return mnemoCode;
    }

    public void setMnemoCode(String mnemoCode) {
        this.mnemoCode = mnemoCode;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
