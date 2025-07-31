import { Link, useLocation } from "react-router-dom";
import { menuItems } from "../menuItems.jsx";

const TabletNavbar = () => {
    const location = useLocation();

    const isActive = (path) => location.pathname === path;

    return (
        <aside className="hidden sm:flex lg:flex fixed top-0 left-0 h-full w-20 bg-white border-r shadow-md flex-col items-center py-6 z-50">
            <div className="flex flex-col w-full">
                {menuItems.slice(0, 3).map((item) => (
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
            <div className="mt-auto w-full">
                <Link
                    to={menuItems[3].path}
                    className={`flex flex-col items-center justify-center w-full py-4 hover:bg-gray-100 ${
                        isActive(menuItems[3].path) ? "bg-gray-200 font-semibold" : ""
                    }`}
                >
                    {menuItems[3].icon}
                    <span className="text-xs mt-1">{menuItems[3].label}</span>
                </Link>
            </div>
        </aside>
    );
};

export default TabletNavbar;
