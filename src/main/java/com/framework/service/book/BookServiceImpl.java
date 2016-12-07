package com.framework.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.dao.book.IBookDao;
import com.framework.entity.book.BookEntity;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    IBookDao bookDao;

    @Override
    public BookEntity queryBookById(Long id) {
        return bookDao.selectById(id);
    }

    @Override
    public void addBook(BookEntity entity) {
        bookDao.insert(entity);
    }

    @Override
    public boolean deleteBookById(Long id) {
        return false;
    }

}
