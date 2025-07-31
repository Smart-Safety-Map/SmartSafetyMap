import { useEffect, useState } from "react";
import axios from "axios";

const AcidentFetcher = () => {
    const [acidentList, setAcidentList] = useState([]);

    const getAcidentList = async () => {
        try {
            const response = await axios.get("/api/ntic/getAllAcident");

            // JSON 전체를 그대로 저장
            setAcidentList(response.data);
        } catch (error) {
            console.error("데이터 불러오기 실패", error);
        }
    };

    useEffect(() => {
        getAcidentList();
    }, []);

    return (
        <div>
            <h2>돌발 상황 목록</h2>
            <ul>
                {acidentList.map((item, index) => (
                    <li key={index}>
                        <strong>{item.eventType ?? "종류없음"}</strong> / {item.message ?? "메시지없음"}
                        <br />
                        시작: {item.startTime}
                        <br />
                        종료: {item.endTime ?? "없음"}
                        <br />
                        예상 종료: {item.expectedEndTime ?? "없음"}
                        <br />
                        위치 유형: {item.locationType}
                        <br />
                        좌표: ({item.ycoord}, {item.xcoord})
                        <br />
                        차선 차단: {item.lanesBlocked ?? "정보 없음"}
                        <br />
                        도로 방향: {item.roadDrcType}
                        <br />
                        업데이트 시각: {item.updateTime ?? "없음"}
                        <hr />
                    </li>
                ))}
            </ul>
        </div>
    );
};

export default AcidentFetcher;
