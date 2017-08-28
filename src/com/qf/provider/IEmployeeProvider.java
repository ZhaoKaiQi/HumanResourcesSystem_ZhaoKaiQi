package com.qf.provider;

import java.util.Map;

import org.apache.ibatis.jdbc.SQL;

public class IEmployeeProvider {
	public String select(final Map<String, Object> map) {
		final StringBuilder builder = new StringBuilder();
		String sql = new SQL() {
			{
				SELECT("*");
				FROM("Employee AS a");
				JOIN("USER AS b ON a.userId=b.userId");
				JOIN("dept AS c ON a.deptId=c.deptId ");
				JOIN("POSITION AS d ON a.posId=d.posId");
				/* WHERE(conditions) */
				if (map.get("empName") != null && map.get("empName") != "") {
					builder.append(" and empName like'%" + map.get("empName")
							+ "%'");
				}
				if (map.get("empCardId") != null && map.get("empCardId") != "") {
					System.out.println("----provider身份证---"
							+ map.get("empCardId"));
					builder.append(" and empCardId = #{empCardId}");
				}
				if (map.get("empSex") != null
						&& !(map.get("empSex")).equals("0")
						&& map.get("empSex") != "") {
					builder.append(" and empSex = #{empSex}");
				}
				if (map.get("posId") != null && !(map.get("posId")).equals("0")
						&& map.get("posId") != "") {
					builder.append(" and a.posId = #{posId}");
				}
				if (map.get("deptId") != null
						&& !(map.get("deptId")).equals("0")
						&& map.get("deptId") != "") {
					builder.append(" and a.deptId = #{deptId}");
				}
				if (map.get("empTel") != null && map.get("empTel") != "") {
					builder.append(" and empTel = #{empTel}");
				}
				int index = builder.indexOf("or");
				if (index > 0 && index < 2) {
					builder.delete(0, index + 2);
				} else {
					index = builder.indexOf("and");
					if (index > 0 && index < 2) {
						builder.delete(0, index + 3);
					}
				}
				System.out.println("---build---" + builder);
				if (builder.length() > 0) {
					WHERE(builder.toString());
					
					
				}
			}
		}.toString();
		System.out.println("动态生成的sql语句为：" + sql);
		return sql;
	}
}