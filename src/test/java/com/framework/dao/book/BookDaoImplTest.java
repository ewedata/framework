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
        entity.setPublisher("动物出版社");
        entity.setContent("bds");
        entity.setDesc("这是描述信息");
        entity.setCreator("张三");
        entity.setOperator("李四");
        entity.setIssueDate(new Date(1234123343123L));
        for (int i = 0; i < 10000; i++)
            bookDao.insert(entity);

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
        BookEntity entity = bookDao.selectById(1L);
        System.out.println(entity);
    }

    @Test
    public void testQueryListByName() {
        List<BookEntity> list = bookDao.selectListByName("人");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

}
