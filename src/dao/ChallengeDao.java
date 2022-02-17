package dao;

import domain.Day;
import jakarta.servlet.http.HttpServletRequest;
import util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChallengeDao {
    /*查询数据库全部内容*/
    public List<Day> showAll(HttpServletRequest req) {
        String sql = "select * from tbl_schedule";

        String date = null;
        float time = 0;
        float timeAll = 0;
        List<Day> dayList = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JdbcUtil.getConnection(req);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                date = rs.getString("date");
                time = rs.getFloat("time");
                timeAll = rs.getFloat("timeAll");
                Day day = new Day(date,time,timeAll);
                dayList.add(day);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(req,conn,ps,rs);
        }

        return dayList;
    }

    /*添加时长*/
    public int add(Day day, HttpServletRequest req) {
        String sql = "insert into tbl_schedule (date,time,timeAll) values (?,?,?)";

        String date = day.getDate();
        float time = day.getTime();
        float timeAll = day.getTimeAll();
        int result = 0;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection(req);
            ps = conn.prepareStatement(sql);
            ps.setString(1,date);
            ps.setFloat(2,time);
            ps.setFloat(3,timeAll);
            result = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(req,conn,ps,rs);
        }

        return result;
    }

    /*取出最新的时间*/
    public Day lastDay(HttpServletRequest req) {
        String sql = "select * from tbl_schedule ORDER BY id DESC LIMIT 1";

        String date = null;
        float time = 0;
        float timeAll = 0;
        Day lastDay = new Day();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JdbcUtil.getConnection(req);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                date = rs.getString("date");
                time = rs.getFloat("time");
                timeAll = rs.getFloat("timeAll");
                lastDay.setDate(date);
                lastDay.setTime(time);
                lastDay.setTimeAll(timeAll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lastDay;
    }
}
