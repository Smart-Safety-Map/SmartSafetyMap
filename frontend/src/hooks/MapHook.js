import {useState, useEffect, useRef} from 'react';
import axios from "axios";

const MapHook = () => {

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


    const mapRef = useRef(null);
    //1. 위치 정보를 저장할 state 추가
    const [myLocation, setMyLocation] = useState(null);

    useEffect(() => {
        //2. Geolocarion API 호출
        navigator.geolocation.getCurrentPosition(
            //성공 시
            (position) => {
                const lat = position.coords.latitude;
                const lng = position.coords.longitude;
                //3. naver.maps.LatLng객체 생성 후 state 업데이트
                setMyLocation(new window.naver.maps.LatLng(lat, lng));
            },
            //실패 시
            () => {
                console.error("현재 위치를 가져올 수 없습니다.");
                //4. 실패하면 기본 위치(서울)로 설정
                setMyLocation(new window.naver.maps.LatLng(37.3595704, 127.105399));
            }
        );
    }, []);

    useEffect(() => {
        // 맵 객체가 이미 있으면 중복 생성 방지
        if (window.naver && window.naver.maps && myLocation && mapRef.current) {
            //5. myLocation state가 설정되면 지도를 생성
            if (!mapRef.current.__naver_map) {
                const map = new window.naver.maps.Map(mapRef.current, {
                    center: myLocation, //지도의 중심을 현재 위치로 설정
                    zoom: 17,
                });
                mapRef.current.__naver_map = map;
                acidentList.forEach((item, index) => {
                    console.log(`🚨 사고 위치 [${index}]`, item);

                    const marker = new window.naver.maps.Marker({
                        position: new window.naver.maps.LatLng(item.ycoord, item.xcoord), // y: 위도, x: 경도
                        map: map,
                        icon: {
                            content: '<div style="width: 3px; height: 3px;font-size:13px;">🚧</div>',
                            anchor: new window.naver.maps.Point(5, 5)
                        }
                    });
                    const infoWindow = new window.naver.maps.InfoWindow({
                        content: `<div style="padding:10px; font-size:10px;">
                                     ${item.message || '돌발 정보'}
                                  </div>`
                    });

                    window.naver.maps.Event.addListener(marker, 'click', function () {
                        infoWindow.open(map, marker);
                    });

                });

                // 현재 위치에 점으로 표시
                new window.naver.maps.Marker({
                    position: myLocation,
                    map: map,
                    icon: {
                        content: '<div style="width: 10px; height: 10px; background-color: #007AFF; border-radius: 50%; border: 2px solid white; box-shadow: 0 0 5px rgba(0,0,0,0.3);"></div>',
                        anchor: new window.naver.maps.Point(7, 7)
                    }
                });

            }
        }
    }, [myLocation]);
    return {mapRef, myLocation};
};

export default MapHook;