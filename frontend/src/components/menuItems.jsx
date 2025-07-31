import { Home, Search, Car, Settings } from "lucide-react";

export const menuItems = [
    { label: "홈", icon: <Home size={24} />, path: "/" },
    { label: "검색", icon: <Search size={24} />, path: "/search" },
    { label: "교통정보", icon: <Car size={24} />, path: "/carInfo" },
    { label: "설정", icon: <Settings size={24} />, path: "/setting" },
];
