package com.framework.dao.book;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.BaseTest;
import com.framework.entity.book.BookEntity;

public class BookDaoImplTest extends BaseTest {

    @Autowired
    private IBookDao bookDao;

    @Test
    public void testSaveBookEntity() {
        BookEntity entity = new BookEntity();
        entity.setAuthor("liuaoz");
        entity.setTitle("人在江湖漂");
        entity.setBookNo("123456");
        entity.setContent("bds");
        bookDao.save(entity);

    }

    @Test
    public void testUpdate() {
        fail("Not yet implemented");
    }

    @Test
    public void testQueryById() {
        fail("Not yet implemented");
    }

    @Test
    public void testQueryListByName() {
        fail("Not yet implemented");
    }

}
