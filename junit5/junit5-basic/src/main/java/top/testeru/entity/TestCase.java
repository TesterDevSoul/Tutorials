package top.testeru.entity;

/**
 * @author www.testeru.top
 * @version 1.0.0
 * @Project junit5-tutorial
 * @Description 测试方法组成实体类
 * @createTime 2022年11月30日 18:17:00
 */
public class TestCase {


    private Integer id;


    private String caseTitle;

    /**
     * 备注
     */
    private String remark;

    /**
     * 测试用例内容
     */
    private String caseData;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaseData() {
        return caseData;
    }

    public void setCaseData(String caseData) {
        this.caseData = caseData;
    }

    public String getCaseTitle() {
        return caseTitle;
    }

    public void setCaseTitle(String caseTitle) {
        this.caseTitle = caseTitle;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
