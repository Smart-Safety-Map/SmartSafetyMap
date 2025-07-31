import React, { useEffect, useState } from 'react';
import MapHook from '/src/hooks/MapHook.js';
import axios from "axios";

const Home = () => {
    const {mapRef, myLocation} = MapHook();

    const[acidentList, setAcidentList] = useState([]);

    const getAcidentList = async () => {
        try{
            const response = await axios.get('/api/ntic/getAllAcident');
            console.log(response);
        }catch (e) {
            console.error(e);
        }
    }
    useEffect(() => {
        getAcidentList(); // 컴포넌트가 마운트될 때 호출
        console.log("성공함");
    }, []);
    // 주: style, id 대신 ref={mapRef} 사용


    return (
            <div className="w-full h-screen">
                <div ref={mapRef} className="w-full h-full"/>
            </div>
    );
};

export default Home;
