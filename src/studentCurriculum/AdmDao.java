package studentCurriculum;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.accessibility.internal.resources.accessibility;
import com.sun.xml.internal.bind.v2.model.runtime.RuntimeReferencePropertyInfo;

public class AdmDao extends BaseDao {

	public AdmDao() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<TeacherBean> getTeacher() {
		String sql = "SELECT * FROM 教师";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);	
			ResultSet resultSet = preparedStatement.executeQuery();
			ArrayList<TeacherBean> tList = new ArrayList<>();
			while (resultSet.next()) {
				TeacherBean teacherBean = new TeacherBean();
				teacherBean.setNum(resultSet.getString("教师编号").trim());
				teacherBean.setName(resultSet.getString("姓名").trim());
				teacherBean.setSex(resultSet.getString("性别").trim());
				teacherBean.setAge(resultSet.getInt("年龄"));
				teacherBean.setRank(resultSet.getString("职称").trim());
				teacherBean.setPhone(resultSet.getString("电话"));
				tList.add(teacherBean);
			}
			connection.close();
			return tList;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public boolean addTeacher(TeacherBean teacherBean) {
		String sql="insert into 教师 values(?,?,?,?,?,?,'123456')";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherBean.getNum());
			preparedStatement.setString(2, teacherBean.getName());
			preparedStatement.setString(3, teacherBean.getSex());
			preparedStatement.setInt(4, teacherBean.getAge());
			preparedStatement.setString(5, teacherBean.getRank());
			preparedStatement.setString(6, teacherBean.getPhone());
			preparedStatement.executeUpdate();
			connection.close();
			return true;
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean deleteTeacher(ArrayList<TeacherBean>tList,int i) {
		String sql="delete from 教师 where 教师编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,tList.get(i).getNum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean changTeacher(TeacherBean teacherBean) {
		String sql="update 教师\r\n" + 
				"set 姓名=?,性别=?,年龄=?,职称=?,电话=?\r\n" + 
				"where 教师编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, teacherBean.getName());
			preparedStatement.setString(2, teacherBean.getSex());
			preparedStatement.setInt(3, teacherBean.getAge());
			preparedStatement.setString(4, teacherBean.getRank());
			preparedStatement.setString(5, teacherBean.getPhone());
			preparedStatement.setString(6, teacherBean.getNum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	}

	
	public ArrayList<StudentBean> getStudent() {
		String sql = "SELECT * FROM 学生,班级 WHERE 班级.班级编号=学生.班级编号";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ArrayList<StudentBean> sList=new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				StudentBean studentBean = new StudentBean();
				studentBean.setNum(resultSet.getString("学号").trim());
				studentBean.setName(resultSet.getString("姓名").trim());
				studentBean.setSex(resultSet.getString("性别").trim());
				studentBean.setAge(resultSet.getInt("年龄"));
				studentBean.setPlace(resultSet.getString("生源所在地").trim());
				studentBean.setCredit(resultSet.getFloat("已修学分总数"));
				studentBean.setClassname(resultSet.getString("班级名称").trim());
				studentBean.setClassnum(resultSet.getString("班级编号").trim());
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

	
	public boolean addStudent(StudentBean studentBean) {
		String sql="insert into 学生 values(?,?,?,?,?,?,'123456',?)";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentBean.getNum());
			preparedStatement.setString(2, studentBean.getName());
			preparedStatement.setString(3, studentBean.getSex());
			preparedStatement.setInt(4, studentBean.getAge());
			preparedStatement.setString(5, studentBean.getPlace());
			preparedStatement.setFloat(6, studentBean.getCredit());
			preparedStatement.setString(7, studentBean.getClassnum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean deleteStudent(ArrayList<StudentBean>sList,int i) {
		String sql="delete from 学生 where 学号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,sList.get(i).getNum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean changeStudent(StudentBean studentBean) {
		String sql="update 学生\r\n" + 
				"set 姓名=?,性别=?,年龄=?,生源所在地=?,班级编号=?\r\n" + "where 学号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, studentBean.getName());
			preparedStatement.setString(2, studentBean.getSex());
			preparedStatement.setInt(3, studentBean.getAge());
			preparedStatement.setString(4, studentBean.getPlace());
			preparedStatement.setString(5, studentBean.getClassnum());
			preparedStatement.setString(6, studentBean.getNum());
			preparedStatement.setString(7, studentBean.getNum());
			preparedStatement.setString(8, studentBean.getClassnum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 

	
	public ArrayList<CourseBean> getCourse() {
		String sql = "SELECT * FROM 课程";
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ArrayList<CourseBean> sList=new ArrayList<>();
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				CourseBean courseBean  = new CourseBean();
				courseBean.setCoursenum(resultSet.getString("课程编号").trim());
				courseBean.setCoursename(resultSet.getString("课程名称").trim());
				courseBean.setTime(resultSet.getInt("学时"));
				if (resultSet.getBoolean("考试或考查")) {
					courseBean.setTestway("考查");
				} else {
					courseBean.setTestway("考试");
				}
				courseBean.setCredit(resultSet.getFloat("学分"));
				sList.add(courseBean);
			}
			connection.close();
			return sList;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addCourse(CourseBean courseBean) {
		String sql="insert into 课程 values(?,?,?,?,?)";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseBean.getCoursenum());
			preparedStatement.setString(2, courseBean.getCoursename());
			preparedStatement.setInt(3, courseBean.getTime());
			preparedStatement.setBoolean(4, courseBean.getTestway().equals("考查"));
			preparedStatement.setFloat(5, courseBean.getCredit());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean changeCourse(CourseBean courseBean) {
		String sql="update 课程\r\n" + 
				"set 课程编号=?,课程名称=?,学时=?,考试或考查=?,学分=?\r\n" + "where 课程编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseBean.getCoursenum());
			preparedStatement.setString(2, courseBean.getCoursename());
			preparedStatement.setInt(3, courseBean.getTime());
			preparedStatement.setBoolean(4, courseBean.getTestway().equals("考查"));
			preparedStatement.setFloat(5, courseBean.getCredit());
			preparedStatement.setString(6, courseBean.getCoursenum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	public boolean changePasscode(String passcode,String num) {
		String sql="UPDATE 管理员 SET 登录密码=? WHERE 管理员编号=?";
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
	
	public boolean deleteCourse(ArrayList<CourseBean>cList,int i) {
		String sql="delete from 课程 where 课程编号=?";
		Connection connection;
		try {
			connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,cList.get(i).getCoursenum());
			preparedStatement.executeUpdate();
			connection.close();
			return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
		
	} 
	
	
}
