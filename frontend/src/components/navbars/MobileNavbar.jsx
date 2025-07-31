import { Link, useLocation } from "react-router-dom";
import { menuItems } from "../menuItems.jsx";

const MobileNavbar = () => {
    const location = useLocation();

    const isActive = (path) => location.pathname === path;

    return (
        <nav className="fixed bottom-0 left-0 w-full flex justify-around items-center bg-white border-t z-50 sm:hidden">
            {menuItems.map((item) => (
                <Link
                    key={item.path}
                    to={item.path}
                    className={`flex flex-col items-center py-2 px-3 text-xs ${
                        isActive(item.path) ? "text-blue-500 font-semibold" : "text-gray-500"
                    }`}
                >
                    {item.icon}
                    <span className="mt-1">{item.label}</span>
                </Link>
            ))}
        </nav>
    );
};

export default MobileNavbar;
