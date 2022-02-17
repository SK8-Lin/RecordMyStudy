package controller;

import dao.ChallengeDao;
import domain.Day;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChallengeController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("进入主功能界面");

        resp.setContentType("text/text;charset=utf-8");
        String path = req.getServletPath();

        /*业务筛选*/
        switch(path){
            /*展示全部信息功能*/
            case "/showAll.do" :
                showAll(req,resp);
                break;
            /*更新功能*/
            case "/add.do" :
                add(req,resp);
                break;
            default :
                System.out.println("请求不存在");
        }
    }

    /*更新功能*/
    private void add(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入更新界面");

        /*获得本次时长*/
        float time = Float.parseFloat(req.getParameter("time"));

        /*获得目前时长*/
        Day lastDay = new Day();
        ChallengeDao challengeDao = new ChallengeDao();
        lastDay = challengeDao.lastDay(req);
        float lastTime = lastDay.getTimeAll();

        /*获得最新时长*/
        float timeAll = time + lastTime;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        String date = sdf.format(today);

        /*更新记录*/
        int result = 0;
        Day day = new Day(date,time,timeAll);
        result = challengeDao.add(day,req);

        if (result == 1) {
            System.out.println("更新成功");

            String str = "{\"succeed\":true}";
            try {
                resp.getWriter().print(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("更新失败");

            String str = "{\"succeed\":false}";
            try {
                resp.getWriter().print(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*展示全部信息功能*/
    private void showAll(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("进入展示全部信息界面");

        List<Day> dayList = new ArrayList<>();
        ChallengeDao challengeDao = new ChallengeDao();
        dayList = challengeDao.showAll(req);
        JSONArray jsonArray =JSONArray.fromObject(dayList);
        try {
            resp.getWriter().print(jsonArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
