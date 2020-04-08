package com.latico.archetype.springboot.postgresql.service.impl;

import com.latico.archetype.springboot.postgresql.entity.PgDemo;
import com.latico.archetype.springboot.postgresql.repository.PgDemoRepository;
import com.latico.archetype.springboot.postgresql.service.DemoPostgreSqlServicee;
import com.latico.commons.spring.util.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <PRE>
 *
 * </PRE>
 *
 * @author: latico
 * @date: 2020-04-08 14:31
 * @version: 1.0
 */
@Service
public class DemoPostgreSqlServiceeImpl implements DemoPostgreSqlServicee {

    @Autowired
    private PgDemoRepository pgDemoRepository;

    @Override
    public void init() {
        pgDemoRepository.save(new PgDemo(1, "aaaaa", "a", 10.0));
        pgDemoRepository.save(new PgDemo(2, "bbbaa", "b", 20D));
        pgDemoRepository.save(new PgDemo(3, "cccaa", "c", 30D));
        pgDemoRepository.save(new PgDemo(4, "dddaa", "d", 40D));
        pgDemoRepository.save(new PgDemo(5, "eeeaa", "e", 50D));
        pgDemoRepository.save(new PgDemo(6, "fffee", "f", 60D));
        pgDemoRepository.save(new PgDemo(7, "gggee", "g", 70D));
        pgDemoRepository.save(new PgDemo(8, "hhhee", "h", 80D));
        pgDemoRepository.save(new PgDemo(9, "iiiee", "i", 90D));

    }

    @Override
    public List<PgDemo> queryAll() {
        return pgDemoRepository.findAll();
    }

    @Override
    public String deleteAll() {
        pgDemoRepository.deleteAll();
        return "删除所有成功";
    }

    @Override
    public List<PgDemo> queryByPage() {
        Pageable pageable = PageableUtils.createPageable(1, 5, Sort.Direction.ASC, "id");
        Page<PgDemo> page = pgDemoRepository.findAll(pageable);
        return page.getContent();
    }
}
