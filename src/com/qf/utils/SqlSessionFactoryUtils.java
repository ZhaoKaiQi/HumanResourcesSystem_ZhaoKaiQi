package com.qf.utils;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils {

	private SqlSessionFactoryUtils(){
		
	}
	private static SqlSessionFactory factory;
	static{
		try {
			factory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("SqlMapConfig.xml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	private static SqlSessionFactoryUtils utils=new SqlSessionFactoryUtils();
//	public static SqlSessionFactoryUtils getUtils() {
//		return utils;
//	}
//	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
}
