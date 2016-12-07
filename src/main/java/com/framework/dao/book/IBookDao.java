package com.framework.dao.book;

import java.util.List;

import com.framework.entity.book.BookEntity;

public interface IBookDao {

    public void insert(BookEntity entity);

    public void update(BookEntity entity);

    public BookEntity selectById(Long id);

    public List<BookEntity> selectListByName(String title);

}
