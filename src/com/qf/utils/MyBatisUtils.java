package com.qf.utils;
import org.apache.ibatis.session.SqlSession;

public class MyBatisUtils {

	private static ThreadLocal<SqlSession> tLocal=new ThreadLocal<>();
	
	public static SqlSession getSession() {
		SqlSession session=tLocal.get();
		if(session==null){
			session=SqlSessionFactoryUtils.getFactory().openSession();
			tLocal.set(session);
		}
		return session;
	}
	public static void commit() {
		tLocal.get().commit();
	}
	public static void rollback() {
		tLocal.get().rollback();
	}
	public static void close() {
		tLocal.get().close();
		tLocal.set(null);
	}
	
	
}
