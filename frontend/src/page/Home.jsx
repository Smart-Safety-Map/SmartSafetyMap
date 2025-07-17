import { useEffect, useRef } from "react";

const Home = () => {
    const mapRef = useRef(null);

    useEffect(() => {
        if (window.naver && window.naver.maps && mapRef.current) {
            // 맵 객체가 이미 있으면 중복 생성 방지
            if (!mapRef.current.__naver_map) {
                mapRef.current.__naver_map = new window.naver.maps.Map(mapRef.current, {
                    center: new window.naver.maps.LatLng(37.3595704, 127.105399),
                    zoom: 10,
                });
            }
        }
    }, []);

    // 주: style, id 대신 ref={mapRef} 사용
    return (
        <>
            <div style={{width: "1000px", height: "400px", margin:"0", padding:"0"}}>
            <div ref={mapRef} style={{ width: "100%", height: "400px", margin:"0", padding:"0" }}></div>

            {/* 아래 이미지 박스는 삭제해도 영향 없음 */}
            </div>
        </>
    );
};

export default Home;
