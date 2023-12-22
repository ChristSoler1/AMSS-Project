import {Link} from "react-router-dom";
import HomeImage from "../../resources/li-home.svg";
import UserImage from "../../resources/li-user-1.svg";
import SearchImage from "../../resources/li-search-1.svg";



const Header = () => {

    return (
        <div className="row col-lg-12"style={{ position: 'fixed', top: 0, width: '100%', zIndex: 1000 }}>
            <div className="col-3 text-center mt-3">
                <div style={{display: 'flex', alignItems: 'center', marginRight: "20px", marginLeft: "20%"}}>
                    <Link to={'/'} className=" form-control image-button bg-dark" style={{marginRight: "20px"}}>
                        <img src={HomeImage} alt="home"/>
                    </Link>
                    <Link to={'/updateaccount'} className="form-control image-button bg-dark" style={{marginRight: "20px"}}>
                        <img src={UserImage} alt="user"/>
                    </Link>
                    <Link to={'/search'} className="form-control image-button bg-dark" style={{marginRight: "20px"}}>
                        <img src={SearchImage} alt="search"/>
                    </Link>
                </div>
            </div>
            <div className="col-8 mt-3 "
                 style={{display: 'flex', alignItems: 'flex-end', marginRight: "20px", marginLeft: "20px"}}>
                <input type="text" disabled={true} className="form-control bg-dark text-light" defaultValue="AMSS"
                       style={{width: 'calc(100% - 20px)', marginRight: '20px', height: "100%", fontSize:"30px"}}/>
            </div>
        </div>
    );
}

export default Header;
