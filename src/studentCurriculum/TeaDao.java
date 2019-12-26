package studentCurriculum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

public class TeaDao extends BaseDao {

	public TeaDao() {
		// TODO Auto-generated constructor stub
	}

	public TeacherBean getTeacher(String num) {
		String sql = "SELECT * FROM 教师  where 教师编号=?";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			TeacherBean teacherBean = new TeacherBean();
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				teacherBean.setNum(resultSet.getString("教师编号").trim());
				teacherBean.setName(resultSet.getString("姓名").trim());
				teacherBean.setSex(resultSet.getString("性别").trim());
				teacherBean.setAge(resultSet.getInt("年龄"));
				teacherBean.setRank(resultSet.getString("职称").trim());
				teacherBean.setPhone(resultSet.getString("电话"));

			}
			connection.close();
			return teacherBean;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<CourseBean> getCourse(String num, String year) {
		String sql = "SELECT * FROM 教师任课查询  where 教师编号=? and 开课学年=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			preparedStatement.setString(2, year);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<CourseBean> cList = new ArrayList<>();
			while (resultSet.next()) {
				CourseBean courseBean = new CourseBean();
				courseBean.setCoursenum(resultSet.getString("课程编号").trim());
				courseBean.setCoursename(resultSet.getString("课程名称").trim());
				courseBean.setTeacher(resultSet.getString("姓名").trim());
				courseBean.setYear(resultSet.getString("开课学年").trim());
				if (resultSet.getBoolean("开课学期")) {
					courseBean.setTerm("上学期");
				} else {
					courseBean.setTerm("下学期");
				}

				courseBean.setTime(resultSet.getInt("学时"));
				if (resultSet.getBoolean("考试或考查")) {
					courseBean.setTestway("考查");
				} else {
					courseBean.setTestway("考试");
				}
				courseBean.setTime(resultSet.getInt("学时"));
				courseBean.setCredit(resultSet.getFloat("学分"));
				courseBean.setTmpnum(resultSet.getString("开课编号"));
				cList.add(courseBean);
			}
			connection.close();
			return cList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean Withdraw(String num) {
		String sql = "DELETE FROM 选课情况 WHERE 开课编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			preparedStatement.executeUpdate();
			sql = "DELETE FROM 开课情况 WHERE 开课编号=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}	
	}
	
	public ArrayList<CourseBean> getNoSet(String num) {
		Connection connection;
		try {
			String sql = "exec 查找未开课程 ?,'2019',0";
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			preparedStatement.executeUpdate();
			sql = "SELECT * FROM 未开课";
			preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<CourseBean> cList = new ArrayList<>();
			while (resultSet.next()) {
				CourseBean courseBean = new CourseBean();
				courseBean.setCoursenum(resultSet.getString("课程编号").trim());
				courseBean.setCoursename(resultSet.getString("课程名称").trim());
				courseBean.setTime(resultSet.getInt("学时"));
				if (resultSet.getBoolean("考试或考查")) {
					courseBean.setTestway("考查");
				} else {
					courseBean.setTestway("考试");
				}
				courseBean.setCredit(resultSet.getFloat("学分"));
				cList.add(courseBean);
			}
			connection.close();
			cList.sort(new Comparator<CourseBean>() {

				@Override
				public int compare(CourseBean o1, CourseBean o2) {
					// TODO Auto-generated method stub
					return o1.getCoursenum().compareTo(o2.getCoursenum());
				}
				
			});
			return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	
	public boolean SetCourse(String num,String colnum) {
		String sql = "insert into 开课情况\r\n" + 
				"values(?,?,?,'2019',0)";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			String tmpString=colnum+num+"20190";
			preparedStatement.setString(1, tmpString);
			preparedStatement.setString(2, colnum);
			preparedStatement.setString(3, num);
			preparedStatement.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public ArrayList<String> getAllClass() {
		String sql = "select 班级编号,班级名称 from 班级";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<String> aList = new ArrayList<>();
			while (resultSet.next()) {
				String aclass = resultSet.getString("班级名称").trim() + "（"
						+ resultSet.getString("班级编号").trim() + "）";
				aList.add(aclass);
			}
			connection.close();
			return aList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<StudentBean> getGpa(String num, int flag) {
		String sql = "";
		if (flag == 1) {
			sql = "select * from 均绩,学生 where 均绩.学号=学生.学号 and 学生.班级编号=?";
		} else if (flag == 2) {
			sql = "select * from 均绩,学生 where 均绩.学号=学生.学号 and 学生.班级编号=?\r\n"
					+ "order by 均绩 asc";
		} else if (flag == 3) {
			sql = "select * from 均绩,学生 where 均绩.学号=学生.学号 and 学生.班级编号=?\r\n"
					+ "order by 均绩 DESC";
		}
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<StudentBean> sList = new ArrayList<>();
			while (resultSet.next()) {
				StudentBean studentBean = new StudentBean();
				studentBean.setName(resultSet.getString("姓名").trim());
				studentBean.setNum(resultSet.getString("学号"));
				studentBean.setCredit(resultSet.getFloat("已修学分总数"));
				studentBean.setGpa(resultSet.getFloat("均绩"));
				sList.add(studentBean);
			}
			connection.close();
			return sList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<CourseBean> getAvg(String num,String year) {
		String sql = "select * from 每门课平均成绩,开课情况,课程,教师\r\n" + 
				"WHERE 每门课平均成绩.课程号=开课情况.开课编号 AND 课程.课程编号=开课情况.课程编号 AND 平均成绩 IS NOT NULL AND 任课教师编号=? AND 开课学年=?  AND 教师编号=任课教师编号";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			preparedStatement.setString(2, year);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<CourseBean> cList = new ArrayList<>();
			while(resultSet.next()) {
				CourseBean courseBean=new CourseBean();
				courseBean.setCoursenum(resultSet.getString("课程编号").trim());
				courseBean.setCoursename(resultSet.getString("课程名称").trim());
				courseBean.setTeacher(resultSet.getString("姓名").trim());
				courseBean.setAvg(resultSet.getFloat("平均成绩"));
				courseBean.setYear(resultSet.getString("开课学年"));
				cList.add(courseBean);
				
			}
			connection.close();
			return cList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}
	
	public ArrayList<String> getACourse(String num){
		String sql="select distinct 课程名称,课程编号 from 教师任课查询 where 教师编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, num);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<String> aList = new ArrayList<>();
			while (resultSet.next()) {
				String aclass = resultSet.getString("课程名称").trim() + "（"
						+ resultSet.getString("课程编号").trim() + "）";
				aList.add(aclass);
			}
			connection.close();
			return aList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}
	
	public ArrayList<StudentBean> getSingle(String teanum,String counum,String year,int flag){
		String sql="";
		if(flag==1) {
			sql="select * from 学生成绩统计\r\n" + 
					"where 学年=? AND 教师编号=? AND 课程编号=?" ;
		}else if(flag==2) {
			sql="select * from 学生成绩统计\r\n" + 
					"where 学年=? AND 教师编号=? AND 课程编号=? \r\n" + 
					"order by 成绩 asc";
		}else if(flag==3) {
			sql="select * from 学生成绩统计\r\n" + 
					"where 学年=? AND 教师编号=? AND 课程编号=? \r\n" + 
					"order by 成绩 DESC";
		}
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(2, teanum);
			preparedStatement.setString(3, counum);
			preparedStatement.setString(1, year);
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<StudentBean> sList = new ArrayList<>();
			while(resultSet.next()) {
				StudentBean studentBean=new StudentBean();
				studentBean.setNum(resultSet.getString("学号"));
				studentBean.setName(resultSet.getString("姓名").trim());
				studentBean.setCoursename(resultSet.getString("课程名称").trim());
				studentBean.setYear(resultSet.getString("学年").trim());
				studentBean.setCredit(resultSet.getFloat("已修学分总数"));
				studentBean.setTmpnum(resultSet.getString("开课编号").trim());
				studentBean.setCoursenum(resultSet.getString("课程编号"));
				studentBean.setGpa(resultSet.getFloat("成绩"));
				sList.add(studentBean);
			}
			connection.close();
			return sList;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean changePasscode(String passcode,String num) {
		String sql="UPDATE 教师 SET 登录密码=? WHERE 教师编号=?";
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,passcode);
			preparedStatement.setString(2,num);
			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}

	public boolean changeGrade(Float grade,String tmpnum,String num) {
		String sql="update 选课情况\r\n" + 
				"set 成绩=?\r\n" + 
				"where 开课编号=? and 学号=?\r\n";
		Connection connection;
		try {
			connection=dataSource.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setFloat(1,grade);
			preparedStatement.setString(2,tmpnum);
			preparedStatement.setString(3,num);
			preparedStatement.executeUpdate();
			sql="exec 更新已修学分 ?";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1,num);
			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	}
}
