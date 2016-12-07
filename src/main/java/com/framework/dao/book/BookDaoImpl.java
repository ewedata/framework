package com.framework.dao.book;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.framework.dao.common.BaseDao;
import com.framework.entity.book.BookEntity;

/**
 * @author LIUAOZ
 * @since 2016年12月6日 下午3:27:35
 * @version 1.0
 * @see BaseDao
 * @see IBookDao
 */
@Repository
public class BookDaoImpl extends BaseDao implements IBookDao {

    private String namespace = BookEntity.class.getName().toString();

    @Override
    public void insert(BookEntity entity) {
        sqlSessionTemplate.insert(namespace + ".insert", entity);
    }

    @Override
    public void update(BookEntity entity) {
        sqlSessionTemplate.update(namespace + ".update", entity);

    }

    @Override
    public BookEntity selectById(Long id) {
        return (BookEntity) sqlSessionTemplate.selectOne(namespace + ".queryById", id);
    }

    @Override
    public List<BookEntity> selectListByName(String title) {
        List<BookEntity> list =
                sqlSessionTemplate.selectList(namespace + ".queryListByName", title);
        return list;
    }

}
