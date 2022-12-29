package top.testeru.page;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author testeru.top
 * @version 1.0.0
 * @Project web-demo
 * @Description
 * @createTime 2022年11月05日 21:59:00
 */
class MemberPageTest {

    /**
     * 成员添加
     */
    @Test
    void addMember() {
        new MainPage().toContactPage().toMemberPage().addMember();
    }
}