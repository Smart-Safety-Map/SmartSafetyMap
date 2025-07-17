import React from 'react';
import MapHook from '/src/hooks/MapHook.js';

const Home = () => {
    const {mapRef, myLocation} = MapHook();
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
