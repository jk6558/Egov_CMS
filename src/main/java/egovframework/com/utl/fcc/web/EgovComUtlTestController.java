package egovframework.com.utl.fcc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.Random;
import org.springframework.ui.ModelMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 공통 유틸리티 테스트용 Controller - 문자열 인덱스 찾기 예제
 */

@Controller
public class EgovComUtlTestController {

	/**
     * 문자열에서 부분 문자열의 위치를 찾는 테스트 메서드
     * @param fullText 전체 문자열
     * @param keyword 찾을 문자열
     * @param model JSP로 전달할 모델 객체
     * @return JSP 페이지 경로
     */
    @RequestMapping("/utl/fcc/StringIndex.do")
    public String stringIndexTest(
            @RequestParam(value = "fullText", required = false) String fullText,
            @RequestParam(value = "keyword", required = false) String keyword,
            Model model) {

        String result = "";

        if (fullText != null && keyword != null && !fullText.isEmpty() && !keyword.isEmpty()) {
            int index = fullText.indexOf(keyword);
            if (index >= 0) {
                result = "입력한 문자열에서 \"" + keyword + "\" 의 시작 위치는 " + index + " 입니다.";
            } else {
                result = "입력한 문자열에 \"" + keyword + "\" 이(가) 포함되어 있지 않습니다.";
            }
        }

        model.addAttribute("fullText", fullText);
        model.addAttribute("keyword", keyword);
        model.addAttribute("result", result);

        return "egovframework/cmm/utl/EgovStringIndex";
    }
    
    /**
     * 랜덤 숫자 생성 테스트 메서드
     * @param min 최소값
     * @param max 최대값
     * @param model 결과 전달용 모델
     * @return JSP 페이지 경로
     */
    @RequestMapping("/utl/cmm/RandomNumber.do")
    public String getRandomNumber(
            @RequestParam(value = "min", required = false) String min,
            @RequestParam(value = "max", required = false) String max,
            Model model) {

        String result = "";
        Integer randomNumber = null;

        try {
            if (min != null && max != null && !min.isEmpty() && !max.isEmpty()) {
                int minVal = Integer.parseInt(min);
                int maxVal = Integer.parseInt(max);

                if (minVal > maxVal) {
                    result = "최소값은 최대값보다 작거나 같아야 합니다.";
                } else {
                    Random random = new Random();
                    randomNumber = random.nextInt((maxVal - minVal) + 1) + minVal;
                    result = "생성된 랜덤 숫자: " + randomNumber;
                }
            }
        } catch (NumberFormatException e) {
            result = "숫자 형식이 잘못되었습니다.";
        }

        model.addAttribute("min", min);
        model.addAttribute("max", max);
        model.addAttribute("result", result);
        model.addAttribute("randomNumber", randomNumber);

        return "egovframework/cmm/utl/EgovRandomNumber";
    }
    
    /**
     * 세션 테스트 화면 호출 및 값 설정/출력
     */
    @RequestMapping("/utl/cmm/SessionTest.do")
    public String sessionTest(HttpServletRequest request, ModelMap model) {
        HttpSession session = request.getSession();

        // 요청 파라미터
        String name = request.getParameter("name");
        String value = request.getParameter("value");

        if (name != null && value != null) {
            // 세션 값 설정
            session.setAttribute(name, value);
        }

        // 현재 세션 값 전체 출력용
        model.addAttribute("sessionAttributes", session.getAttributeNames());

        return "/egovframework/cmm/utl/EgovSession";
    }
}
