package com.hebut.annotation;

import org.springframework.stereotype.Repository;

//�൱�� extends Repository
@Repository
public @interface MybatisDao {
	String value() default "";
}
