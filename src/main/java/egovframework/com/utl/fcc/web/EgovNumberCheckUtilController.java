package egovframework.com.utl.fcc.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 숫자 여부 확인 유틸리티 테스트 Controller
 */
@Controller
public class EgovNumberCheckUtilController {

	/**
     * 숫자 여부 검사 메서드
     * @param input 입력 문자열
     * @param model JSP 전달용 모델
     * @return 결과 페이지
     */
    @RequestMapping("/utl/fcc/NumberCheck.do")
    public String numberCheck(@RequestParam(value = "input", required = false) String input, Model model) {

        String result = "";

        if (input != null && !input.trim().isEmpty()) {
            if (isNumeric(input)) {
                result = "\"" + input + "\" 은(는) 숫자입니다.";
            } else {
                result = "\"" + input + "\" 은(는) 숫자가 아닙니다.";
            }
        }

        model.addAttribute("input", input);
        model.addAttribute("result", result);

        return "egovframework/cmm/utl/EgovNumberCheck";
    }

    /**
     * 숫자인지 확인하는 유틸 메서드
     */
    private boolean isNumeric(String str) {
        return str.matches("[-+]?\\d+(\\.\\d+)?"); // 정수 또는 소수
    }
}
