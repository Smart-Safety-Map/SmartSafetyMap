package SmartSafetyMap.backend.xmlDto;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum UTICIcnType {

    // 사고 (1)
    사고_보행자사고    (1, 11, "사고", "보행자 사고"),
    사고_차량파손      (1,  2, "사고", "차량 파손"),
    사고_구조작업      (1,  1, "사고", "구조 작업"),
    사고_교통사고      (1,255, "사고", "교통사고 (대표 코드)"),
    사고_도로파손      (1, 10, "사고", "도로 파손"),
    사고_전복_전도     (1,  3, "사고", "전복/전도"),
    사고_고장_차량     (1,  8, "사고", "고장 차량"),
    사고_차도_이탈     (1,  7, "사고", "차도 이탈"),
    사고_기름_유출     (1,  6, "사고", "기름 유출"),
    사고_화재          (1,  4, "사고", "화재"),
    사고_적재물_낙하   (1,  9, "사고", "적재물 낙하"),

    // 공사 (2)
    공사_도로공사      (2,255, "공사", "도로공사 (대표 코드)"),
    공사_신호기_고장   (2, 20, "공사", "신호기 고장"),
    공사_표지판_작업   (2, 19, "공사", "표지판 작업"),
    공사_가로등_작업   (2, 18, "공사", "가로등 작업"),
    공사_방책_유지보수 (2, 15, "공사", "방책 유지보수"),
    공사_배수구_청소   (2, 14, "공사", "배수구 청소"),
    공사_노변_유지보수 (2, 13, "공사", "노변 유지보수"),
    공사_가로수_벌목   (2,  8, "공사", "가로수 벌목"),
    공사_도로도색      (2,  7, "공사", "도로 도색"),
    공사_유지보수      (2,  6, "공사", "유지보수"),
    공사_하수도_배수로 (2,  4, "공사", "하수도/배수로 작업"),
    공사_지하_시설작업 (2,  2, "공사", "지하 시설 작업"),
    공사_재포장        (2,  1, "공사", "재포장"),
    공사_고가_시설작업 (2,  3, "공사", "고가 시설 작업"),

    // 행사 (3)
    행사_복합행사      (3,  1, "행사", "복합행사"),
    행사_축제          (3,  2, "행사", "축제"),
    행사_집회          (3,  3, "행사", "집회"),
    행사_스포츠행사    (3,  4, "행사", "스포츠 행사"),
    행사_국가행사      (3,  5, "행사", "국가 행사"),
    행사_문화행사      (3,  6, "행사", "문화 행사"),
    행사_대표          (3,255, "행사", "행사 (대표 코드)"),
    행사_훈련          (3,  7, "행사", "훈련"),

    // 기상 (4)
    기상_짙은안개      (4,115, "기상", "짙은 안개 (2021년 신규)"),
    기상_우박          (4,110, "기상", "우박 (2021년 신규)"),
    기상_결빙          (4,109, "기상", "결빙 (2021년 신규)"),
    기상_침수          (4,107, "기상", "침수 (2021년 신규)"),
    기상_대표          (4,255, "기상", "기상 (대표 코드)"),
    기상_노면미끄러움  (4,102, "기상", "노면 미끄러움 (2021년 신규)"),
    기상_강우          (4,  2, "기상", "강우"),
    기상_강설          (4,  3, "기상", "강설"),
    기상_지진          (4,  4, "기상", "지진"),
    기상_안개          (4,  5, "기상", "안개"),
    기상_강풍          (4,  1, "기상", "강풍"),

    // 통제 (5)
    통제_방재          (5, 30, "통제", "방재"),
    통제_행사          (5, 20, "통제", "행사"),
    통제_공사          (5, 10, "통제", "공사"),
    통제_재해          (5,  1, "통제", "재해"),
    통제_기타          (5,255, "통제", "기타 통제 (대표 코드)"),
    통제_방역          (5, 70, "통제", "방역 (AI, ASF 등)"),
    통제_도로결빙      (5, 60, "통제", "도로 결빙"),
    통제_도로침수      (5, 50, "통제", "도로 침수"),
    통제_노면요철      (5, 40, "통제", "노면 요철"),

    // 재난 (6)
    재난_댐붕괴        (6, 13, "재난", "댐 붕괴"),
    재난_산불          (6,  1, "재난", "산불"),
    재난_산사태        (6,  2, "재난", "산사태"),
    재난_지진          (6,  3, "재난", "지진");

    private final int mainCode;
    private final int subCode;
    private final String type;
    private final String description;

    UTICIcnType(int mainCode, int subCode, String type, String description) {
        this.mainCode = mainCode;
        this.subCode = subCode;
        this.type = type;
        this.description = description;
    }

    private static final Map<Integer, String> MAIN_CODE_TO_TYPE = new HashMap<>();
    private static final Map<String, String> CODE_PAIR_TO_DESC = new HashMap<>();

    static {
        for (UTICIcnType e : values()) {
            // mainCode 기준 type
            MAIN_CODE_TO_TYPE.putIfAbsent(e.mainCode, e.type);

            // mainCode,subCode 기준 description (Key 문자열 조립)
            String key = e.mainCode + "_" + e.subCode;
            CODE_PAIR_TO_DESC.put(key, e.description);
        }
    }

    // 기존 생성자, getter 생략

    public static String getTypeByMainCode(int mainCode) {
        return MAIN_CODE_TO_TYPE.getOrDefault(mainCode, "알 수 없는 유형");
    }
    public static String getDescriptionByCodes(int mainCode, int subCode) {
        String key = mainCode + "_" + subCode;
        return CODE_PAIR_TO_DESC.getOrDefault(key, "알 수 없는 코드 조합");
    }
}
