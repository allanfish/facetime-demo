package com.javase.reflact;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.annotation.Resource;

public class ReflectTest {

	public static void main(String[] args) throws Exception {
		Class<Student> clazz = Student.class;
		// the structure of Student
		System.out.println("===================Class Structure================");
		Package aPackage = clazz.getPackage();
		Type[] interfaces = clazz.getGenericInterfaces();
		Type type = clazz.getGenericSuperclass();
		System.out.println("package name -> " + aPackage.getName());
		for (Type aInterface : interfaces)
			System.out.println("implement interface -> " + aInterface.toString());
		System.out.println("extend class -> " + type.toString());

		System.out.println("==========================Fields================");
		Field[] declaredFields = clazz.getDeclaredFields();
		Field[] undeclaredFields = clazz.getFields(); // ֻ����ʾpublicȨ�޵�����
		for (Field declaredField : declaredFields)
			System.out.println("declared field -> " + declaredField);
		for (Field undeclaredField : undeclaredFields)
			System.out.println("undeclared field -> " + undeclaredField);

		System.out.println("=======================Method=====================");
		Method[] declaredMethods = clazz.getDeclaredMethods(); //
		Method[] undeclaredMethods = clazz.getMethods();// only access pulic
														// method
		for (Method declaredMethod : declaredMethods)
			System.out.println("declared method -> " + declaredMethod.getName());
		for (Method undeclaredMethod : undeclaredMethods)
			System.out.println("undeclared method -> " + undeclaredMethod.getName());
	}

	class Good {
	}

	@Resource
	class Student extends Good implements Serializable {
		private static final long serialVersionUID = 5024388519199338394L;
		private Integer id;
		private String name;
		public int age;

		@Override
		public boolean equals(Object obj) {
			if (obj == null)
				return false;
			if (this == obj)
				return true;
			return hashCode() == obj.hashCode();
		}

		public int getAge() {
			return age;
		}

		public Integer getId() {
			return id;
		}

		public String getName() {
			return name;
		}

		@Override
		public int hashCode() {
			int num = 47;
			return getId().hashCode() * getName().hashCode() + num;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public void setName(String name) {
			this.name = name;
		}

		@Override
		public String toString() {
			return "I'am" + name;
		}

	}
}
