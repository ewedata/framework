package com.framework.service.book;

import com.framework.entity.book.BookEntity;

public interface IBookService {

    public BookEntity queryBookById(Long id);

    public void addBook(BookEntity entity);

    public boolean deleteBookById(Long id);

}
