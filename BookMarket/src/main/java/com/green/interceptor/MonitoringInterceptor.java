package com.green.interceptor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.util.StopWatch;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

//유저의 요청이 Controller 로 가기 전에 가로채는 Interceptor 클래스 입니다.
//implements HandlerInterceptor 를 상속받아 구체화 합니다.
public class MonitoringInterceptor implements HandlerInterceptor {

	ThreadLocal<StopWatch> stopWatchLocal = new ThreadLocal<StopWatch>();

	//logger 객체 가져오기
    public Logger logger = LoggerFactory.getLogger(this.getClass());  

    //HandlerInterceptor 클래스의 메서드 입니다. 
    //preHandle - 요청이 컨트롤러 에 전달 되기 전에 호출 
    //postHandle - 요청 url을 컨트롤러가 처리 후 호출
    //afterCompletion - 컨트롤러가 웹 요청 처리 후 뷰(view)에 응답 전송 종료후 호출
    public boolean preHandle(HttpServletRequest request,  
                             HttpServletResponse response,
                             Object handler) throws Exception {
        StopWatch stopWatch = new StopWatch(handler.toString());
        stopWatch.start(handler.toString());
        stopWatchLocal.set(stopWatch);
        //로그메세지 consle에 출력
        logger.info("접근한 URL 경로 : " + getURLPath(request)); 
        logger.info("요청 처리 시작 시각 : " + getCurrentTime());  
        return true;
    } 
    
    //요청의 처리 종료 시각을 구한다음 로그메시지를 출력하는 메서드
    public void postHandle(HttpServletRequest arg0,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception { 
        logger.info("요청 처리 종료 시각 : " + getCurrentTime()); 
    }  
    
    //요청 의 처리소요 시간을 구한다음 로그메시지 출력하는 메서드
    public void afterCompletion(HttpServletRequest request,
                               HttpServletResponse response,
                               Object handler, 
                               Exception exception) throws Exception { 
    	
    	//spring에서 제공하는 StopWatch 클래스는 작업 시간을 측정해주며 표시해줍니다
        StopWatch stopWatch = stopWatchLocal.get();
        stopWatch.stop(); //StopWatch 종료.
        logger.info("요청 처리 소요 시간 : " + stopWatch.getTotalTimeMillis()+ " ms");  
        stopWatchLocal.set(null);
         logger.info("==========================================");  
    }  
    
    //요청 URL과 쿼리문을 구하는 메서드
    private String getURLPath(HttpServletRequest request) {  
        String currentPath = request.getRequestURI();
        String queryString = request.getQueryString();
        queryString = queryString == null ? "" : "?" + queryString;
        return currentPath+queryString;
    } 
    
    //현재 시각을 구하는 메서드
    private String getCurrentTime() {  
        DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        return formatter.format(calendar.getTime());
    }  
}
