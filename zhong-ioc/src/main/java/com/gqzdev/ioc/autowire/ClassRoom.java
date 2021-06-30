package com.gqzdev.ioc.autowire;

import com.gqzdev.ioc.bean.Student;
import com.gqzdev.ioc.bean.TeacherBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ClassRoom
 * @author: ganquanzhong
 * @date: 2020/7/10 10:07
 */
@Component
public class ClassRoom {

	@Autowired
	private Student student;

	@Qualifier("teacherBean")
	private TeacherBean teacher;

	@Override
	public String toString() {
		return "ClassRoom{" +
				"student=" + student.toString() +
				", teacher=" + teacher.toString() +
				'}';
	}
}
