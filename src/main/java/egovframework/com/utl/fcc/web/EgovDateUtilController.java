package egovframework.com.utl.fcc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.ModelMap;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

@Controller
public class EgovDateUtilController {

	/**
     * 날짜로 요일을 계산하는 테스트용 컨트롤러 메서드
     * @param inputDate 사용자 입력 날짜 (yyyy-MM-dd 형식)
     * @param model 모델 객체
     * @return jsp 페이지 경로
     */
    @RequestMapping("/utl/fcc/WeekCalc.do")
    public String calculateWeekDay(@RequestParam(value = "inputDate", required = false) String inputDate, Model model) {

        String result = null;

        if (inputDate != null && !inputDate.trim().equals("")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdf.parse(inputDate);

                Calendar cal = Calendar.getInstance();
                cal.setTime(date);

                String[] weekDays = {"일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"};
                int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // 1 (일) ~ 7 (토)

                result = inputDate + "는 " + weekDays[dayOfWeek - 1] + "입니다.";

            } catch (Exception e) {
                result = "입력 형식이 올바르지 않습니다. (예: 2025-05-16)";
            }
        }

        model.addAttribute("inputDate", inputDate);
        model.addAttribute("result", result);

        // /WEB-INF/jsp/egovframework/cmm/utl/EgovWeekCalc.jsp로 포워딩
        return "egovframework/cmm/utl/EgovWeekCalc";
    }
    
    /**
     * 날짜 형식 변환 테스트 컨트롤러
     * @param inputDate 사용자 입력 날짜 문자열 (예: 20250516 또는 2025-05-16)
     * @param model 모델 객체
     * @return 변환 결과를 포함한 JSP 뷰
     */
    @RequestMapping("/utl/fcc/DateCnvr.do")
    public String convertDateFormat(@RequestParam(value = "inputDate", required = false) String inputDate, Model model) {

        String result = null;

        if (inputDate != null && !inputDate.trim().equals("")) {
            try {
                Date date;
                if (inputDate.contains("-")) {
                    // yyyy-MM-dd 형식
                    date = new SimpleDateFormat("yyyy-MM-dd").parse(inputDate);
                } else {
                    // yyyyMMdd 형식
                    date = new SimpleDateFormat("yyyyMMdd").parse(inputDate);
                }

                // 변환 결과
                String formatted1 = new SimpleDateFormat("yyyy-MM-dd").format(date);
                String formatted2 = new SimpleDateFormat("yyyyMMdd").format(date);

                result = "입력한 날짜: " + inputDate + "<br/>" +
                         "yyyy-MM-dd 형식: " + formatted1 + "<br/>" +
                         "yyyyMMdd 형식: " + formatted2;

            } catch (Exception e) {
                result = "날짜 형식이 올바르지 않습니다. (예: 20250516 또는 2025-05-16)";
            }
        }

        model.addAttribute("inputDate", inputDate);
        model.addAttribute("result", result);

        return "egovframework/cmm/utl/EgovDateCnvr";
    }
    
    /**
     * 날짜 유효성 검사 테스트
     */
    @RequestMapping("/utl/fcc/DateValidCheck.do")
    public String dateValidCheck(
            @RequestParam(value = "inputDate", required = false) String inputDate,
            ModelMap model
    ) {
        boolean isValid = false;

        if (inputDate != null && !inputDate.isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            sdf.setLenient(false); // 엄격한 날짜 형식 검사

            try {
                sdf.parse(inputDate);
                isValid = true;
            } catch (ParseException e) {
                isValid = false;
            }
        }

        model.addAttribute("inputDate", inputDate);
        model.addAttribute("isValid", isValid);

        return "/egovframework/cmm/utl/EgovDateValidCeck";
    }
}
