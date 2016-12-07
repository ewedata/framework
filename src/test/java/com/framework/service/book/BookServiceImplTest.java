package com.framework.service.book;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.framework.BaseTest;
import com.framework.entity.book.BookEntity;

public class BookServiceImplTest extends BaseTest {

    @Autowired
    IBookService iBookService;

    @Test
    public void testQueryBookById() {
        BookEntity book = iBookService.queryBookById(1L);
        System.out.println(book);
    }

    @Test
    public void testAddBook() {
        fail("Not yet implemented");
    }

    @Test
    public void testDeleteBookById() {
        fail("Not yet implemented");
    }

}
