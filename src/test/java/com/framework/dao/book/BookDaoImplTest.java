package com.framework.dao.book;

import java.sql.Date;
import java.util.List;

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
        entity.setIssueDate(new Date(1234123343123L));
        bookDao.save(entity);

    }

    @Test
    public void testUpdate() {
        BookEntity entity = new BookEntity();
        entity.setId(1L);
        entity.setAuthor("liuaoz");
        entity.setTitle("人在江湖漂");
        entity.setBookNo("123456");
        entity.setContent("bds");
        entity.setIssueDate(new Date(1234123343123L));
        bookDao.update(entity);
    }

    @Test
    public void testQueryById() {
        BookEntity entity = bookDao.queryById(1L);
        System.out.println(entity);
    }

    @Test
    public void testQueryListByName() {
        List<BookEntity> list = bookDao.queryListByName("人");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
