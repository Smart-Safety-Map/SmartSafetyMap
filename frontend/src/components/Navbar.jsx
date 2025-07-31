import DesktopNavbar from './navbars/DesktopNavbar';
import TabletNavbar from "./navbars/TabletNavbar";
import MobileNavbar from "./navbars/MobileNavbar";

const Navbar = () => {
    return (
        <>
            <div className="hidden lg:block">
                <DesktopNavbar/>
            </div>
            <div className="hidden sm:block lg:hidden">
                <TabletNavbar/>
            </div>
            <div className="block sm:hidden">
                <MobileNavbar/>
            </div>
        </>
    );
};

export default Navbar;
