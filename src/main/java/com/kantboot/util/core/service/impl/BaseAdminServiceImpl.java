package com.kantboot.util.core.service.impl;

import com.kantboot.util.common.exception.BaseException;
import com.kantboot.util.core.service.IBaseAdminService;
import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;

@Service
public class BaseAdminServiceImpl<T extends Serializable, ID> implements IBaseAdminService<T, ID> {

    @Resource
    EntityManagerFactory entityManagerFactory;

    @Override
    public T save(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        SimpleJpaRepository<T, ID> jpaRepository = new SimpleJpaRepository<T, ID>((Class<T>) entity.getClass(), entityManager);
        transaction.begin();
        T result = null;
        try {
            result = jpaRepository.save(entity);
        } catch (Exception e) {
            entityManager.close();
            throw BaseException.of("saveError", "保存失败");
        }
        transaction.commit();
        entityManager.close();
        return result;
    }

    @Override
    public void saveBatch(List<T> entityList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            for (T entity : entityList) {
                SimpleJpaRepository<T, ID> jpaRepository = new SimpleJpaRepository<T, ID>((Class<T>) entity.getClass(), entityManager);
                jpaRepository.save(entity);
            }
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
            throw BaseException.of("saveError", "保存失败");
        }
        transaction.commit();
        entityManager.close();
    }

    public void remove(T entity) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        try {
            T entity1 = entityManager.find((Class<T>) entity.getClass(), getId(entity));
            entityManager.remove(entity1);
        } catch (Exception e) {
            entityManager.close();
            e.printStackTrace();
            throw BaseException.of("deleteError", "删除失败");
        }
        transaction.commit();
        entityManager.close();
    }


    @Override
    public void removeBatch(List<T> entityList) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        Iterator<T> entityIterator = entityList.iterator();
        while (entityIterator.hasNext()) {
            T entity = entityIterator.next();
            try {
                transaction.begin();
                T entity1 = entityManager.find((Class<T>) entity.getClass(), getId(entity));
                entityManager.remove(entity1);
                transaction.commit();
                entityIterator.remove(); // 使用迭代器的 remove 方法
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        entityManager.close();
    }


    public Object getId(Object object) {
        // 从Class对象中获取Demo中声明方法对应的Method对象
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            // 判断方法是否被加上了@Autowired这个注解
            if (field.isAnnotationPresent(Id.class)) {
                field.setAccessible(true);
                try {
                    return field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }


}
