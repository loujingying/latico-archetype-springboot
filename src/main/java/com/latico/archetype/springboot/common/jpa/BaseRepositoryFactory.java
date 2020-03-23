package com.latico.archetype.springboot.common.jpa;

import com.latico.archetype.springboot.common.jpa.impl.BaseRepositoryImpl;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.RepositoryMetadata;

import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * <PRE>
 *  自定义持久化工厂
 * </PRE>
 * @author: latico
 * @date: 2020-03-23 11:11:50
 * @version: 1.0
 */
public class BaseRepositoryFactory<T, I extends Serializable> extends JpaRepositoryFactory {

    private final EntityManager em;

    public BaseRepositoryFactory(EntityManager em) {
        super(em);
        this.em = em;
    }
    //    @Override
//    protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation information,
//                                                                    EntityManager entityManager) {
//        return new BaseRepositoryImpl(information.getDomainType(), entityManager);
//    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {

//        获取自定义的JpaRepository
        Class<?> repositoryInterface = metadata.getRepositoryInterface();
        Class<?>[] interfaces = repositoryInterface.getInterfaces();
        if (interfaces != null) {
            for (Class<?> anInterface : interfaces) {
                if (anInterface == BaseRepository.class) {
                    //            指定自定义实现类
                    return BaseRepositoryImpl.class;
                }
            }
        }

//        不是自定义的JpaRepository，就使用默认的
        return super.getRepositoryBaseClass(metadata);
    }
}