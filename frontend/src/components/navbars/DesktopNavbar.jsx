import { Home, Search, Car, Settings } from "lucide-react";
import { Link, useLocation } from "react-router-dom";

const topMenuItems = [
    { label: "홈", icon: <Home size={24} />, path: "/" },
    { label: "검색", icon: <Search size={24} />, path: "/search" },
    { label: "교통정보", icon: <Car size={24} />, path: "/carInfo" },
];

const bottomMenuItem = { label: "설정", icon: <Settings size={24} />, path: "/setting" };

const DesktopNavbar = () => {
    const location = useLocation();

    const isActive = (path) => location.pathname === path;

    return (
        <aside className="hidden lg:flex fixed top-0 left-0 h-full w-20 bg-white border-r shadow-md flex-col items-center py-6 z-50">
            {/* 상단 메뉴 */}
            <div className="flex flex-col w-full">
                {topMenuItems.map((item) => (
                    <Link
                        key={item.path}
                        to={item.path}
                        className={`flex flex-col items-center justify-center w-full py-4 hover:bg-gray-100 ${
                            isActive(item.path) ? "bg-gray-200 font-semibold" : ""
                        }`}
                    >
                        {item.icon}
                        <span className="text-xs mt-1">{item.label}</span>
                    </Link>
                ))}
            </div>

            {/* 하단 설정 메뉴 */}
            <div className="mt-auto w-full">
                <Link
                    to={bottomMenuItem.path}
                    className={`flex flex-col items-center justify-center w-full py-4 hover:bg-gray-100 ${
                        isActive(bottomMenuItem.path) ? "bg-gray-200 font-semibold" : ""
                    }`}
                >
                    {bottomMenuItem.icon}
                    <span className="text-xs mt-1">{bottomMenuItem.label}</span>
                </Link>
            </div>
        </aside>
    );
};

export default DesktopNavbar;
